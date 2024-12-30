package edu.hitsz.aircraft;

import edu.hitsz.application.MusicThread;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.*;
import edu.hitsz.shoot.SanShoot;
import edu.hitsz.shoot.StrategyShoot;
import edu.hitsz.shoot.ZhiShoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Boss extends AbstractEnemy{
    public Boss(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    @Override
    public void forward() {
        super.forward();
    }

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向下发射：1，向上发射：-1)
     */
    private int direction = 1;

    @Override
    public List<BaseBullet> shoot(){
        Context context=new Context(new SanShoot());
        return context.exeStrategy(this);
    }

    @Override
    public int Score(){return 100;}

    private AbstractProp prop;
    private PropFactory propFactory;
    @Override
    public List<AbstractProp> product(int x,int y) {
        List<AbstractProp> props = new LinkedList<>();
        int i=0;
        int propNum=3;
        while (i<propNum){
            double r = Math.random();
            String chooseProp;
            if(r<0.3){
                chooseProp="bomb";
            }
            else if(r>=0.3 && r<0.6){
                chooseProp="blood";
            }
            else {
                chooseProp="bullet";
            }
            switch (chooseProp) {
                case "blood":
                    propFactory = new BloodFactory();
                    prop = propFactory.createProp(x + 50*i, y );
                    props.add(prop);
                    break;
                case "bomb":
                    propFactory = new BombFactory();
                    prop = propFactory.createProp(x +50*i, y );
                    props.add(prop);
                    break;
                case "bullet":
                    propFactory = new BulletFactory();
                    prop = propFactory.createProp(x + 50*i, y );
                    props.add(prop);
                    break;
                default:
            }
            i++;
        }

        MusicThread.bossBgm=false;
        MusicThread.bgBgm=true;
        new MusicThread("src/videos/bgm.wav").start();
        return props;
    }

    @Override
    public int getDirection(){return direction;}
    @Override
    public int getShootNum(){return shootNum;}
    @Override
    public int getPower(){return power;}
}
