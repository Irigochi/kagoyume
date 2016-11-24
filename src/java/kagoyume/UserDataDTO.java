/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.sql.Date;

/**
 *
 * @author Kouta
 */
public class UserDataDTO {
    
    private int id;
    private String name;
    private String pass;
    private String mail;
    private String address;
    private int total;
    private Date date;
    private int deletef;
    
    
    public UserDataDTO(){
        this.id = 0;
        total = 0;
        deletef = 0;
        name = "";
    }
    
    
    public int getID(){
        return this.id;
    }
    public void setID(int i){
        this.id = i;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String s){
        this.name = s;
    }
    
    public String getPass(){
        return this.pass;
    }
    public void setPass(String s){
        this.pass = s;
    }
    
    public String getMail(){
        return this.mail;
    }
    public void setMail(String s){
        this.mail = s;
    }
    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String s){
        this.address = s;
    }
    
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int i){
        this.total = i;
    }
    public void addTotal(int i){
        this.total = total + i;
    }
    
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date d){
        this.date = d;
    }
    
    public void deleteUser(){
        this.deletef = 1;
    }
    public void setDeletef(int i){
        this.deletef = i;
    }
    public int getDeletef(){
        return this.deletef;
    }
    
    
    //ユーザーIDをString型で返すメソッド
    public String getStrID(){
        return String.valueOf(this.id);
    }
}
