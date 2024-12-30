package edu.hitsz.printScore;

public class PlayerScore {
    private String playerName;
    private int score;
    private String time;
    private String degree;

    public PlayerScore(String playerName, int score, String time, String degree){
        this.playerName=playerName;
        this.score=score;
        this.time=time;
        this.degree=degree;
    }

    public String getPlayerName(){
        return playerName;
    }
    public String getDegree(){return degree;}
    public int getPlayerScore(){
        return score;
    }
    public String getTime(){
        return time;
    }
}
