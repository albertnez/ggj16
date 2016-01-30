package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Mappers;

/**
 * Created by anon on 1/30/16.
 */
public class LifeSystem extends IteratingSystem
{
    Engine engine;

    public LifeSystem(Engine engine)
    {
        super(Family.all(LifeComponent.class).get());
        this.engine = engine;
    }

    // Kill if dead
    public void processEntity(Entity entity, float deltaTime)
    {
        if (Mappers.life.get(entity).life <= 0)
        {
            engine.removeEntity(entity);
        }
    }
}
