package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Mappers;

/**
 * Created by threpwood on 20/12/2015.
 */
public class MovementSystem extends IteratingSystem
{
    Engine engine;

    public MovementSystem(Engine engine)
    {
        super(Family
                .all(PositionComponent.class, VelocityComponent.class)
                .get());

        this.engine = engine;
    }

    public void processEntity(Entity entity, float deltaTime)
    {
        PositionComponent pos = Mappers.position.get(entity);
        VelocityComponent vel = Mappers.velocity.get(entity);

        pos.x += vel.x*deltaTime;
        pos.y += vel.y*deltaTime;
    }
}