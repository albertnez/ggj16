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


    /** TEXTAQUENS **/
    enum DialogOwner {
        PLAYER_1,
        PLAYER_2
    };


    public static String dialogs[] = {
        "KE APSA IYO",
        "OSTI POS WENO",
        "LLA PUTO BES NEN",
        "ENGA AMOS AL LIO NO O KE"
    };
    public static DialogOwner dialog_owner[] =
    {
        DialogOwner.PLAYER_1,
        DialogOwner.PLAYER_2,
        DialogOwner.PLAYER_1,
        DialogOwner.PLAYER_2,
    };

    public static float textTimer = 0.0f;
    public static int textIndex = 0;
    public static float textDuration = 2.0f;

    public static boolean nextText()
    {
        textTimer = 0.0f;
        textIndex++;

        return (textIndex != dialogs.length);
    }

    public static String getText()
    {
        String txt = dialogs[textIndex];
        return txt.substring(0,
            Math.min(
                (int)((textTimer/textDuration)*txt.length()),
                txt.length()
            )
        );
    }

    public static boolean isTextTimerFinished()
    {
        return textTimer > textDuration;
    }
}
