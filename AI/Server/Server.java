import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    //TALVEZ MUDAR O TIPO DO ENVIO(STRING -> BYTE(?))...

    ServerSocket server;
    private String moviment;

    public Server() throws Exception{
        server = new ServerSocket(5000);        
    }

    public void sendMoviment() throws Exception{
        Socket socket = server.accept();
        try(PrintWriter w = new PrintWriter(socket.getOutputStream())){
            w.println(moviment);
        }
    }
    public void setMoviment(int xo, int yo, int xf, int yf){
        moviment += String.valueOf(xo)+String.valueOf(yo)+String.valueOf(xf)+String.valueOf(yf);//O split é dado por todos os chars, pois o valor das variaveis são compreendidos entre 0-7
    }
    public void finalizeCommunication() throws IOException{
        server.close();
    }

}