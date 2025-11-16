
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient{

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int port = 4444;

        String server_ip = "127.0.0.1"; // This is just the localhost

        InetAddress ip = null;

        DatagramSocket clientSocket = null;

        try{
            ip = InetAddress.getByName(server_ip);

        }catch(UnknownHostException e){
            System.out.println("Error 1");
        }


        while(true){
           /*
            Constructs a datagram packet for sending packets of length length 
            to the specified port number on the specified host.
            */
            System.out.print("Enter Message: ");
            String text = scan.nextLine();

            if(text.equalsIgnoreCase("exit")){
                break;
            }

            byte [] buffer = text.getBytes();

            try{

                clientSocket = new DatagramSocket();
                DatagramPacket sendPacket = null;
                sendPacket = new DatagramPacket(buffer, buffer.length, ip, port);
        
               /* Now that we have packet, we want to send this.

               What class do we use for sending and receiving
               datagram packets?

               Answer: DatagramSocket.

               */

              clientSocket.send(sendPacket);

              /* We need to update the client to receive a response
              from the server
              */

              byte [] receiveBuffer = new byte[1024];

              DatagramPacket receivePacket = null;
              receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
              clientSocket.receive(receivePacket);

              String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
              System.out.println("Server message is: => " + receivedString);
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
        }



        //System.out.println(ip);
    }
}