package com.mygdx.ritualggj16.Factorys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.TextureManager;
import com.mygdx.ritualggj16.Utils;

/**
 * Created by anon on 1/30/16.
 */
public class ItemFactory
{
    public static Gaem gaem;

    public static void spawnAltarEnabler(float x, float y)
    {
        gaem.engine.addEntity(gaem.engine.createEntity()
                .add(new PositionComponent(x, y))
                .add(new TypeComponent(TypeComponent.EntityType.AltarItem))
                .add(new CollisionComponent(10, 10))
                .add(new RenderComponent(Utils.dumbSprite(10 * 2, 10 * 2)))
        );
    }

    public static void spawnCandle(float x, float y)
    {
        gaem.engine.addEntity(gaem.engine.createEntity()
                        .add(new PositionComponent(x, y))
                        .add(new TypeComponent(TypeComponent.EntityType.Decoration))
                        .add(new CollisionComponent(10, 10))
                        .add(new RenderComponent(Utils.dumbSprite(16, 32)) )
                        .add(new AnimationComponent(AnimationFactory.candle()))
        );

    }
}
