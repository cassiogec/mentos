package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Veiculo;
import br.com.negocio.ArquivoBD;
import br.com.negocio.Posicao;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

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