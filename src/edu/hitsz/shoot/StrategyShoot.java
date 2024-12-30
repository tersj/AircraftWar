package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface StrategyShoot {
    public abstract List<BaseBullet> fight(AbstractAircraft aircraft);
}
