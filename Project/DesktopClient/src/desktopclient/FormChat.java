/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopclient;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ohanna
 */
public class FormChat extends javax.swing.JFrame implements Runnable {

    Socket client;
    String email, displayName;
    BufferedReader input;
    DataOutputStream output;
    Thread t;
    Boolean chatWithBot, cekChatHistory;
    Integer oneTimePrint,count;

    /**
     * Creates new form FormChat
     */
    public FormChat() {
        initComponents();
        //coloring jform
        this.getContentPane().setBackground(Color.WHITE);
        labelBot.setForeground(Color.green);
    }

    public FormChat(Socket inClient, String _email, String _displayName) {
        try {
            initComponents();
            //coloring jform
            this.getContentPane().setBackground(Color.WHITE);

            client = inClient;
            email = _email;
            displayName = _displayName;
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
            chatWithBot = true;
            cekChatHistory = false;
            oneTimePrint = 0;
            output.writeBytes("JOIN \n");
            count = 0;
            
            // Audio Socket
            audioSock = new MulticastSocket(null);
            audioSock.setReuseAddress(true);
            audioSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 0));

            // Video Socket
            videoSock = new MulticastSocket(null);
            videoSock.setReuseAddress(true);
            videoSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 0));

            if (t == null) {
                t = new Thread(this, "client");
                t.start();
            }
            //FormChat fc = (FormChat)this;
            //fc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            

        } catch (IOException ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ScrollDown() {
        textArea.setCaretPosition(textArea.getText().length());
    }

    private void ShowChat() {
        try {

            String message = input.readLine();
            
            if (cekChatHistory == false) 
            {
                
                if (message.equals("false")) 
                {

                } 
                else 
                {
                    if (message.equals(";;DONE;;")) {
                        cekChatHistory = true;
                        count += 1;
                        
                        if(count == 1)
                        {
                            textArea.append("----------------------Welcome to Hibitsu Chat-----------------------\n\n");
                        }
                        else
                        {
                            count = 0;
                            textArea.append("----------------------Chat Sebelumnya-----------------------\n\n");
                        }
                        
                    } else {
                        textArea.append(" " + message + "\n");
                        count += 1;
                    }

                }

            } else if (chatWithBot == true) {
                if (oneTimePrint == 0) {
                    textArea.append(" ChatBot : \n");
                    oneTimePrint = 1;
                }

                if (message.equals(("EndFromBot"))) {
                    oneTimePrint = 0;

                } else {

                    textArea.append(" " + message + "\n");
                }

            } else {
               
                textArea.append(" Admin : " + message + "\n");

            }

            ScrollDown();

        } catch (IOException ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SendChat(String msg) {
        try {
            output.writeBytes(email + ";;" + msg + "\n");
            if(chatWithBot == true)
            {
                textArea.append(" \nMe : " + msg + "\n\n");
            }
            else
            {
                textArea.append(" Me : " + msg + "\n");
            }
            ScrollDown();

        } catch (IOException ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtVideoCall = new javax.swing.JLabel();
        txtAudioCall = new javax.swing.JLabel();
        jLabelChat2 = new javax.swing.JLabel();
        jLabelChat = new javax.swing.JLabel();
        btnAudioCall = new javax.swing.JLabel();
        btnVideoCall = new javax.swing.JLabel();
        panelChat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        txtChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        labelBot = new javax.swing.JLabel();
        buttonClose1 = new javax.swing.JButton();

        buttonClose.setBackground(new java.awt.Color(255, 102, 102));
        buttonClose.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        buttonClose.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose.setText("CLOSE");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Rubik", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 212, 121));
        jLabel1.setText("Welcome to Hibitsu!");

        jLabel2.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(90, 90, 90));
        jLabel2.setText("Start live conversation");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtVideoCall.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        txtVideoCall.setForeground(new java.awt.Color(29, 212, 121));
        txtVideoCall.setText("VIDEO");
        txtVideoCall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtVideoCall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVideoCallMouseClicked(evt);
            }
        });

        txtAudioCall.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        txtAudioCall.setForeground(new java.awt.Color(29, 212, 121));
        txtAudioCall.setText("AUDIO");
        txtAudioCall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtAudioCall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAudioCallMouseClicked(evt);
            }
        });

        jLabelChat2.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        jLabelChat2.setForeground(new java.awt.Color(29, 212, 121));
        jLabelChat2.setText("CHAT");
        jLabelChat2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelChat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChat2MouseClicked(evt);
            }
        });

        jLabelChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/desktopclient/icon/CHAT.png"))); // NOI18N
        jLabelChat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChatMouseClicked(evt);
            }
        });

        btnAudioCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/desktopclient/icon/AUDIO.png"))); // NOI18N
        btnAudioCall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAudioCall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAudioCallMouseClicked(evt);
            }
        });

        btnVideoCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/desktopclient/icon/VIDEO.png"))); // NOI18N
        btnVideoCall.setToolTipText("");
        btnVideoCall.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVideoCall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVideoCall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVideoCall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVideoCallMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelChat)
                    .addComponent(jLabelChat2))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAudioCall)
                    .addComponent(txtAudioCall))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtVideoCall)
                    .addComponent(btnVideoCall))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAudioCall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAudioCall, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVideoCall, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelChat, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelChat2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVideoCall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );

        panelChat.setBackground(new java.awt.Color(255, 255, 255));

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(textArea);

        txtChat.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        txtChat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtChat.setMinimumSize(new java.awt.Dimension(2, 34));

        btnSend.setBackground(new java.awt.Color(8, 191, 91));
        btnSend.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChatLayout = new javax.swing.GroupLayout(panelChat);
        panelChat.setLayout(panelChatLayout);
        panelChatLayout.setHorizontalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );
        panelChatLayout.setVerticalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Chat with Bot : ");

        labelBot.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        labelBot.setForeground(new java.awt.Color(0, 204, 0));
        labelBot.setText("ON");

        buttonClose1.setBackground(new java.awt.Color(255, 102, 102));
        buttonClose1.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        buttonClose1.setForeground(new java.awt.Color(255, 255, 255));
        buttonClose1.setText("CLOSE");
        buttonClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClose1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBot)))
                .addGap(92, 92, 92))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelBot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(buttonClose1)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String msg = txtChat.getText();
        SendChat(msg);
        txtChat.setText("");
        ScrollDown();

    }//GEN-LAST:event_btnSendActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed


    }//GEN-LAST:event_formWindowClosed

    private void jLabelChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChatMouseClicked
        BotChatOff();
        
    }//GEN-LAST:event_jLabelChatMouseClicked

    private void jLabelChat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChat2MouseClicked
       BotChatOff();
    }//GEN-LAST:event_jLabelChat2MouseClicked

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClose1ActionPerformed
        try {
            output.writeBytes("LOGOUT \n");
            
            
            this.dispose();
            System.exit(1);
           
        } catch (IOException ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonClose1ActionPerformed

    byte[] receiveData;
    byte[] sendData;
    DatagramPacket sendPacket, receivePacket;

    DatagramSocket audioSock;
    DatagramSocket videoSock;


    private void txtAudioCallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAudioCallMouseClicked
        AudioCall();
    }//GEN-LAST:event_txtAudioCallMouseClicked

    private void btnAudioCallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAudioCallMouseClicked
        AudioCall();
    }//GEN-LAST:event_btnAudioCallMouseClicked

    private void btnVideoCallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVideoCallMouseClicked
        VideoCall();
    }//GEN-LAST:event_btnVideoCallMouseClicked

    private void txtVideoCallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVideoCallMouseClicked
        VideoCall();
    }//GEN-LAST:event_txtVideoCallMouseClicked

    
    public void BotChatOff()
    {
        try {

            if (chatWithBot == false) {

                chatWithBot = true;
                textArea.append("\n\n----------ChatBot Aktif----------\n\n");
                output.writeBytes("CHATWITHBOT;;true\n");
                labelBot.setForeground(Color.green);
                labelBot.setText("ON");
                JOptionPane.showMessageDialog(null, "Anda telah men-Aktifkan ChatBot.");

            } else {
                chatWithBot = false;
                output.writeBytes("CHATWITHBOT;;false\n");
                labelBot.setForeground(Color.red);
                labelBot.setText("OFF");
                JOptionPane.showMessageDialog(null, "Anda telah men-Nonaktifkan ChatBot.\nSekarang anda bisa melakukan chat lansgung dengan admin.");
                textArea.append("\n\n----------ChatBot Non-Aktif----------\n\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AudioCall() {
        try {
            if (audioSock.isClosed()) {
                audioSock = new MulticastSocket(null);
                audioSock.setReuseAddress(true);
                audioSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 0));
            }
            receiveData = new byte[2048];
            sendData = new byte[2048];

            System.out.println("Sending Data ...");
            String kirim = "AudioCall;;" + displayName + "(" + email + ")";
            sendData = kirim.getBytes();
            System.out.println("Data Sent!");

            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 5000);
            audioSock.send(sendPacket);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            audioSock.receive(receivePacket);

            System.out.println("Data Received!");

            String received = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            if (received.equals("yes")) {
                AudioClient audioClient = new AudioClient(audioSock, this);
                audioClient.setVisible(true);
                this.setVisible(false);
            } else if (received.equals("no")) {
                JOptionPane.showMessageDialog(rootPane, "Owner is currently on call");
            } else if (received.equals("decline")) {
                JOptionPane.showMessageDialog(rootPane, "Call declined, owner is busy");
            }
        } catch (Exception e) {
            System.out.println("Error Btn Call : " + e);
        }
    }

    public void VideoCall() {
        try {
            if (audioSock.isClosed()) {
                audioSock = new MulticastSocket(null);
                audioSock.setReuseAddress(true);
                audioSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 0));

                videoSock = new MulticastSocket(null);
                videoSock.setReuseAddress(true);
                videoSock.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 0));
            }
            receiveData = new byte[2048];
            sendData = new byte[2048];

            System.out.println("Sending Data ...");
            String kirim = "VideoCall;;" + displayName + "(" + email + ")";
            sendData = kirim.getBytes();
            System.out.println("Data Sent!");

            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 5000);
            audioSock.send(sendPacket);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            audioSock.receive(receivePacket);

            System.out.println("Data Received!");

            String received = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            if (received.equals("yes")) {

                videoSock.send(sendPacket);

                VideoAudioClient vaClient = new VideoAudioClient(audioSock, videoSock, this);
                vaClient.setVisible(true);
                this.setVisible(false);

            } else if (received.equals("no")) {
                JOptionPane.showMessageDialog(rootPane, "Owner is currently on call");
            } else if (received.equals("decline")) {
                JOptionPane.showMessageDialog(rootPane, "Call declined, owner is busy");
            }
        } catch (Exception e) {
            System.out.println("Error Btn VideoCall : " + e);
        }
    }

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
            java.util.logging.Logger.getLogger(FormChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAudioCall;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel btnVideoCall;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonClose1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelChat;
    private javax.swing.JLabel jLabelChat2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBot;
    private javax.swing.JPanel panelChat;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel txtAudioCall;
    private javax.swing.JTextField txtChat;
    private javax.swing.JLabel txtVideoCall;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            ShowChat();
        }
    }
}
