/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Rubens Rangel
 */
public class UDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception{
        // TODO code application logic here
        int porta = 2006;
        byte buffercliente[] = new byte[100];
        InetAddress address;
        
        String mensagem = new String("Grêmio Campeão");
        System.out.println("Cliente Executando");
        //pegando o endereço ip do host
        String host = new String("localhost");
        address = InetAddress.getByName(host);
        //serialização
        buffercliente = mensagem.getBytes();
        
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket pacote = new DatagramPacket(buffercliente, buffercliente.length, address, porta);
        
        socket.send(pacote);
        int porta_cliente = socket.getLocalPort();
        System.out.println("Cliente Enviou Mensagem pela Porta :"+porta_cliente);
        socket.close();
        
        // socket de retorno da mensagem
        byte buffer_retorno[] = new byte[100];
        DatagramSocket soc_retorno = new DatagramSocket(porta_cliente);
        DatagramPacket pacote_retorno = new DatagramPacket(buffer_retorno,buffer_retorno.length);
        System.out.println("Cliente Aguardando Retorno do Servidor");
        soc_retorno.receive(pacote_retorno);
        
        System.out.println("Mensagem de retorno do Servidor : "+ new String(pacote_retorno.getData()));
        System.out.println("Fim da comunicação de retorno");
        soc_retorno.close();
             
    }
    
}
