/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cassio
 */
public class UDPClientSimuladorThread extends Thread {
    private String dsPlaca;
    
    private List<Posicao> json;
    
    private Integer tempo;
    
    private String host;

    public UDPClientSimuladorThread(String dsPlaca, List<Posicao> json, Integer tempo, String host) {
        this.dsPlaca = dsPlaca;
        this.json = json;
        this.tempo = tempo;
        this.host = host;
    }
    
    public void run()
    {
        try {
            Integer porta = 2006;
            InetAddress address;

            address = InetAddress.getByName(this.host);
            byte buffer[] = new byte[100];
            
            for (Posicao p : this.json)
            {
                Thread.sleep(this.tempo * 1000);
                
                String msg = new String(this.dsPlaca+":"+ p.getLat()+":"+p.getLng() + ":");
                DatagramSocket soc = new DatagramSocket();
                buffer = msg.getBytes();
                DatagramPacket pct = new DatagramPacket(buffer, buffer.length, address, porta);
                soc.send(pct);
                soc.close();
            }
            
            Thread.currentThread().interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
