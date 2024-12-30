package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class EnemyList implements MyObserver{
    private List<BaseBullet> enemyBullets;
    private List<AbstractEnemy> enemies;
    public EnemyList(List<AbstractEnemy> enemies,List<BaseBullet> enemyBullets){
        this.enemies=enemies;
        this.enemyBullets=enemyBullets;
    }
    @Override
    public void response() {
        for(AbstractEnemy enemy:enemies){
            if (enemy instanceof EliteEnemy || enemy instanceof MobEnemy){
                enemy.decreaseHp(999999999);
                Game.score+=enemy.Score();
            }
        }
    }
}
