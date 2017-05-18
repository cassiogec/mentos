package br.com.servidor;

import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface WebServiceSoapInterface {
    @WebMethod public Boolean adicionarVeiculo(
            @XmlElement(name="dsPlaca", required=true)      String dsPlaca,
            @XmlElement(name="idTipo", required=true)       Integer idTipo,
            @XmlElement(name="vlCapacidade", required=true) Integer vlCapacidade,
            @XmlElement(name="dsUnidade", required=true)    String dsUnidade) throws Exception;

    @WebMethod public Boolean alterarVeiculo(
            @XmlElement(name="cdVeiculo", required=true)    Integer cdVeiculo,
            @XmlElement(name="dsPlaca", required=true)      String dsPlaca,
            @XmlElement(name="idTipo", required=true)       Integer idTipo,
            @XmlElement(name="vlCapacidade", required=true) Integer vlCapacidade,
            @XmlElement(name="dsUnidade", required=true)    String dsUnidade) throws Exception;

    @WebMethod public Boolean excluirVeiculo(
            @XmlElement(name="cdVeiculo", required=true) Integer cdVeiculo) throws Exception;

    @WebMethod public Veiculo consultarVeiculo(
            @XmlElement(name="cdVeiculo", required=true) Integer cdVeiculo) throws Exception;
    
    @WebMethod public List<Veiculo> listaTipo(
            @XmlElement(name="idTipo", required=true) Integer idTipo) throws Exception;
    
    @WebMethod public List<Posicao> localizacao(
            @XmlElement(name="cdVeiculo", required=true)     Integer cdVeiculo,
            @XmlElement(name="dtLocalizacao")                Calendar dtLocalizacao) throws Exception;
}