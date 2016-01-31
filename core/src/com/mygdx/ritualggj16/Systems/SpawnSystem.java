package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.EnemyFactory;
import com.mygdx.ritualggj16.UltraManager;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Created by anon on 1/30/16.
 */
public class SpawnSystem extends IntervalSystem
{
    public Engine engine;
    private static float interval;
    public SpawnSystem (float interval, Engine engine){
        super(interval);
        this.interval = interval;
    }
    private static final float incrInitProbPeriod = 5.0f;  // Time for a single initial probability incremental.
    private static float incrInitProbTime = 5.0f;
    private float currentProbability = 0.5f;
    public static boolean spawnInCircle = true;
    public static int currRound = 0;
    public static float accumInitProb = 0.0f;

    private static class RoundStats {
        public float initChance;  // The initial chance.
        public float failedChanceMultiplier;  // The chance multiplier when there was no spawn.
        public float chanceIncremental;  // How much the initChance is incremented during the round.
        public int minNumEnemies;
        public int maxNumEnemies;
        public int maxLevelEnemy;
        public RoundStats(float initChance, float failedChanceMultiplier, float chanceIncremental,
                          int minNumEnemies, int maxNumEnemies, int maxLevelEnemy)
        {
            this.initChance = initChance;
            this.failedChanceMultiplier = failedChanceMultiplier;
            this.chanceIncremental = chanceIncremental;
            this.minNumEnemies = minNumEnemies;
            this.maxNumEnemies = maxNumEnemies;
            this.maxLevelEnemy = maxLevelEnemy;
        }
    }

    public static RoundStats[] roundStats = new RoundStats[] {
            new RoundStats(0.10f, 1.1f, 0.05f, 1, 2, 1),
            new RoundStats(0.15f, 1.5f, 0.10f, 1, 3, 1),
            new RoundStats(0.20f, 1.1f, 0.10f, 2, 4, 2),
            new RoundStats(0.25f, 1.1f, 0.10f, 2, 7, 2),
            new RoundStats(0.30f, 1.1f, 0.10f, 3, 10, 3),
            new RoundStats(0.60f, 1.5f, 0.10f, 5, 15, 4),
    };

    private static float currProb()
    {
        return roundStats[ItemSpawnSystem.numAltarPointsActivated].initChance;
    }
    private static float initProb()
    {
        return roundStats[ItemSpawnSystem.numRound()].initChance;
    }
    private static float failedMult()
    {
        return roundStats[ItemSpawnSystem.numRound()].failedChanceMultiplier;
    }
    private static float incrProb()
    {
        return roundStats[ItemSpawnSystem.numRound()].chanceIncremental;
    }
    private static int minEnemies()
    {
        return roundStats[ItemSpawnSystem.numRound()].minNumEnemies;
    }
    private static int maxEnemies()
    {
        return roundStats[ItemSpawnSystem.numRound()].maxNumEnemies;
    }
    private static int enemyLevel()
    {
        return roundStats[ItemSpawnSystem.numRound()].maxLevelEnemy;
    }

    @Override
    protected void updateInterval()
    {
        if (!UltraManager.isGaemActive) return;
        if (currRound != ItemSpawnSystem.numRound())
        {
            incrInitProbTime = incrInitProbPeriod;
            currRound = ItemSpawnSystem.numRound();
            currentProbability = initProb();
            accumInitProb = 0.0f;
        }

        // START
        incrInitProbTime -= interval;
        if (incrInitProbTime <= 0)
        {
            incrInitProbTime = incrInitProbTime;
            accumInitProb += incrProb();
        }
        if (!MathUtils.randomBoolean(currentProbability))
        {
            currentProbability *= failedMult();
            return;
        }
        else
        {
            currentProbability = initProb() + accumInitProb;
        }

        int numToSpawn = MathUtils.random(minEnemies(), maxEnemies());
        if (spawnInCircle)
        {
            spawnInCircle(numToSpawn);
        }
        else
        {
            // TODO: Not used anymore?
            float posX, posY;
            int mult = (MathUtils.randomBoolean()) ? 1 : -1;

            if (MathUtils.randomBoolean())
            {
                posY = mult * (Constants.RES_Y + MathUtils.random(100, 500));
                posX = MathUtils.random(-Constants.RES_X, Constants.RES_Y);
            } else
            {
                posY = MathUtils.random(-Constants.RES_Y, Constants.RES_Y);
                posX = mult * (Constants.RES_X + MathUtils.random(100, 500));
            }
            spawnNear(posX, posY, numToSpawn);
        }
    }

    private void spawnInCircle(int num, float dist)
    {
        // TODO: Now they are spawning equidistant in the circle. Maybe add randomness?
        float alpha = MathUtils.random(0.0f, MathUtils.PI2);
        for (int i = 0; i < num; ++i)
        {
            EnemyFactory.spawnLevelEnemy(
                    MathUtils.cos(alpha) * dist,
                    MathUtils.sin(alpha) * dist,
                    MathUtils.random(enemyLevel())
            );
            alpha += MathUtils.PI2 / (float)num;
        }
    }
    private void spawnInCircle(int num)
    {
        spawnInCircle(num, Constants.RES_X);
    }

    private void spawnNear(float x, float y, int num, float maxDist)
    {
        for (int i = 0; i < num; ++i)
        {
            EnemyFactory.spawnWalker(
                    x + MathUtils.random(-maxDist, maxDist),
                    y + MathUtils.random(-maxDist, maxDist)
            );
        }
    }
    private void spawnNear(float x, float y, int num)
    {
        spawnNear(x, y, num, 200.0f);
    }
}
