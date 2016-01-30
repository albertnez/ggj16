package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/30/16.
 */
public class ItemComponent implements Component
{
    public int value;

    public ItemComponent(int value)
    {
        this.value = value;
    }

}
