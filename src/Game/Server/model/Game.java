package Game.Server.model;

public class Game {
    //sent data
    private String gameData = " / / /";

    private String serverWord;

    // Game data need to be sent to user
    private int score = 0;
    private int attempts;
    private String clientWord;          // the word displayed for the user
    private String message;
    private String wrongLetters = " ";  // the wrong guessed letters

    private boolean guessedLetter = false;
    private boolean win = false;
    private boolean lose = false;
    private boolean gameInProgress;



    // send the compound data together to the client
    public String getGamedata(){
        return gameData;
    }

    // data received form the client
    public void receivedData (String receivedWord ) throws InterruptedException {

        if(receivedWord.equalsIgnoreCase("start game"))
            gameInProgress = true;

        if (gameInProgress){

            if ( receivedWord.equalsIgnoreCase("start game")){
                startGame();
            }
            else if (receivedWord.equalsIgnoreCase("end game")){
                endGame();
            }
            else {
                Thread.sleep(15000);
                gameInProgress(receivedWord);
            }
        }

        else{
            message = " Please type 'start game' to play ";
            clientWord = " ";
            wrongLetters = " ";
            updateGameData();
        }

    }

    // set the information for a new game
    private void startGame(){
        serverWord = Word.randomWord();
        System.out.println(serverWord);
        attempts = serverWord.length();
        clientWord = createWord (serverWord.length());
        message = " GAME ON ";
        wrongLetters  = " ";
        updateGameData();

        win = false;
        lose = false;
        guessedLetter = false;
    }

    private void endGame(){
        message = " GAME OVER ";
        updateGameData();
    }

    private String createWord (int length){
        StringBuilder word = new StringBuilder();
        for (int i=0 ; i< length ; i++){
            word.append("_");
        }
        return String.valueOf(word);
    }

    // Processing the received data from the client
    private void gameInProgress (String receivedWord){
        guessedLetter = false;

        // if user sent a letter
        if(receivedWord.length() == 1){
             char [] serverWordArray = serverWord.toCharArray();
             char letter = receivedWord.charAt(0);
             for (int i=0; i<serverWordArray.length; i++){
                 if (serverWordArray[i] == letter ){
                     StringBuilder str = new StringBuilder(clientWord);
                     str.setCharAt(i,letter);
                     clientWord = String.valueOf(str);
                     guessedLetter = true; // flag
                 }
             }

             // if the letter is wrong letter
            // decrement the number of attempts
            // and add it to the list of the wrong numbers
             if(!guessedLetter){
                 attempts--;
                 addWrongletter(letter);
             }

             // check if the word is guessed after
            // the last letter was received by the client
             if (serverWord.equalsIgnoreCase(clientWord))
                 win = true;
        }

        // check if the word is guessed
        if (serverWord.equalsIgnoreCase(receivedWord))
            win = true;

        if(attempts == 0)
            gameOver();

        if(win)
            gameWin();

        updateGameData();
    }

    // set the format of the sent data
    // the data is sent as a string of
    // other strings separated by '/'
    // scrore/attemps/clientWord/message/wrongLetters
    private void updateGameData(){

        StringBuilder temp = new StringBuilder();
        temp.append(score);
        temp.append("/");
        temp.append(attempts);
        temp.append("/");
        temp.append(separatedClientWord());
        temp.append("/");
        temp.append(message);
        temp.append("/");
        temp.append(wrongLetters);
        gameData = String.valueOf(temp);
    }

    private void gameOver (){
        score--;
        message = " YOU LOSE! :( ";
        gameInProgress = false;
        clientWord = serverWord;
    }

    private void gameWin (){
        score++;
        message = " YOU WIN! :) ";
        gameInProgress = false;
        clientWord = serverWord;
    }


    private String separatedClientWord() {
        char[] array = clientWord.toCharArray();
        StringBuilder str = new StringBuilder();
        for(int i=0 ; i<array.length; i++){
            str.append(array[i]);
            str.append(" ");
        }
        return String.valueOf(str);
    }

    private void addWrongletter(char letter){
        StringBuilder temp = new StringBuilder(wrongLetters);
        temp.append(letter);
        temp.append(" ");
        wrongLetters = String.valueOf(temp);
    }





}
