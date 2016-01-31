package com.mygdx.ritualggj16;

import com.mygdx.ritualggj16.Components.EnemyComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class Wikipedia
{

    public static float Speed(TypeComponent.EntityType type)
    {
        return 200.0f;
    }
    public static float Speed(EnemyComponent.EnemyType type)
    {

        if (type == EnemyComponent.EnemyType.Big) return 25.0f;

        return 45.0f;
    }

}
