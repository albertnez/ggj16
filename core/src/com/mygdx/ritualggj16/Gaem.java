package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Gaem extends Game {
	SpriteBatch batch;
    public PooledEngine engine;

    public OrthographicCamera cam;
    public Viewport viewport;

	@Override
	public void create ()
    {
		batch = new SpriteBatch();

        engine = new PooledEngine(1024, 1024*4, 1024, 1024*4);

        cam = new OrthographicCamera(Constants.RES_X, Constants.RES_Y);
        viewport = new FitViewport(Constants.RES_X, Constants.RES_Y, cam);


        Gdx.graphics.setContinuousRendering(true);
        setScreen(new PlayScreen(this));
	}

	@Override
	public void render ()
    {
        super.render();
	}
}
