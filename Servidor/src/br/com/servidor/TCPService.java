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
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class TCPService {
    
//    public static ArquivoBD comunicar (Socket s, ArquivoBD a) throws Exception
//    {
//        ObjectOutputStream esc = new ObjectOutputStream(s.getOutputStream());
//        esc.writeObject(a);
//        esc.flush();
//    }

    public static Boolean adicionarVeiculo(String dsPlaca,
                                    Integer idTipo,
                                    Integer vlCapacidade,
                                    String dsUnidade) throws Exception {
        try {
            Veiculo v = new Veiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.incluir(v);
            return true;
        } catch (SQLException e) {
            throw new Exception("Não foi possivel inserir o veículo.");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean alterarVeiculo(Integer cdVeiculo,
                                  String dsPlaca,
                                  Integer idTipo,
                                  Integer vlCapacidade,
                                  String dsUnidade) throws Exception {
        try {
            Veiculo v = new Veiculo(cdVeiculo, dsPlaca, idTipo, vlCapacidade, dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.alterar(v);
            return true;
        } catch (SQLException e) {
            throw new Exception("Não foi possivel alterar o veículo.");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static Boolean excluirVeiculo(Integer cdVeiculo) throws Exception{
        try {
            Veiculo v = new Veiculo(cdVeiculo);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.excluir(v);
            return true;
        }  catch (SQLException e) {
            throw new Exception("Não foi possivel excluir o veículo.");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static Veiculo consultarVeiculo(Integer cdVeiculo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = vdao.consultarVeiculo(cdVeiculo);
            v.setPosicoes(null);
            return v;
        } catch (Exception ex) {
            throw new Exception("Não foi possivel consultar o veículo");
        }
    }

    public static List<Veiculo> listaTipo(Integer idTipo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            return list;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public static List<Veiculo> listaTodosVeiculos() throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculos();

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            return list;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Posicao> localizacao(Integer cdVeiculo, Calendar dtLocalizacaoInicio) throws Exception {
        try {
            PosicaoDAO pdao = new PosicaoDAO();
            List<Posicao> list = new ArrayList<Posicao>();
                    
            if (dtLocalizacaoInicio == null)
            {
                list = pdao.consultarPosicoesCarro(cdVeiculo);
            }
            else
            {
                list = pdao.consultarPosicao(cdVeiculo, dtLocalizacaoInicio);
            }
            
            for (Posicao p : list) {
                p.setVeiculo(null);
            }
            
            return list;
            
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public static void main(String[] args)throws Exception{

        int porta = 2010;
        String oper = "";
        System.out.println("Servidor Executando");
        
        ServerSocket soc = new ServerSocket(porta);
        
        while (true) {       
            Socket s = soc.accept(); // AGUARDA NOVOS DADOS
            System.out.println("Conexão estabelecida.");
            
            // LÊ O DADO RECEBIDO
            ObjectInputStream esc = new ObjectInputStream(s.getInputStream());
            Object obj = esc.readObject();

            ArquivoBD arquivo = (ArquivoBD) obj;
                
                if(arquivo.getOpe()== 1)
                oper = "Adicionar veículo";
                
                if(arquivo.getOpe()== 2)
                oper = "Alterar Veiculo";
                
                if(arquivo.getOpe()== 3)
                oper = "Excluir Veiculo";
                
                if(arquivo.getOpe()== 4)
                oper = "Consultar Veiculo";
               
                if(arquivo.getOpe()== 5)
                oper = "Listar veiculos por tipo";
                
                if(arquivo.getOpe()== 6)
                oper = "Localização do Veiculo";
             
                if(arquivo.getOpe()== 7)
                oper = "O cliente finalizou a conexão";
                
                Veiculo recebido = (Veiculo) arquivo.getObjetos().get(0);
            
                
            if (arquivo.getOpe() >= 1 || arquivo.getOpe() <= 6)
            {
           System.out.println("Dados recebidos para processamento de: " + oper + " :" + recebido.getPlaca());
            }
            
//            if (arquivo.getOpe() == 7)
//            {
//                System.out.println("O cliente encerrou a conexão.");
//            }
                
                    Veiculo v = (Veiculo) arquivo.getObjetos().get(0);
                    Integer cdVeiculo = v.getCodigo();
                    String dsPlaca = v.getPlaca();
                    Integer idTipo = v.getTipo();
                    Integer vlCapacidade = v.getCapacidade();
                    String dsUnidade = v.getUncapac();
                    Calendar dtLocalizacaoInicio = arquivo.getData();
                    List<Object> listRetorno = new ArrayList<Object>();
                    
            switch (arquivo.getOpe())
            {
                case 1: // Adicionar veículo
                    try
                    {
                        adicionarVeiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
                        arquivo.setRetorno("Veiculo inserido com sucesso");
                        arquivo.setCode(0);
                    } catch (Exception ex) {
                        arquivo.setRetorno(ex.getMessage());
                        arquivo.setCode(1);
                    }
                    break;
                    
                case 2: //Alterar veículo
                    
                    try
                    {
                    alterarVeiculo(cdVeiculo, dsPlaca, idTipo, vlCapacidade, dsUnidade);
                    arquivo.setRetorno("Veiculo alterado com sucesso");
                    arquivo.setCode(0);
                    } catch (Exception ex) {
                    arquivo.setRetorno(ex.getMessage());
                    arquivo.setCode(1);
                    }
                    break;
                    
                case 3: // Exclui veículo

                    try
                    {
                    excluirVeiculo(cdVeiculo);
                    arquivo.setRetorno("Veiculo excluído com sucesso");
                    arquivo.setCode(0);
                    } catch (Exception ex) {
                    arquivo.setRetorno(ex.getMessage());
                    arquivo.setCode(1);
                    }
                    break;
                    
                case 4: //Consultar veículo
                    
                    try
                    {
                    v = consultarVeiculo(cdVeiculo);
                    listRetorno.add((Object)v);
                    arquivo.setObjetos(listRetorno);
                    arquivo.setRetorno("Arquivo consultado com sucesso.");
                    arquivo.setCode(0);
                    } catch (Exception ex) {
                    arquivo.setRetorno(ex.getMessage());
                    arquivo.setCode(1);
                    }
                    break;
                
                case 5: // Listar veículo por tipo
                    
                    try
                    {
                        for (Veiculo vei : listaTipo(idTipo))
                        {
                        listRetorno.add((Object)vei);
                        }
                        arquivo.setObjetos(listRetorno);
                        arquivo.setRetorno("Veículos consutaldos com sucesso.");
                        arquivo.setCode(0);
                    } catch (Exception ex) {
                        arquivo.setRetorno(ex.getMessage());
                        arquivo.setCode(1);
                    }
                    break;    
                
                case 6: //localização do veículo
                    localizacao(cdVeiculo, dtLocalizacaoInicio);
                    try
                    {
                        //for (Veiculo vei : listaTipo(idTipo))
                        {
                        //listRetorno.add((Object)vei);
                        }
                        arquivo.setObjetos(listRetorno);
                        arquivo.setRetorno("Posições consultadas com sucesso.");
                        arquivo.setCode(0);
                    } catch (Exception ex) {
                        arquivo.setRetorno(ex.getMessage());
                        arquivo.setCode(1);
                    }
                    break;  
                
            }
            
        }

    }
}