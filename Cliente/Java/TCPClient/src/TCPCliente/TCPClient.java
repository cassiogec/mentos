package TCPCliente;

import br.com.negocio.Veiculo;
import br.com.negocio.ArquivoBD;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TCPClient {

    private static Veiculo lerVeiculo()
    {
        Scanner leitor = new Scanner(System.in);
        
        Veiculo v = new Veiculo();

        System.out.println("Digite a placa do veiculo: ");
        v.setPlaca(leitor.nextLine());

        while (true)
        {
            System.out.println("Digite o tipo do veiculo: ");
            System.out.println("1 - Carro");
            System.out.println("2 - Caminhão Toco");
            System.out.println("3 - Caminhão Carreta");
            System.out.println("4 - Caminhão Bitrem");
            System.out.println("5 - Caminh]ao Treminhão");
            System.out.println("6 - Moto");
            System.out.println("7 - Triciclo");
            System.out.println("8 - Quadriciclo");
            Integer tipo = Integer.parseInt(leitor.nextLine());

            if (tipo >= 9 || tipo <= 1)
            {
                System.out.println("Tipo Invalido");
                continue;
            }
            else
            {
                v.setTipo(tipo);
                break;
            }
        }

        while (true)
        {
            System.out.println("Digite a Unidade do Veiculo com 5 CARACTERES: ");
            String unidade = leitor.nextLine();

            if (unidade.length() != 5)
            {
                System.out.println("Unidade deve ter 5 caracteres");
                continue;
            }
            else
            {
                v.setUncapac(unidade);
                break;
            }
        }

        System.out.println("Digite a capacidade do veiculo: ");
        v.setCapacidade(Integer.parseInt(leitor.nextLine()));
        
        return v;
    }
    
    public static void main(String[] args) throws Exception{
        Scanner leitor = new Scanner(System.in);
                
        int porta = 2010;
        byte buffer[] = new byte[100];
        InetAddress address;
        String host = "localhost";
        
        try ( //ServerSocket soc = new ServerSocket(2005);
            Socket s = new Socket("127.0.0.1", porta)) {
                Veiculo a = new Veiculo("TESTE C", 1, 1, "AA");
                List<Object> l = new ArrayList<Object>();
                l.add(a);
                l.add(new Veiculo("LEO123",1,1,"AA"));
                ArquivoBD b = new ArquivoBD(4,1,l);
                ObjectOutputStream esc = new ObjectOutputStream(s.getOutputStream());
                esc.writeObject(b);
                esc.flush();
        }
        
        System.out.println("Objeto enviado.");
        
        while (true)
        {
            System.out.println("Digite o código da operação: ");
            System.out.println("1 - Adicionar Veiculo");
            System.out.println("2 - Alterar Veiculo");
            System.out.println("3 - Excluir Veiculo");
            System.out.println("4 - Consultar Veiculo");
            System.out.println("5 - Listar veiculos por tipo");
            System.out.println("6 - Localização do Veiculo");
            System.out.println("7 - Sair e fechar conexão");
            Integer operacao = Integer.parseInt(leitor.nextLine());
            
            Veiculo v = new Veiculo();
            ArquivoBD a = new ArquivoBD();
            switch (operacao)
            {
                case 1:
                    v = lerVeiculo();
                break;
                case 2:
                    v = lerVeiculo();
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                case 7:
                    
                    System.exit(0);
                break;
                default:
                    System.out.println("Operação Inválida");
                break;
            }
        }
    }    
}
