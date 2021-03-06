package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Gaem extends Game {
	public SpriteBatch batch;
    public PooledEngine engine;

    public OrthographicCamera cam;
    public Viewport viewport;

    public ShapeRenderer shapeRenderer;

	@Override
	public void create ()
    {
		batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        engine = new PooledEngine(1024, 1024*4, 1024, 1024*4);

        cam = new OrthographicCamera(Constants.RES_X, Constants.RES_Y);
        viewport = new FitViewport(Constants.RES_X/2, Constants.RES_Y/2, cam);

        FontManager.generateAll();
        AudioManager.init();



        Gdx.graphics.setContinuousRendering(true);
       // setScreen(new PlayScreen(this));
        setScreen(new MenuScreen(this));

        setupControllers();
	}

	@Override
	public void render ()
    {
        super.render();
	}

    public void setupControllers()
    {
        for (int i = 0; i < Controllers.getControllers().size; ++i)
        {
            Controller controller = Controllers.getControllers().get(i);
            if (controller != null)
            {
                controller.addListener(new ControllerAdapter() {
                    @Override
                    public boolean buttonDown(Controller controller, int buttonIndex)
                    {
                        XBox360Pad.buttonPressed(buttonIndex);
                        return false;
                    }
                });
            }
        }
    }
}
