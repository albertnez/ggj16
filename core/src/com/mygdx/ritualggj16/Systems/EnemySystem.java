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

    static private float centerOffsetX = 12.0f;
    static private float centerOffsetY = 30.0f;

    public EnemySystem(Engine engine) {
        super(Family
                .all(EnemyComponent.class, PositionComponent.class, VelocityComponent.class)
                .get());
        this.engine = engine;
    }

    public void processEntity(Entity entity, float deltaTime) {
        EnemyComponent enemy = Mappers.enemy.get(entity);
        enemy.attackCooldown -= deltaTime;
        if (enemy.type == EnemyComponent.EnemyType.Walker ||
                enemy.type == EnemyComponent.EnemyType.Warrior) {
            // Walker enemy just goes to the center for now.
            PositionComponent pos = Mappers.position.get(entity);
            VelocityComponent vel = Mappers.velocity.get(entity);

            if (pos.x >= -centerOffsetX && pos.x <= centerOffsetX &&
                pos.y >= -centerOffsetY && pos.y <= centerOffsetY)
            {
                if (vel.x != 0.0f && vel.y != 0.0)
                {
                    if (enemy.shakeY)
                    {
                        vel.x = 0.0f;
                        vel.y = enemy.speed;
                    }
                    else
                    {
                        vel.y = 0.0f;
                        vel.x = enemy.speed;
                    }
                }
                enemy.shakeTime -= deltaTime;
                if (enemy.shakeTime < -0.0f)
                {
                    enemy.shakeTime = enemy.shakePeriod;
                    vel.x *= -1.0f;
                    vel.y *= -1.0f;
                }
                return;
            }
            float targetX = -pos.x;
            float targetY = -pos.y;
            float alpha = MathUtils.atan2(targetY, targetX);
            vel.x = enemy.speed * MathUtils.cos(alpha);
            vel.y = enemy.speed * MathUtils.sin(alpha);

            Mappers.render_comp.get(entity).invert = (vel.x < 0)? true : false;
        }
    }
}
