/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorUDP;


import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class UDPService extends Thread {
    
        public void run (){
            
            while(true){
                System.out.println("Servidor Funcionando");
                int porta = 2006;
                byte buffer[] = new byte[100];

                System.out.println("vai instancia o socket");
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
                //System.out.println("Servidor Recebeu a Menssagem :"+ new String(pacote.getData())+" Da Porta "+pacote.getPort());

                //SIMULANDO AS FUNÇÕES DO BD, APÓS CHEGADO DO PACOTE

                Veiculo vei = new Veiculo();     
                VeiculoDAO veidao = new VeiculoDAO();
                
                //ADICIONA
                Boolean cond_adicionar = veidao.verificacodigo(vei.getCodigo());
                if(cond_adicionar == false){
                    try {
                        veidao.incluir(vei);
                    } catch (Exception ex) {
                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    System.out.println("retonar mensagem de erro, pois veículo já exite no banco");
                }
                
                //ALTERAR
                Boolean cond_alterar = veidao.verificacodigo(vei.getCodigo());
                if(cond_alterar == true){
                    try {
                        veidao.alterar(vei);
                    } catch (Exception ex) {
                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    System.out.println("retornar a informação de que o veiculo não existe no banco");
                }
                
                //EXCLUIR
                Boolean cond_excluir = veidao.verificacodigo(vei.getCodigo());
                if(cond_excluir == true){
                    try {
                        veidao.excluir(vei);
                    } catch (Exception ex) {
                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{                 
                    System.out.println("retornar a informação de que o veiculo não existe no banco");
                }
                
                //CONSULTAR
                Boolean cond_consultar = veidao.verificacodigo(vei.getCodigo());
                if(cond_consultar == true){
                    try {
                        veidao.consultarVeiculo(vei.getCodigo());
                    } catch (Exception ex) {
                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    System.out.println("retornar a informação de que o veiculo não existe no banco");
                }
                
                //LISTA TIPO
                int tipo = 0;
                List<Veiculo> veilist = veidao.consultarVeiculosPorTipo(tipo);
                
                //LOCALIZAÇÃO
                
                
                
                
               
                   
               
                

                //RETORNANDO A REQUISIÇÃO
                InetAddress end_requisicao = pacote.getAddress();
                int porta_requisicao = pacote.getPort();

                soc.close();

                String msg_retorno = new String(pacote.getData());
                msg_retorno = msg_retorno.toUpperCase();

                buffer = msg_retorno.getBytes();

                DatagramSocket sock = null;

                try {
                    sock = new DatagramSocket();
                } catch (SocketException ex) {
                    Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                }


                DatagramPacket pack = new DatagramPacket(buffer, buffer.length, end_requisicao, porta_requisicao);

                System.out.println("vai durmi");

                int tentativas_entrega = 40;
                int controle = 0;
                while(true){
                    if(controle < tentativas_entrega){
                        try {
                            sock.send(pack);
                            System.out.println("sock enviou "+controle+" vezes");
                            controle++;

                        } catch (IOException ex) {
                            Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                        break;
                }            

        }
    }
    
}
