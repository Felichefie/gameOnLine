import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ShipClient{
    int lives;
    int x, y;
    ShipData sd;
    Socket socket;
    String name;

    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    public ShipClient (int x, int y, String name){
        lives = 3;
        this.x = x;
        this.y = y;
        this.name = name;
        sd = new ShipData(this.x, this.y, this.name);
    }

    public void connectClient(){
        try {
            socket = new Socket(Conf.address, Conf.port);
            System.out.println("Conectado al servidor");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject() {
        try {
            int x = sd.getX();
            x++;
            sd.setX(x);
            oos.writeObject(sd);
            oos.reset();
            System.out.println(sd.getX());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]){
        ShipClient sc = new ShipClient(10,10, "Feliche");    
        sc.connectClient();
        
        while(true){
            sc.sendObject();
        }
    }

    public class Conf {
        public static final long serialVersionUID = 7829136421241571165L;
        public static final int port = 1234;
        public static final String address = "localhost";
    }
}

