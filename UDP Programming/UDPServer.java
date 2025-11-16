
//import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/*
UDP (User Datagram Protocol)
This is a connectionless transport protocol in the TCP/IP stack(suite).

UDP does not establish a connection before sending data
UDP does not guarantee delivery, order, or integrity
It's faster and has lower overhead cost than TCP

UDP is ideal for 
real-time systems (games and live video streaming.)
DNS lookups

TCP/IP stack (layers).
1. Application
2. Transport  (UDP, TCP)
3. Internet (IP)
4. Network Interface
5. Network hardware

Data is send in small packets that we call datagrams.

Each datagram includes 
Source port and IP
Destination port
Data payload

The receiver listens on a specific port and processes any incoming 
datagrams.


The Java programming labguage provides two main classes in the java.net
package for UDP programming
1. DatagramSocket class
2. DatagramPacket class

DatagramSocket is used to send or receive datagram packets.

DatagramPacket represents the actual datagram packet.
This datagram packet would have data and ip addresses and ports.

UDP program architecture, we typically have two programs
1. A UDP Server (receiver)
2. A UPD Client (sender)

*/


// 
public class UDPServer{


    public static void main(String [] args){

        // Create our datagram socket and bind to a port
        DatagramSocket socket = null;
        Scanner scan = new Scanner(System.in);
        try{
            /*Constructs a datagram socket and binds it to the 
            specified port on this local host machine (127.0.0.1).
            */
            socket = new DatagramSocket(4444);
            System.out.println("Our simple UDP server is running");


            byte [] receivedBuffer = new byte[1024];


            // Receive a packet
            // Constructs a DatagramPacket for receiving packets of length length.
            DatagramPacket receivePacket = null;

            while(true){
                receivePacket = new  DatagramPacket(receivedBuffer, receivedBuffer.length );
                socket.receive(receivePacket);

                String receivedClientMSG = null;
                receivedClientMSG = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client says =>" + receivedClientMSG);



                /*
                It is also possible that a server, may want to
                send a response back to the client.

                So upon receiving a message, the server can prepare
                a response to send back to the client.

                Rememeber in order to send a message, we need
                a datagram socket. We already have a socket in
                this program that we are using to receive messages
                from client. We can resuse this same socket now
                to send replies to clients.

                To send a message, we need the message itself,
                we also need the length of the message, we also
                need the IP address of the destination and also 
                the port number at the destination. When a message
                comes from a client, it contains the IP address of the
                sender where we can send the reply to, it also includes
                the sender port number
                */
                System.out.print("Server => ");
                String msg = scan.nextLine();

                byte [] msgByteBuffer = msg.getBytes();
                DatagramPacket sendReplyPacket = null;

                sendReplyPacket = new DatagramPacket(msgByteBuffer, 
                    msgByteBuffer.length, receivePacket.getAddress(),
                     receivePacket.getPort());

                // System.out.println(receivePacket.getAddress());
                // System.out.println(receivePacket.getPort());
                socket.send(sendReplyPacket);

           }
        }catch(Exception e){

            System.out.println("We could not construct a datagram server for you");

        }


     

    }
}