package br.com.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class TCPService {
    
    public static void main(String[] args){

        try {
            int porta = 2010;
            
            System.out.println("Servidor Executando");
            
            ServerSocket soc = new ServerSocket(porta);
            
            while (true) {
                Socket s = soc.accept();
                TCPServiceThread t = new TCPServiceThread(s);
                t.start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}