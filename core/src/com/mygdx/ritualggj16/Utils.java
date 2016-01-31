package com.mygdx.ritualggj16;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class Utils
{
    static public Sprite dumbSprite(int w, int h)
    {
        return new Sprite(TextureManager.getTexture("images/teh_pixel.png"), w, h);
    }

    /* Returns the angle from 8 direction angles closest to the given alpha.
     */
    static public float angleTo8Dir(float alpha)
    {
        if (alpha < MathUtils.PI2)
        {
            alpha += MathUtils.PI2;
        }
        alpha = MathUtils.round(alpha / (MathUtils.PI2 / 8.0f));
        return alpha * MathUtils.PI2 / 8.0f;
    }

}
