package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SocketClientExample {
   
   
    /*
     * Modify this example so that it opens a dialogue window using java swing,
     * takes in a user message and sends it
     * to the server. The server should output the message back to all connected clients
     * (you should see your own message pop up in your client as well when you send it!).
     *  We will build on this project in the future to make a full fledged server based game,
     *  so make sure you can read your code later! Use good programming practices.
     *  ****HINT**** you may wish to have a thread be in charge of sending information
     *  and another thread in charge of receiving information.
    */

    public static int numUsers;


    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        numUsers ++;
        int clientNum = numUsers;
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 52000);
            //write to socket using ObjectOutputStream
        ObjectOutputStream   oos = new ObjectOutputStream(socket.getOutputStream());
        Scanner input = new Scanner(System.in);
        String line ="";
        JFrame frame = new JFrame("Chat Client");
        JTextField textField = new JTextField(20); 
        JLabel label = new JLabel("Message will apear here");
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setSize(400, 100);
        frame.setVisible(true);


        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("User " + clientNum + ": " + textField.getText());
                textField.setText("");
            }
        });
        while(!(line = input.nextLine()).equals("disconnect")){
            oos.writeObject(line);
            oos.flush();
        }
        socket.shutdownOutput();
        System.out.println("connection closed!");
    }
}