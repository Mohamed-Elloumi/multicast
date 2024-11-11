import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;

public class Recepteur {
    // Adresse de groupe multicast
    private static final String MCAST_GRP = "239.0.0.1";
    // Port sur lequel le récepteur écoute
    private static final int MCAST_PORT = 5007;

    public static void main(String[] args) {
        try {
            // Création d'un socket multicast pour écouter sur le port spécifié
            MulticastSocket socket = new MulticastSocket(MCAST_PORT);
            // Obtention de l'adresse du groupe multicast
            InetAddress group = InetAddress.getByName(MCAST_GRP);
            // Rejoindre le groupe multicast pour recevoir des messages
            socket.joinGroup(group);

            System.out.println("En attente de messages sur le groupe " + MCAST_GRP + " port " + MCAST_PORT + "...");

            while (true) {
                // Création d'un tableau de bytes pour recevoir le message
                byte[] buf = new byte[256];
                // Création d'un paquet de données pour recevoir le message
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                // Attente de la réception du paquet de données
                socket.receive(packet);
                // Conversion des données reçues en chaîne de caractères
                String message = new String(packet.getData(), 0, packet.getLength());
                // Affichage du message reçu ainsi que l'adresse de l'émetteur
                System.out.println("Message reçu de " + packet.getAddress() + ": " + message);
            }
        } catch (Exception e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
    }
}
