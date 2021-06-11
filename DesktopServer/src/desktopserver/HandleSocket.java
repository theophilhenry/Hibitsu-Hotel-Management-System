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
                if(tmp.contains("JOIN"))
                {
                   /* chatWithBot = true;
                    SendChat("Selamat datang di hibitsu Bpk/Ibu " + displayName + "("+email + ") \n "
                            + "Apabila anda ingin konsultasi secara langsung dengan Admin, anda dapat menekan tombol audio/video call di atas,\n" 
                            + "Apabila anda ingin berbincang-bincang dengan saya(bot), anda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n" 
                            + "1. Reservasi\n" 
                            + "2. Check Booking / Track Order\n"
                            + "3. Konsultasi Chat dengan Admin\n");
                    */
                    
                    
                    
                    SendChat("Halo, apa yang bisa dibantu?");

                    
                   /* String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    String thisClient = email ;
                    if(selectedComboBox.contains(thisClient))
                    {
                        parent.ShowChat(tmp);
                    }
*/
                               
                    parent.clientsArr.add(this);
                    parent.AddComboBoxClient(displayName, email);
                                      
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
                else
                {
                    String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    
                    String thisClient = email ;
                    
                    if(selectedComboBox.contains(thisClient))
                    {
                        parent.ShowChat(tmp);
                    }
                    
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

    