package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Wikipedia;

/**
 * Created by anon on 1/30/16.
 */
public class EnemyComponent implements Component {

    public enum EnemyType {
        Walker,
        Warrior,
        Big
    }

    public EnemyType type;
    public float speed;
    public int damage;
    public float shakePeriod;
    public float shakeTime;
    public boolean shakeY;
    public float attackPeriod;
    public float attackCooldown;
    public boolean containsAltarItem;

    public boolean shooting = false;

    public EnemyComponent(EnemyType type, boolean spawnsWithEnemy)
    {
        this.type = type;
        this.shakeTime = 0.0f;
        this.shakeY = MathUtils.randomBoolean();
        this.attackCooldown = 0.0f;
        this.containsAltarItem = spawnsWithEnemy;
        switch (type)
        {
            case Walker:
                this.speed = Wikipedia.Speed(type);
                this.damage = 1;
                this.shakePeriod = 0.2f;
                this.attackPeriod = 3.0f;
                break;
            case Warrior:
                this.speed = Wikipedia.Speed(type);
                this.damage = 2;
                this.shakePeriod = 0.2f;
                this.attackPeriod = 2.0f;
                break;
            case Big:
                this.speed = Wikipedia.Speed(type);
                this.damage = 4;
                this.shakePeriod = 0.2f;
                this.attackPeriod = 5.0f;
                break;
        }
    }
    public EnemyComponent(EnemyType type)
    {
        this(type, false);
    }
}
