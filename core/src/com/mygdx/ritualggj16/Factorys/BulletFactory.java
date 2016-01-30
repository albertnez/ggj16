package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.TextureManager;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class BulletFactory
{
    public static Gaem gaem;

    public static void shootBullet(float x, float y, float angle)
    {
        Entity bullet = gaem.engine.createEntity();

        Texture tex = TextureManager.getTexture("bullets.png");
        RenderComponent rc = new RenderComponent(new Sprite(new TextureRegion(tex, 0, 0, 16, 16)), RenderComponent.Layer.Bullet);
        rc.rotation = angle-90;
        bullet.add(rc);

        bullet.add(new PositionComponent(x, y));
        bullet.add(new VelocityComponent(
                        600* MathUtils.cosDeg(angle ),
                        600* MathUtils.sinDeg(angle ))
        );
        bullet.add(new LifeComponent(1));
        bullet.add(new CollisionComponent(8, 8));
        bullet.add(new TypeComponent(TypeComponent.EntityType.Bullet));

        //bullet.add(new CollisionComponent(14, 16));
        //bullet.add(new TypeComponent(TypeComponent.TypeEntity.BULLET));

        BulletComponent bc = new BulletComponent();
        bc.lifeTime = 3.0f;
        bullet.add(bc);

        gaem.engine.addEntity(bullet);
    }
}
