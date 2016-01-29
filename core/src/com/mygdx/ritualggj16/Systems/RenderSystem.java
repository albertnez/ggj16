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
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Mappers;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class RenderSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities;
    SpriteBatch batch;
    Camera cam;
    Sprite spr;

    public RenderSystem(SpriteBatch batch, Camera cam)
    {
        this.batch = batch;
        this.cam = cam;
        this.spr = new Sprite();
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderComponent.class
        ).get());

    }

    @Override
    public void removedFromEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                RenderComponent.class
        ).get());
    }

    @Override
    public void update (float deltaTime)
    {

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.get(i);

            RenderComponent rc = Mappers.render_comp.get(e);
            PositionComponent pos = Mappers.position.get(e);

            rc.spr.setAlpha(1.0f);

            /*
            if (Mappers.animation.has(e))
            {
                AnimationComponent anim = Mappers.animation.get(e);
                anim.timer += deltaTime;
                rc.spr.setRegion(anim.animation.getKeyFrame(anim.timer));
                if (anim.animation.getPlayMode() == Animation.PlayMode.NORMAL
                        && anim.timer > anim.animation.getAnimationDuration())
                {
                    //Todo:Make a TimeOutSystem and TimeOutComponent and get rid of this shit
                    e.remove(AnimationComponent.class);
                    Gaem.engine.removeEntity(e);
                }
            }
*/
            rc.spr.setCenterX(pos.x);
            rc.spr.setCenterY(pos.y);

            rc.spr.setRotation(rc.rotation);

            rc.spr.draw(batch);

        }
        batch.end();
    }
}