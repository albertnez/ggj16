package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Mappers;
import com.mygdx.ritualggj16.UltraManager;

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
        if (!UltraManager.isGameActive())
        {
            return;
        }
        PositionComponent pos = Mappers.position.get(entity);
        VelocityComponent vel = Mappers.velocity.get(entity);

        // Apply forces to velocity.
        if (Mappers.collision.has(entity))
        {
            CollisionComponent col = Mappers.collision.get(entity);
            vel.x += col.forceX;
            vel.y += col.forceY;
            col.forceX = 0.0f;
            col.forceY = 0.0f;
        }
        pos.last_x = pos.x;
        pos.last_y = pos.y;

        pos.x += vel.x*deltaTime;
        pos.y += vel.y*deltaTime;
    }
}