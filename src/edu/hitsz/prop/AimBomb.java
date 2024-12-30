package edu.hitsz.prop;

public class AimBomb extends AbstractAim{
    @Override
    public void notice() {
        for (Object obs:observers){
            ((MyObserver)obs).response();
        }
    }
}
