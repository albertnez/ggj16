package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.AudioManager;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.InputComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.AnimationFactory;
import com.mygdx.ritualggj16.Factorys.BulletFactory;
import com.mygdx.ritualggj16.Mappers;
import com.mygdx.ritualggj16.UltraManager;
import com.mygdx.ritualggj16.Utils;
import com.mygdx.ritualggj16.Wikipedia;
import com.mygdx.ritualggj16.XBox360Pad;

/**
 * Created by anon on 1/30/16.
 */
public class InputSystem extends IteratingSystem
{
    Engine engine;

    public InputSystem(Engine engine)
    {
        super(Family.all(InputComponent.class).get());
        this.engine = engine;
    }

    public void processEntity(Entity entity, float deltaTime)
    {
        if (!UltraManager.isGaemActive) return;

        InputComponent input = Mappers.input.get(entity);
        Controller controller = Mappers.input.get(entity).controller;
        PositionComponent pos = Mappers.position.get(entity);
        VelocityComponent vel = Mappers.velocity.get(entity);

        input.bulletCooldown -= deltaTime;

        vel.x = 0.0f;
        vel.y = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            vel.x -= Constants.RES_X * deltaTime * 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            vel.x += Constants.RES_X * deltaTime * 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vel.y -= Constants.RES_Y * deltaTime * 10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vel.y += Constants.RES_Y * deltaTime * 10.0f;
        }


        if (controller != null)
        {
            if (Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_X)) > 0.4)
                vel.x = controller.getAxis(XBox360Pad.AXIS_LEFT_X) *
                        Wikipedia.Speed(TypeComponent.EntityType.Player);

            if (Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_Y)) > 0.4)
                vel.y = -controller.getAxis(XBox360Pad.AXIS_LEFT_Y) *
                        Wikipedia.Speed(TypeComponent.EntityType.Player);


            if (Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_X)) > 0.4 ||
                    Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_Y)) > 0.4)
            {
                float ang = MathUtils.radDeg * MathUtils.atan2(
                        -controller.getAxis(XBox360Pad.AXIS_LEFT_Y),
                        controller.getAxis(XBox360Pad.AXIS_LEFT_X));

                AnimationComponent animationComponent = Mappers.animation.get(entity);

                if (ang < 45 && ang > -45)
                {
                    animationComponent.animation = AnimationFactory.playerRight(Mappers.owner.get(entity).owner);
                }
                else if (ang > 45 && ang < 45+90)
                {
                    animationComponent.animation = AnimationFactory.playerUp(Mappers.owner.get(entity).owner);
                }
                else if (ang < -45 && ang > -45-90)
                {
                    animationComponent.animation = AnimationFactory.playerDown(Mappers.owner.get(entity).owner);
                }
                else
                {
                    animationComponent.animation = AnimationFactory.playerLeft(Mappers.owner.get(entity).owner);
                }

            }



            if (Math.abs(controller.getAxis(XBox360Pad.AXIS_RIGHT_X)) > 0.4 ||
                Math.abs(controller.getAxis(XBox360Pad.AXIS_RIGHT_Y)) > 0.4)
            {

                if (input.bulletCooldown < 0 + UltraManager.losetas*0.08f)
                {
                    float alpha = Utils.angleTo8Dir(MathUtils.atan2(
                            -controller.getAxis(XBox360Pad.AXIS_RIGHT_Y),
                            controller.getAxis(XBox360Pad.AXIS_RIGHT_X)
                    ));

                    input.bulletCooldown = input.bulletDelay;

                    BulletFactory.shootBullet(
                            pos.x,
                            pos.y,
                            MathUtils.radDeg * alpha,
                            Mappers.owner.get(entity).owner
                    );
                    AudioManager.player.play();
                }



            }

            if (controller.getAxis(XBox360Pad.AXIS_RIGHT_TRIGGER) < -0.75f) {
                // BulletFactory.shootBullet(pos.x, pos.y, 0);
            }
        }
    }
}
