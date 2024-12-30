package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ZhiShoot implements StrategyShoot{
    @Override
    public List<BaseBullet> fight(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int direction=aircraft.getDirection();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedY = aircraft.getSpeedY() + direction*5;
        int shootNum=aircraft.getShootNum();
        int power=aircraft.getPower();
        BaseBullet abstractBullet;
        if(aircraft instanceof HeroAircraft){
            abstractBullet = new HeroBullet(x + (- shootNum + 1)*10, y, 0, speedY, power);
            res.add(abstractBullet);
        }
        else {
            abstractBullet = new EnemyBullet(x + (- shootNum + 1)*10, y, 0, speedY, power);
            res.add(abstractBullet);
        }
        return res;
    }
}
