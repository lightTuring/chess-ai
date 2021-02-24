package Server;
@SuppressWarnings("all")
public class SerialRobot {
    SerialPort[] ports = SerialPort.getCommPorts();
    int port;

    SerialRobot(int port, Bits bit) {        
        this.port = port;
    }
    public SerialPort start() {
        System.out.println("Select a port:");
        int i = 1;
        for(SerialPort port : ports)
            System.out.println(i++ +  ": " + port.getSystemPortName());
        Scanner s = new Scanner(System.in);
        int chosenPort = s.nextInt();

        SerialPort serialPort = ports[chosenPort - 1];
        if(serialPort.openPort()) {
            System.out.println("Port opened successfully.");
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
            return serialPort;
        }
            
        else {
            System.out.println("Unable to open the port.");
            return null;
        }
    }
    public int receive(SerialPort serialPort) {
        Scanner data = new Scanner(serialPort.getInputStream());
        int value = 0;
        while(data.hasNextLine()){
            try{value = Integer.parseInt(data.nextLine());}catch(Exception e){}
            return value;
        }
        System.out.println("Done.");
        return (-1);
    }
    public void send(SerialPort serialPort, char[][] Board) {
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                byte[] buffer = new byte[2];
                serialPort.writeBytes​(buffer, (long)board[i][j])
            }
        }
        System.out.println("Done.");
    }
    //serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);


}
