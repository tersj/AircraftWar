package edu.hitsz.prop;

import java.util.ArrayList;

public abstract class AbstractAim {
    protected ArrayList observers=new ArrayList();

    public void attach(MyObserver observer){
        observers.add(observer);
    }

    public abstract void notice();
}
