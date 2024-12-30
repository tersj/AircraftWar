package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.*;
import edu.hitsz.shoot.SanShoot;
import edu.hitsz.shoot.StrategyShoot;
import edu.hitsz.shoot.ZhiShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 精英敌机
 * 可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemy {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
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

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向下发射：1，向上发射：-1)
     */
    private int direction = 1;

    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    @Override
    public List<BaseBullet> shoot(){
        Context context=new Context(new ZhiShoot());
        return context.exeStrategy(this);
    }

    @Override
    public int Score(){return 50;}

    private AbstractProp prop;
    private PropFactory propFactory;
    @Override
    public List<AbstractProp> product(int x,int y) {
        List<AbstractProp> props = new LinkedList<>();
        double r = Math.random();
        String chooseProp = null;
        if(r<0.3){
            chooseProp="bomb";
        }
        else if(r>=0.3 && r<0.6){
            chooseProp="blood";
        }
        else if(r>=0.6 && r<1){
            chooseProp="bullet";
        }
        switch (chooseProp){
            case "blood":
                propFactory = new BloodFactory();
                prop = propFactory.createProp(x,y);
                props.add(prop);
                break;
            case "bomb":
                propFactory = new BombFactory();
                prop = propFactory.createProp(x,y);
                props.add(prop);
                break;
            case "bullet":
                propFactory = new BulletFactory();
                prop = propFactory.createProp(x,y);
                props.add(prop);
                break;
            default:
        }
        return props;
    }
    @Override
    public int getShootNum(){return shootNum;}
    @Override
    public int getPower(){return power;}
    @Override
    public int getDirection(){return direction;}
}
