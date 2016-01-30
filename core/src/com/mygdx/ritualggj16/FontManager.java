package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class FontManager
{

    public static BitmapFont damage;

    public static void generateAll()
    {
        damage = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("bitDarling.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.RED;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        damage = generator.generateFont(parameter);
        damage.getData().markupEnabled = true;
    }

}
