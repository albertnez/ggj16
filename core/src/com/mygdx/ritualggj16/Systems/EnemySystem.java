package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Mappers;

/**
 * Created by anon on 1/30/16.
 */
public class EnemySystem extends IteratingSystem {
    Engine engine;

    public EnemySystem(Engine engine) {
        super(Family
                .all(EnemyComponent.class, PositionComponent.class, VelocityComponent.class)
                .get());
        this.engine = engine;
    }

    public void processEntity(Entity entity, float deltaTime) {
        EnemyComponent enemy = Mappers.enemy.get(entity);
        if (enemy.type == EnemyComponent.EnemyType.Walker) {
            // Walker enemy just goes to the center for now.
            PositionComponent pos = Mappers.position.get(entity);
            VelocityComponent vel = Mappers.velocity.get(entity);

            float targetX = -pos.x;
            float targetY = -pos.y;
            float alpha = MathUtils.atan2(targetY, targetX);
            vel.x = enemy.speed * MathUtils.cos(alpha);
            vel.y = enemy.speed * MathUtils.sin(alpha);

            Mappers.render_comp.get(entity).invert = (vel.x < 0)? true : false;
        }
    }
}
