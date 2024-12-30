package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class SanShoot implements StrategyShoot{
    @Override
    public List<BaseBullet> fight(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int direction=aircraft.getDirection();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX = -aircraft.getShootNum();
        int speedY = aircraft.getSpeedY() + direction*5;
        int shootNum=aircraft.getShootNum();
        int power=aircraft.getPower();
        BaseBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            if (aircraft instanceof HeroAircraft){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                if(i!=shootNum/2){
                    speedX = speedX + 2;
                    abstractBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                    res.add(abstractBullet);
                }
                else {
                    abstractBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, 0, speedY, power);
                    res.add(abstractBullet);
                }
            }
            else {
                if(i!=shootNum/2){
                    speedX = speedX + 2;
                    abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                    res.add(abstractBullet);
                }
                else {
                    abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, 0, speedY, power);
                    res.add(abstractBullet);
                }
            }
        }
        return res;
    }
}
