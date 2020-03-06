package Teste;

import Server.Server;

public class server_test {

	public static void main(String[] args) throws Exception {
		Server s = new Server(5005);
		s.setMoviment(1, 1, 1, 1);
		s.sendMoviment();
		s.finalizeCommunication();
		System.out.println("end...");
	}

}
