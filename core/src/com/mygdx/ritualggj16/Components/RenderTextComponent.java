package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class RenderTextComponent implements Component
{
    public BitmapFont font;
    public String text;

    public float lifetime = 0.0f;

    public RenderTextComponent(BitmapFont font, String text)
    {
        this.font = font;
        this.text = text;
    }


}
