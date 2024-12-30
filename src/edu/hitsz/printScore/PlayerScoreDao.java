package edu.hitsz.printScore;

import java.util.List;

public interface PlayerScoreDao {
    List<PlayerScore> getAllScores();
    void doAdd(PlayerScore playerScore);
    void doDelete(List<PlayerScore> playerScores,String time);
}
