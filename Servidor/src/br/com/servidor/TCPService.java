package br.com.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class TCPService {
    
//    public static ArquivoBD comunicar (Socket s, ArquivoBD a) throws Exception
//    {
//        ObjectOutputStream esc = new ObjectOutputStream(s.getOutputStream());
//        esc.writeObject(a);
//        esc.flush();
//    }
    
    public static void main(String[] args){

        try {
            int porta = 2010;
            
            System.out.println("Servidor Executando");
            
            ServerSocket soc = new ServerSocket(porta);
            
            while (true) {
                Socket s = soc.accept(); // AGUARDA NOVOS DADOS
                TCPServiceThread t = new TCPServiceThread(s);
                t.start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}