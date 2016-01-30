package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by ThrepwooD on 29/01/2016.
 */

public class RenderComponent implements Component
{
    public Sprite spr;
    public float rotation;

    public boolean invert = false;

    public RenderComponent(Sprite spr)
    {
        this.spr = spr;
    }
}