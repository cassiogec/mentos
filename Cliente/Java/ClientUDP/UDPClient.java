/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientUDP;

import static java.lang.Thread.sleep;
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
        
        MensagemVeiculo mv = new MensagemVeiculo("v", "adiciona", "AAA0001", "1", "503", "test3");
        MensagemPosicao mp = new MensagemPosicao("p", "consulta", "AAA0001","10/10/2016", "50.5", "49.5");
        

        String mensagem_veiculo = new String(mv.getTipomensagem()+":"+mv.getOperacao()+":"+mv.getPlaca()
                                                      +":"+mv.getTipo()+":"+mv.getCapacidade()+":"+mv.getUncapacidade()+":");

        String mensagem_posicao = new String(mp.getTipomensagem()+":"+mp.getOperacao()+":"+mp.getPlaca()+":"+mp.getDatahora()+
                                                                                ":"+mp.getLatitude()+":"+mp.getLongitude()+":");
                
        int porta = 2006;
        byte buffercliente[] = {100};
        InetAddress address;
        
        System.out.println("Cliente Executando");
        
        //pegando o endereço ip do host
        String host = new String("localhost");
        address = InetAddress.getByName(host);
        //serialização
        buffercliente = mensagem_posicao.getBytes();
        
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
        sleep(1);
        soc_retorno.receive(pacote_retorno);
        
        System.out.println("Mensagem de retorno do Servidor : "+ new String(pacote_retorno.getData()));
        System.out.println("Fim da comunicação de retorno");
        soc_retorno.close();
             
    }
    
}
