/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOTO
 */
public class HandleSocket extends Thread{
    FormDashboard parent;
    Socket client;
    DataOutputStream output;
    BufferedReader input;
    String email,displayName,idUser;
    Boolean chatWithBot;
    Integer botStep;
    
    
    public HandleSocket(FormDashboard _parent, Socket _client)
    {
        try {
            this.parent = _parent;
            this.client = _client;
            
            output = new DataOutputStream(client.getOutputStream());
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SendChat(String msg)
    {
        try {
            output.writeBytes(msg + "\n");
        } catch (IOException ex) {
            Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                String tmp = input.readLine();
                System.out.println("58 ReadLine : " + tmp);
                if(tmp.contains("JOIN"))
                {
                    chatWithBot = true;
                    botStep = 0;
                    
                     
                     parent.clientsArr.add(this);
                     parent.AddComboBoxClient(displayName, email);
                    
                    String msgWelcome = "Selamat datang di hibitsu\n"
                            + "Jika ingin konsultasi langsung dengan Admin,\ndapat menekan chat/audio/video call di atas,\n"
                            + "Jika ingin berbicara dengan saya(BotChat),\ndapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n" 
                            + "1. Reservasi\n" 
                            + "2. Track Order Booking\n";
                    String historyChat =  parent.TampilChat(email, parent.emailAdmin,"client");
                    
                    msgWelcome += "EndFromBot";
                    
                    SendChat(historyChat + "\n;;DONE;;");
                    
                    SendChat(msgWelcome);
                   
                     parent.SimpanChat(parent.emailAdmin,email, msgWelcome);
                    
                                  
                    
                    String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    String thisClient = email ;
                    
                    System.out.println("92");
                    if(selectedComboBox.contains(thisClient))
                    {
                        System.out.println("95");
                        parent.textArea.append("BotChat :\n" + msgWelcome + "\n");
                    }

                               
                   
                                      
                }
               
                else if(tmp.contains("LOGIN"))
                {
                    String[] login = tmp.split(";;");
                    String loginEmail = login[1];
                    String loginPassword = login[2];
                   
                    
                    String status = parent.loginClient(loginEmail, loginPassword);
                    
                    //status = arr[1];
                    
                    if(status.contains("true"))
                    {
                        String[] arr = status.split(";;");
                        displayName = arr[4];
                        email = loginEmail;
                        idUser = arr[2];
                    }
                    
                   
                    output.writeBytes(status+"\n");
                    
                    
                    
                }
                else if(tmp.contains("LOGOUT"))
                {
                    parent.RemoveComboBoxClient(displayName, email);
                }
                else if(chatWithBot == true)
                {
                    String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    
                    String thisClient = email ;
                    
                    String[] arr = tmp.split(";;");
                    //parent.SimpanChat(email, parent.emailAdmin, arr[1]);
                    String warning = "";
                    
                    if(botStep == 0) 
                    {
                        try
                        {
                            botStep = 1;
                            Integer hasil = Integer.parseInt(arr[1]);
                            
                            if(hasil == 1)
                            {
                                
                            }
                            else
                            {
                                
                            }
                            
                        }
                        catch(Exception e)
                        {
                            warning = "Harap memberi input dalam bentuk angka saja sesuai opsi.";
                        }
                        
                    }
                    
                    //System.out.println("Selected Combo Box : " + selectedComboBox + "\n email Client ini : " + thisClient + "\n Hasil : " + selectedComboBox.contains(thisClient));
                    
                    if(selectedComboBox.contains(thisClient))
                    {
                        System.out.println("142");
                        parent.ShowChat(tmp);
                    }
                }
                else
                {
                    
                    
                    String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    
                    String thisClient = email ;
                    
                    String[] arr = tmp.split(";;");
                    parent.SimpanChat(email, parent.emailAdmin, arr[1]);
                    
                    //System.out.println("Selected Combo Box : " + selectedComboBox + "\n email Client ini : " + thisClient + "\n Hasil : " + selectedComboBox.contains(thisClient));
                    
                    if(selectedComboBox.contains(thisClient))
                    {
                        System.out.println("142");
                        parent.ShowChat(tmp);
                    }
                    
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

    