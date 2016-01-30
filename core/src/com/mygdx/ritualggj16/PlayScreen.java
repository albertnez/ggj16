package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.CollisionComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Factorys.AnimationFactory;
import com.mygdx.ritualggj16.Factorys.BulletFactory;
import com.mygdx.ritualggj16.Factorys.EnemyFactory;
import com.mygdx.ritualggj16.Systems.BulletSystem;
import com.mygdx.ritualggj16.Systems.CollisionSystem;
import com.mygdx.ritualggj16.Systems.EnemySystem;
import com.mygdx.ritualggj16.Systems.MovementSystem;
import com.mygdx.ritualggj16.Systems.RenderSystem;


/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PlayScreen implements Screen {

    private static int numPlayers = 2;

    Gaem gaem;

    Sprite spr;
    Sprite bg_floor;

    Entity[] players;
    Controller[] controllers;

    public PlayScreen(Gaem gaem) {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X / 2.0f);
        bg_floor.setY(-Constants.RES_Y / 2.0f);

        gaem.engine.addSystem(new MovementSystem(gaem.engine));
        gaem.engine.addSystem(new BulletSystem(gaem.engine));
        gaem.engine.addSystem(new CollisionSystem(gaem.engine));
        gaem.engine.addSystem(new RenderSystem(gaem));
        gaem.engine.addSystem(new EnemySystem(gaem.engine));

        BulletFactory.gaem = this.gaem;
        EnemyFactory.gaem = this.gaem;

        players = new Entity[numPlayers];
        controllers = new Controller[numPlayers];

        for (int i = 0; i < numPlayers; ++i)
        {
            controllers[i] = (i < Controllers.getControllers().size) ?
                    Controllers.getControllers().get(i) : null;
        }

        spr = new Sprite(TextureManager.getTexture("player.png"));
        players[0] = gaem.engine.createEntity()
                .add(new PositionComponent(0, 0))
                .add(new VelocityComponent(0, 0))
                .add(new TypeComponent(TypeComponent.EntityType.Player))
                .add(new OwnerComponent(OwnerComponent.Owner.Player1))
                .add(new RenderComponent(spr))
                .add(new AnimationComponent(AnimationFactory.playerLeft()));
        gaem.engine.addEntity(players[0]);

        if (numPlayers > 1)
        {
            players[1] = gaem.engine.createEntity()
                    .add(new PositionComponent(100, 0))
                    .add(new VelocityComponent(0, 0))
                    .add(new TypeComponent(TypeComponent.EntityType.Player))
                    .add(new OwnerComponent(OwnerComponent.Owner.Player2))
                    .add(new RenderComponent(new Sprite(TextureManager.getTexture("player.png"))));
            gaem.engine.addEntity(players[1]);
        }

        // Dumb control points.
        createControlPoint(100, 100);
        createControlPoint(150, 200);

        EnemyFactory.spawnWalker(120, 100);
    }

    void updateInput(float dt)
    {
        for (int i = 0; i < numPlayers; ++i)
        {

            Entity player = players[i];
            Controller controller = controllers[i];

            PositionComponent pos = Mappers.position.get(player);
            VelocityComponent vel = Mappers.velocity.get(player);

            vel.x = 0.0f;
            vel.y = 0.0f;

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                vel.x -= dt * Wikipedia.Speed(TypeComponent.EntityType.Player);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                vel.x += dt * Wikipedia.Speed(TypeComponent.EntityType.Player);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                vel.y -= dt * Wikipedia.Speed(TypeComponent.EntityType.Player);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                vel.y +=  dt * Wikipedia.Speed(TypeComponent.EntityType.Player);
            }


            if (controller != null)
            {
                if (Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_X)) > 0.2)
                    vel.x = controller.getAxis(XBox360Pad.AXIS_LEFT_X) * Constants.RES_X / 8 * 10.0f;

                if (Math.abs(controller.getAxis(XBox360Pad.AXIS_LEFT_Y)) > 0.2)
                    vel.y = -controller.getAxis(XBox360Pad.AXIS_LEFT_Y) * Constants.RES_Y / 8 * 10.0f;

                if (Math.abs(controller.getAxis(XBox360Pad.AXIS_RIGHT_X)) > 0.2 ||
                        Math.abs(controller.getAxis(XBox360Pad.AXIS_RIGHT_Y)) > 0.2) {

                    BulletFactory.shootBullet(
                            pos.x,
                            pos.y,
                            MathUtils.radDeg * MathUtils.atan2(
                                    -controller.getAxis(XBox360Pad.AXIS_RIGHT_Y),
                                    controller.getAxis(XBox360Pad.AXIS_RIGHT_X)
                            )
                    );

                }

                if (controller.getAxis(XBox360Pad.AXIS_RIGHT_TRIGGER) < -0.75f) {
                    // BulletFactory.shootBullet(pos.x, pos.y, 0);
                }
            }
        }
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gaem.batch.begin();
        bg_floor.draw(gaem.batch);
        gaem.batch.end();

        updateInput(delta);

        gaem.engine.update(delta);

        Gdx.graphics.setTitle("RITUAL: TEH GAEM | FPS: " + Gdx.graphics.getFramesPerSecond());
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

    private void createControlPoint(float x, float y)
    {
        Sprite sprite = new Sprite(TextureManager.getTexture("control_point.png"));
        gaem.engine.addEntity(
                gaem.engine.createEntity()
                        .add(new PositionComponent(x, y))
                        .add(new CollisionComponent(sprite.getWidth(), sprite.getHeight()))
                        .add(new VelocityComponent(0, 0))
                        .add(new TypeComponent(TypeComponent.EntityType.ControlPoint))
                        .add(new OwnerComponent())
                        .add(new RenderComponent(sprite))
        );
    }
}
