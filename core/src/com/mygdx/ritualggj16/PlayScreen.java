package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Systems.MovementSystem;
import com.mygdx.ritualggj16.Systems.RenderSystem;

import java.util.Map;

/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PlayScreen implements Screen {

    Gaem gaem;

    Sprite spr;
    Sprite bg_floor;

    Entity player;

    public PlayScreen(Gaem gaem)
    {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X);
        bg_floor.setY(-Constants.RES_Y);

        gaem.engine.addSystem(new MovementSystem(gaem.engine));
        gaem.engine.addSystem(new RenderSystem(gaem.batch, gaem.cam));

        player = gaem.engine.createEntity();
        player.add(new PositionComponent());
        player.add(new VelocityComponent(0, 0));
        spr = new Sprite(TextureManager.getTexture("player.png"));
        player.add(new RenderComponent(spr));
        gaem.engine.addEntity(player);

    }

    void updateInput(float dt)
    {
        VelocityComponent vel = Mappers.velocity.get(player);

        vel.x = 0.0f;
        vel.y = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            vel.x -= Constants.RES_X*dt*10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            vel.x += Constants.RES_X*dt*10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            vel.y -= Constants.RES_Y*dt*10.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            vel.y += Constants.RES_Y*dt*10.0f;
        }

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gaem.batch.begin();
        gaem.batch.draw(bg_floor, 0, 0);
        gaem.batch.end();

        updateInput(delta);
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
