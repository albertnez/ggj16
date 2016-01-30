package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by ThrepwooD on 29/01/2016.
 */

public class RenderComponent implements Component
{
    public enum Layer {
        Background,
        Ground,
        Items,
        Explosion,
        Enemy,

        Bullet,
        Player,
        Text,
    }
    public Sprite spr;
    public float rotation = 0.0f;
    public float scale = 1.0f;
    public int layer;  // Lower is higher preference.

    public boolean invert = false;

    public RenderComponent(Sprite spr)
    {
        this(spr, Layer.Ground);
    }
    public RenderComponent(Sprite spr, Layer layer)
    {
        this(spr, layer, 1.0f);
    }

    public RenderComponent(Sprite spr, Layer layer, float scale)
    {
        this.spr = spr;
        this.layer = layer.ordinal();
        this.scale = scale;
    }
}