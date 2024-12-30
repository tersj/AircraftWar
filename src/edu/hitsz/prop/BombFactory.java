package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BombFactory implements PropFactory{

    @Override
    public AbstractProp createProp(int locationX,int locationY){
        return new PropBomb(
                locationX,locationY,
                0,5);
    }
}
