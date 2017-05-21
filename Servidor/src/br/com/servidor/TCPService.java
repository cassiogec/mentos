package br.com.servidor;

import br.com.util.TCPUtils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPService {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader inputStream;
    private static DataOutputStream outStream;
    private static String result;
    private static String className;

    public static void main(String[] args) throws IOException {

            className = TCPService.class.getSimpleName();

            try 
            {
                int contador = 0;

                    serverSocket = new ServerSocket(2010);
                    System.out.println("Aqui é o servidor");
                    while (true) 
                    {
                            socket = serverSocket.accept();
                            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            result = inputStream.readLine();
                            System.out.println(result);
                            outStream = new DataOutputStream(socket.getOutputStream());
                            outStream.writeBytes("Funcionou");
                            outStream.close();
                            contador = contador +1;
                            System.out.println(contador);
                    }
            } catch (Exception e) 

            {
                    TCPUtils.tratarException(className, e);
                    if (serverSocket != null)
                            serverSocket.close();
            } finally 

            {
                    if (socket != null) 
                    {
                            socket.close();
                    }
            }

                            System.out.println(result);
    }

}
