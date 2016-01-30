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
import com.mygdx.ritualggj16.Components.InputComponent;
import com.mygdx.ritualggj16.Components.LifeComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.Components.PositionComponent;
import com.mygdx.ritualggj16.Components.RenderComponent;
import com.mygdx.ritualggj16.Components.TypeComponent;
import com.mygdx.ritualggj16.Components.VelocityComponent;
import com.mygdx.ritualggj16.Factorys.AnimationFactory;
import com.mygdx.ritualggj16.Factorys.BulletFactory;
import com.mygdx.ritualggj16.Factorys.EnemyFactory;
import com.mygdx.ritualggj16.Factorys.FXFactory;
import com.mygdx.ritualggj16.Systems.BulletSystem;
import com.mygdx.ritualggj16.Systems.CollisionSystem;
import com.mygdx.ritualggj16.Systems.EnemySystem;
import com.mygdx.ritualggj16.Systems.InputSystem;
import com.mygdx.ritualggj16.Systems.LifeSystem;
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

    public PlayScreen(Gaem gaem) {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X / 2.0f);
        bg_floor.setY(-Constants.RES_Y / 2.0f);

        gaem.engine.addSystem(new InputSystem(gaem.engine));
        gaem.engine.addSystem(new MovementSystem(gaem.engine));
        gaem.engine.addSystem(new BulletSystem(gaem.engine));
        gaem.engine.addSystem(new EnemySystem(gaem.engine));
        gaem.engine.addSystem(new CollisionSystem(gaem.engine));
        gaem.engine.addSystem(new LifeSystem(gaem.engine));
        gaem.engine.addSystem(new RenderSystem(gaem));

        BulletFactory.gaem = this.gaem;
        EnemyFactory.gaem = this.gaem;
        FXFactory.gaem = this.gaem;

        players = new Entity[numPlayers];

        spr = new Sprite(TextureManager.getTexture("player.png"));
        Controller controller = (Controllers.getControllers().size > 0) ?
                Controllers.getControllers().get(0) : null;
        players[0] = gaem.engine.createEntity()
                .add(new PositionComponent(0, 0))
                .add(new VelocityComponent(0, 0))
                .add(new TypeComponent(TypeComponent.EntityType.Player))
                .add(new LifeComponent(10))
                .add(new OwnerComponent(OwnerComponent.Owner.Player1))
                .add(new RenderComponent(spr))
                .add(new CollisionComponent(10, 16))
                .add(new AnimationComponent(AnimationFactory.playerLeft()))
                .add(new InputComponent(controller));
        gaem.engine.addEntity(players[0]);

        if (numPlayers > 1) {
            controller = (Controllers.getControllers().size > 1) ?
                    Controllers.getControllers().get(1) : null;

            players[1] = gaem.engine.createEntity()
                    .add(new PositionComponent(100, 0))
                    .add(new VelocityComponent(0, 0))
                    .add(new TypeComponent(TypeComponent.EntityType.Player))
                    .add(new LifeComponent(10))
                    .add(new OwnerComponent(OwnerComponent.Owner.Player2))
                    .add(new RenderComponent(new Sprite(TextureManager.getTexture("player.png"))))
                    .add(new CollisionComponent(10, 16))
                    .add(new InputComponent(controller));

            gaem.engine.addEntity(players[1]);
        }


        FXFactory.MakeHitText(0, 0);

        // Dumb control points.
        createControlPoint(100, 100);
        createControlPoint(150, 200);

        EnemyFactory.spawnWalker(120, 100);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gaem.batch.begin();
        bg_floor.draw(gaem.batch);
        gaem.batch.end();

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
