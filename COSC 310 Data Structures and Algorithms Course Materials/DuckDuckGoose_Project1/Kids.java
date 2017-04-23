/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AustriaCatherine;

/**
 *
 * @author Catherine Austria
 */
public class Kids <E> {
    public String status;
    public String name;
    
    public void setKid(String kidName,String kidStatus){
        status=kidStatus;
        name=kidName;
    }
    public void setStatus(String stat){
        this.status=stat;
    }
    public String getName(){
        return name;
    }
    public String getStatus(){
        return status;
    }
}
