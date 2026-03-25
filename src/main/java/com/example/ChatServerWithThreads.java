package com.example;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program should allow the client to send it messages. The messages should then 
 * become visible to all other clients.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example). 
 * 
 * This version of the program creates a new thread for
 * every connection request.
 */
public class ChatServerWithThreads {

    public static final int LISTENING_PORT = 9876;

    public static void main(String[] args) {

        int portNumber = 52000;

        ServerSocket listener;  // Listens for incoming connections.
        Socket connection;      // For communication with the connecting program.

        /* Accept and process connections forever, or until some error occurs. */

        try {
            Socket socket = new Socket("localhost",portNumber);
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            while (true) {
                  // Accept next connection request and handle it.
            }
        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }

    }  // end main()


    /**
     *  Defines a thread that handles the connection with one
     *  client.
     */
    private static class ConnectionHandler extends Thread {
        private static ArrayList<ConnectionHandler> connectionList;
        Socket client;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        InputStreamReader isr = new InputStreamReader(ois);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();
        System.out.println(message);
        ConnectionHandler(Socket socket) {
            client = socket;
            if(connectionList == null)
                connectionList = new ArrayList<ConnectionHandler>();
            connectionList.add(this);
            try{
                ois = new ObjectInputStream(client.getInputStream());
                oos = new ObjectOutputStream(client.getOutputStream());
            }
            catch(IOException e){}
        }
        public void run() {
            String clientAddress = client.getInetAddress().toString();
            while(true) {
	            try {
	            	//your code to send messages goes here.

	            }
	            catch (Exception e){
	                System.out.println("Error on connection with: " 
	                        + clientAddress + ": " + e);
	            }
            }
        }
    }


}
