package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.shoot.SanShoot;
import edu.hitsz.shoot.StrategyShoot;
import edu.hitsz.shoot.ZhiShoot;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

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
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    public List<BaseBullet> shoot(){
        if (shootNum==1){
            Context context=new Context(new ZhiShoot());
            return context.exeStrategy(this);
        }
        else {
            Context context=new Context(new SanShoot());
            return context.exeStrategy(this);
        }
    }

    /**
     * 单例模式创建英雄机
     * @return  英雄机
     */
    private static HeroAircraft instance;

    public static synchronized HeroAircraft getInstance(){
        if (instance == null){
            synchronized (HeroAircraft.class){
                if (instance == null){
                    instance = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 3000);
                }
            }
        }
        return instance;
    }

    /**
     * 子弹增加
     * @param num
     */
    public void bulletNum(int num){
        shootNum += num;
    }

    @Override
    public int getShootNum(){return shootNum;}
    public void setShootNum(int i){shootNum=shootNum+i;}
    @Override
    public int getDirection(){return direction;}
    @Override
    public int getPower(){return power;}
}
