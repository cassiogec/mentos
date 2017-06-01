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

    public UDPClientSimuladorThread(String dsPlaca, List<Posicao> json, Integer tempo) {
        this.dsPlaca = dsPlaca;
        this.json = json;
        this.tempo = tempo;
    }
    
    public void run()
    {
        try {
            Integer porta = 2006;
            InetAddress address;
            Scanner leitor = new Scanner(System.in);
            System.out.println("Digite o IP de conexão ou deixe em branco para 'localhost': ");
            
            String host = leitor.nextLine();
            
            if (host.equals(""))
                host = "localhost";
            
            address = InetAddress.getByName(host);
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
