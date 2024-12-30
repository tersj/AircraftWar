package edu.hitsz.aircraft;

public interface EnemyFactory {
    public abstract AbstractEnemy createEnemy();
    public abstract int getEnemyHp();
    public abstract int getEnemySpeedX();
    public abstract int getEnemySpeedY();
}
