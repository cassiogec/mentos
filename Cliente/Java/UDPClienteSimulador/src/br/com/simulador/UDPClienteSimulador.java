/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.simulador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cassio
 */
public class UDPClienteSimulador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner leitor = new Scanner(System.in);
        List<UDPClientSimuladorThread> listThreads = new ArrayList<UDPClientSimuladorThread>();
        
        while (true)
        {
            System.out.println("Digite o código da operação: ");
            System.out.println("1 - Simular Veiculos");
            System.out.println("2 - Sair e fechar threads");
            Integer operacao = Integer.parseInt(leitor.nextLine());
            
            if (operacao == 1)
            {
                BufferedReader buffRead = null;
                
                try {
                    System.out.println("Digite a quantidade de veiculos: ");
                    Integer veiculos = Integer.parseInt(leitor.nextLine());
                    
                    for (int i = 0; i < veiculos; i++)
                    {
                        File file = new File("rotas");
                        Integer random = 1 + (int) (Math.random() * file.list().length);
                        
                        String json = new String(Files.readAllBytes(Paths.get("rotas/rota_"+random+".json")), StandardCharsets.UTF_8); // The String You Need To Be Converted 
                        List<Posicao> jsonArray = new Gson().fromJson(json, new TypeToken<List<Posicao>>(){}.getType());
                        
                        System.out.println("Digite a placa do veiculo sem formatação: ");
                        String placa = leitor.nextLine();
                        
                        System.out.println("Segundos entre cada envio de posição: ");
                        Integer tempo = Integer.parseInt(leitor.nextLine());
                        
                        UDPClientSimuladorThread t = new UDPClientSimuladorThread(placa, jsonArray, tempo);
                        t.start();
                        
                        listThreads.add(t);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if (operacao == 2)
            {
                for (UDPClientSimuladorThread t: listThreads)
                {
                    t.interrupt();
                }
                
                System.exit(0);
            }
            else
                System.out.println("Operação inválida!");

        }
    }
    
}
