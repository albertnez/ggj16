package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PositionComponent implements Component
{
    public float x = 0.0f;
    public float y = 0.0f;

    public float last_x = 0.0f;
    public float last_y = 0.0f;

    public PositionComponent() {}

    public PositionComponent(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
}