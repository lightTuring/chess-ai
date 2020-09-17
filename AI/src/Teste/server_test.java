package Teste;

import Server.Server;

public class server_test {

	public static void main(String[] args) throws Exception {
		Server s = new Server(5006);
		//s.setMoviment(1, 1, 1, 1);
		
		for(int i=0;i<10;i++) {s.sendMoviment("E2E4");
		
	}s.finalizeCommunication();
	System.out.println("end...");
	}

}
