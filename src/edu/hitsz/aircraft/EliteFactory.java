package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteFactory implements EnemyFactory{

    private int speedX=3;
    private int speedY=5;
    private int hp=50;

    @Override
    public int getEnemyHp(){return hp;}

    @Override
    public int getEnemySpeedX() {
        return speedX;
    }

    @Override
    public int getEnemySpeedY() {
        return speedY;
    }

    @Override
    public AbstractEnemy createEnemy(){
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                speedX,
                speedY,
                hp);
    }
}
