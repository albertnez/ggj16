package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/29/16.
 */
public class TypeComponent implements Component {

    public enum EntityType {
        Player,
        Enemy,
        Bullet,
        AltarPoint,
        AltarItem,
    }

    public EntityType type;

    public TypeComponent(EntityType type) {
        this.type = type;
    }
}
