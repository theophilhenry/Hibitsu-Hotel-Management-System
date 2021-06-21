/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Timer;

public class AudioClient extends javax.swing.JFrame {

    FormChat parent;
    DatagramSocket sock;

    AudioReceiver audioReceiver;
    AudioTransmitter audioTransmitter;
    EndCallReceiver endCallReceiver;

    boolean continueCall = true;

    public AudioClient() {
        initComponents();
    }

    public AudioClient(DatagramSocket _sock, FormChat _parent) {
        initComponents();
        this.sock = _sock;
        this.parent = _parent;
        
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
                        if(minutes >= 60) {
                            hour++;
                            minutes = 0;
                        }
                    }
                    String _second = String.format("%02d", second);
                    String _minutes = String.format("%02d", minutes);
                    String _hour =  String.format("%02d", hour);
                    txtTimer.setText(_hour + ":" + _minutes + ":" + _second);
                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

        try {

            // Start Thread Receiver
            audioReceiver = new AudioReceiver();
            audioReceiver.start();

            // Start Thread Transmitter
            audioTransmitter = new AudioTransmitter();
            audioTransmitter.start();

            // Wait for End Call
            endCallReceiver = new EndCallReceiver(this);
            endCallReceiver.start();

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
                // START MIC
                //==============================================================
                // Mendapatkan info mic
                Mixer.Info mixerInfo[] = AudioSystem.getMixerInfo();
                AudioFormat audioFormat = getAudioFormat();
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                Mixer mixer = null;
                // Mencari MIC yang bisa dipake
                for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
                    mixer = AudioSystem.getMixer(mixerInfo[cnt]);
                    if (mixer.isLineSupported(dataLineInfo)) {
                        System.out.println(mixerInfo[cnt].getName());
                        targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
                        break;
                    }
                }
                targetDataLine.open(audioFormat);
                targetDataLine.start();
                //==============================================================
                // END OF START MIC

            } catch (Exception ex) {
                System.out.println("Error Constructor Audio Transmitter : " + ex);
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

                        sendAudioPacket = new DatagramPacket(sendAudioByte, sendAudioByte.length, InetAddress.getByName("localhost"), 2000);
                        sock.send(sendAudioPacket);
                    } catch (Exception ex) {
                        System.out.println("Error Thread Audio Transmitter : " + ex);
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

        public void run() {
            byte b[] = null;
            while (continueCall) {
                try {
                    // Menerima Input Suara
                    sock.receive(incomingAudioPacket);
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
        AudioClient parent;

        EndCallReceiver(AudioClient _parent) {
            this.parent = _parent;
            try {
                endCallReceiver = new MulticastSocket(null);
                endCallReceiver.setReuseAddress(true);
                endCallReceiver.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 9000));

                incomingByte = new byte[2048];
                incomingPacket = new DatagramPacket(incomingByte, incomingByte.length);
            } catch (IOException ex) {
                Logger.getLogger(AudioClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void run() {
            try {
                endCallReceiver.receive(incomingPacket);

                parent.endCall();
            } catch (IOException ex) {
                Logger.getLogger(AudioClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnUnmute = new javax.swing.JButton();
        txtTimer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Audio Client");

        btnEnd.setText("End Call");
        btnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndActionPerformed(evt);
            }
        });

        jLabel1.setText("CLIENT");

        btnUnmute.setText("Unmute");
        btnUnmute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnmuteActionPerformed(evt);
            }
        });

        txtTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimer.setText("TIMER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnd)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(btnUnmute)
                    .addComponent(txtTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(txtTimer)
                .addGap(18, 18, 18)
                .addComponent(btnUnmute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnEnd)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void endCall() {
        continueCall = false;

        audioTransmitter.targetDataLine.close();
        audioReceiver.sourceDataLine.close();

        // Close Socket Endcall
        endCallReceiver.endCallReceiver.close();

        audioReceiver.stop();
        audioTransmitter.stop();
        sock.close();
        this.dispose();
        parent.setVisible(true);
    }

    private void btnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndActionPerformed
        try {
            byte sendData[] = new byte[2048];
            String kirim = "EndCall";
            sendData = kirim.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 12000);
            sock.send(sendPacket);

            // Stop Thread agar gak minta receive terus pas ngambil command endCall, karena pas end call di close
            endCallReceiver.stop();
        } catch (Exception e) {
            System.out.println("Error End Call");
        }

        endCall();
    }//GEN-LAST:event_btnEndActionPerformed

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
            java.util.logging.Logger.getLogger(AudioClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AudioClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AudioClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AudioClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AudioClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnd;
    private javax.swing.JButton btnUnmute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtTimer;
    // End of variables declaration//GEN-END:variables
}
