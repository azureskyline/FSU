/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpchat_austria;
import java.net.*;
import java.io.*;
/**
 *  UDPServer handles initiating the server, handling connecting the clients and migrating messages through to other clients connected.
 * @author Catherine Austria
 */
public class UDPServer implements Runnable{
    private ServerSocket mainServer; //the server
    public UDPServerThread someclients[] = new UDPServerThread[5]; //can hold up to 5 clients 
    private int clients = 0; // number of clients connected
    private Thread serverThread = null; //the thread being run; iniate to null for start and stop ops
    
    /**
     * The main method initializes starts the chat server using the port# from the command line.
     * @param args is the port number the server is using
     */
    public static void main(String args[]){  
        UDPServer myServer = null;
        if (args.length != 1)
            System.out.println("UDP Chat Server failed to run. Input another port number.");
        else
            myServer = new UDPServer(Integer.parseInt(args[0]));
    }
    /**
     * Constructor that creates the ServerSocket and displays port and server information.
     * @param portNumber The port number the server is using
     */
    public UDPServer (int portNumber){  
        try{  //try to bind to the port number passed.
            mainServer = new ServerSocket(portNumber);  //create ServerSocket using portNumber
            System.out.println("Port " + portNumber+" The server was started succesfully: " + mainServer); //display server info
            start(); //Start the thread!
        }catch(IOException e){System.out.println("The server was NOT started succesfully. Port Number Used: " + portNumber);}//means server could not be set up. prints error msg.
    }
    /**
     * This method starts a thread.
     */
    public void start(){  
        if (serverThread == null){
           serverThread = new Thread(this); //create a new thread
           serverThread.start();
        }
    }
    /**
     * This method stops the thread.
     */
    public void stop(){  
        if (serverThread != null){  
            serverThread.stop(); //stop the thread!
            serverThread = null;
        }
    }
    /**
     * This lets the clients connect.
     */
    public void run(){  
        while (serverThread != null){  
            try{  
                System.out.println("Ready for a clients to connect."); 
                addThread(mainServer.accept()); //accept the client
            }catch(IOException e){System.out.println("Server Error: " + e); stop();}
        }//end while
    }
    /**
     * Adds a thread for new clients connecting.
     * @param mysocket socket from client
     */
    private void addThread(Socket mysocket){  
        if (clients < someclients.length){  //as long as there are still slots to connect to ; < 5
            System.out.println("Client Accepted at socket: " + mysocket);
            someclients[clients] = new UDPServerThread(this, mysocket); //create thread
            try{  
                someclients[clients].open(); 
                someclients[clients].start();  
                clients++; //increase total# of clients
            }catch(IOException e){System.out.print("IOException Error!");} 
        }//end if
        else System.out.println("No more client room! Max clients:" + someclients.length); //print if too many clients!
    }
    /**
     * Finds a client
     * @param client the client number; kind of obvious
     * @return 
     */
    private int findClient(int client){  
        //iterate over connected clients
        for (int i = 0; i < clients; i++) if (someclients[i].getClient() == client) return i; //if found client, return his/her #
        return -1; // if not return -1
    }
    /**
     * Handles chat input from multiple clients. Exits if client says goodbye.
     * @param client the client
     * @param msg the message
     */
    public synchronized void messenger(int client, String msg){  
        if (msg.equals("Goodbye")){  //if client wants to leave
            //send "Goodbye" message and remove the client from the chat.
            someclients[findClient(client)].send("Goodbye");//send the msg
            removeClient(client); //remove client method
        }//end if
        //send the client's message over ; got lazy and combined the whole thing; sorry
        else for (int i = 0; i < clients; i++) someclients[i].send(client + " sent: " + msg); // all other client receive the msg   
    }
    /**
     * Removes a client.
     * @param client The client to be removed.
     */
    public synchronized void removeClient(int client){  
        int pos = findClient(client);
        if (pos >= 0){  
            UDPServerThread toTerminate = someclients[pos];
            System.out.println("Removing Client: " + client);
            //iterate over clients
            if (pos < clients-1) for (int i = pos+1; i < clients; i++) someclients[i-1] = someclients[i];
            clients--; //reduce # of clients in total
            try{  
                toTerminate.close(); //close thread
            }catch(IOException e){System.out.println("Error closing thread.");}
            toTerminate.stop(); //stop thread 
        }//end if
    }
}
