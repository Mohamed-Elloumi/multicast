import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;

public class Recepteur {
    private static final String MCAST_GRP = "224.0.0.1";
    private static final int MCAST_PORT = 5007;

    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(MCAST_PORT);
            InetAddress group = InetAddress.getByName(MCAST_GRP);
            socket.joinGroup(group);

            System.out.println("En attente de messages sur le groupe " + MCAST_GRP + " port " + MCAST_PORT + "...");

            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message reçu de " + packet.getAddress() + ": " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
