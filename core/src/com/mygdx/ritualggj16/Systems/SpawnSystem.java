package com.mygdx.ritualggj16.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Constants;
import com.mygdx.ritualggj16.Factorys.EnemyFactory;
import com.mygdx.ritualggj16.UltraManager;

/**
 * Created by anon on 1/30/16.
 */
public class SpawnSystem extends IntervalSystem
{
    public Engine engine;
    private static float interval;
    private static float currentProbability = 0.5f;
    public static boolean spawnInCircle = true;
    public static int currRound = 0;
    public static float accumInitProb = 0.0f;
    public static float currRoundTime = 0.0f;
    public static int aliveEnemies = 0;
    public static boolean lastRound = false;

    public SpawnSystem (float interval, Engine engine){
        super(interval);
        this.interval = interval;
        reset();
    }

    private static class RoundStats {
        public float initChance;  // The initial chance.
        public float failedChanceMultiplier;  // The chance multiplier when there was no spawn.
        public float chanceIncremental;  // How much the initChance is incremented during the round.
        public int minNumEnemies;  // Number of enemies that will spawn each time.
        public int extraNumEnemies;  // Number of extra enemies that can spawn.
        public int maxLevelEnemy;
        public float roundDuration;
        public int numEnemiesFinal;

        public RoundStats(float initChance, float failedChanceMultiplier, float chanceIncremental,
                          int minNumEnemies, int maxNumEnemies, int maxLevelEnemy,
                          float roundDuration, int numEnemiesFinal)
        {
            this.initChance = initChance;
            this.failedChanceMultiplier = failedChanceMultiplier;
            this.chanceIncremental = chanceIncremental;
            this.minNumEnemies = minNumEnemies;
            this.extraNumEnemies = maxNumEnemies;
            this.maxLevelEnemy = maxLevelEnemy;
            this.roundDuration = roundDuration;
            this.numEnemiesFinal = numEnemiesFinal;
        }
    }

    public static RoundStats[] roundStats = new RoundStats[] {
            new RoundStats(0.10f, 1.01f, 0.01f, 1, 3, 1, 25.0f, 9),
            new RoundStats(0.15f, 1.02f, 0.10f, 1, 5, 1, 35.0f, 12),
            new RoundStats(0.20f, 1.03f, 0.10f, 2, 7, 3, 35.0f, 15),
            new RoundStats(0.25f, 1.04f, 0.10f, 2, 9, 3, 45.0f, 20),
            new RoundStats(0.30f, 1.05f, 0.10f, 3, 10, 3, 45.0f, 25),
            new RoundStats(0.60f, 1.06f, 0.10f, 5, 15, 4, 45.0f, 32),
    };

    private static float initProb()
    {
        return roundStats[ItemSpawnSystem.numRound()].initChance;
    }
    private static float incrProb()
    {
        return roundStats[ItemSpawnSystem.numRound()].chanceIncremental;
    }
    private static int minEnemies()
    {
        return roundStats[ItemSpawnSystem.numRound()].minNumEnemies;
    }
    private static int extraEnemies()
    {
        return roundStats[ItemSpawnSystem.numRound()].extraNumEnemies;
    }
    private static int enemyLevel()
    {
        return roundStats[ItemSpawnSystem.numRound()].maxLevelEnemy;
    }
    private static float roundTime()
    {
        return roundStats[ItemSpawnSystem.numRound()].roundDuration;
    }
    private static void advanceRound()
    {
        currRound = ItemSpawnSystem.numRound();
        currentProbability = initProb();
        accumInitProb = 0.0f;
        currRoundTime = 0.0f;
        lastRound = false;
    }
    @Override
    protected void updateInterval()
    {
        if (!UltraManager.isGaemActive) return;

        if (currRound != ItemSpawnSystem.numRound())
        {
            advanceRound();
        }
        if (currRoundTime >= roundTime())
        {
            return;
        }
        currRoundTime += interval;
        accumInitProb += incrProb();
        int numToSpawn = minEnemies();
        if (MathUtils.randomBoolean(currentProbability))
        {
            numToSpawn += MathUtils.random(extraEnemies());
        }
        if (spawnInCircle)
        {
            spawnInCircle(numToSpawn);
            if (currRoundTime >= roundTime())
            {
                lastRound = true;
            }
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
        aliveEnemies += num;
        float alpha = MathUtils.random(0.0f, MathUtils.PI2);
        for (int i = 0; i < num; ++i)
        {
            EnemyFactory.spawnLevelEnemy(
                    MathUtils.cos(alpha) * dist,
                    MathUtils.sin(alpha) * dist,
                    MathUtils.random(enemyLevel())
            );
            // Check if it's last round, to drop the item.
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
            EnemyFactory.spawnWarrior(
                    x + MathUtils.random(-maxDist, maxDist),
                    y + MathUtils.random(-maxDist, maxDist)
            );
        }
    }
    private void spawnNear(float x, float y, int num)
    {
        spawnNear(x, y, num, 200.0f);
    }

    public static void reset()
    {
        currentProbability = 0.5f;
        spawnInCircle = true;
        currRound = 0;
        accumInitProb = 0.0f;
        currRoundTime = 0.0f;
        lastRound = false;
    }
}
