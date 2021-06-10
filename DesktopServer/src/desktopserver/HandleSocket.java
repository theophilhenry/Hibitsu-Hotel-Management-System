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
    String email,displayName;
    
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
                    SendChat("Hai " + displayName + "("+email + ") \n What can i help you?");
                    parent.clientsArr.add(this);
                    parent.AddComboBoxClient(displayName, email);
                                      
                }
                else if(tmp.contains("LOGIN"))
                {
                    String[] login = tmp.split(";;");
                    String loginEmail = login[1];
                    String loginPassword = login[2];
                    email = loginEmail;
                    
                    String status = parent.LoginUser(loginEmail, loginPassword);
                    String[] arr = status.split(";;");
                    
                    displayName = arr[1];
                    output.writeBytes(status+"\n");
                    
                    
                    
                }
                else
                {
                    String selectedComboBox =  parent.comboBoxClient.getSelectedItem().toString();
                    
                    String thisClient = (parent.comboBoxClient.getItemCount()+1) + "." + displayName + " (" + email + ")";
                    
                    if(selectedComboBox.equals(thisClient))
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

    