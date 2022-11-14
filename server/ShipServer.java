import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ShipServer {
    ServerSocket socketServer;
    Socket socketClient;

    ObjectInputStream ois;
    ObjectOutputStream oos;

    
    public ShipServer (){
        System.out.println("start Server...");
        try {
            socketServer = new ServerSocket(Conf.port);
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        // Conexión desconexión de clientes
        while(true){
            try {
                socketClient = socketServer.accept();
                System.out.println(socketClient);
                ois = new ObjectInputStream(socketClient.getInputStream());
                oos = new ObjectOutputStream(socketClient.getOutputStream());

                while(true){
                    ShipData shipData = (ShipData) ois.readObject();
                    System.out.println(shipData.getX());
                }

            } catch (IOException e) {
                if(socketClient.isConnected()){
                    try {
                        socketClient.close();
                        e.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main (String arg[]){
         new ShipServer();
    }

    public class Conf {
        public static final long serialVersionUID = 7829136421241571165L;
        public static final int port = 1234;
        public static final String address = "localhost";
    }
}


