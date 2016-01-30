package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/30/16.
 */
public class AltarPointComponent implements Component
{
    public enum State {
        Inactive,
        Active,
    }

    public int id;
    public State state;

    public AltarPointComponent(int id, State state)
    {
        this.id = id;
        this.state = state;
    }
    public AltarPointComponent(int id)
    {
        this(id, State.Inactive);
    }
}
