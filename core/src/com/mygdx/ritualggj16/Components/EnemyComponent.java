package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anon on 1/30/16.
 */
public class EnemyComponent implements Component {

    public enum EnemyType {
        Walker,
    }

    public EnemyType type;
    public float speed;
    public int damage;

    public EnemyComponent(EnemyType type) {
        this.type = type;
        switch (type) {
            case Walker:
                this.speed = 50.0f;
                this.damage = 5;
        }
    }
}
