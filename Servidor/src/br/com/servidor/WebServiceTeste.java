package br.com.servidor;

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
            URL url = new URL("http://127.0.0.1:9876/Servidor/WebServiceSoap?wsdl");
            QName qname = new QName("http://servidor.com.br/",
                                    "WebServiceSoapService");
            Service ws = Service.create(url, qname);

            WebServiceSoapInterface soap = ws.getPort(WebServiceSoapInterface.class);
        
            System.out.println(soap.adicionarVeiculo("AAA-1111", 0, 1000, "Tonelada"));
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
