package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Factorys.FXFactory;
import com.mygdx.ritualggj16.Mappers;
import com.mygdx.ritualggj16.UltraManager;

import java.util.Map;

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
        if (Mappers.life.get(entity).justDied)
        {
            System.out.println("entity just died!");
            Mappers.life.get(entity).updateJustDied();
            if (Mappers.type.get(entity).type == TypeComponent.EntityType.Altar)
            {
                System.out.println("Setting game over dialog");
                UltraManager.setState(UltraManager.State.GameOverDialog);
                return;
            }
            PositionComponent pos = Mappers.position.get(entity);
            FXFactory.makeDissapearEnemy(
                    pos.x, pos.y,
                    Mappers.enemy.get(entity),
                    Mappers.render_comp.get(entity).invert);
            engine.removeEntity(entity);
        }
    }
}
