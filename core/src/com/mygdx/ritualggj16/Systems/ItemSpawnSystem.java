package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.ItemFactory;

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
    }

    @Override
    protected void updateInterval()
    {
        if (altarItemActive) {
            return;
        }
        float offset = 20.0f;
        float posX = MathUtils.random(-Constants.RES_X*0.5f + offset, Constants.RES_X *0.5f- offset);
        float posY = MathUtils.random(-Constants.RES_Y*0.5f + offset, Constants.RES_Y *0.5f- offset);
        ItemFactory.spawnAltarEnabler(posX, posY);
        altarItemActive = true;
    }
}
