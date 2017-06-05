/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor.rest;

import com.google.gson.Gson;

/**
 *
 * @author Daniel
 */
public class MsgReturn {
    
    private String ret;
    private String msg;
    private Object objeto;

    public MsgReturn(String ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public MsgReturn(String ret, String msg, Object objeto) {
        this.ret = ret;
        this.msg = msg;
        this.objeto = objeto;
    }
    
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    public static String retorna(String ret, String msg, Object obj) {
        
        MsgReturn f = new MsgReturn(ret, msg, obj);
     
        String retorno = new Gson().toJson(f);
        
         return retorno;
    }
    
    
    public static String retorna(String ret, String msg) {
        
        MsgReturn f = new MsgReturn(ret, msg);
        
        String retorno = new Gson().toJson(f);
        
        return retorno;
    }
}
