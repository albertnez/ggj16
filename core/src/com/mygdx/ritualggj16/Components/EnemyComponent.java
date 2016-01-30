package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Wikipedia;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anon on 1/30/16.
 */
public class EnemyComponent implements Component {

    public enum EnemyType {
        Walker,
        Warrior
    }

    public EnemyType type;
    public float speed;
    public int damage;
    public float shakePeriod;
    public float shakeTime;
    public boolean shakeY;

    public EnemyComponent(EnemyType type)
    {
        this.type = type;
        this.shakeTime = 0.0f;
        this.shakeY = MathUtils.randomBoolean();
        switch (type)
        {
            case Walker:
                this.speed = Wikipedia.Speed(type);
                this.damage = 5;
                this.shakePeriod = 0.2f;

            case Warrior:
                this.speed = Wikipedia.Speed(type);
                this.damage = 5;
                this.shakePeriod = 0.2f;
        }
    }
}
