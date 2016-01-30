package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.Utils;

/**
 * Created by anon on 1/30/16.
 */
public class EnemyFactory {
    public static Gaem gaem;

    public static void spawnWalker(float x, float y)
    {
        Entity enemy = gaem.engine.createEntity()
                .add(new PositionComponent(x, y))
                .add(new VelocityComponent(0, 0))
                .add(new EnemyComponent(EnemyComponent.EnemyType.Walker))
                .add(new TypeComponent(TypeComponent.EntityType.Enemy))
                .add(new LifeComponent(2))
                .add(new AnimationComponent(AnimationFactory.blob()))
                .add(new CollisionComponent(10*4, 8*4))
                .add(new RenderComponent(Utils.dumbSprite(10*4, 8*4), RenderComponent.Layer.Player));

        gaem.engine.addEntity(enemy);
    }

    public static void spawnWarrior(float x, float y)
    {
        Entity enemy = gaem.engine.createEntity()
                .add(new PositionComponent(x, y))
                .add(new VelocityComponent(0, 0))
                .add(new EnemyComponent(EnemyComponent.EnemyType.Warrior))
                .add(new TypeComponent(TypeComponent.EntityType.Enemy))
                .add(new LifeComponent(1))
                .add(new AnimationComponent(AnimationFactory.blobWarrior()))
                .add(new CollisionComponent(10*4, 8*4))
                .add(new RenderComponent(Utils.dumbSprite(10*4, 8*4), RenderComponent.Layer.Player));

        gaem.engine.addEntity(enemy);
    }
}
