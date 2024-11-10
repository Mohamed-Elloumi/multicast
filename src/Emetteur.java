import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Emetteur {
    private static final String MCAST_GRP = "224.0.0.1";
    private static final int MCAST_PORT = 5007;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName(MCAST_GRP);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Entrez un message à envoyer (ou 'exit' pour quitter) :");
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break; // Quitter si l'utilisateur tape 'exit'
                }
                byte[] buf = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, MCAST_PORT);
                socket.send(packet);
                System.out.println("Message envoyé: " + message);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
