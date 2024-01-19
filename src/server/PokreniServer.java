/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class PokreniServer extends Thread{
    
    Socket socket;
    ServerSocket ssocket;
    ObradaKlijentskihZahteva okz;
    private ArrayList<ObradaKlijentskihZahteva> clients = new ArrayList<>();
    private boolean kraj=false;

    @Override
    public void run() {

        try {
            ssocket=new ServerSocket(9000);
            System.out.println("Server je pokrenut....");
            
            while(!kraj && !isInterrupted()){
                socket=ssocket.accept();
                System.out.println("Socket klijent se povezao.");
                okz=new ObradaKlijentskihZahteva(socket);
                clients.add(okz);
                okz.start();
                
                
                
                
            }
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    




    }
    
    
    
    public void zaustavi(){
        interrupt();
        kraj=true;
        System.out.println("Server je ugasen!");
    }

    public ArrayList<ObradaKlijentskihZahteva> getClients() {
        return clients;
    }

    public void setClients(ArrayList<ObradaKlijentskihZahteva> clients) {
        this.clients = clients;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
