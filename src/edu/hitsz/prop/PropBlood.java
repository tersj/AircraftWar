package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 加血道具
 * @author hitsz
 */
public class PropBlood extends AbstractProp {
    private int hpRaise=100;
    private HeroAircraft heroAircraft;

    public PropBlood(int locationX, int locationY, int speedX, int speedY) {
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
        new MusicThread("src/videos/get_supply.wav").start();
        heroAircraft.increaseHp(hpRaise);
    }
}
