package com.mygdx.ritualggj16;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class UltraManager
{
    public static boolean isGaemActive = false;

    public static float textTimer = 0.0f;
    public static int textIndex = 0;

    public static void nextText()
    {
        textTimer = 0.0f;

    }
    public static String txt = "KUIDAO Q BIENE ALESSIO";

    public static String getText()
    {
        return txt.substring(0,
                Math.min(
                        (int)((textTimer/2.0f)*txt.length()),
                        txt.length())
                        );
    }
}
