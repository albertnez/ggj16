package com.mygdx.ritualggj16.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.controllers.Controller;

/**
 * Created by anon on 1/30/16.
 */
public class InputComponent implements Component
{
    public Controller controller;

    public InputComponent(Controller controller)
    {
        this.controller = controller;
    }
}
