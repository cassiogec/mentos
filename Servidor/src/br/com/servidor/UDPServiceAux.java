/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class UDPServiceAux extends Thread{
    
    private Integer time;

    public UDPServiceAux(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void run(){
        try {
            sleep(time*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UDPServiceAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
