package desktopserver;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class VideoAudioServer extends javax.swing.JFrame {

    InetAddress ipAudioClient;
    int portAudioClient;
    InetAddress ipVideoClient;
    int portVideoClient;
    FormDashboard parent;

    DatagramSocket audioSock;
    DatagramSocket videoSock;

    AudioReceiver audioReceiver;
    AudioTransmitter audioTransmitter;
    EndCallReceiver endCallReceiver;

    VideoReceiver videoReceiver;
    VideoTransmitter videoTransmitter;

    boolean continueCall = true;

    public VideoAudioServer() {
        initComponents();
    }

    public VideoAudioServer(InetAddress _ipAudioClient, int _portAudioClient, InetAddress _ipVideoClient, int _portVideoClient, FormDashboard _parent) {
        initComponents();
        ipAudioClient = _ipAudioClient;
        portAudioClient = _portAudioClient;
        ipVideoClient = _ipVideoClient;
        portVideoClient = _portVideoClient;
        parent = _parent;

        // Set Timer
        Timer timer = new Timer(1000, new ActionListener() {
            int hour = 0;
            int minutes = 0;
            int second = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (continueCall) {
                    second++;
                    if (second >= 60) {
                        minutes++;
                        second = 0;
                        if (minutes >= 60) {
                            hour++;
                            minutes = 0;
                        }
                    }
                    String _second = String.format("%02d", second);
                    String _minutes = String.format("%02d", minutes);
                    String _hour = String.format("%02d", hour);
                    txtTimer.setText(_hour + ":" + _minutes + ":" + _second);
                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

        try {
            // Deklarasi Socket
            audioSock = new MulticastSocket(null);
            audioSock.setReuseAddress(true);
            audioSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 2000));

            videoSock = new MulticastSocket(null);
            videoSock.setReuseAddress(true);
            videoSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 7800));

            // Start Thread Audio Receiver
            audioReceiver = new AudioReceiver();
            audioReceiver.start();

            // Start Thread Audio Transmitter
            audioTransmitter = new AudioTransmitter();
            audioTransmitter.start();

            // Start End Call Receiver
            endCallReceiver = new EndCallReceiver(this);
            endCallReceiver.start();

            videoReceiver = new VideoReceiver();
            videoReceiver.start();

            videoTransmitter = new VideoTransmitter();
            videoTransmitter.start();
        } catch (Exception ex) {
            System.out.println("Error Constructor : " + ex);
        }
    }

    public static AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    class AudioTransmitter extends Thread {

        TargetDataLine targetDataLine;
        boolean muted = true;

        byte sendAudioByte[] = new byte[1000];
        DatagramPacket sendAudioPacket;

        AudioTransmitter() {
            StartMic();
        }

        public void StartMic() {
            try {
                Mixer.Info mixerInfo[] = AudioSystem.getMixerInfo();
                AudioFormat audioFormat = getAudioFormat();
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                Mixer mixer = null;
                // Mencari MIC yang bisa dipake
                for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
                    mixer = AudioSystem.getMixer(mixerInfo[cnt]);
                    if (mixer.isLineSupported(dataLineInfo)) {
                        targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
                        break;
                    }
                }
                targetDataLine.open(audioFormat);
                targetDataLine.start();

            } catch (Exception ex) {
                System.out.println("Mic start error : " + ex);
            }
        }

        public void mute() {
            muted = true;
        }

        public void unmute() {
            muted = false;
        }

        @Override
        public void run() {
            while (continueCall) {
                if (muted == false) {
                    try {
                        // Membaca MIC
                        targetDataLine.read(sendAudioByte, 0, sendAudioByte.length);

                        sendAudioPacket = new DatagramPacket(sendAudioByte, sendAudioByte.length, ipAudioClient, portAudioClient);
                        audioSock.send(sendAudioPacket);
                    } catch (Exception ex) {
                        System.out.println("Error Audio Transmitter : " + ex);
                    }
                } else {
                    System.out.print("");
                }
            }
        }
    }

    class AudioReceiver extends Thread {

        SourceDataLine sourceDataLine;

        byte incomingAudioByte[] = new byte[1000];
        DatagramPacket incomingAudioPacket;

        public AudioReceiver() {
            incomingAudioPacket = new DatagramPacket(incomingAudioByte, incomingAudioByte.length);
            StartSpeaker();
        }

        @Override
        public void run() {
            byte b[] = null;
            while (continueCall) {
                try {
                    // Menerima Input Suara
                    audioSock.receive(incomingAudioPacket);
                    byte soundbytes[] = incomingAudioPacket.getData();

                    // Output Speaker
                    sourceDataLine.write(soundbytes, 0, soundbytes.length);
                } catch (IOException ex) {
                    System.out.println("Audio Receiver Thread Error : " + ex);
                }
            }
        }

        public void StartSpeaker() {
            try {
                // Setting Speaker
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, getAudioFormat());
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(getAudioFormat());
                sourceDataLine.start();
            } catch (LineUnavailableException ex) {
                System.out.println("Setting Speaker Failed : " + ex);
            }
        }
    }

    class EndCallReceiver extends Thread {

        MulticastSocket endCallReceiver;
        byte incomingByte[];
        DatagramPacket incomingPacket;
        VideoAudioServer parent;

        EndCallReceiver(VideoAudioServer _parent) {
            this.parent = _parent;
            try {
                endCallReceiver = new MulticastSocket(null);
                endCallReceiver.setReuseAddress(true);
                endCallReceiver.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 12000));

                incomingByte = new byte[2048];
                incomingPacket = new DatagramPacket(incomingByte, incomingByte.length);
            } catch (IOException ex) {
                Logger.getLogger(VideoAudioServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void run() {
            try {
                endCallReceiver.receive(incomingPacket);

                parent.endCall();
            } catch (IOException ex) {
                Logger.getLogger(VideoAudioServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class VideoReceiver extends Thread {

        DatagramPacket incomingImagePacket;
        ImageIcon incomingImageIcon;

        byte incomingImageByte[] = new byte[65536];

        VideoReceiver() {
            incomingImagePacket = new DatagramPacket(incomingImageByte, incomingImageByte.length);
        }

        @Override
        public void run() {
            while (continueCall) {
                try {
                    videoSock.receive(incomingImagePacket);
                    byte[] buffer = incomingImagePacket.getData();

                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(buffer));
                    incomingImageIcon = new ImageIcon(img);

                    // Transform Image
                    Image image = incomingImageIcon.getImage();
                    Image newimg = image.getScaledInstance(imgOtherCam.getWidth(), imgOtherCam.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    incomingImageIcon = new ImageIcon(newimg);

                    imgOtherCam.setIcon(incomingImageIcon);
                } catch (IOException ex) {
                    System.out.println("Error Video Receiver Thread : " + ex);
                }
            }
        }
    }

    class VideoTransmitter extends Thread {

        Webcam webcam;
        boolean onCam = false;

        DatagramPacket sendVideoPacket;

        ImageIcon sendImageIcon;
        BufferedImage bufferedImg;

        VideoTransmitter() {
            webcam = Webcam.getDefault();
            Dimension dim = new Dimension(640, 480);
            webcam.setViewSize(dim);
        }

        @Override
        public void run() {
            while (continueCall) {
                if (onCam) {
                    try {
                        bufferedImg = webcam.getImage();
                        sendImageIcon = new ImageIcon(bufferedImg);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(bufferedImg, "jpg", bos);
                        byte[] data = bos.toByteArray();

                        sendVideoPacket = new DatagramPacket(data, data.length, ipVideoClient, portVideoClient);
                        videoSock.send(sendVideoPacket);

                        // Resize To Fit My Cam
                        Image image = sendImageIcon.getImage();
                        Image newimg = image.getScaledInstance(imgMyCam.getWidth(), imgMyCam.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        sendImageIcon = new ImageIcon(newimg);

                        imgMyCam.setIcon(sendImageIcon);
                    } catch (Exception e) {
                        System.out.println("Error Video Transmitter ON CAM : " + e);
                    }
                } else {
                    try {
                        bufferedImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
                        sendImageIcon = new ImageIcon(bufferedImg);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(bufferedImg, "jpg", bos);
                        byte[] data = bos.toByteArray();

                        sendVideoPacket = new DatagramPacket(data, data.length, ipVideoClient, portVideoClient);
                        videoSock.send(sendVideoPacket);

                        // Resize To Fit My Cam
                        Image image = sendImageIcon.getImage();
                        Image newimg = image.getScaledInstance(imgMyCam.getWidth(), imgMyCam.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        sendImageIcon = new ImageIcon(newimg);

                        imgMyCam.setIcon(sendImageIcon);
                    } catch (Exception e) {
                        System.out.println("Error Video Transmitter OFF CAM : " + e);
                    }
                }
            }
        }

        public void OffCam() {
            this.onCam = false;
            this.webcam.close();
        }

        public void OnCam() {
            this.onCam = true;
            webcam.open();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOnCam = new javax.swing.JButton();
        btnUnmute = new javax.swing.JButton();
        btnEnd = new javax.swing.JButton();
        imgMyCam = new javax.swing.JLabel();
        imgOtherCam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTimer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(705, 645));
        setSize(new java.awt.Dimension(705, 605));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOnCam.setText("ON CAM");
        btnOnCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnCamActionPerformed(evt);
            }
        });
        getContentPane().add(btnOnCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        btnUnmute.setText("Unmute");
        btnUnmute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnmuteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUnmute, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, -1, -1));

        btnEnd.setText("End Call");
        btnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, -1, -1));

        imgMyCam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgMyCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(imgMyCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 240, 180));

        imgOtherCam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgOtherCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(imgOtherCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 640, 480));

        jLabel1.setText("SERVER");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        txtTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimer.setText("TIMER");
        getContentPane().add(txtTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 560, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void endCall() {
        continueCall = false;

        audioTransmitter.targetDataLine.close();
        audioReceiver.sourceDataLine.close();
        if (videoTransmitter.webcam.isOpen()) {
            videoTransmitter.webcam.close();
        }

        // Close Socket EndCall
        endCallReceiver.endCallReceiver.close();

        audioReceiver.stop();
        audioTransmitter.stop();
        videoTransmitter.stop();
        videoReceiver.stop();

        audioSock.close();
        videoSock.close();

        this.dispose();
        parent.setOnCall(false);
        parent.setVisible(true);
    }

    private void btnOnCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnCamActionPerformed
        String text = btnOnCam.getText();

        if (text.equals("ON CAM")) {
            videoTransmitter.OnCam();
            btnOnCam.setText("OFF CAM");
        } else {
            videoTransmitter.OffCam();
            btnOnCam.setText("ON CAM");
        }
    }//GEN-LAST:event_btnOnCamActionPerformed

    private void btnUnmuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnmuteActionPerformed
        String text = btnUnmute.getText();

        if (text.equals("Unmute")) {
            audioTransmitter.muted = false;
            btnUnmute.setText("Mute");
        } else {
            audioTransmitter.muted = true;
            btnUnmute.setText("Unmute");
        }
    }//GEN-LAST:event_btnUnmuteActionPerformed

    private void btnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndActionPerformed
        try {
            byte sendData[] = new byte[2048];
            String kirim = "EndCall";
            sendData = kirim.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAudioClient, 9000);
            audioSock.send(sendPacket);

            // Stop Thread agar gak minta receive terus pas ngambil command endCall, karena pas end call di close
            endCallReceiver.stop();
        } catch (Exception e) {
            System.out.println("Error End Call");
        }

        endCall();
    }//GEN-LAST:event_btnEndActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VideoAudioServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VideoAudioServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VideoAudioServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VideoAudioServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VideoAudioServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnd;
    private javax.swing.JButton btnOnCam;
    private javax.swing.JButton btnUnmute;
    public static javax.swing.JLabel imgMyCam;
    public static javax.swing.JLabel imgOtherCam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtTimer;
    // End of variables declaration//GEN-END:variables
}
