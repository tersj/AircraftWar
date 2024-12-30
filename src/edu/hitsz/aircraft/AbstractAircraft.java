package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.shoot.StrategyShoot;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }
    public void increaseHp(int increase){
        hp += increase;
        if(hp >= maxHp){
            hp = maxHp;
        }
    }
    public int getHp() {
        return hp;
    }

    public abstract List<BaseBullet> shoot();

    public abstract int getDirection();

    public abstract int getShootNum();

    public abstract int getPower();

    public class Context{
        private StrategyShoot strategyShoot;
        public Context(StrategyShoot strategyShoot){
            this.strategyShoot=strategyShoot;
        }
        public List<BaseBullet> exeStrategy(AbstractAircraft aircraft){
            return strategyShoot.fight(aircraft);
        }
    }
}


