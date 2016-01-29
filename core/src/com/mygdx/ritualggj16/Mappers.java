package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;

/**
 * Created by ThrepwooD on 29/01/2016.
 */

public class Mappers
{
    public static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);

    public static ComponentMapper<RenderComponent> render_comp = ComponentMapper.getFor(RenderComponent.class);

    public static ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);



}

