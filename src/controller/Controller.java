/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Admin;
import model.Poruka;
import model.User;

/**
 *
 * @author Acer
 */
public class Controller {
    private static Controller instance;
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private List<Poruka> poruke = new ArrayList<>();
    private int kapacitet=0;
    //ZOKA JE OVO DODAO
    private ArrayList<User> ulogovani = new ArrayList<>();
    
    private Controller(){
        
        Admin a1=new Admin("mika@gmail.com", "mika1", "Mika", "Mahovac");
        admins.add(a1);
        
        
        User u1 = new User("ana123", "123456");
        User u2 = new User("biba123", "123456");
        User u3=new User("mira123", "123456");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        
        Poruka p = new Poruka(u1, u2, "Caos !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", new Date());
        
        poruke.add(p);
    }
    
    
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        
        
        return instance;
    }

    public Admin loginAdmin(String username, String passwd) {


        for(Admin ad:admins){
            if(ad.getGmail().equals(username) && ad.getPassword().equals(passwd))
               
                return ad;
        }
        
return null;

    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User loginUser(User user) {

        for(User u : users){
            if(u.getUsername().equals(user.getUsername())    &&    u.getPassword().equals(user.getPassword()) && !ulogovani.contains(u)  && ulogovani.size()<kapacitet){
                 ulogovani.add(u);
                return u;
            }
        }



        return null;





    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public ArrayList<User> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(ArrayList<User> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public boolean posaljiSvimUlogovanim(Poruka poruka) {


        for(User u:ulogovani){
            poruka.setPrimalac(u);
            poruke.add(poruka);
        }


        return true;

    }

    public boolean posaljiJednom(Poruka poruka) {

        poruke.add(poruka);

        return true;

    }

    public List<Poruka> nadjiPorPrimaoca(User user) {

        List<Poruka> porukeFilter=new ArrayList<>();
        
        for(Poruka p : poruke){
            if(p.getPrimalac().equals(user)){
                porukeFilter.add(p);
            }
        }

        

        return porukeFilter;



    }

    public List<Poruka> vratiPorukeUsera(User user) {

        List<Poruka> poruke = new ArrayList<>();
        
        for(Poruka p:poruke){
            if(p.getPosiljalac().equals(user))
                poruke.add(p);
        }

        


        return poruke;

    }
    
    
    
    
    
    
}
