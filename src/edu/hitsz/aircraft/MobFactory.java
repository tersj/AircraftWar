package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobFactory implements EnemyFactory{
    private int speedX=0;
    private int speedY=8;
    private int hp=30;

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
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                speedX,
                speedY,
                hp);
    }
}
