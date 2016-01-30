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

    public State state;

    public AltarPointComponent(State state)
    {
        this.state = state;
    }
    public AltarPointComponent()
    {
        this(State.Inactive);
    }
}
