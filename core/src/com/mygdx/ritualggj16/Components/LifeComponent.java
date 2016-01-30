package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/30/16.
 */
public class LifeComponent implements Component
{
    public int life;
    public int maxLife;


    public LifeComponent(int life)
    {
        this.life = life;
        this.maxLife = life;
    }
}
