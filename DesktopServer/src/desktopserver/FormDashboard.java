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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
    public String emailAdmin;//, idAdmin;
    public Boolean adaClient;

    /**
     * Creates new form FormDashboard
     */
    
    public FormDashboard()
    {
        initComponents();
    }
    
    public FormDashboard(String emailLoginAdmin) {
        try {
            initComponents();

            this.ss = new ServerSocket(12345);
            emailAdmin = emailLoginAdmin;
            //idAdmin = "3";
            adaClient = false;
            labelBot.setText("NONE");

            this.IsiComboBoxVilla();

            if (t == null) {
                t = new Thread(this, "Server");
                t.start();
            }

            TampilReservasi("", "");
            this.getContentPane().setBackground(Color.WHITE);

            waitingForCall = new WaitingForCall(this);

        } catch (IOException ex) {
            Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ScrollDown() {
        textArea.setCaretPosition(textArea.getText().length());
    }

    public void ShowChat(String msg) {
        String[] msgSplit = msg.split(";;");
        String content = msgSplit[1];
        String clientName = comboBoxClient.getSelectedItem().toString();
        textArea.append(clientName + " : " + content + "\n");
        ScrollDown();

    }

    public void AddComboBoxClient(String name, String email) {

        comboBoxClient.addItem(name + " (" + email + ")");
        adaClient = true;
    }

    public void RemoveComboBoxClient(String name, String email) {
        comboBoxClient.removeItem(name + " (" + email + ")");
    }

    public String LoginUser(String email, String password) {
        String status = "Your username or password is incorrect";
        //Ambil nama panggilan dari webservice 

        String hasil = loginClient(email, password);

        if (hasil.contains("true")) {
            String[] arr = hasil.split(";;");

        } else {
            status = "Your username or password is incorrect";
        }

        return (status);
    }

    public void SendChatToOne(String msg) {
        for (HandleSocket client : clientsArr) {

            if (comboBoxClient.getSelectedItem().toString().contains(client.email)) {
                client.SendChat(msg);
            }

        }
        ScrollDown();
    }

    public void SimpanChat(String emailSend, String emailReceiv, String msg) {

        System.out.println("SIMPAN CHAT : " + msg);
        System.out.println(insertChat(emailSend ,emailReceiv,msg ));
    }

    public String TampilChat(String emailSend, String emailReceiv, String untukSiapa) {
        String result = displayChat(emailSend, emailReceiv);
//        System.out.println("Hasil result \n" + result);
        String hasil = "";
        String cekName = "admin";

        if (result.equals("") || result == null) {
            hasil = "false";
        } else {
            String[] arr1 = result.split("\\|\\|");

//            System.out.println("Arr1 \n");
//            System.out.println(Arrays.toString(arr1));
            untukSiapa = untukSiapa.toLowerCase();

            if (untukSiapa.equals("admin")) {
                for (Integer i = 0; i < arr1.length; i++) {
                    String[] arr2 = arr1[i].split(";;");

                    if (arr2[1].equals("3")) {
                        if (cekName.equals("client")) {
                            cekName = "admin";
                            hasil += "\n";
                        }

                        hasil += "Admin : " + arr2[2];
                    } else {
                        if (cekName.equals("admin")) {
                            cekName = "client";
                            hasil += "\n";
                        }
                        hasil += comboBoxClient.getSelectedItem().toString() + " : " + arr2[2];
                    }

                    if (i != arr1.length - 1) {
                        hasil += "\n";
                    }
                }
            } else {
                for (Integer i = 0; i < arr1.length; i++) {
                    String[] arr2 = arr1[i].split(";;");

                    if (arr2[1].equals("3")) {
                        if (cekName.equals("client")) {
                            cekName = "admin";
                            hasil += "\n\n";
                        }
                        cekName = "admin";
                        hasil += "Admin : " + arr2[2];

                    } else {
                        if (cekName.equals("admin")) {
                            cekName = "client";
                            hasil += "\n\n";
                        }

                        hasil += "Me : " + arr2[2];
                    }

                    if (i != arr1.length - 1) {
                        hasil += "\n";
                    }
                }
            }
           
        }

        return hasil;

    }

    public void IsiComboBoxVilla() {
        String result = displayVillaAll();
        String[] arr1 = result.split("\\|\\|");
//        System.out.println(Arrays.toString(arr1));

        for (Integer i = 0; i < arr1.length; i++) {
            String[] arr2 = arr1[i].split(";;");
            comboBoxVillaType.addItem(arr2[1] + ")" + arr2[2] + "\n");
        }

    }

    public String TampilSemuaVilla() {
        String result = displayVillaAll();
        String[] arr1 = result.split("\\|\\|");
        String hasil = "";

        for (Integer i = 0; i < arr1.length; i++) {
            String[] arr2 = arr1[i].split(";;");
            hasil += arr2[1] + ")" + arr2[2] + "\n";
        }
        return hasil;

    }

    public String CekAvaible(String checkin, String checkout, String idVilla) {
        Integer id = Integer.parseInt(idVilla);

        String hasil = checkAvailability(id, checkin, checkout);
        String[] arr = hasil.split(";;");
        return arr[1];

    }

    public void TampilReservasi(String kriteria, String dicari) {
        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
        model.setRowCount(0);
        tableOrder.getColumnModel().getColumn(0).setPreferredWidth(35);
        tableOrder.getColumnModel().getColumn(5).setPreferredWidth(30);
        String[] arr1 = displayReservationAll(kriteria, dicari).split("\\|\\|");

        if (arr1.length != 0) {
            for (int i = 0; i < arr1.length; i++) {
//            System.out.println(arr1[i] + "\n");
                String[] arr2 = arr1[i].split(";;");
//            System.out.println("\n\napa null : " + arr2[9] + "\n\n");
                Object[] row = {arr2[1], arr2[13], arr2[11], arr2[3], arr2[4], arr2[5]};
                model.addRow(row);

            }
        }

        //[1]idreservation,[2]res_timestamp,[3]chcekin_date,[4]checkout_date,[5]status,[6]total_guest,[7]total_price,[8]notes,[19]url_bukti_pembayaran,[10]idvilla,
        //[11]villa_name,[12]iduser,[13]fullname,[14]display_name[15],phone_number,[16]email,[17]no_ktp;;
        //333;;2021-06-14 16:35:00.0;;2021-06-15;;2021-06-17;;PENDING;;2;;3600000;;Tolong sediakan grill;;null;;1;;The La Llorona;;1;;Jasti Ohanna;;jasti;;08123456789;;jasti@gmail.com;;3315060711900001
    }

    WaitingForCall waitingForCall;
    DatagramSocket datagramSocket;

    DatagramPacket sendPacket;
    DatagramPacket incomingPacket;

    boolean onCall = false;

    public void setOnCall(boolean _onCall) {
        onCall = _onCall;
    }

    class WaitingForCall implements Runnable {

        Thread callThread;
        FormDashboard parent;

        byte[] sendData;
        byte[] receiveData;

        public WaitingForCall(FormDashboard _parent) {
            this.parent = _parent;
            try {
                datagramSocket = new DatagramSocket(5000);
                if (callThread == null) {
                    callThread = new Thread(this, "CallWaitingThread");
                    callThread.start();
                }
            } catch (Exception ex) {
                System.out.println("Datagram Socket Error : " + ex);
            }
        }

        @Override
        public void run() {
            while (true) {
                byte[] sendData = new byte[2048];
                byte[] receiveData = new byte[2048];

                try {
                    incomingPacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Waiting For Client Packet");
                    datagramSocket.receive(incomingPacket);

                    String received = new String(incomingPacket.getData(), incomingPacket.getOffset(), incomingPacket.getLength());
                    System.out.println("Data Received!");
                    System.out.println("Data : " + received);

                    if (received.contains("AudioCall")) {

                        InetAddress ipClient = incomingPacket.getAddress();
                        int portClient = incomingPacket.getPort();
                        
                        if (parent.onCall == false) {
                            // Biar saat lagi request telephon, gabisa di call sm orang lain
                            parent.setOnCall(true);
                            String[] arrNama = received.split(";;");
                            
                            int answer = JOptionPane.showConfirmDialog(parent, "Anda di telephon oleh " + arrNama[1] , "Call Masuk", JOptionPane.YES_NO_OPTION);

                            if (answer == 0) { // Yes
                                String kirim = "yes";
                                sendData = kirim.getBytes();
                                sendPacket = new DatagramPacket(sendData, sendData.length, ipClient, portClient);
                                datagramSocket.send(sendPacket);

                                AudioServer form = new AudioServer(ipClient, portClient, parent);
                                form.setVisible(true);
                                parent.setVisible(false);
                            } else {
                                parent.setOnCall(false);
                                String kirim = "decline";
                                sendData = kirim.getBytes();
                                sendPacket = new DatagramPacket(sendData, sendData.length, ipClient, portClient);
                                datagramSocket.send(sendPacket);
                            }

                        } else {
                            String kirim = "no";
                            sendData = kirim.getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, ipClient, portClient);
                            datagramSocket.send(sendPacket);
                        }

                    } else if (received.contains("VideoCall")) {
                        InetAddress ipAudioClient = incomingPacket.getAddress();
                        int portAudioClient = incomingPacket.getPort();

                        if (parent.onCall == false) {
                            // Biar saat lagi request telephon, gabisa di call sm orang lain
                            parent.setOnCall(true);
                            String[] arrNama = received.split(";;");
                            int answer = JOptionPane.showConfirmDialog(parent, "Anda di telephon oleh " + arrNama[1] , "Call Masuk", JOptionPane.YES_NO_OPTION);

                            if (answer == 0) { // Yes
                                String kirim = "yes";
                                sendData = kirim.getBytes();
                                sendPacket = new DatagramPacket(sendData, sendData.length, ipAudioClient, portAudioClient);
                                datagramSocket.send(sendPacket);

                                System.out.println("Waiting for another packet");
                                datagramSocket.receive(incomingPacket);
                                System.out.println("Packet received");
                                InetAddress ipVideoClient = incomingPacket.getAddress();
                                int portVideoClient = incomingPacket.getPort();

                                System.out.println("Anda di telephon oleh " + String.valueOf(ipAudioClient) + " dari port " + String.valueOf(portAudioClient));
                                System.out.println("Lalu di vidcall oleh " + String.valueOf(ipVideoClient) + " dari port " + String.valueOf(portVideoClient));

                                VideoAudioServer form = new VideoAudioServer(ipAudioClient, portAudioClient, ipVideoClient, portVideoClient, parent);
                                form.setVisible(true);
                                parent.setVisible(false);
                            } else {
                                parent.setOnCall(false);
                                String kirim = "decline";
                                sendData = kirim.getBytes();
                                sendPacket = new DatagramPacket(sendData, sendData.length, ipAudioClient, portAudioClient);
                                datagramSocket.send(sendPacket);
                            }

                        } else {
                            String kirim = "no";
                            sendData = kirim.getBytes();
                            sendPacket = new DatagramPacket(sendData, sendData.length, ipAudioClient, portAudioClient);
                            datagramSocket.send(sendPacket);
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Threading Error : " + ex);
                }
            }
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
        textSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        comboBoxFindOrder = new javax.swing.JComboBox<>();
        buttonRefresh = new javax.swing.JButton();
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
        labelBot = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(900, 150));

        jLabel1.setFont(new java.awt.Font("Rubik", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 212, 121));
        jLabel1.setText("Admin Dashboard");

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
        jLabel9.setText("Find Reservation");

        textSearch.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textSearchKeyReleased(evt);
            }
        });

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Reservation", "Client Name", "Villla", "CheckIn_Date", "CheckOut_Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrder.setSurrendersFocusOnKeystroke(true);
        tableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOrderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableOrder);

        comboBoxFindOrder.setFont(new java.awt.Font("Rubik", 1, 14)); // NOI18N
        comboBoxFindOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IdReservation", "Client Name", "Villa", "Checkin_Date", "Checkout_Date", "Status" }));

        buttonRefresh.setBackground(new java.awt.Color(8, 191, 91));
        buttonRefresh.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        buttonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        buttonRefresh.setText("REFRESH TABLE");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxFindOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRefresh)))
                .addGap(26, 26, 26))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxFindOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonRefresh)))
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

        dateCheckOut.setBackground(java.awt.Color.white);
        dateCheckOut.setDateFormatString("yyyy-MM-dd");

        dateCheckIn.setBackground(java.awt.Color.white);
        dateCheckIn.setDateFormatString("yyyy-MM-dd");

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

        labelBot.setBackground(new java.awt.Color(255, 0, 51));
        labelBot.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelBot.setText("NONE");

        jLabel10.setText("This Client BotChat :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBot)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(labelBot))
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
        textChat.setText("");
        String info = comboBoxClient.getSelectedItem().toString();
        String[] arr = info.split("\\(");
        String emailDest = arr[1].substring(0, arr[1].length() - 1);
//        System.out.println("Email Dest : " + emailDest);
        this.SimpanChat(emailAdmin, emailDest, msg);


    }//GEN-LAST:event_buttonSendActionPerformed

    private void comboBoxClientItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxClientItemStateChanged

        String info = comboBoxClient.getSelectedItem().toString();

        String[] arr = info.split("\\(");
        String emailDest = arr[1].substring(0, arr[1].length() - 1);

        textEmail.setText(emailDest);

        String historyChat = TampilChat(emailAdmin, emailDest, "admin");

        textArea.setText("");

        textArea.append(historyChat + "\n");
        textArea.append("----------------------Chat Sebelumnya-----------------------\n\n");
        ScrollDown();

        for (HandleSocket client : clientsArr) {

            if (comboBoxClient.getSelectedItem().toString().contains(client.email)) {
                if (client.chatWithBot == false) {
                    labelBot.setText("OFF");
                    labelBot.setForeground(Color.red);
                } else {
                    labelBot.setText("ON");
                    labelBot.setForeground(Color.green);
                }
            }

        }

    }//GEN-LAST:event_comboBoxClientItemStateChanged

    private void buttonCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckActionPerformed
        String checkIn = ((JTextField) dateCheckIn.getDateEditor().getUiComponent()).getText();
        String checkOut = ((JTextField) dateCheckOut.getDateEditor().getUiComponent()).getText();
//        System.out.println("601");
        String[] arr = comboBoxVillaType.getSelectedItem().toString().split("\\)");
        Integer villaId = Integer.parseInt(arr[0]);
//        System.out.println("604");
        String[] arr2 = checkAvailability(villaId, checkIn, checkOut).split(";;");
        String status = "";

        if (arr2[1].equals("true")) {
            status = "Villa Tersedia untuk Pemesanan";
        } else if (arr2[1].equals("false")) {
            status = "Villa Tidak Tersedia";
        } else {
            status = arr2[1];
        }
        JOptionPane.showMessageDialog(null, status);

    }//GEN-LAST:event_buttonCheckActionPerformed

    private void buttonBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookActionPerformed
        String checkIn = ((JTextField) dateCheckIn.getDateEditor().getUiComponent()).getText();
        String checkOut = ((JTextField) dateCheckOut.getDateEditor().getUiComponent()).getText();
        String[] arr = comboBoxVillaType.getSelectedItem().toString().split("\\)");
        Integer villaId = Integer.parseInt(arr[0]);
        String emailClient = textEmail.getText();
        Integer totalGuest = textTotalGuest.getValue();
        Integer idClient = 0;
        String clientNote = textNotes.getText();
     
        String hasil = getUserIdBasedOnEmail(emailClient);
        if(hasil.contains("false"))
        {
            JOptionPane.showMessageDialog(null, "Tidak ditemukan user dengan alamat email : " + emailClient);
        }
        else
        {
            String[] arrResult = hasil.split(";;");
            idClient = Integer.parseInt(arrResult[1]);
                    
                    
            String[] arr2 = insertReservation(checkIn, checkOut, totalGuest, clientNote, idClient, villaId).split(";;");
            String status = "";

            if (arr2[1].equals("true")) {
                status = "Villa berhasil dibooking";
                //SendChatToOne(status + "\n Id Nota adalah  " + arr2[2]);
                //textArea.append("Admin : " + status + "\nAdmin : Id Nota adalah  " + arr2[2] + "\n");
                //SimpanChat(emailAdmin, emailClient, status);
                //SimpanChat(emailAdmin, emailClient, "Id Nota adalah  " + arr2[2]);
            } else if (arr2[1].equals("false")) {
                status = "Mohon maaf, villa Tidak Tersedia";
                //SendChatToOne(status);
            } else {
                status = arr2[1];
            }

            JOptionPane.showMessageDialog(null, status + "\nOrder ID : " + arr2[2]);
        }
        
        

        

        
    }//GEN-LAST:event_buttonBookActionPerformed

    private void tableOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrderMouseClicked
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        String id = source.getModel().getValueAt(row, 0) + "";

        String result = displayReservationAll("idreservation", id);

        FormOrderDetail fod = new FormOrderDetail(result,this);
        fod.setVisible(true);

    }//GEN-LAST:event_tableOrderMouseClicked

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        TampilReservasi("", "");
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void textSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyReleased
        String kriteria = "";
        String dicari = textSearch.getText();

        if (comboBoxFindOrder.getSelectedItem().toString().equals("Client Name")) {
            kriteria = "fullname";

        } else if (comboBoxFindOrder.getSelectedItem().toString().equals("Villa")) {
            kriteria = "name";

        } else {
            kriteria = comboBoxFindOrder.getSelectedItem().toString();
            System.out.println(kriteria);
        }

        TampilReservasi(kriteria, dicari);
    }//GEN-LAST:event_textSearchKeyReleased

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
    private javax.swing.JButton buttonBook;
    private javax.swing.JButton buttonCheck;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSend;
    public javax.swing.JComboBox<String> comboBoxClient;
    private javax.swing.JComboBox<String> comboBoxFindOrder;
    private javax.swing.JComboBox<String> comboBoxVillaType;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    public javax.swing.JLabel labelBot;
    private javax.swing.JPanel panelBooking;
    private javax.swing.JPanel panelChat;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableOrder;
    public javax.swing.JTextArea textArea;
    private javax.swing.JTextField textChat;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNotes;
    private javax.swing.JTextField textSearch;
    private com.toedter.components.JSpinField textTotalGuest;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {

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

    public static String displayVillaAll() {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.displayVillaAll();
    }

    private static String displayChat(java.lang.String emailSender, java.lang.String emailReceiver) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.displayChat(emailSender, emailReceiver);
    }

    public static String checkAvailability(java.lang.Integer idvilla, java.lang.String checkin, java.lang.String checkout) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.checkAvailability(idvilla, checkin, checkout);
    }

    public static String insertReservation(java.lang.String checkinDate, java.lang.String checkoutDate, java.lang.Integer totalGuest, java.lang.String notes, java.lang.Integer iduser, java.lang.Integer idvilla) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.insertReservation(checkinDate, checkoutDate, totalGuest, notes, iduser, idvilla);
    }

    public static String trackOrderApp(java.lang.Integer idreservation) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.trackOrderApp(idreservation);
    }

    private static String displayReservationAll(java.lang.String kriteria, java.lang.String dicari) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.displayReservationAll(kriteria, dicari);
    }

    public static String changeStatus(java.lang.String status, java.lang.Integer idreservation) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.changeStatus(status, idreservation);
    }

    public static String updateReservation(java.lang.String email, java.lang.Integer totalGuest, java.lang.String notes, java.lang.Integer orderId) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.updateReservation(email, totalGuest, notes, orderId);
    }

    private static String getUserIdBasedOnEmail(java.lang.String email) {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        return port.getUserIdBasedOnEmail(email);
    }
}
