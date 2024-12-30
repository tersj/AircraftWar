package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;

import java.util.List;

public abstract class AbstractEnemy extends AbstractAircraft{
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    public abstract int Score();
    public abstract List<AbstractProp> product(int x,int y);

}
