package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.ArquivoBD;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.util.Logger;

/**
 *
 * @author cassio
 */
public class TCPServiceThread extends Thread{
    private Socket s;
    
    public TCPServiceThread(Socket s)
    {
        this.s = s;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }
    
    public static Boolean adicionarVeiculo(String dsPlaca,
                                    Integer idTipo,
                                    Integer vlCapacidade,
                                    String dsUnidade) throws Exception {
        try {
            Veiculo v = new Veiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.incluir(v);
            Logger.logMethod("TCP", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("TCP", e.getMessage());
            throw new Exception("Não foi possivel inserir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean alterarVeiculo(String dsPlaca,
                                         Integer idTipo,
                                         Integer vlCapacidade,
                                         String dsUnidade) throws Exception {
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = new Veiculo(vdao.consultarVeiculo(dsPlaca).getCodigo(), dsPlaca, idTipo, vlCapacidade, dsUnidade);
            
            vdao.alterar(v);
            Logger.logMethod("TCP", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("TCP", e.getMessage());
            throw new Exception("Não foi possivel alterar o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public static Boolean excluirVeiculo(String dsPlaca) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = new Veiculo(vdao.consultarVeiculo(dsPlaca).getCodigo());
            vdao.excluir(v);
            Logger.logMethod("TCP", "");
            return true;
        }  catch (SQLException e) {
            Logger.logMethod("TCP", e.getMessage());
            throw new Exception("Não foi possivel excluir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public static Veiculo consultarVeiculo(String dsPlaca) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = vdao.consultarVeiculo(dsPlaca);
            v.setPosicoes(null);
            Logger.logMethod("TCP", "");
            return v;
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Veiculo> listaTipo(Integer idTipo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            Logger.logMethod("TCP", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
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
            Logger.logMethod("TCP", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Posicao> localizacao(String dsPlaca, Calendar dtLocalizacaoInicio) throws Exception {
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Integer cdVeiculo = vdao.consultarVeiculo(dsPlaca).getCodigo();
            
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
            Logger.logMethod("TCP", "");
            return list;
            
        } catch (Exception ex) {
            Logger.logMethod("TCP",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void run()
    {
        ObjectInputStream inp = null;
        ObjectOutputStream esc = null;
        
        try {
            String oper = "";
            System.out.println("Conexão estabelecida.");

            inp = new ObjectInputStream(s.getInputStream());
            esc = new ObjectOutputStream(s.getOutputStream());
            
            while (true)
            {
                Object obj = inp.readObject();
                ArquivoBD arquivo = (ArquivoBD) obj;

                Veiculo v = (Veiculo) arquivo.getObjetos().get(0);

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
                            alterarVeiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
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
                            excluirVeiculo(dsPlaca);
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
                            v = consultarVeiculo(dsPlaca);
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
                        try
                        {
                            for (Posicao pos : localizacao(dsPlaca, dtLocalizacaoInicio))
                            {
                                listRetorno.add((Object)pos);
                            }
                            
                            arquivo.setObjetos(listRetorno);
                            arquivo.setRetorno("Posições consultadas com sucesso.");
                            arquivo.setCode(0);
                        } catch (Exception ex) {
                            arquivo.setRetorno(ex.getMessage());
                            arquivo.setCode(1);
                        }
                        break;
                    case 7:
                        return;
                }
                
                esc.writeObject(arquivo);
                esc.flush();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                esc.close();
                return;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
         
    }
}
