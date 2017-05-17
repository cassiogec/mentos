package br.com.servidor;

import br.com.DAO.veiculoDAO;
import br.com.negocio.Veiculo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(endpointInterface = "br.com.servidor.WebServiceSoapInterface")
public class WebServiceSoap implements WebServiceSoapInterface{
    
    public Boolean adicionarVeiculo(String dsPlaca,
                                    Integer idTipo,
                                    Integer vlCapacidade,
                                    String dsUnidade) {
        try {
            Veiculo v = new Veiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
            veiculoDAO vdao = new veiculoDAO();
            vdao.incluir(v);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean alterarVeiculo(Integer cdVeiculo,
                                  String dsPlaca,
                                  Integer idTipo,
                                  Integer vlCapacidade,
                                  String dsUnidade) {
        try {
            Veiculo v = new Veiculo(cdVeiculo, dsPlaca, idTipo, vlCapacidade, dsUnidade);
            veiculoDAO vdao = new veiculoDAO();
            vdao.alterar(v);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean excluirVeiculo(Integer cdVeiculo) {
        try {
            Veiculo v = new Veiculo(cdVeiculo);
            veiculoDAO vdao = new veiculoDAO();
            vdao.excluir(v);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Veiculo consultarVeiculo(Integer cdVeiculo) throws Exception{
        try {
            veiculoDAO vdao = new veiculoDAO();
            Veiculo v = vdao.consultarVeiculo(cdVeiculo);
            return v;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }
    
    public List<Veiculo> listaTipo(Integer idTipo) throws Exception{
        try {
            veiculoDAO vdao = new veiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public Integer localizacao() {
        return 1;
    }
}
