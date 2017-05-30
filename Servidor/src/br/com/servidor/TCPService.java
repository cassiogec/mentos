package br.com.servidor;

import br.com.DAO.VeiculoDAO;
import br.com.negocio.Veiculo;
import br.com.negocio.ArquivoBD;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
//import br.upf.sd.Controller.VeiculoController;
//import br.upf.sd.Controller.EnviaResposta;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

public class TCPService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception{
        // TODO code application logic here
        int porta = 2006; // DEFINE A PORTA
        byte buffer[] = new byte[100];
        System.out.println("Servidor Executando");
        
        // CRIA SERVIDOR E O EXECUTA
        ServerSocket soc = new ServerSocket(porta);
        
        //EnviaResposta res = new EnviaResposta();
        //VeiculoController veiCon = new VeiculoController();
        while (true) {       
            Socket s = soc.accept(); // AGUARDA NOVOS DADOS
            // LE O DADO RECEBIDO
            ObjectInputStream esc = new ObjectInputStream(s.getInputStream());
            Object a = esc.readObject();
            // ASSUME QUE O DADO RECEBIDO É DO TIPO VEICULO BD
            ArquivoBD b = (ArquivoBD) a;
            
            if (b.getTipobj() == 1)
               // veiCon.verificaOperacao(b);
            
            
            System.out.println(b.getObjetos().size());
            System.out.println(((Veiculo)b.getObjetos().get(0)).getPosicoes().get(0).getVeiculo().getPlaca());
            System.out.println(b.getRetorno());
            
           // ObjectOutputStream esc_out = new ObjectOutputStream(s.getOutputStream());
           // esc_out.writeObject(b);
           // esc_out.flush();
            
           // res.EnviaResposta(s,b);
        }
       
     //   System.out.println(b);

    }
}