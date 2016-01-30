package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.RenderEffectComponent;
import com.mygdx.ritualggj16.Components.RenderTextComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.Mappers;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class RenderSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities;
    private ImmutableArray<Entity> texts;
    SpriteBatch batch;
    Camera cam;
    Sprite spr;

    Gaem gaem;

    public RenderSystem(Gaem gaem)
    {
        this.gaem = gaem;

        this.batch = gaem.batch;
        this.cam = gaem.cam;
        this.spr = new Sprite();
    }

    @Override
    public void addedToEngine (Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderComponent.class
        ).get());

        texts = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderTextComponent.class
        ).get());

    }

    @Override
    public void removedFromEngine (Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderComponent.class
        ).get());

        texts = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderTextComponent.class
        ).get());
    }

    @Override
    public void update (float deltaTime)
    {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        //Draw Entities Normales
        for (int i = 0; i < entities.size(); ++i)
        {
            Entity e = entities.get(i);

            RenderComponent rc = Mappers.render_comp.get(e);
            PositionComponent pos = Mappers.position.get(e);

            rc.spr.setAlpha(1.0f);

            if (Mappers.animation.has(e))
            {
                AnimationComponent anim = Mappers.animation.get(e);
                anim.timer += deltaTime;
                rc.spr.setRegion(anim.animation.getKeyFrame(anim.timer));
                if (anim.animation.getPlayMode() == Animation.PlayMode.NORMAL
                        && anim.timer > anim.animation.getAnimationDuration())
                {
                    e.remove(AnimationComponent.class);
                    gaem.engine.removeEntity(e);
                }
            }


            if (Mappers.render_effect.has(e))
            {
                RenderEffectComponent effect = Mappers.render_effect.get(e);
                effect.timer += deltaTime;

                float sc = Interpolation.linear.apply(
                        effect.scale_start, effect.scale_end,
                        effect.timer / effect.timer_total);
                rc.spr.setScale(sc, sc);

                float a = Interpolation.linear.apply(
                        effect.alpha_start, effect.alpha_end,
                        effect.timer / effect.timer_total
                );
                rc.spr.setAlpha(a);

                if (effect.timer > effect.timer_total)
                {
                    e.remove(RenderEffectComponent.class);
                    if (effect.single_use)
                    {
                        gaem.engine.removeEntity(e);
                    }

                }
            }

            rc.spr.setCenterX(pos.x);
            rc.spr.setCenterY(pos.y);

            rc.spr.setRotation(rc.rotation);

            rc.spr.setScale((rc.invert?-rc.scale:rc.scale), rc.scale);

            rc.spr.draw(batch);

        }

        //Draw los Texts
        for (int i = 0; i < texts.size(); ++i)
        {
            Entity e = texts.get(i);

            RenderTextComponent rt = Mappers.renderText.get(e);
            PositionComponent pos = Mappers.position.get(e);

            rt.lifetime += deltaTime;

            rt.font.draw(gaem.batch, rt.text, pos.x, pos.y);

            if (rt.lifetime > 0.8f) gaem.engine.removeEntity(e);

        }


        batch.end();
    }
}