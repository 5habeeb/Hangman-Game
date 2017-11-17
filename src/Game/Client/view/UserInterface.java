package Game.Client.view;


import Game.Client.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Controller cont;


    public void start(){
        cont = new Controller();
        runProgramm();
    }


    public void runProgramm() {
        System.out.println("Type 'Start Game' to play...");
        while (true) {
            Scanner userEntry = new Scanner(System.in);

            String packetTosend = userEntry.nextLine();
            new Thread(new Sender(packetTosend)).start();

        }
    }

    private void showOutput (String receivedPacket){
        String[] dataToShow = receivedPacket.split("/");
        System.out.println("---" + dataToShow[3] + "---" );
        System.out.println("Score: " + dataToShow[0] + "     Attempts: " + dataToShow[1] + "    Wrong letters: " + dataToShow[4]);
        System.out.println("Word to guess:   " + dataToShow[2] + "\n");

    }

    private class Sender implements Runnable{
        private String packetoSend;

        Sender (String packetToSend){
            this.packetoSend = packetToSend;
        }

        @Override
        public void run() {
            try {
                cont.sendToController(packetoSend);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                String receivedPacket = cont.receiveFromController();
                showOutput(receivedPacket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
