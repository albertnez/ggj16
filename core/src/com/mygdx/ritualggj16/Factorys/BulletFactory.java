package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
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
        RenderComponent rc = new RenderComponent(new Sprite(new TextureRegion(tex, 0, 0, 16, 16)));
        rc.rotation = angle-90;
        bullet.add(rc);

        bullet.add(new PositionComponent(x, y));
        bullet.add(new VelocityComponent(
                        300* MathUtils.cosDeg(angle ),
                        300* MathUtils.sinDeg(angle ))
        );

        //bullet.add(new CollisionComponent(14, 16));
        //bullet.add(new TypeComponent(TypeComponent.TypeEntity.BULLET));

        BulletComponent bc = new BulletComponent();
        bc.lifeTime = 5.0f;
        bullet.add(bc);

        gaem.engine.addEntity(bullet);
    }
}