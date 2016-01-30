package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Factorys.FXFactory;
import com.mygdx.ritualggj16.Gaem;
import com.mygdx.ritualggj16.Mappers;


/**
 * Created by threpwood on 20/12/2015.
 */
public class CollisionSystem extends IteratingSystem
{

    private Engine engine;

    public CollisionSystem(Engine engine)
    {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
    }

    public void processEntity(Entity entity, float deltaTime) {
        Family family = Family.all(CollisionComponent.class).get();
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        PositionComponent pos_me = Mappers.position.get(entity);
        CollisionComponent col_me = Mappers.collision.get(entity);
        Rectangle rect_me = new Rectangle(pos_me.x - col_me.sizeX * 0.5f,
                pos_me.y - col_me.sizeY * 0.5f,
                col_me.sizeX,
                col_me.sizeY);

        for (Entity other : entities) {
            if (other == entity) continue;

            PositionComponent pos_other = Mappers.position.get(other);
            CollisionComponent col_other = Mappers.collision.get(other);
            Rectangle rect_other = new Rectangle(
                    pos_other.x - col_other.sizeX * 0.5f,
                    pos_other.y - col_other.sizeY * 0.5f,
                    col_other.sizeX,
                    col_other.sizeY);

            if (rect_me.overlaps(rect_other))
            {
                TypeComponent et = Mappers.type.get(entity);
                TypeComponent ot = Mappers.type.get(other);
                if (et.type == TypeComponent.EntityType.Enemy && ot.type == TypeComponent.EntityType.Player)
                {
                    Mappers.life.get(other).life -= Mappers.enemy.get(entity).damage;
                }
                else if (Mappers.bullet.has(entity) &&
                        ot.type == TypeComponent.EntityType.Enemy &&
                        Mappers.life.get(other).life > 0)
                {
                    PositionComponent pos = Mappers.position.get(entity);

                    Mappers.life.get(other).life -= Mappers.bullet.get(entity).damage;
                    engine.removeEntity(entity);
                    FXFactory.MakeHitText(pos.x, pos.y);
                }
            }
        }
    }
}