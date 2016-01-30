package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.ritualggj16.Components.AltarPointComponent;
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
import com.mygdx.ritualggj16.Factorys.ItemFactory;
import com.mygdx.ritualggj16.Systems.BulletSystem;
import com.mygdx.ritualggj16.Systems.CollisionSystem;
import com.mygdx.ritualggj16.Systems.EnemySystem;
import com.mygdx.ritualggj16.Systems.InputSystem;
import com.mygdx.ritualggj16.Systems.ItemSpawnSystem;
import com.mygdx.ritualggj16.Systems.LifeSystem;
import com.mygdx.ritualggj16.Systems.MovementSystem;
import com.mygdx.ritualggj16.Systems.RenderSystem;
import com.mygdx.ritualggj16.Systems.SpawnSystem;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;


/**
 * Created by ThrepwooD on 29/01/2016.
 */
public class PlayScreen implements Screen {

    private static int numPlayers = 2;
    private static int numAltarPoints = 5;
    private static float altarPointDistToCenter = 350.0f;

    Gaem gaem;

    Sprite spr;
    Sprite bg_floor;

    Entity[] players;

    Entity altar;

    public PlayScreen(Gaem gaem) {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X / 2.0f);
        bg_floor.setY(-Constants.RES_Y / 2.0f);

        gaem.engine.addSystem(new InputSystem(gaem.engine));
        gaem.engine.addSystem(new SpawnSystem(5.0f, gaem.engine));
        gaem.engine.addSystem(new ItemSpawnSystem(5.0f, gaem.engine));
        gaem.engine.addSystem(new MovementSystem(gaem.engine));
        gaem.engine.addSystem(new BulletSystem(gaem.engine));
        gaem.engine.addSystem(new EnemySystem(gaem.engine));
        gaem.engine.addSystem(new CollisionSystem(gaem.engine));
        gaem.engine.addSystem(new LifeSystem(gaem.engine));
        gaem.engine.addSystem(new RenderSystem(gaem));

        BulletFactory.gaem = this.gaem;
        EnemyFactory.gaem = this.gaem;
        FXFactory.gaem = this.gaem;
        ItemFactory.gaem = this.gaem;

        players = new Entity[numPlayers];

        spr = new Sprite(Utils.dumbSprite(10*4, 16*4));
        Controller controller = (Controllers.getControllers().size > 0) ?
                Controllers.getControllers().get(0) : null;
        players[0] = gaem.engine.createEntity()
                .add(new PositionComponent(-400, 0))
                .add(new VelocityComponent(0, 0))
                .add(new TypeComponent(TypeComponent.EntityType.Player))
                .add(new LifeComponent(10))
                .add(new OwnerComponent(OwnerComponent.Owner.Player1))
                .add(new RenderComponent(spr, RenderComponent.Layer.Player))
                .add(new CollisionComponent(10, 16))
                .add(new AnimationComponent(AnimationFactory.playerRight(OwnerComponent.Owner.Player1)))
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
                    .add(new RenderComponent(Utils.dumbSprite(10*4, 16*4), RenderComponent.Layer.Player))
                    .add(new CollisionComponent(10, 16))
                    .add(new AnimationComponent(AnimationFactory.playerLeft(OwnerComponent.Owner.Player2)))
                    .add(new InputComponent(controller));

            gaem.engine.addEntity(players[1]);
        }

        //Altar
        altar = ItemFactory.spawnAltar();

        // Dumb control points.
        createAltarPoints();

        EnemyFactory.spawnWalker(120, 100);

        UltraManager.lasthit_p1_anim = AnimationFactory.blob();
        UltraManager.lasthit_p1_spr = Utils.dumbSprite(75, 75);
    }

    float le_timer = 0.0f;

    @Override
    public void render(float delta)
    {
        le_timer += delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gaem.batch.begin();
        bg_floor.draw(gaem.batch);
        gaem.batch.end();

        gaem.batch.setProjectionMatrix(gaem.cam.combined);
        gaem.batch.begin();
        gaem.engine.update(delta);
        gaem.batch.end();

        gaem.batch.begin();
        UltraManager.lasthit_p1_spr.setRegion(UltraManager.lasthit_p1_anim.getKeyFrame(le_timer));
        UltraManager.lasthit_p1_spr.setX(-Constants.RES_X/2 + Constants.RES_X*0.05f);
        UltraManager.lasthit_p1_spr.setY((Constants.RES_Y/2) -(Constants.RES_Y/2)*0.25f );
        UltraManager. lasthit_p1_spr.draw(gaem.batch);
        gaem.batch.end();


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

    private void createAltarPoint(float x, float y, int id)
    {
        Sprite sprite = new Sprite(TextureManager.getTexture("losetas.png"), 0, 16*id, 16, 16);
        gaem.engine.addEntity(
                gaem.engine.createEntity()
                        .add(new PositionComponent(x, y))
                        .add(new CollisionComponent(sprite.getWidth(), sprite.getHeight()))
                        .add(new VelocityComponent(0, 0))
                        .add(new TypeComponent(TypeComponent.EntityType.AltarPoint))
                        .add(new OwnerComponent())
                        .add(new RenderComponent(sprite, RenderComponent.Layer.Ground, 4.0f))
                        .add(new AltarPointComponent(id))
        );
    }

    private void createAltarPoints()
    {
        float alpha = MathUtils.random(0.0f, MathUtils.PI2);
        for (int i = 0; i < numAltarPoints; ++i)
        {
            float posX = MathUtils.cos(alpha) * altarPointDistToCenter;
            float posY = MathUtils.sin(alpha) * altarPointDistToCenter;
            createAltarPoint(posX, posY, i);
            ItemFactory.spawnCandle(
                    MathUtils.cos(alpha) * altarPointDistToCenter * 0.2f,
                    MathUtils.sin(alpha) * altarPointDistToCenter * 0.2f
            );
            float calpha = alpha + MathUtils.PI2 / 10.0f;
            ItemFactory.spawnCandle(
                    MathUtils.cos(calpha) * altarPointDistToCenter,
                    MathUtils.sin(calpha) * altarPointDistToCenter
            );
            alpha += MathUtils.PI2 / 5.0f;
        }
    }

    private void initCandles()
    {
        for (int i = 0; i < MathUtils.random(5, 10); ++i)
        {
            ItemFactory.spawnCandle(
                    MathUtils.random(-Constants.RES_X/2.0f, Constants.RES_X/2.0f),
                    MathUtils.random(-Constants.RES_Y/2.0f, Constants.RES_Y/2.0f)
            );
        }
    }
}
