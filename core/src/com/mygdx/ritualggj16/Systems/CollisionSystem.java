package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.ritualggj16.AltarCandles;
import com.mygdx.ritualggj16.AudioManager;
import com.mygdx.ritualggj16.CameraManager;
import com.mygdx.ritualggj16.Components.AltarPointComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderEffectComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Factorys.FXFactory;
import com.mygdx.ritualggj16.FontManager;
import com.mygdx.ritualggj16.Mappers;
import com.mygdx.ritualggj16.TextureManager;



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

        Rectangle rect_me = new Rectangle(
                pos_me.x - col_me.sizeX * 0.45f,
                pos_me.y - col_me.sizeY * 0.45f,
                col_me.sizeX*0.9f,
                col_me.sizeY*0.9f);

        for (Entity other : entities)
        {
            if (other == entity) continue;

            PositionComponent pos_other = Mappers.position.get(other);
            CollisionComponent col_other = Mappers.collision.get(other);
            Rectangle rect_other = new Rectangle(
                    pos_other.x - col_other.sizeX * 0.45f,
                    pos_other.y - col_other.sizeY * 0.45f,
                    col_other.sizeX*0.9f,
                    col_other.sizeY*0.9f
            );

            if (rect_me.overlaps(rect_other))
            {
                TypeComponent et = Mappers.type.get(entity);
                TypeComponent ot = Mappers.type.get(other);

                /*
                //PLAYER vs ENEMY
                if (et.type == TypeComponent.EntityType.Player &&
                    ot.type == TypeComponent.EntityType.Enemy)
                {
                    Mappers.velocity.get(other).x += Mappers.velocity.get(entity).x;
                    Mappers.velocity.get(other).y += Mappers.velocity.get(entity).y;
                }

                else if ((et.type == TypeComponent.EntityType.Enemy ||
                        et.type == TypeComponent.EntityType.Player )
                        && ot.type == TypeComponent.EntityType.Altar)
                {
                    Mappers.velocity.get(entity).x = 0;
                    Mappers.velocity.get(entity).y = 0;
                }*/

                // Constrain move
                if (isObstacle(et.type, ot.type))
                {
                    if (!isFixed(ot.type))
                    {

                    }
                    else
                    {
                        Rectangle fixedX = new Rectangle(rect_me);
                        Rectangle fixedY = new Rectangle(rect_me);
                        fixedX.setX(pos_me.last_x - col_me.sizeX * 0.5f);
                        fixedY.setY(pos_me.last_y - col_me.sizeY * 0.5f);
                        if (!fixedX.overlaps(rect_other))
                        {
                            pos_me.x = pos_me.last_x;
                        } else if (!fixedY.overlaps(rect_other))
                        {
                            pos_me.y = pos_me.last_y;
                        } else
                        {
                            pos_me.x = pos_me.last_x;
                            pos_me.y = pos_me.last_y;
                        }
                    }
                }

                //BULLET vs ENEMY
                else if (Mappers.bullet.has(entity) &&
                        Mappers.owner.get(entity).owner != OwnerComponent.Owner.Enemy &&
                        ot.type == TypeComponent.EntityType.Enemy &&
                        Mappers.life.get(other).life > 0)
                {
                    PositionComponent pos = Mappers.position.get(entity);

                    AudioManager.hit.play();
                    Mappers.life.get(other).damage(Mappers.bullet.get(entity).damage);
                    FXFactory.MakeHitText(pos.x, pos.y, FontManager.damage, 1);
                    FXFactory.makeExplosion(pos.x, pos.y,
                            (Mappers.owner.get(entity).owner == OwnerComponent.Owner.Player2) ?
                                    FXFactory.ExplosionType.BONES :
                                    FXFactory.ExplosionType.PURPLE
                    );
                    CameraManager.shake(0.3f, 3.0f);
                    engine.removeEntity(entity);

                    //UltraManager.lasthit_p1_anim = Mappers.animation.get(other).animation;

                    return;
                }

                //BULLET ENEMY vs ALTAR
                else if (Mappers.bullet.has(entity) &&
                        Mappers.owner.get(entity).owner == OwnerComponent.Owner.Enemy &&
                        ot.type == TypeComponent.EntityType.Altar)
                {
                    PositionComponent pos = Mappers.position.get(entity);

                    AudioManager.hit.play();
                    Mappers.life.get(other).damage(Mappers.bullet.get(entity).damage);
                    FXFactory.MakeHitText(pos.x, pos.y, FontManager.damage_altar, 2);
                    FXFactory.makeExplosion(pos.x, pos.y,
                                    FXFactory.ExplosionType.RED
                    );
                    CameraManager.shake(0.3f, 3.0f);



                    LifeComponent lc = Mappers.life.get(other);
                    if (Mappers.life.get(other).life > 0)
                    {
                        AudioManager.scream.play();
                        Mappers.life.get(other).damage(Mappers.bullet.get(entity).damage);
                        CameraManager.smallShake();

                    }

                    engine.removeEntity(entity);

                    return;
                }

                //PLAYER vs PICKABLE
                else if (et.type == TypeComponent.EntityType.Player &&
                        ot.type == TypeComponent.EntityType.AltarItem)
                {
                    enableAltarPoint(Mappers.item.get(other).value);
                    engine.removeEntity(other);
                    ItemSpawnSystem.altarItemActive = false;
                    ItemSpawnSystem.altarPointEnabled();
                }

                // ENEMY vs ALTAR
                if (et.type == TypeComponent.EntityType.Enemy &&
                        Mappers.enemy.get(entity).type != EnemyComponent.EnemyType.Warrior &&
                        ot.type == TypeComponent.EntityType.Altar)
                {


                    LifeComponent lc = Mappers.life.get(other);
                    if (Mappers.life.get(other).life > 0)
                    {
                        EnemyComponent ec = Mappers.enemy.get(entity);
                        if (ec.attackCooldown <= 0.0f)
                        {
                            ec.attackCooldown = ec.attackPeriod * MathUtils.random(0.80f, 1.20f);

                            entity.add(new RenderEffectComponent(0.3f, 2.0f, 1.0f, 1.0f, 1.0f, false));

                            AudioManager.scream.play();
                            Mappers.life.get(other).damage(Mappers.enemy.get(entity).damage);
                            CameraManager.smallShake();
                        }
                    }
                }
            }
        }
    }

    private void enableAltarPoint(int id)
    {
        AudioManager.pickup.play();
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(AltarPointComponent.class).get());
        for (Entity entity : entities)
        {
            if (Mappers.altarPoint.get(entity).id == id)
            {
                Mappers.altarPoint.get(entity).state = AltarPointComponent.State.Active;
                // TODO: Maybe replace this by some animation.
                Sprite spr = new Sprite(TextureManager.getTexture("images/losetas.png"), 16, 16*id, 16, 16);
                Mappers.render_comp.get(entity).spr = spr;
                ItemSpawnSystem.setEnabled(id);
                AltarCandles.update(id);

                FXFactory.losetaCatch(
                        Mappers.position.get(entity).x,
                        Mappers.position.get(entity).y,
                        id);

                return;
            }
        }
    }

    private static boolean isFixed(TypeComponent.EntityType type)
    {
        return type == TypeComponent.EntityType.Altar;
    }
    private static boolean isObstacle(TypeComponent.EntityType from, TypeComponent.EntityType to)
    {
        // No collision between players.
        if (from == TypeComponent.EntityType.Player && to == TypeComponent.EntityType.Player)
        {
            return false;
        }
        if (from == TypeComponent.EntityType.Player || from == TypeComponent.EntityType.Enemy)
        {
            return to == TypeComponent.EntityType.Player || to == TypeComponent.EntityType.Enemy ||
                    to == TypeComponent.EntityType.Altar;
        };
        return false;
    }
}