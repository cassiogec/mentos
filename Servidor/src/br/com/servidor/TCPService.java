package br.com.servidor;

import br.com.DAO.VeiculoDAO;
import br.com.negocio.Veiculo;
import br.com.negocio.ArquivoBD;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

public class TCPService {


    public static void main(String[] args)throws Exception{

        int porta = 2010;
        String oper = "";
        System.out.println("Servidor Executando");
        
        ServerSocket soc = new ServerSocket(porta);
        
        //EnviaResposta res = new EnviaResposta();
        //VeiculoController veiCon = new VeiculoController();
        while (true) {       
            Socket s = soc.accept(); // AGUARDA NOVOS DADOS
            System.out.println("Conexão estabelecida.");
            
            // LÊ O DADO RECEBIDO
            ObjectInputStream esc = new ObjectInputStream(s.getInputStream());
            Object obj = esc.readObject();

            ArquivoBD arquivo = (ArquivoBD) obj;
                
                if(arquivo.getOpe()== 1)
                oper = "Adicionar veículo";
                
                if(arquivo.getOpe()== 2)
                oper = "Alterar Veiculo";
                
                if(arquivo.getOpe()== 3)
                oper = "Excluir Veiculo";
                
                if(arquivo.getOpe()== 4)
                oper = "Consultar Veiculo";
               
                if(arquivo.getOpe()== 5)
                oper = "Listar veiculos por tipo";
                
                if(arquivo.getOpe()== 6)
                oper = "Localização do Veiculo";
             
                if(arquivo.getOpe()== 7)
                oper = "O cliente finalizou a conexão";
                
                Veiculo vTeste = (Veiculo) arquivo.getObjetos().get(0);
            
                
            if (arquivo.getOpe() >= 1 || arquivo.getOpe() <= 6)
            {
           System.out.println("Dados recebidos para processamento de: " + oper + " :" + vTeste.getPlaca());
            }
            
//            if (arquivo.getOpe() == 7)
//            {
//                System.out.println("O cliente encerrou a conexão.");
//            }
                
            
            switch (arquivo.getOpe())
            {
                case 3:
                    Veiculo v = (Veiculo) arquivo.getObjetos().get(0);
                    System.out.println(v.getPlaca());
                break;
            }
            
        }

    }
}