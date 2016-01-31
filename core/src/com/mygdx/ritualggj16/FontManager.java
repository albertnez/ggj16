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

    public static BitmapFont damage_altar;
    public static BitmapFont dialog;

    public static void generateAll()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BitDarling.ttf"));

        damage = new BitmapFont();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.RED;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        damage = generator.generateFont(parameter);
        damage.getData().markupEnabled = true;

        damage_altar = new BitmapFont();
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.YELLOW;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.RED;
        damage_altar = generator.generateFont(parameter);
        damage_altar.getData().markupEnabled = true;

        dialog = new BitmapFont();
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        dialog = generator.generateFont(parameter);
        dialog.getData().markupEnabled = true;


    }

}
