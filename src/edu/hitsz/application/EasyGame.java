package edu.hitsz.application;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteFactory;
import edu.hitsz.aircraft.EnemyFactory;
import edu.hitsz.aircraft.MobFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class EasyGame extends Game{
    BufferedImage BACKGROOUND=ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
    private EnemyFactory enemyFactory;
    private AbstractEnemy enemy;
    private int enemyMax = 3;

    public EasyGame() throws IOException {
        ImageManager imageManager = new ImageManager();
        imageManager.setBackgroundImage(BACKGROOUND);
    }

    @Override
    public List<AbstractEnemy> enemyProduce(List<AbstractEnemy> enemies) {
        if (enemies.size() < enemyMax) {
            enemyFactory = new MobFactory();
            enemy = enemyFactory.createEnemy();
            enemies.add(enemy);

        }
        if(enemies.size() < enemyMax) {
            enemyFactory = new EliteFactory();
            enemy = enemyFactory.createEnemy();
            enemies.add(enemy);

        }

        return enemies;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
