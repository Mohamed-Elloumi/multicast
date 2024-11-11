import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Emetteur {
    // Adresse de groupe multicast
    private static final String MCAST_GRP = "239.0.0.1";
    // Port pour envoyer les données en multicast
    private static final int MCAST_PORT = 5007;

    public static void main(String[] args) {
        try {
            // Création du socket UDP
            DatagramSocket socket = new DatagramSocket();
            // Obtention de l'adresse du groupe multicast
            InetAddress group = InetAddress.getByName(MCAST_GRP);
            // Création d'un scanner pour lire l'entrée utilisateur
            Scanner scanner = new Scanner(System.in);

            System.out.println("Entrez un message à envoyer (ou 'exit' pour quitter) :");
            while (true) {
                // Lecture du message saisi par l'utilisateur
                String message = scanner.nextLine();
                // Si l'utilisateur tape 'exit', on sort de la boucle
                if (message.equalsIgnoreCase("exit")) {
                    break; // Quitter si l'utilisateur tape 'exit'
                }
                // Conversion du message en tableau de bytes
                byte[] buf = message.getBytes();
                // Création du paquet de données avec l'adresse du groupe et le port
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, MCAST_PORT);
                // Envoi du paquet de données via le socket
                socket.send(packet);
                // Affichage du message envoyé
                System.out.println("Message envoyé: " + message);
            }

            // Fermeture du scanner après utilisation
            scanner.close();
        } catch (Exception e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
    }
}
