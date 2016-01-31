package com.mygdx.ritualggj16;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by anon on 1/31/16.
 */
public class CameraManager
{
    public static Camera cam;
    public static float shakeTime = 0.0f;
    public static float timePerShake = 0.05f;
    public static float timeToNextShake = 0.0f;
    public static boolean shaking = false;
    public static float intensity = 0.0f;

    public static void smallShake()
    {
        shake(0.2f, 2.0f);
    }
    public static void bigShake()
    {
        shake(1.0f, 5.0f);
    }
    public static void shake(float time, float magnitude)
    {
        shakeTime = time;
        shaking = true;
        intensity = magnitude;
    }

    public static void update(float deltaTime)
    {
        if (!shaking)
        {
            return;
        }
        shakeTime -= deltaTime;
        timeToNextShake -= deltaTime;
        if (shakeTime <= 0.0f)
        {
            shaking = false;
            cam.position.setZero();
            cam.update();
            return;
        }
        if (timeToNextShake <= 0)
        {
            timeToNextShake = timePerShake;
            cam.position.set(
                    MathUtils.random(intensity * -1.0f, intensity),
                    MathUtils.random(intensity * -1.0f, intensity),
                    0.0f
            );
            cam.update();
        }
    }
}
