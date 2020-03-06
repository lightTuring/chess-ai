import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    ServerSocket server;
    private String moviment;

    public Server() throws Exception{
        server = new ServerSocket(5000);        
    }

    public void sendMoviment(){

    }
    public void setMoviment(int xo, int yo, int xf, int yf){

    }
    public void finalizeCommunication(){

    }

}