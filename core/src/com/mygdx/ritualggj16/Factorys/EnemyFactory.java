package com.mygdx.ritualggj16.Factorys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.Utils;

/**
 * Created by anon on 1/30/16.
 */
public class EnemyFactory {
    public static Gaem gaem;

    public static void spawnWalker(float x, float y) {
        Entity enemy = gaem.engine.createEntity()
                .add(new PositionComponent(x, y))
                .add(new VelocityComponent(0, 0))
                .add(new EnemyComponent(EnemyComponent.EnemyType.Walker))
                .add(new AnimationComponent(AnimationFactory.blob()))
                .add(new RenderComponent(Utils.dumbSprite(20, 20)));

        gaem.engine.addEntity(enemy);
    }
}
