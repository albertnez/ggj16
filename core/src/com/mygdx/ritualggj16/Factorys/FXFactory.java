package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.RenderTextComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.FontManager;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.TextureManager;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class FXFactory
{
    public static Gaem gaem;

    public static void MakeHitText(float x, float y)
    {
        Entity text = gaem.engine.createEntity();

        text.add(new RenderTextComponent(FontManager.damage, "5"))
            .add(new PositionComponent(x, y))
            .add(new VelocityComponent(0, Constants.RES_Y/6));

        gaem.engine.addEntity(text);
    }

}
