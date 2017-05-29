/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador;

import java.util.Scanner;

/**
 *
 * @author cassio
 */
public class UDPClienteSimulador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Digite a quantidade de veiculos: ");
        Integer veiculos = Integer.parseInt(leitor.nextLine());
        
        for (int i = 0; i < veiculos; i++)
        {
            System.out.println("Digite a placado veiculo sem formatação: ");
            String placa = leitor.nextLine();
            UDPClientSimuladorThread t = new UDPClientSimuladorThread(placa);
            t.start();
        }
    }
    
}
