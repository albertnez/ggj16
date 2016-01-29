package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gaem extends Game {
	SpriteBatch batch;
    Engine engine;

	@Override
	public void create () {
		batch = new SpriteBatch();

        engine = new PooledEngine(1024, 1024*4, 1024, 1024*4);

        Gdx.graphics.setContinuousRendering(true);
        setScreen(new PlayScreen(this));
	}

	@Override
	public void render ()
    {
        super.render();
	}
}
