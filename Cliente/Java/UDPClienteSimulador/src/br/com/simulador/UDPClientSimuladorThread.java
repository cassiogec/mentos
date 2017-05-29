/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador;

import org.json.JSONObject;
import com.google.gson.Gson;

/**
 *
 * @author cassio
 */
public class UDPClientSimuladorThread extends Thread {
    public String dsPlaca;

    public UDPClientSimuladorThread(String dsPlaca) {
        this.dsPlaca = dsPlaca;
    }
    
    public void run()
    {

    }
}
