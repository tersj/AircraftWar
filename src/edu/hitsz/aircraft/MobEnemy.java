package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
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
    public List<AbstractProp> product(int x,int y) {
        return new LinkedList<>();
    }

    @Override
    public int Score(){return 10;}

    @Override
    public List<BaseBullet> shoot(){return new LinkedList<>();}
    @Override
    public int getDirection(){return 0;}
    @Override
    public int getShootNum(){return 0;}
    @Override
    public int getPower(){return 0;}

}
