package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/30/16.
 */
public class LifeComponent implements Component
{
    public int life;
    public int maxLife;
    public boolean justDied;


    public LifeComponent(int life)
    {
        this.life = life;
        this.maxLife = life;
        this.justDied = false;
    }

    public void damage(int dmg)
    {
        if (life > 0)
        {
            life -= dmg;
            if (life <= 0)
            {
                justDied = true;
            }
        }
    }

    public void updateJustDied()
    {
        justDied = false;
    }
}
