package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class VelocityComponent implements Component
{
    public float x = 0.0f;
    public float y = 0.0f;

    public VelocityComponent() {}

    public VelocityComponent(float speedX, float speedY)
    {
        x = speedX;
        y = speedY;
    }

}