package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AltarPointComponent;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.ItemFactory;
import com.mygdx.ritualggj16.Mappers;

import java.util.ArrayList;

/**
 * Created by anon on 1/30/16.
 */
public class ItemSpawnSystem extends IntervalSystem
{
    public static boolean altarItemActive = false;
    public Engine engine;
    public ItemSpawnSystem(float interval, Engine engine)
    {
        super(interval);
        this.engine = engine;
    }

    @Override
    protected void updateInterval()
    {
        if (altarItemActive) {
            return;
        }
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(AltarPointComponent.class).get());
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Entity entity : entities)
        {
            if (Mappers.altarPoint.get(entity).state == AltarPointComponent.State.Inactive)
            {
                ids.add(Mappers.altarPoint.get(entity).id);
            }
        }
        if (ids.isEmpty())
        {
            return;
        }
        float offset = 20.0f;
        float posX = MathUtils.random(-Constants.RES_X*0.5f + offset, Constants.RES_X *0.5f- offset);
        float posY = MathUtils.random(-Constants.RES_Y*0.5f + offset, Constants.RES_Y *0.5f- offset);
        ItemFactory.spawnAltarEnabler(posX, posY, ids.get(MathUtils.random(ids.size()-1)));
        altarItemActive = true;
    }
}
