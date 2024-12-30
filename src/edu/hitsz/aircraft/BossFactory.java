package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

public class BossFactory implements EnemyFactory{
    private int speedX=3;
    private int speedY=0;
    private int hp=300;

    @Override
    public int getEnemyHp(){
        return hp;
    }

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
        MusicThread.bgBgm=false;
        MusicThread.bossBgm=true;
        new MusicThread("src/videos/bgm_boss.wav").start();
        return new Boss(
                Main.WINDOW_WIDTH / 2,
                ImageManager.BOSS_IMAGE.getHeight()/2,
                speedX,
                speedY,
                hp);
    }
}
