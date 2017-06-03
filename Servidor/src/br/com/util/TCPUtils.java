package br.com.util;

public class TCPUtils {

	public static final int PORTA_SERVIDOR = 2010;
	public static final String HOST = "localhost";
	
	public static void printLog(String source, String log) {
		System.out.println(source.toUpperCase() + ": " + log);
	}
	
	public static void tratarException(String source, Exception e) {
		if (e.getMessage().contains("Address already in use")) {
			TCPUtils.printLog(source.toUpperCase(), "Já existe um processo ativo");
		} else if (e.getMessage().contains("Connection refused")) {
			TCPUtils.printLog(source.toUpperCase(), "Porta incorreta ou servidor inativo");
		} else if (e.getClass().getSimpleName().equals("UnknownHostException")) {
			TCPUtils.printLog(source, "Host desconhecido");
		} else {
			TCPUtils.printLog(source, "erro desconhecido " + e.getMessage());
		}
	}
}
