package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Factorys.AnimationFactory;
import com.mygdx.ritualggj16.Factorys.ItemFactory;
import com.mygdx.ritualggj16.Systems.ItemSpawnSystem;

import java.util.ArrayList;

/**
 * Created by anon on 1/31/16.
 */
public class AltarCandles
{
    public static final int numPoints = 5;
    public static final int numCandles = 10;
    public static Entity candles[][][] = new Entity[numPoints][numPoints][numCandles];

    public void reset()
    {
        for (int i = 0; i < numPoints; ++i)
        {
            for (int j = 0; j < numPoints; ++j)
            {
                for (int k = 0; k < numCandles; ++k)
                {
                    candles[i][j][k] = null;
                }
            }
        }
    }

    public static void update(int id)
    {
        for (int j = 0; j < numPoints; ++j)
        {
            if (j == id || !ItemSpawnSystem.isEnabled(j))
            {
                continue;
            }
            int from = Math.min(id, j);
            int to = Math.max(id, j);
            for (int k = 0; k < numCandles; ++k)
            {
                candles[from][to][k].add(new AnimationComponent(AnimationFactory.candle()));
                Mappers.render_comp.get(candles[from][to][k]).spr.setSize(8*2, 16*2);
            }
        }
    }

    public static void spawn(Engine engine)
    {
        float initAlpha = MathUtils.PI / 2.0f - MathUtils.PI2 / 10.0f;
        for (int from = 0; from < numPoints; ++from)
        {
            float fromAlpha = initAlpha + from * MathUtils.PI2 / 5.0f;
            float fromX = MathUtils.cos(fromAlpha) * PlayScreen.altarPointDistToCenter;
            float fromY = MathUtils.sin(fromAlpha) * PlayScreen.altarPointDistToCenter;
            for (int to = from+1; to < numPoints; ++to)
            {
                float toAlpha = initAlpha + to * MathUtils.PI2 / 5.0f;
                float toX = MathUtils.cos(toAlpha) * PlayScreen.altarPointDistToCenter;
                float toY = MathUtils.sin(toAlpha) * PlayScreen.altarPointDistToCenter;
                for (int k = 0; k < numCandles; ++k)
                {
                    float dstX = MathUtils.lerp(fromX, toX, (float)(k+1) / (numCandles+1));
                    float dstY = MathUtils.lerp(fromY, toY, (float)(k+1) / (numCandles+1));
                    candles[from][to][k] = ItemFactory.spawnCandle(dstX, dstY);
                }
            }
        }
    }
}
