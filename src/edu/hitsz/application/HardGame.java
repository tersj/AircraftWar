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

public class HardGame extends Game{
    BufferedImage BACKGROOUND= ImageIO.read(new FileInputStream("src/images/bg4.jpg"));
    private EnemyFactory enemyFactory;
    private AbstractEnemy enemy;
    private int enemyMax = 4;

    private int creatBossScore=1;
    private int hpIncreace=1;
    private int i=1;
    private int j=1;

    public HardGame() throws IOException {
        ImageManager imageManager = new ImageManager();
        imageManager.setBackgroundImage(BACKGROOUND);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public java.util.List<AbstractEnemy> enemyProduce(List<AbstractEnemy> enemies) {
        Random random=new Random();
        switch (random.nextInt(4)){
            case 0:
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
            case 1: case 2:case 3:
                if(enemies.size() < enemyMax) {
                    enemyFactory = new EliteFactory();
                    try {
                        Field f_speedX=enemyFactory.getClass().getDeclaredField("speedX");
                        //Field f_speedY=enemyFactory.getClass().getDeclaredField("speedY");
                        Field f_hp=enemyFactory.getClass().getDeclaredField("hp");
                        f_speedX.setAccessible(true);
                        //f_speedY.setAccessible(true);
                        f_hp.setAccessible(true);
                        f_speedX.set(enemyFactory,enemyFactory.getEnemySpeedX()+degreeIncrease/5);
                        //f_speedY.set(enemyFactory,enemyFactory.getEnemySpeedY()+degreeIncrease/5);
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
            try {

                Field f_hp=enemyFactory.getClass().getDeclaredField("hp");
                f_hp.setAccessible(true);
                f_hp.set(enemyFactory,enemyFactory.getEnemyHp()*hpIncreace);
                System.out.println("boss机生命值变为："+enemyFactory.getEnemyHp());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            enemy = enemyFactory.createEnemy();
            enemies.add(enemy);
            boss++;
            creatBossScore=creatBossScore*2;
            hpIncreace++;
        }
        if (degreeIncrease/10>=i){
            enemyMax++;
            i++;
        }

        if(degreeIncrease/5>=j){
            j++;
            System.out.println("游戏难度增加，普通敌机的y轴方向速度比原来提升："+degreeIncrease/5+
                    ",精英敌机的x轴方向速度比原来提升："+degreeIncrease/5+
                    ",敌机生命值比原来提升："+degreeIncrease+
                    ",现在的敌机最大数量："+enemyMax);
        }
        return enemies;
    }
}
