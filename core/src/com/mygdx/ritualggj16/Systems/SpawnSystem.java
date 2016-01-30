package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.EnemyFactory;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Created by anon on 1/30/16.
 */
public class SpawnSystem extends IntervalSystem
{
    public Engine engine;
    public SpawnSystem (float interval, Engine engine){
        super(interval);
    }

    @Override
    protected void updateInterval()
    {
        float posX, posY;
        int numToSpawn = MathUtils.random(1, 5);
        int mult = (MathUtils.randomBoolean()) ? 1 : -1;

        if (MathUtils.randomBoolean())
        {
            posY = mult * (Constants.RES_Y + MathUtils.random(100, 500));
            posX = MathUtils.random(-Constants.RES_X, Constants.RES_Y);
        }
        else {
            posY = MathUtils.random(-Constants.RES_Y, Constants.RES_Y);
            posX = mult * (Constants.RES_X + MathUtils.random(100, 500));
        }
        spawnNear(posX, posY, numToSpawn);
    }

    private void spawnNear(float x, float y, int num, float maxDist)
    {
        for (int i = 0; i < num; ++i) {
            EnemyFactory.spawnWalker(
                    x + MathUtils.random(-maxDist, maxDist),
                    y + MathUtils.random(-maxDist, maxDist));
        }
    }
    private void spawnNear(float x, float y, int num)
    {
        spawnNear(x, y, num, 200.0f);
    }
}
