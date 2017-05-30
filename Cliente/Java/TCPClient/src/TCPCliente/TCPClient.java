package TCPCliente;

import br.com.negocio.Veiculo;
import br.com.negocio.ArquivoBD;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPClient {

    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        int porta = 2006;
        byte buffer[] = new byte[100];
        InetAddress address;
        String host = "localhost";
        String msg = "Hello World Distributed System";
        try ( //ServerSocket soc = new ServerSocket(2005);
                Socket s = new Socket("127.0.0.1", porta)) {
            Veiculo a = new Veiculo("TESTE C", 1, 1, "AA");
            List<Object> l = new ArrayList<Object>();
            l.add(a);
            l.add(new Veiculo("LEO123",1,1,"AA"));
            ArquivoBD b = new ArquivoBD(4,1,l);
            ObjectOutputStream esc = new ObjectOutputStream(s.getOutputStream());
            esc.writeObject(b);
            esc.flush();
        }
        System.out.println("Objeto enviado.");
    }    
}
