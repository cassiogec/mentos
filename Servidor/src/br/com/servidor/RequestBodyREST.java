/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author clayt
 */
@XmlRootElement
public class RequestBodyREST {
    
    @XmlElement String dsPlaca;
    @XmlElement Integer idTipo;
    @XmlElement Integer vlCapacidade;
    @XmlElement String dsUnidade;    
    @XmlElement Integer cdVeiculo;
}
