/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Poruka;
import model.User;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class ObradaKlijentskihZahteva extends Thread{

    Socket socket;
    private boolean kraj=false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void run() {
        
        while(!kraj && !isInterrupted()){
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()){
                
                case Operacije.LOGIN_USER:
                    User u = Controller.getInstance().loginUser((User)kz.getParam());
                    so.setOdgovor(u);
                
                break;
                
                case Operacije.SEND_TO_ALL:
                 boolean odg =    Controller.getInstance().posaljiSvimUlogovanim((Poruka)kz.getParam());
                so.setOdgovor(odg);
                break;
                
                case Operacije.VRATI_ULOGOVANE:
                    ArrayList<User> ulogovani = Controller.getInstance().getUlogovani();
                   so.setOdgovor(ulogovani);
                break;
                
                case Operacije.POSALJI_JEDNOM:
                  boolean odg1  =   Controller.getInstance().posaljiJednom((Poruka)kz.getParam());
                  so.setOdgovor(odg1);
                break;
                
                case Operacije.NADJI_PORUKE_PRIMAOCA:
                 List<Poruka> poruke = Controller.getInstance().nadjiPorPrimaoca((User)kz.getParam());
                so.setOdgovor(poruke);
                break;
                
                
                case Operacije.VRATI_PORUKE_USRA:
                    List<Poruka> poruke1 = Controller.getInstance().vratiPorukeUsera((User)kz.getParam());
                    so.setOdgovor(poruke1);
                 break;
                
                
                
            }
            
            posaljiOdgovor(so);
            
            
        }
        
        
        
        
    }

    private KlijentskiZahtev primiZahtev() {


        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        



    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                
                
                }
    
    
    
    
    
    public void zaustavi(){
        interrupt();
        kraj=true;
        System.out.println("Klijent se otkacio.");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
