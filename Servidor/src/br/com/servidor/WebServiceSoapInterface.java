package br.com.servidor;

import br.com.negocio.veiculo;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface WebServiceSoapInterface {
    @WebMethod public Boolean adicionarVeiculo(String dsPlaca,
                                               Integer idTipo,
                                               Integer vlCapacidade,
                                               String dsUnidade);

    @WebMethod public Boolean alterarVeiculo(Integer cdVeiculo,
                                             String dsPlaca,
                                             Integer idTipo,
                                             Integer vlCapacidade,
                                             String dsUnidade);

    @WebMethod public Boolean excluirVeiculo(Integer cdVeiculo);

    @WebMethod public veiculo consultarVeiculo(Integer cdVeiculo) throws Exception;
    
    @WebMethod public List<veiculo> listaTipo(Integer idTipo) throws Exception;

    @WebMethod public Integer localizacao();
}