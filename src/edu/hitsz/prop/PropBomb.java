package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 爆炸道具
 * @author hitsz
 */
public class PropBomb extends AbstractProp {
    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public void function(HeroAircraft heroAircraft, List<AbstractEnemy> enemies,List<BaseBullet> enemyBullets){
        System.out.println("BombSupply active!");
        new MusicThread("src/videos/bomb_explosion.wav").start();
        AbstractAim abstractAim=new AimBomb();
        MyObserver ob1,ob2;
        ob1=new EnemyBulletList(enemies,enemyBullets);
        ob2=new EnemyList(enemies,enemyBullets);
        abstractAim.attach(ob1);
        abstractAim.attach(ob2);
        abstractAim.notice();
    }
}
