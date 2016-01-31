package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.RenderEffectComponent;
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

    public static void shootBullet(float x, float y, float angle, OwnerComponent.Owner owner)
    {
        Entity bullet = gaem.engine.createEntity();

        Texture tex = TextureManager.getTexture(
                owner == OwnerComponent.Owner.Player1 ?
                "images/bullets.png":"images/bones.png"
        );
        RenderComponent rc = new RenderComponent(
                new Sprite(new TextureRegion(tex, 0, 0, 16, 16)),
                RenderComponent.Layer.Bullet);
        rc.rotation = angle-90;
        rc.scale = 2.0f;
        bullet.add(rc);

        bullet.add(new PositionComponent(x, y));
        bullet.add(new VelocityComponent(
                        600* MathUtils.cosDeg(angle ),
                        600* MathUtils.sinDeg(angle ))
        );
        bullet.add(new LifeComponent(1));
        bullet.add(new CollisionComponent(8, 8));
        bullet.add(new TypeComponent(TypeComponent.EntityType.Bullet));
        bullet.add(new OwnerComponent(owner));

        if (owner == OwnerComponent.Owner.Player1)
        {
            bullet.add(new AnimationComponent(AnimationFactory.bullet()));
        }
        else
        {
            bullet.add(new RenderEffectComponent(999.0f, 2.0f, 2.0f, 1.0f, 1.0f, false, true, true));
        }
        BulletComponent bc = new BulletComponent();
        bc.lifeTime = 3.0f;
        bullet.add(bc);

        gaem.engine.addEntity(bullet);
    }
}
