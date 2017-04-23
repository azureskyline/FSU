/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpchat_austria;
import java.net.*;
import java.io.*;
/**
 * This is Thread helper for the UDPServer class.
 * @author Catherine Austria
 */
public class UDPServerThread extends Thread{
    private UDPServer server = null; //udp server
    private Socket thiSocket = null; // socket used
    private int client = -1; // socket port #
    private DataInputStream in =  null; //for msgs comming in
    private DataOutputStream out = null; //for msgs going out
    /**
     * This constructor initiates the client, server and socket to be used.
     * @param myserver server used
     * @param mysocket socket used
     */
    public UDPServerThread(UDPServer myserver, Socket mysocket){  
        super();
        thiSocket = mysocket;
        server = myserver;
        client = thiSocket.getPort(); //get client sokect port
    }
    /**
     * Fetches client.
     * @return client #
     */
    public int getClient(){return client;}
    /**
     * This sends out the chat message
     * @param msg chat message to be sent
     */
    public void send(String msg){   
        try{  
            out.writeUTF(msg); //send message
            out.flush();
        }
        catch(IOException e){  
            System.out.println("Sending Error: '"+msg+"'");
            stop();
        }
    }
    /**
     * Opens thread for client for server.
     * @throws IOException throws error for invalid input
     */
    public void open() throws IOException{  
        in = new DataInputStream(new BufferedInputStream(thiSocket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(thiSocket.getOutputStream()));
    }
    /**
     * Closes thread for client for server.
     * @throws IOException throws error for invalid input
     */
    public void close() throws IOException{  
        if (thiSocket != null) thiSocket.close(); // close Socket
        if (in != null)  in.close(); //close DataInputStream
        if (out != null) out.close(); // close DataOutputStream
    }
    /**
     * When client connects, this runs and shows server client is connected and initiates chatting.
     */
    public void run(){  
        System.out.println(client + " is Online.");
        while (true){  
            try{  
                server.messenger(client, in.readUTF());
            }catch(IOException e){  
                System.out.println("Error running client "+client+"'s messenger. Client is offline.");
                server.removeClient(client);
                stop();
            }//end try-catch
        }//end while
    }
    
}
