package br.com.servidor;

import br.com.DAO.veiculoDAO;
import br.com.negocio.veiculo;
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
            veiculo v = new veiculo(dsPlaca, idTipo, vlCapacidade, dsUnidade);
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
            veiculo v = new veiculo(cdVeiculo, dsPlaca, idTipo, vlCapacidade, dsUnidade);
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
            veiculo v = new veiculo(cdVeiculo);
            veiculoDAO vdao = new veiculoDAO();
            vdao.excluir(v);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public veiculo consultarVeiculo(Integer cdVeiculo) throws Exception{
        try {
            veiculoDAO vdao = new veiculoDAO();
            veiculo v = vdao.consultarVeiculo(cdVeiculo);
            return v;
        } catch (Exception ex) {
            Logger.getLogger(WebServiceSoap.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }
    
    public List<veiculo> listaTipo(Integer idTipo) throws Exception{
        try {
            veiculoDAO vdao = new veiculoDAO();
            List<veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);
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
