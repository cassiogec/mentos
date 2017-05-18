package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
            return v;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
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
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }


    public List<Posicao> localizacao(Integer cdVeiculo, Calendar dtLocalizacao) throws Exception {
        try {
            PosicaoDAO pdao = new PosicaoDAO();
            
            if (dtLocalizacao == null)
            {
                return pdao.consultarPosicoesCarro(cdVeiculo);
            }
            else
            {
                Posicao p = pdao.consultarPosicao(cdVeiculo, dtLocalizacao);
                List<Posicao> list = new ArrayList<Posicao>();
                
                list.add(p);
                
                return list; 
            }
            
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }
}
