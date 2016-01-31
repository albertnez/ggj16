package com.mygdx.ritualggj16.Components;


import com.badlogic.ashley.core.Component;

/**
 * Created by anon on 1/29/16.
 */
public class OwnerComponent implements Component {

    public enum Owner {
        None,
        Player1,
        Player2,
        Enemy
    }

    public Owner owner;

    public OwnerComponent() {
        this.owner = Owner.None;
    }
    public OwnerComponent(Owner owner) {
        this.owner = owner;
    }
}
