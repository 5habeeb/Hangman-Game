package Game.Server.model;

public class Game {
    //testing
    private String gameData = "Default Data";

    private String dataSet = Word.randomWord();
    private int score = 0;
    private int attempts;
    private String word;
    private String message;



    // communicating with the controller
    public String getGamedata(){
        return gameData;
    }

    public void setGamedata( String str ){
        System.out.println("got to the server");
        gameData = Word.randomWord();
    }





}
