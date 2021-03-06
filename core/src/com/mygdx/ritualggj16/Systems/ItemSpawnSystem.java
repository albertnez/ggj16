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
import com.mygdx.ritualggj16.UltraManager;

import java.util.ArrayList;

/**
 * Created by anon on 1/30/16.
 */
public class ItemSpawnSystem extends IntervalSystem
{
    public static boolean altarItemActive = false;
    public static int numAltarPointsActivated = 0;
    public static Engine engine;
    private static boolean whichEnabled[] = new boolean[5];
    public ItemSpawnSystem(float interval, Engine engine)
    {
        super(interval);
        this.engine = engine;
        reset();
    }

    @Override
    protected void updateInterval()
    {
        if (!UltraManager.isGaemActive) return;

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
        float posY = MathUtils.random(-Constants.RES_Y*0.5f + offset, Constants.RES_Y * 0.5f - offset);
        ItemFactory.spawnAltarEnabler(posX, posY, ids.get(MathUtils.random(ids.size()-1)));
        altarItemActive = true;
    }

    public static int getAltarEnablerId()
    {
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
            return -1;
        }
        return ids.get(MathUtils.random(ids.size()-1));
    }
    public static void altarPointEnabled()
    {
        numAltarPointsActivated++;
    }

    public static int numRound()
    {
        return numAltarPointsActivated;
    }
    public static void setEnabled(int id)
    {
        whichEnabled[id] = true;
    }
    public static boolean isEnabled(int id)
    {
        return whichEnabled[id];
    }
    public static void reset()
    {
        altarItemActive = false;
        numAltarPointsActivated = 0;
        for (int i = 0; i < 5; ++i)
        {
            whichEnabled[i] = false;
        }
    }
}
