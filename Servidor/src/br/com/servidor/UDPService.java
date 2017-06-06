/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubens Rangel da Silva
 */
public class UDPService extends Thread {

    public void run() {
        int porta = 2006;
        DatagramSocket soc = null;

        try {
            soc = new DatagramSocket(porta);
        } catch (SocketException ex) {
            Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            byte buffer[] = new byte[100];

            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

            try {
                soc.receive(pacote);
            } catch (IOException ex) {
                Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
            }

            String msg_recebida = new String(pacote.getData());

            String[] vetor_msg_recebida = msg_recebida.split(":");

            // AS FUNÇÕES APÓS CHEGADO DO PACOTE
            Veiculo vei = new Veiculo();
            Posicao pos = new Posicao();

            if (!new VeiculoDAO().verificaplaca(vetor_msg_recebida[0])) {
                try {
                    vei = new VeiculoDAO().consultarVeiculo(vetor_msg_recebida[0]);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                pos = montaObjPosicao(vei, vetor_msg_recebida);

                try {
                    new PosicaoDAO().incluir(pos);
                } catch (Exception ex) {
                    Logger.getLogger(UDPService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("Operacao Adiciona Posicao falhou, veiculo nao esta inserido no banco");
            }
        }
    }

    private Posicao montaObjPosicao(Veiculo vei, String[] msg_recebida) {
        Posicao pos_aux = new Posicao();
        pos_aux.setVeiculo(vei);

        Calendar car = Calendar.getInstance();
        car.setTime(new Date());
        pos_aux.setDatahora(car);
        pos_aux.setLatitude(Float.parseFloat(msg_recebida[1]));
        pos_aux.setLongitude(Float.parseFloat(msg_recebida[2]));
        return pos_aux;
    }
}
