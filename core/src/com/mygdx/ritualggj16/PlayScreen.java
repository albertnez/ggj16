package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PlayScreen implements Screen {

    Gaem gaem;

    Texture bg_floor;

    public PlayScreen(Gaem gaem)
    {
        this.gaem = gaem;
        bg_floor = new Texture("bg_floor.png");
    }

    @Override
    public void render(float delta)
    {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        gaem.batch.begin();
        gaem.batch.draw(bg_floor, 0, 0);
        gaem.batch.end();

    }


    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
