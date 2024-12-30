package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BulletFactory implements PropFactory{

    @Override
    public AbstractProp createProp(int locationX,int locationY){
        return new PropBullet(
                locationX,locationY,
                0,5);
    }
}
