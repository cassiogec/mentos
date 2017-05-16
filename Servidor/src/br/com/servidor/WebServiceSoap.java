package br.com.servidor;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class WebServiceSoap{
    
    @WebMethod
    public Boolean adicionarVeiculo(String dsPlaca,
                                    Integer idTipo, 
                                    Integer vlCapacidade,
                                    String dsUnidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @WebMethod
    public Boolean alterarVeiculo(String dsPlaca, 
                                  Integer idTipo, 
                                  Integer vlCapacidade,
                                  String dsUnidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @WebMethod
    public Boolean excluirVeiculo(String dsPlaca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @WebMethod
    public Boolean consultarVeiculo(String dsPlaca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @WebMethod
    public Boolean listaTipo(Integer idTipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @WebMethod
    public Boolean localizacao(String dsPlaca, Date dtInicial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
