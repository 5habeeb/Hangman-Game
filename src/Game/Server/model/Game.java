package Game.Server.model;

public class Game {
    //sent data
    private String gameData = " / / /";

    private String serverWord;

    // Game data need to be sent to user
    private int score = 0;
    private int attempts;
    private String clientWord;
    private String message;

    boolean guessedLetter = false;
    boolean win = false;
    boolean lose = false;

    private boolean gameInProgress;

    /*
    private static final int START = 0;
    private static final int IN_GAME = 1;
    private static final int WIN = 2;
    private static final int LOSE = 3;
    private static final int END = 4;
    private static final int
    */


    // send the compound data together to the client
    public String getGamedata(){
        return gameData;
    }

    // data received form the client
    public void receivedData (String receivedWord ){

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
                gameInProgress(receivedWord);
            }
        }

        else{
            message = "Please type start game to platy again";
            updateGameData();
        }

    }

    private void startGame(){
        serverWord = Word.randomWord();
        System.out.println(serverWord);
        attempts = serverWord.length();
        clientWord = createWord (serverWord.length());
        message = "Game On";
        updateGameData();

        win = false;
        lose = false;
        guessedLetter = false;
    }

    private void endGame(){
        message = "GAME OVER";
        updateGameData();
    }

    private String createWord (int lenght){
        StringBuilder word = new StringBuilder();
        for (int i=0 ; i< lenght ; i++){
            word.append("_");
        }
        return String.valueOf(word);
    }

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
                     guessedLetter = true;
                 }
             }
             if (serverWord.equalsIgnoreCase(clientWord))
                 win = true;
        }

        // check if the word is guessed
        if (serverWord.equalsIgnoreCase(receivedWord))
            win = true;


        if (!guessedLetter) {
            attempts--;}

        if(attempts == 0)
            gameOver();

        if(win)
            gameWin();


        updateGameData();
    }

    private void updateGameData(){
        StringBuilder temp = new StringBuilder();
        temp.append(score);
        temp.append("/");
        temp.append(attempts);
        temp.append("/");
        temp.append(clientWord);
        temp.append("/");
        temp.append(message);
        gameData = String.valueOf(temp);
    }

    private void gameOver (){
        score--;
        message = "YOU LOSE! :( ";
        gameInProgress = false;
        clientWord = serverWord;
    }

    private void gameWin (){
        score++;
        message = "YOU WIN! :) ";
        gameInProgress = false;
        clientWord = serverWord;
    }








}
