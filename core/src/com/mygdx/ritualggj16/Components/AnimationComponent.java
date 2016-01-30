package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class AnimationComponent implements Component
{
    public Animation animation;
    public float timer = 0.0f;

    public AnimationComponent(Animation animation)
    {
        this.animation = animation;
        this.timer = 0.0f;
    }

}

