/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class UDPService extends Thread{
    
        public void run(){
        
        System.out.println("Servidor Funcionando");
        int porta = 2006;
        byte buffer[] = new byte[100];
        
        while(true){
            DatagramSocket soc = null;
            try {
                soc = new DatagramSocket(porta);
            } catch (SocketException ex) {
                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            }
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
            try {
                System.out.println("Servidor Aguardando Pacote");
                soc.receive(pacote);
            } catch (IOException ex) {
                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            }
            //pacote.getData() desserializa o vetor de bytes
            System.out.println("Servidor Recebeu a Menssagem :"+ new String(pacote.getData())+" Da Porta "+pacote.getPort());
            soc.close();
        }
    }
    
}
