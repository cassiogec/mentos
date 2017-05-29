package br.com.servidor;

import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 *
 * @author cassio
 */
public class WebServiceTeste {

    public static void main(String args[]) {
        try {
            URL url = new URL("http://127.0.0.1:8080/Servidor/WebServiceSoapService?wsdl");
            QName qname = new QName("http://servidor.com.br/",
                                    "WebServiceSoapService");
            Service ws = Service.create(url, qname);
            
            WebServiceSoapInterface soap = ws.getPort(WebServiceSoapInterface.class);
            
//            System.out.println(soap.adicionarVeiculo("AAA1113", 0, 1000, "TESTE"));
//            System.out.println(soap.alterarVeiculo(1, "AAA1111", 1, 666, "TEST2"));
            
//            for (Veiculo v : soap.listaTipo(1)) {
//                System.out.println(v.getCodigo());
//            }
//              System.out.println(soap.listaTipo(1));
            System.out.println(soap.excluirVeiculo(42));
//            System.out.println(soap.localizacao(19, null, null));
//            for (Posicao p : soap.localizacao(5, null, null)) {
//                System.out.println(p.getDatahora().getTime());
//            }

//            for (Veiculo v : soap.listaTodosVeiculos()) {
//                System.out.println(v.getPlaca() + v.getCodigo() + v.getTipo());
//                
//            }
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
