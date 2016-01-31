package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.RenderEffectComponent;
import com.mygdx.ritualggj16.Components.RenderTextComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.FontManager;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.TextureManager;
import com.mygdx.ritualggj16.Utils;

import java.util.Random;

import javax.rmi.CORBA.Util;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class FXFactory
{
    public static Gaem gaem;

    public static void MakeHitText(float x, float y)
    {
        Entity text = gaem.engine.createEntity();

        text.add(new RenderTextComponent(FontManager.damage, "1"))
            .add(new PositionComponent(x, y))
            .add(new RenderComponent(Utils.dumbSprite(0, 0), RenderComponent.Layer.Text))
            .add(new VelocityComponent(
                    MathUtils.random(-30, 30),
                    Constants.RES_Y / 6 * MathUtils.random(0.75f, 1.25f)))
            .add(new RenderEffectComponent(1.0f, 0.5f, 1.0f, 1.0f, 0.0f, false))
        ;

        gaem.engine.addEntity(text);
    }



    public static void makeDissapearEnemy(float x, float y, EnemyComponent monster, boolean inver)
    {
        Entity enemy = gaem.engine.createEntity();
        if (monster.type == EnemyComponent.EnemyType.Walker)
        {
            RenderComponent rc = new RenderComponent(Utils.dumbSprite(10*4, 8*4), RenderComponent.Layer.Player);
            rc.invert = inver;
            enemy.add(rc);
            Animation anim = AnimationFactory.blobDie();
            anim.setPlayMode(Animation.PlayMode.LOOP);
            enemy.add(new AnimationComponent(anim));
        }
        else if (monster.type == EnemyComponent.EnemyType.Warrior)
        {
            RenderComponent rc = new RenderComponent(Utils.dumbSprite(10*4, 8*4), RenderComponent.Layer.Player);
            rc.invert = inver;
            enemy.add(rc);
            Animation anim = AnimationFactory.blobWarriorDie();
            anim.setPlayMode(Animation.PlayMode.LOOP);
            enemy.add(new AnimationComponent(anim));
        }

        enemy.add(new PositionComponent(x, y));
        enemy.add(new RenderEffectComponent(0.3f, 1.0f, 3.0f, 0.9f, 0.1f, true));
        gaem.engine.addEntity(enemy);

    }

    public enum ExplosionType
    {
        PURPLE,
        BONES
    }


    public static void makeExplosion(float x, float y, ExplosionType type)
    {
        Texture tex = TextureManager.getTexture(
                type == ExplosionType.BONES?
                    "images/bones.png":
                    "images/bullets.png"
        );
        Animation anim = new Animation(0.25f/5,
                new TextureRegion(tex, 16, 0, 16 ,16),
                new TextureRegion(tex, 16, 16, 16 ,16),
                new TextureRegion(tex, 16, 16, 16 ,16),
                new TextureRegion(tex, 16, 32, 16 ,16),
                new TextureRegion(tex, 16, 32, 16 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.NORMAL);

        Entity entity = gaem.engine.createEntity();
        entity.add(new PositionComponent(x, y));
        entity.add(new AnimationComponent(anim));
        entity.add(new RenderComponent(
                new Sprite(new TextureRegion(tex, 16, 0, 16 ,16)),
                RenderComponent.Layer.Explosion,
                1.0f));
        entity.add(new RenderEffectComponent(
                0.25f,
                1.0f, 5.0f,
                1.0f, 0.6f,
                false,
                type == ExplosionType.BONES, false));
        gaem.engine.addEntity(entity);
    }

}
