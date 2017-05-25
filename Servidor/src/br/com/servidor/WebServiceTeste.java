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
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
            System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
            System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dumpTreshold", "999999");
            URL url = new URL("http://127.0.0.1:9876/Servidor/WebServiceSoap?wsdl");
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
//            System.out.println(soap.excluirVeiculo(5));
//            System.out.println(soap.localizacao(5, null));
            for (Posicao p : soap.localizacao(5, null)) {
                System.out.println(p.getDatahora().getTime());
            }

        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
