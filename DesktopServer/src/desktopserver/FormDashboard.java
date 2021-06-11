/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopserver;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ohanna
 */
public class FormDashboard extends javax.swing.JFrame implements Runnable {

    ServerSocket ss;
    Socket client;
    public ArrayList<HandleSocket> clientsArr = new ArrayList<HandleSocket>();
    Thread t;
    HandleSocket hs;
    public String emailAdmin,idAdmin;
    public Boolean firstClient;
    
    /**
     * Creates new form FormDashboard
     */
    public FormDashboard() {
        try {
            initComponents();
            
            this.ss = new ServerSocket(12345);
            emailAdmin = "toto@gmail.com";
            idAdmin = "3";
            //firstClient = false;
            
            this.IsiComboBoxVilla();
            
            if(t==null)
            {
                t = new Thread(this,"Server");
                t.start();
            }
            
            // set color form
            this.getContentPane().setBackground(Color.WHITE);
        } catch (IOException ex) {
            Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void ShowChat(String msg)
    {
        String[] msgSplit = msg.split(";;");
        String content = msgSplit[1];
        String clientName = comboBoxClient.getSelectedItem().toString();
        textArea.append(clientName + " : " + content + "\n");
        
    }
    
    public void AddComboBoxClient(String name, String email)
    {
        Integer count = comboBoxClient.getItemCount();
        count +=1;
        
        comboBoxClient.addItem( /*count + "." +*/name + " (" + email + ")");
    }
    
    public void RemoveComboBoxClient(String name, String email)
    {
        comboBoxClient.removeItem(name + " (" + email + ")");
    }
    
   
    
    public String LoginUser(String email,String password)
    {
        String status = "Your username or password is incorrect";
        //Ambil nama panggilan dari webservice 
        
        String hasil = loginClient(email,password);
        
        if(hasil.contains("true"))
        {
            String[] arr = hasil.split(";;");
            
        }
        else
        {
            status = "Your username or password is incorrect";
        }
        
        return(status);
    }
    
    public void SendChatToOne(String msg)
    {
        for(HandleSocket client : clientsArr)
        {
            
            if(comboBoxClient.getSelectedItem().toString().contains(client.email))
            {
                client.SendChat(msg);
            }
            
            
        }
    }
    
    public void SimpanChat(String emailSend, String emailReceiv, String msg)
    {
        
        System.out.println(insertChat(emailSend ,emailReceiv,msg ));
    }
     
    public String TampilChat(String emailSend, String emailReceiv, String untukSiapa)
    {
        String result = displayChat(emailSend, emailReceiv);
        System.out.println("Hasil result \n" + result);
        String hasil = "";
        
        if(result.equals("") || result == null)
        {
            hasil = "false";
        }
        else
        {
            String[] arr1 = result.split("\\|\\|");
        
        System.out.println("Arr1 \n");
        System.out.println(Arrays.toString(arr1));
        
        untukSiapa = untukSiapa.toLowerCase();
        
       
        
        if(untukSiapa.equals("admin"))
        {
            for(Integer i =0; i<arr1.length; i++)
            {
                String[] arr2 = arr1[i].split(";;");
                
                if(arr2[1].equals("3"))
                {
                    
                    hasil += "Admin : " + arr2[1] ;
                }
                else
                {
                    hasil += comboBoxClient.getSelectedItem().toString() + " : " + arr2[2] ;
                }
                
                if(i!=arr1.length-1)
                {
                    hasil += "\n";
                }
            }
        }
        else
        {
            for(Integer i =0; i<arr1.length; i++)
            {
                String[] arr2 = arr1[i].split(";;");
                
                System.out.println("\n\n Arr2 : \n");
                System.out.println(Arrays.toString(arr2));
                
                if(arr2[1].equals("3"))
                {
                    
                    hasil += "Admin : " + arr2[2] ;
                }
                else
                {
                    hasil += "Me : " + arr2[2] ;
                }
                
                 if(i!=arr1.length-1)
                {
                    hasil += "\n";
                }
            }
        }
        //hasil += "========================================\n";
        }
        
        
        return hasil;
        
       
    }
    
    public void IsiComboBoxVilla()
    {
        String result = displayVillaAll();
        String[] arr = result.split(";;");
        
        System.out.println(Arrays.toString(arr));
        
        for(String s : arr)
        {
            
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

        jLabel1 = new javax.swing.JLabel();
        panelChat = new javax.swing.JPanel();
        textChat = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        panelTable = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        comboBoxSelect = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        panelBooking = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textNotes = new javax.swing.JTextField();
        buttonBook = new javax.swing.JButton();
        buttonCheck = new javax.swing.JButton();
        comboBoxVillaType = new javax.swing.JComboBox<>();
        dateCheckOut = new com.toedter.calendar.JDateChooser();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        textTotalGuest = new com.toedter.components.JSpinField();
        comboBoxClient = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Rubik", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 212, 121));
        jLabel1.setText("Owner Dashboard");

        panelChat.setBackground(new java.awt.Color(255, 255, 255));
        panelChat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        textChat.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N

        buttonSend.setBackground(new java.awt.Color(8, 191, 91));
        buttonSend.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        buttonSend.setForeground(new java.awt.Color(255, 255, 255));
        buttonSend.setText("CHAT");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout panelChatLayout = new javax.swing.GroupLayout(panelChat);
        panelChat.setLayout(panelChatLayout);
        panelChatLayout.setHorizontalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChatLayout.createSequentialGroup()
                        .addComponent(textChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelChatLayout.setVerticalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSend))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelTable.setBackground(new java.awt.Color(255, 255, 255));
        panelTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel9.setFont(new java.awt.Font("Rubik", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(29, 212, 121));
        jLabel9.setText("Find Order");

        txtSearch.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblOrder);

        comboBoxSelect.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        comboBoxSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setBackground(new java.awt.Color(8, 191, 91));
        btnSearch.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panelTableLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelBooking.setBackground(new java.awt.Color(255, 255, 255));
        panelBooking.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Rubik", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(29, 212, 121));
        jLabel2.setText("Book Property");

        jLabel3.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel3.setText("Email");

        textEmail.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel4.setText("Villa Type");

        jLabel5.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel5.setText("Check In");

        jLabel6.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel6.setText("Check Out");

        jLabel7.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel7.setText("Total Guests");

        jLabel8.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        jLabel8.setText("Notes");

        textNotes.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N

        buttonBook.setBackground(new java.awt.Color(8, 191, 91));
        buttonBook.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        buttonBook.setForeground(new java.awt.Color(255, 255, 255));
        buttonBook.setText("BOOK");
        buttonBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookActionPerformed(evt);
            }
        });

        buttonCheck.setBackground(new java.awt.Color(8, 191, 91));
        buttonCheck.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        buttonCheck.setForeground(new java.awt.Color(255, 255, 255));
        buttonCheck.setText("CHECK");
        buttonCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckActionPerformed(evt);
            }
        });

        comboBoxVillaType.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        comboBoxVillaType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dateCheckOut.setBackground(java.awt.Color.white);

        dateCheckIn.setBackground(java.awt.Color.white);

        textTotalGuest.setBackground(java.awt.Color.white);
        textTotalGuest.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelBookingLayout = new javax.swing.GroupLayout(panelBooking);
        panelBooking.setLayout(panelBookingLayout);
        panelBookingLayout.setHorizontalGroup(
            panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookingLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBookingLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGap(26, 26, 26))
                        .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(textNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(textTotalGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxVillaType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBookingLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBookingLayout.createSequentialGroup()
                        .addComponent(buttonCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBook, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        panelBookingLayout.setVerticalGroup(
            panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookingLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxVillaType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBookingLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5))
                    .addGroup(panelBookingLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBookingLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(panelBookingLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(textTotalGuest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(textNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBookingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBook)
                    .addComponent(buttonCheck))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        comboBoxClient.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxClientItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        String msg = textChat.getText();
        SendChatToOne(msg);
        textArea.append("Admin : " + msg + "\n");
        
        String info = comboBoxClient.getSelectedItem().toString();
        String[] arr = info.split("(");
        String emailDest = arr[1].substring(0,arr[1].length()-1);
        
        //SimpanChat(emailAdmin, emailDest, msg);
        System.out.println(emailDest);
        
    }//GEN-LAST:event_buttonSendActionPerformed

    private void comboBoxClientItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxClientItemStateChanged
       // String info = comboBoxClient.getSelectedItem().toString();
       // String[] arr = info.split("(");
       // String emailDest = arr[1].substring(0,arr[1].length()-1);
        
      //  String historyChat = TampilChat(emailAdmin, emailDest, "admin");
        
        textArea.setText("");
     //   textArea.append(historyChat);
    }//GEN-LAST:event_comboBoxClientItemStateChanged

    private void buttonCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCheckActionPerformed

    private void buttonBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBookActionPerformed

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
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton buttonBook;
    private javax.swing.JButton buttonCheck;
    private javax.swing.JButton buttonSend;
    public javax.swing.JComboBox<String> comboBoxClient;
    private javax.swing.JComboBox<String> comboBoxSelect;
    private javax.swing.JComboBox<String> comboBoxVillaType;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelBooking;
    private javax.swing.JPanel panelChat;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tblOrder;
    public javax.swing.JTextArea textArea;
    private javax.swing.JTextField textChat;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNotes;
    private com.toedter.components.JSpinField textTotalGuest;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true)
        {
           
            try {
                client = ss.accept();
                HandleSocket hs = new HandleSocket(this, client);
                hs.start();
            } catch (IOException ex) {
                Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
                
           
        }
    }

    public static String loginClient(java.lang.String email, java.lang.String password) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.loginClient(email, password);
    }

    public static String insertChat(java.lang.String emailSender, java.lang.String emailReceiver, java.lang.String messages) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.insertChat(emailSender, emailReceiver, messages);
    }

    

    private static String displayVillaAll() {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.displayVillaAll();
    }

    private static String displayChat(java.lang.String emailSender, java.lang.String emailReceiver) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.displayChat(emailSender, emailReceiver);
    }
}
