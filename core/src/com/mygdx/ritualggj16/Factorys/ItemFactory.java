package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.ItemComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.TextureManager;
import com.mygdx.ritualggj16.Utils;

/**
 * Created by anon on 1/30/16.
 */
public class ItemFactory
{
    public static Gaem gaem;

    public static Entity spawnAltar()
    {
        Sprite spr_altar = new Sprite(TextureManager.getTexture("images/altar.png"), 20, 32);
        Entity altar = gaem.engine.createEntity()
                .add(new PositionComponent(0, 0))
                .add(new VelocityComponent(0, 0))
                .add(new TypeComponent(TypeComponent.EntityType.Altar))
                .add(new LifeComponent(250))
                .add(new RenderComponent(spr_altar, RenderComponent.Layer.Player, 3.0f))
                .add(new AnimationComponent(AnimationFactory.altarGirl()))
                .add(new CollisionComponent(20.0f * 3.0f, 32.0f * 3.0f));
        gaem.engine.addEntity(altar);

        return altar;
    }

    public static void spawnAltarEnabler(float x, float y, int id)
    {
        if (id == -1)
        {
            return;
        }
        gaem.engine.addEntity(gaem.engine.createEntity()
                        .add(new PositionComponent(x, y))
                        .add(new TypeComponent(TypeComponent.EntityType.AltarItem))
                        .add(new CollisionComponent(16 * 4, 16 * 4))
                        .add(new RenderComponent(Utils.dumbSprite(16 * 4, 16 * 4), RenderComponent.Layer.Ground))
                        .add(new AnimationComponent(AnimationFactory.altarItem(id)))
                        .add(new ItemComponent(id))
        );
    }

    public static Entity spawnCandle(float x, float y)
    {
        RenderComponent renderComponent = new RenderComponent(Utils.dumbSprite(16, 32), RenderComponent.Layer.Ground);

        renderComponent.invert = MathUtils.randomBoolean();

        Sprite spr_candle = new Sprite(TextureManager.getTexture("images/items.png"), 0, 16, 8, 16);
        Entity entity = gaem.engine.createEntity();
        gaem.engine.addEntity(entity
                        .add(new PositionComponent(x, y))
                        .add(new TypeComponent(TypeComponent.EntityType.Decoration))
                        .add(new RenderComponent(spr_candle, RenderComponent.Layer.Candle))
        );
        return entity;
    }
}
