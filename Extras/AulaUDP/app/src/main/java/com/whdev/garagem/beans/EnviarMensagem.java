package com.whdev.garagem.beans;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static com.whdev.garagem.activits.LoginActivity.pref;

/**
 * Created by Lucas Altmann on 30/05/2017.
 */

public class EnviarMensagem implements Runnable {

    private String mensagem;

    public EnviarMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        try {
            int PORTA_SERVIDOR = 2006;
            DatagramSocket s = new DatagramSocket(2007);
            InetAddress ADDRESS = InetAddress.getByName(pref.getString("ip",null));
            byte[] message = this.mensagem.getBytes();
            DatagramPacket p = new DatagramPacket(message, this.mensagem.length(), ADDRESS, PORTA_SERVIDOR);
            s.send(p);
            s.close();
        } catch (Exception e) {
            Log.d("EXCEPTION", e.getMessage());
        }
    }
}