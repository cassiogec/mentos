/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
=======
>>>>>>> 1307b2900d942f903d7c4f5b8e16254a7732dc75
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class UDPService extends Thread{
    
<<<<<<< HEAD
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
                
                System.out.println("Servidor Recebeu a Menssagem :"+ new String(pacote.getData())+" Da Porta "+pacote.getPort());
                
                String msg_recebida = new String(pacote.getData());
                
                String[] vetor_msg_recebida = msg_recebida.split(":");
                
                System.out.println(vetor_msg_recebida[1]);
                
                Veiculo vei = new Veiculo();
                Posicao pos = new Posicao();
                if(vetor_msg_recebida[0].equals("v")){
                    System.out.println("entrei no if do veiculo");
                    vei = montaObjVeiculo(vetor_msg_recebida);
                }
                if(vetor_msg_recebida[0].equals("p")){
                    System.out.println("entrei no if da posicao");
                    //CONSULTA AO BANCO PARA VER SE O CODIGO DO VEICULO EXISTE
                    if( new VeiculoDAO().verificacodigo(Integer.parseInt(vetor_msg_recebida[2]))){
                       vei = new VeiculoDAO().consultarVeiculo(Integer.parseInt(vetor_msg_recebida[2]));
                       pos = montaObjPosicao(vei,vetor_msg_recebida);
                    }
                    else{
                        System.out.println("Veiculo não está inserido no banco, objeto posição negado");
                    }
                    
                }
                else{
                    System.out.println("Estrutura de Dados desconhecida ao desempacotar a mensagem");
                }
                
                

                //SIMULANDO AS FUNÇÕES DO BD, APÓS CHEGADO DO PACOTE

//                Veiculo vei = new Veiculo();     
//                VeiculoDAO veidao = new VeiculoDAO();
//                
//                //ADICIONA
//                Boolean cond_adicionar = veidao.verificacodigo(vei.getCodigo());
//                if(cond_adicionar == false){
//                    try {
//                        veidao.incluir(vei);
//                    } catch (Exception ex) {
//                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                else{
//                    System.out.println("retonar mensagem de erro, pois veículo já exite no banco");
//                }
//                
//                //ALTERAR
//                Boolean cond_alterar = veidao.verificacodigo(vei.getCodigo());
//                if(cond_alterar == true){
//                    try {
//                        veidao.alterar(vei);
//                    } catch (Exception ex) {
//                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                else{
//                    System.out.println("retornar a informação de que o veiculo não existe no banco");
//                }
//                
//                //EXCLUIR
//                Boolean cond_excluir = veidao.verificacodigo(vei.getCodigo());
//                if(cond_excluir == true){
//                    try {
//                        veidao.excluir(vei);
//                    } catch (Exception ex) {
//                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                else{                 
//                    System.out.println("retornar a informação de que o veiculo não existe no banco");
//                }
//                
//                //CONSULTAR
//                Boolean cond_consultar = veidao.verificacodigo(vei.getCodigo());
//                if(cond_consultar == true){
//                    try {
//                        veidao.consultarVeiculo(vei.getCodigo());
//                    } catch (Exception ex) {
//                        Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                else{
//                    System.out.println("retornar a informação de que o veiculo não existe no banco");
//                }
//                
//                //LISTA TIPO
//                int tipo = 0;
//                List<Veiculo> veilist = veidao.consultarVeiculosPorTipo(tipo);
//                
//                //LOCALIZAÇÃO
//                
//                
//                
//                
//               
//                   
//               
                

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

                int tentativas_entrega = 10;
                int controle = 0;
                while(true){
                    if(controle < tentativas_entrega){
                        try {
                            sock.send(pack);
                            //System.out.println("sock enviou "+controle+" vezes");
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

    private Veiculo montaObjVeiculo(String[] msg_recebida) {
        Veiculo vei_aux = new Veiculo();
        vei_aux.setCodigo(Integer.parseInt(msg_recebida[2]));
        vei_aux.setPlaca(msg_recebida[3]);
        vei_aux.setTipo(Integer.parseInt(msg_recebida[4]));
        vei_aux.setCapacidade(Integer.parseInt(msg_recebida[5]));
        vei_aux.setUncapac(msg_recebida[6]);
        return vei_aux;   
    }

    private Posicao montaObjPosicao(Veiculo vei, String[] msg_recebida) {
        Posicao pos_aux = new Posicao();
        pos_aux.setCodigo(vei.getCodigo());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
        Calendar car = Calendar.getInstance();
        try {
            car.getInstance().setTime(sdf.parse(msg_recebida[2]));
        } catch (ParseException ex) {
            Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
=======
        public void run(){
        
        System.out.println("Servidor Funcionando");
        int porta = 2006;
        byte buffer[] = new byte[100];
        
        while(true){
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
            System.out.println("Servidor Recebeu a Menssagem :"+ new String(pacote.getData())+" Da Porta "+pacote.getPort());
            soc.close();
>>>>>>> 1307b2900d942f903d7c4f5b8e16254a7732dc75
        }
        pos_aux.setDatahora(car);
        pos_aux.setLatitude(Float.parseFloat(msg_recebida[3]));
        pos_aux.setLongitude(Float.parseFloat(msg_recebida[4]));
        return pos_aux;

    }
    
}
