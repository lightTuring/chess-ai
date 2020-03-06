import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String path;
    ServerSocket server;

    public Server(){
        server = new ServerSocket(5000);
    }
    public void sendMsg() throws Exception{
        Socket socket = server.accept();
        try(PrintWriter w = new PrintWriter(socket.getOutputStream())){
            w.println(path);
        }
      
    }
    public void finalizeCommunication(){
        server.close();
    }
    public void setPath(int xo, int yo, int xf, int yf){
        
    }
}