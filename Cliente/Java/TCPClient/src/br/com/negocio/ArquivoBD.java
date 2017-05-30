/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocio;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author leonardo.rocha
 */
public class ArquivoBD implements Serializable {
    
    // 1 - ADICIONAR | 2 - EDITAR | 3 - EXCLUIR | 4 - CONSULTAR
    int ope;
    // 1 - VEICULO | 2 - LOCALIZAÇÃO
    int tipobj;
    // LISTA DE OBJETOS DO TIPO SELECIONADO
    List<Object> objetos;
    // MENSAGEM DE RETORNO
    String retorno;
    
    public int getTipobj() {
        return tipobj;
    }

    public void setTipobj(int tipobj) {
        this.tipobj = tipobj;
    }

    public List<Object> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Object> objetos) {
        this.objetos = objetos;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public int getOpe() {
        return ope;
    }

    public void setOpe(int ope) {
        this.ope = ope;
    }


    public ArquivoBD() {
    }

    public ArquivoBD(int ope) {
        this.ope = ope;
    }

    public ArquivoBD(int ope, int tipobj, List<Object> objetos) {
        this.ope = ope;
        this.tipobj = tipobj;
        this.objetos = objetos;
    }   
    
}
