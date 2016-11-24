/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kouta
 */
public class HistoryData {
    private final ArrayList<String> codeList;
    private final ArrayList<Integer> typeList;
    private final ArrayList<Date> buyDateList;
    
    public HistoryData(){
        this.codeList = new ArrayList();
        this.typeList = new ArrayList();
        this.buyDateList = new ArrayList();
    }
    
    public String getItemCode(int i){
        return this.codeList.get(i);
    }
    public void addItemCode(String s){
        this.codeList.add(s);
    }
    
    public int getType(int i){
        return this.typeList.get(i);
    }
    public void addType(int i){
        this.typeList.add(i);
    }
    
    public Date getBuyDate(int i){
        return this.buyDateList.get(i);
    }
    public void addBuyDate(Date d){
        this.buyDateList.add(d);
    }
    
    public int getSize(){
        return this.codeList.size();
    }
}
