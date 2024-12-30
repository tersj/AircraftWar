package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class EnemyBulletList implements MyObserver{
    private List<BaseBullet> enemyBullets;
    private List<AbstractEnemy> enemies;
    public EnemyBulletList(List<AbstractEnemy> enemies,List<BaseBullet> enemyBullets){
        this.enemies=enemies;
        this.enemyBullets=enemyBullets;
    }

    @Override
    public void response() {
        enemyBullets.removeAll(enemyBullets);
    }
}
