
package br.com.servidor;

import javax.xml.ws.Endpoint;

public class WebServiceSoapPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:9876/Servidor/WebServiceSoap",
                         new WebServiceSoap());
    }
    
}
