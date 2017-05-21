package TCPCliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TCPClient {
	
	private static Socket socket;
	private static String className;
	private static String operacao;
	private static String retorno;
	private static BufferedReader inFromServer;
	private static DataOutputStream outToServer;
	private static BufferedReader inFromUser;
        public ObjectInputStream testeEnvio; 
	
	public static void main(String[] args) {
		
		className = TCPClient.class.getSimpleName();
                System.out.println("Aqui é o cliente");
		
                while (true){
                try { 
                        //ObjectInputStream testeEnvio = new ObjectInputStream(socket.getInputStream()); 
			inFromUser = new BufferedReader( new InputStreamReader(System.in));
			socket = new Socket(Utils.HOST, Utils.PORTA_SERVIDOR);
			outToServer = new DataOutputStream(socket.getOutputStream());
			
			operacao = inFromUser.readLine();
                        outToServer.writeBytes(operacao.toLowerCase() + "\n");
                        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			retorno = inFromServer.readLine();
			Utils.printLog(className, operacao.toUpperCase());
			
		} catch (Exception e) 
		{
			Utils.tratarException(className, e);
		} finally 
		{
			try 
			{
				if (socket != null) 
				{
					socket.close();
				}	
			} catch (IOException io) 
			
			{
				Utils.tratarException(className, io);
			}
		}
	}
        }
}
