package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Systems.MovementSystem;
import com.mygdx.ritualggj16.Systems.RenderSystem;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PlayScreen implements Screen {

    Gaem gaem;

    Sprite spr;
    Sprite bg_floor;

    public PlayScreen(Gaem gaem)
    {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X);
        bg_floor.setY(-Constants.RES_Y);

        gaem.engine.addSystem(new MovementSystem(gaem.engine));
        gaem.engine.addSystem(new RenderSystem(gaem.batch, gaem.cam));

        Entity player = gaem.engine.createEntity();
        player.add(new PositionComponent());
        player.add(new VelocityComponent(0, 0));
        spr = new Sprite(TextureManager.getTexture("player.png"));
        player.add(new RenderComponent(spr));
        gaem.engine.addEntity(player);

    }

    @Override
    public void render(float delta)
    {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gaem.batch.begin();
        gaem.batch.draw(bg_floor, 0, 0);
        gaem.batch.end();


        gaem.engine.update(delta);
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
