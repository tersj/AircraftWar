package edu.hitsz.application;

import edu.hitsz.aircraft.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class NormalGame extends Game{
    BufferedImage BACKGROOUND= ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
    private EnemyFactory enemyFactory;
    private AbstractEnemy enemy;
    private int enemyMax = 4;

    private int creatBossScore=1;
    private int j=1;

    public NormalGame() throws IOException {
        ImageManager imageManager = new ImageManager();
        imageManager.setBackgroundImage(BACKGROOUND);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public List<AbstractEnemy> enemyProduce(List<AbstractEnemy> enemies) {
        Random random=new Random();
        switch (random.nextInt(3)){
            case 0: case 2:
                if (enemies.size() < enemyMax) {
                    enemyFactory = new MobFactory();
                    try {

                        Field f_speedY=enemyFactory.getClass().getDeclaredField("speedY");
                        Field f_hp=enemyFactory.getClass().getDeclaredField("hp");

                        f_speedY.setAccessible(true);
                        f_hp.setAccessible(true);

                        f_speedY.set(enemyFactory,enemyFactory.getEnemySpeedY()+degreeIncrease/5);
                        f_hp.set(enemyFactory,enemyFactory.getEnemyHp()+5*(degreeIncrease/5));

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    enemy = enemyFactory.createEnemy();
                    enemies.add(enemy);

                }
                break;
            case 1:
                if(enemies.size() < enemyMax) {
                    enemyFactory = new EliteFactory();
                    try {
                        Field f_speedX=enemyFactory.getClass().getDeclaredField("speedX");
                        Field f_hp=enemyFactory.getClass().getDeclaredField("hp");
                        f_speedX.setAccessible(true);
                        f_hp.setAccessible(true);
                        f_speedX.set(enemyFactory,enemyFactory.getEnemySpeedX()+degreeIncrease/5);
                        f_hp.set(enemyFactory,enemyFactory.getEnemyHp()+5*(degreeIncrease/5));

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    enemy = enemyFactory.createEnemy();
                    enemies.add(enemy);

                }
                break;
            default:
        }

        if(Game.boss < Game.bossMaxNumber && Game.score>300*creatBossScore){
            enemyFactory = new BossFactory();
            enemy = enemyFactory.createEnemy();
            enemies.add(enemy);
            boss++;
            creatBossScore=creatBossScore*3;
        }
        if(degreeIncrease/5>=j){
            j++;
            System.out.println("游戏难度增加，普通敌机y轴方向速度比原来提升："+degreeIncrease/5+
                    ",精英敌机的x轴方向速度比原来提升："+degreeIncrease/5+
                    ",敌机生命值比原来提升："+degreeIncrease);
        }
        return enemies;
    }
}
