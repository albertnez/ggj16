package com.mygdx.ritualggj16.Components;


/**
 * Created by anon on 1/29/16.
 */
public class OwnerComponent {

    public enum Owner {
        None,
        Player1,
        Player2,
    }

    public Owner owner;

    public OwnerComponent() {
        this.owner = Owner.None;
    }
    public OwnerComponent(Owner owner) {
        this.owner = owner;
    }
}
