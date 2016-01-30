package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class AnimationComponent implements Component
{
    public Animation animation;
    public float timer = 0.0f;

    public float variant;

    public AnimationComponent(Animation animation)
    {
        this.animation = animation;
        this.timer = 0.0f;
        variant = MathUtils.random(0.0f, 1.0f);
    }

}

