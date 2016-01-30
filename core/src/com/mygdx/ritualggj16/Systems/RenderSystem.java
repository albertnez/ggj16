package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
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

import java.util.Comparator;


/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class RenderSystem extends SortedIteratingSystem
{

    SpriteBatch batch;
    Sprite spr;

    Gaem gaem;

    public RenderSystem(Gaem gaem)
    {
        super(Family.one(RenderComponent.class, RenderTextComponent.class).get(), new LayerComparator());
        this.gaem = gaem;

        this.batch = gaem.batch;
        this.spr = new Sprite();
    }

    @Override
    public void processEntity(Entity e, float deltaTime)
    {
        if (Mappers.renderText.has(e))
        {
            RenderTextComponent rt = Mappers.renderText.get(e);
            PositionComponent pos = Mappers.position.get(e);

            rt.lifetime += deltaTime;

            rt.font.draw(gaem.batch, rt.text, pos.x, pos.y);

            if (rt.lifetime > 0.8f) gaem.engine.removeEntity(e);
        }
        else if (Mappers.render_comp.has(e))
        {
            RenderComponent rc = Mappers.render_comp.get(e);
            PositionComponent pos = Mappers.position.get(e);

            rc.spr.setAlpha(1.0f);

            if (Mappers.animation.has(e))
            {
                AnimationComponent anim = Mappers.animation.get(e);
                boolean isNormal = anim.animation.getPlayMode() == Animation.PlayMode.NORMAL;


                anim.timer += deltaTime;
                rc.spr.setRegion(anim.animation.getKeyFrame(
                        anim.timer + (isNormal? 0:anim.variant )
                ));
                if (isNormal
                        && anim.timer > anim.animation.getAnimationDuration())
                {
                    e.remove(AnimationComponent.class);
                    gaem.engine.removeEntity(e);
                }
            }


            //rc.scale = 1.0f;
            if (Mappers.render_effect.has(e))
            {
                RenderEffectComponent effect = Mappers.render_effect.get(e);
                effect.timer += deltaTime;

                float sc = Interpolation.linear.apply(
                        effect.scale_start, effect.scale_end,
                        effect.timer / effect.timer_total);
                rc.scale = sc;

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

            rc.spr.setScale((rc.invert ? -rc.scale : rc.scale), rc.scale);

            rc.spr.draw(batch);
        }
    }

    private static class LayerComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {

            int l1 = Mappers.render_comp.get(e1).layer;
            int l2 = Mappers.render_comp.get(e2).layer;
            if (l1 == l2)
            {
                if (Mappers.position.has(e1) && Mappers.position.has(e2))
                {
                    return (int)Math.signum(Mappers.position.get(e2).y - Mappers.position.get(e1).y);
                }
            }
            return l1 - l2;
        }
    }
}