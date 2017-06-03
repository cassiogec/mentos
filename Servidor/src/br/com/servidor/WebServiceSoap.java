package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import br.com.util.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "br.com.servidor.WebServiceSoapInterface")
public class WebServiceSoap implements WebServiceSoapInterface{
    
    /**
     *
     * @param dsPlaca
     * @param idTipo
     * @param vlCapacidade
     * @param dsUnidade
     * @return
     */
    @Override
    public Boolean adicionarVeiculo(String dsPlaca,
                                    Integer idTipo,
                                    Integer vlCapacidade,
                                    String dsUnidade) throws Exception {
        try {
            Veiculo v = new Veiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.incluir(v);            
            Logger.logMethod("Soap", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("Soap", e.getMessage());
            throw new Exception("Não foi possivel inserir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Soap",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     *
     * @param cdVeiculo
     * @param dsPlaca
     * @param idTipo
     * @param vlCapacidade
     * @param dsUnidade
     * @return
     */
    @Override
    public Boolean alterarVeiculo(Integer cdVeiculo,
                                  String dsPlaca,
                                  Integer idTipo,
                                  Integer vlCapacidade,
                                  String dsUnidade) throws Exception {
        try {
            Veiculo v = new Veiculo(cdVeiculo, dsPlaca, idTipo, vlCapacidade, dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.alterar(v);            
            Logger.logMethod("Soap", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("Soap", e.getMessage());
            throw new Exception("Não foi possivel alterar o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Soap", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     *
     * @param cdVeiculo
     * @return
     */
    @Override
    public Boolean excluirVeiculo(Integer cdVeiculo) throws Exception{
        try {
            Veiculo v = new Veiculo(cdVeiculo);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.excluir(v);
            Logger.logMethod("Soap", "");
            return true;
        }  catch (SQLException e) {
            Logger.logMethod("Soap", e.getMessage());
            throw new Exception("Não foi possivel excluir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Soap", ex.getMessage());
            throw new Exception(ex.getMessage());
        } 
    }

    /**
     *
     * @param cdVeiculo
     * @return
     * @throws Exception
     */
    @Override
    public Veiculo consultarVeiculo(Integer cdVeiculo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = vdao.consultarVeiculo(cdVeiculo);
            v.setPosicoes(null);
            Logger.logMethod("Soap", "");
            return v;
        } catch (Exception ex) {
            Logger.logMethod("Soap",  ex.getMessage());
            throw new Exception("Não foi possivel consultar o veículo");
        } 
    }
    
    /**
     *
     * @param dsPlaca
     * @return
     * @throws Exception
     */
    @Override
    public Veiculo consultarVeiculoPlaca(String dsPlaca) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo v = vdao.consultarVeiculo(dsPlaca);
            v.setPosicoes(null);
            return v;
        } catch (Exception ex) {
            throw new Exception("Não foi possivel consultar o veículo");
        }
    }
    
    /**
     *
     * @param idTipo
     * @return
     * @throws Exception
     */
    @Override
    public List<Veiculo> listaTipo(Integer idTipo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);

            for (Veiculo v : list)
            {
                v.setPosicoes(null);
            }
            
            Logger.logMethod("Soap", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("Soap",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Veiculo> listaTodosVeiculos() throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculos();

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            
            Logger.logMethod("Soap", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("Soap",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     *
     * @param cdVeiculo
     * @param dtLocalizacaoInicio
     * @param dtLocalizacaoFim
     * @return
     * @throws Exception
     */
    @Override
    public List<Posicao> localizacao(Integer cdVeiculo, Calendar dtLocalizacaoInicio) throws Exception {
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
            
            Logger.logMethod("Soap", "");
            return list;
            
        } catch (Exception ex) {
            Logger.logMethod("Soap",  ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}
