package Game.Server.net;

import Game.Server.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private Scanner input;
    private PrintWriter output;
    private final Controller contr = new Controller();
    private boolean conncet = true;


    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    // method called from the <code>startConnection</code>
    @Override
    public void run() {

        while(conncet){
            try {
                input = new Scanner(clientSocket.getInputStream());
                output =  new PrintWriter(clientSocket.getOutputStream(), true);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // receiving data from network
            String received = input.nextLine();

            if(received.contains("disconnect"))
                disconnect();
            else{
                try {
                    contr.setInput(received);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // sending data to network
                output.println(contr.getOutput());
            }
        }

    }

    private void disconnect(){
        conncet = false;
        System.out.println("Client is disconnected " + clientSocket.toString());
        try{
            clientSocket.close();
        }
        catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
