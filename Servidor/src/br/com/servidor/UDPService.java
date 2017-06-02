/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;


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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
                
                System.out.println("Servidor Recebeu a Menssagem :"+ new String(pacote.getData())+" Da Porta "+pacote.getPort());
                
                String msg_recebida = new String(pacote.getData());
                
                String[] vetor_msg_recebida = msg_recebida.split(":");
                
                // AS FUNÇÕES APÓS CHEGADO DO PACOTE
                
                Veiculo vei = new Veiculo();
                Posicao pos = new Posicao();
//                //OPERAÇÔES SOBRE VEICULO
//                if(vetor_msg_recebida[0].equals("v")){
//                    System.out.println("entrei no if do veiculo");
//                    //ADICIONA VEICULO
//                    if(vetor_msg_recebida[1].equals("adiciona")){
//                        Boolean cond_adicionar = new VeiculoDAO().verificaplaca(vetor_msg_recebida[2]);
//                        if(cond_adicionar == true){
//                            try {
//                                vei = montaObjVeiculo(vetor_msg_recebida);
//                                new VeiculoDAO().incluir(vei);
//                                System.out.println("Operação Adiciona Veiculo realizada com sucesso");
//                            } catch (Exception ex) {
//                                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        else{
//                            System.out.println("Operação de adicionar falhou, veiculo já está inserido no banco");
//                        }
//                
//                    }
//                    //ALTERAR VEICULO
//                    if(vetor_msg_recebida[1].equals("altera")){
//                        Boolean cond_alterar = new VeiculoDAO().verificaplaca(vetor_msg_recebida[2]);
//                        if(cond_alterar == false){
//                            try {
//                                vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[2]);
//                                vei.setTipo(Integer.parseInt(vetor_msg_recebida[3]));
//                                vei.setCapacidade(Integer.parseInt(vetor_msg_recebida[4]));
//                                vei.setUncapac(vetor_msg_recebida[5]);
//                                new VeiculoDAO().alterar(vei);
//                                System.out.println("Operação Altera Veiculo realizada com sucesso");
//                            } catch (Exception ex) {
//                                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        else{
//                            System.out.println("Operação de Alterar falhou, Veiuclo não está inserido no banco");
//                        }
//                    }
//                    //EXCLUIR VEICULO
//                    if(vetor_msg_recebida[1].equals("excluir")){
//                        Boolean cond_excluir = new VeiculoDAO().verificaplaca(vetor_msg_recebida[2]);
//                        if(cond_excluir == false){
//                            try {
//                                vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[2]);
//                                new VeiculoDAO().excluir(vei);
//                                System.out.println("Operação Exluir Veiculo realizada com sucesso");
//                            } catch (Exception ex) {
//                                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        else{                 
//                            System.out.println("Operação de Excluir falhou, Veiculo não está inserido no banco");
//                        }                        
//                    }
//                    //CONSULTAR VEICULO
//                    if(vetor_msg_recebida[1].equals("consulta")){
//                        Boolean cond_consultar = new VeiculoDAO().verificaplaca(vetor_msg_recebida[2]);
//                        if(cond_consultar == true){
//                            try {
//                                vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[2]);
//                                System.out.println("Operação Consulta Veiculo realizada com sucesso");
//                            } catch (Exception ex) {
//                                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        else{
//                            System.out.println("Operação de Consultar Veiculo falhou, Veiculo não está inserido no banco");
//                        }                        
//                    }
//                    //LISTATIPO DO VEICULO
//                    if(vetor_msg_recebida[1].equals("1")|vetor_msg_recebida[1].equals("2")|vetor_msg_recebida[1].equals("3")
//                        |vetor_msg_recebida[1].equals("4")|vetor_msg_recebida[1].equals("5")|vetor_msg_recebida[1].equals("6")
//                                                        |vetor_msg_recebida[1].equals("7")|vetor_msg_recebida[1].equals("8")){
//                        //IMPLEMENTACAO DA CONSULTA
//                        //List<Veiculo> veilist = veidao.consultarVeiculosPorTipo(tipo);
//                    }
////                    else(vetor_msg_recebida){
////                        System.out.println("passou por todas as Operaçoes sobre Veiculos se achar");
////                    }
//                        
//
//                }
                //OPERAÇÔES SOBRE POSIÇÃO
//                if(vetor_msg_recebida[0].equals("p")){
//                    System.out.println("entrei no if da posicao");
//                    //ADICIONA POSICAO
//                    if(vetor_msg_recebida[1].equals("adiciona")){
//                        //CONSULTA A TABELA POSICAO PARA VER 
                        if(!new VeiculoDAO().verificaplaca(vetor_msg_recebida[0])){
                    try {
                        vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[0]);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                           pos = montaObjPosicao(vei,vetor_msg_recebida);
                            try {
                                new PosicaoDAO().incluir(pos);
                                System.out.println("Operacao Adiciona Posicao realizada com sucesso");
                            } catch (Exception ex) {
                                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{
                            System.out.println("Operação Adiciona Posicao falhou, veiculo nao está inserido no banco");
                        }
//                    }
//                    //LOCALIZAÇÃO
//                    if(vetor_msg_recebida[1].equals("consulta")){
//                        //VERIFICA SE A PLACA ESTA ISERIDA NA TABELA VEICULO
//                        if(!new VeiculoDAO().verificaplaca(vetor_msg_recebida[2])){
//                            vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[2]);
//                            //VERIFICA NA TABELA POSICAO SE HA REGISTROS COM O CODIGO DO VEICULO
//                            if(new PosicaoDAO().verificacodigo(vei.getCodigo())){
//                                List<Posicao> listadeposicoes = new PosicaoDAO().consultarPosicoesCarro(vei.getCodigo());
//                                System.out.println("Operacao Consulta Posicoes realizada com sucesso");
//                            }
//                            else{
//                                System.out.println("Operação Consulta Posicao falhou, veiculo nao esta na tabela Posicao");
//                            }
//                        }
//                        else{
//                            System.out.println("Operação Consulta Posicao falhou, veiculo nao esta na tabela Veiculo");
//                        }
//                    }
//                    if(!vetor_msg_recebida[1].equals("adiciona") && !vetor_msg_recebida[1].equals("consulta")){
//                        System.out.println("Operação sobre Posição desconhecida");
//                    }
//                }
//                if(!vetor_msg_recebida[0].equals("v") && !vetor_msg_recebida[0].equals("p")){
//                    System.out.println("Estrutura de Dados desconhecida ao desempacotar a mensagem");
//                }      

                
                
                
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

//    private Veiculo montaObjVeiculo(String[] msg_recebida) {
//        Veiculo vei_aux = new Veiculo();
//        vei_aux.setPlaca(msg_recebida[2]);
//        vei_aux.setTipo(Integer.parseInt(msg_recebida[3]));
//        vei_aux.setCapacidade(Integer.parseInt(msg_recebida[4]));
//        vei_aux.setUncapac(msg_recebida[5]);
//        return vei_aux;   
//    }

    private Posicao montaObjPosicao(Veiculo vei, String[] msg_recebida) {
        Posicao pos_aux = new Posicao();
        pos_aux.setVeiculo(vei);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
        Calendar car = Calendar.getInstance();
//        try {
//            car.getInstance().setTime(sdf.parse(msg_recebida[3]));
//        } catch (ParseException ex) {
//            Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        car.clear();
        car.setTime(new Date());
        pos_aux.setDatahora(car);
        pos_aux.setLatitude(Float.parseFloat(msg_recebida[1]));
        pos_aux.setLongitude(Float.parseFloat(msg_recebida[2]));
        return pos_aux;
    }
    
}
