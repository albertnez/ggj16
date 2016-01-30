package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.ritualggj16.Components.AltarPointComponent;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.BulletComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.InputComponent;
import com.mygdx.ritualggj16.Components.ItemComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.RenderEffectComponent;
import com.mygdx.ritualggj16.Components.RenderTextComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;

/**
 * Created by ThrepwooD on 29/01/2016.
 */

public class Mappers
{
    public static ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);

    public static ComponentMapper<BulletComponent> bullet = ComponentMapper.getFor(BulletComponent.class);

    public static ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);

    public static ComponentMapper<OwnerComponent> owner = ComponentMapper.getFor(OwnerComponent.class);

    public static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);

    public static ComponentMapper<RenderComponent> render_comp = ComponentMapper.getFor(RenderComponent.class);

    public static ComponentMapper<RenderEffectComponent> render_effect = ComponentMapper.getFor(RenderEffectComponent.class);

    public static ComponentMapper<RenderTextComponent> renderText = ComponentMapper.getFor(RenderTextComponent.class);

    public static ComponentMapper<TypeComponent> type = ComponentMapper.getFor(TypeComponent.class);

    public static ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);

    public static ComponentMapper<EnemyComponent> enemy = ComponentMapper.getFor(EnemyComponent.class);

    public static ComponentMapper<LifeComponent> life = ComponentMapper.getFor(LifeComponent.class);

    public static ComponentMapper<InputComponent> input = ComponentMapper.getFor(InputComponent.class);

    public static ComponentMapper<AltarPointComponent> altarPoint = ComponentMapper.getFor(AltarPointComponent.class);

    public static ComponentMapper<ItemComponent> item = ComponentMapper.getFor(ItemComponent.class);

}

