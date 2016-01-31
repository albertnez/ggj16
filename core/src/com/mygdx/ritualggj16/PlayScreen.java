package com.mygdx.ritualggj16;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
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
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
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

    Sprite blackzor;

    Sprite p1_faec;
    Sprite p2_faec;
    Sprite p3_faec;

    Sprite pinxo;

    Entity[] players;

    Entity altar;
    Sprite altarLifeBack;
    Sprite altarLifeFront;

    Sprite gameOverFade;

    public PlayScreen(Gaem gaem) {
        this.gaem = gaem;

        bg_floor = new Sprite(TextureManager.getTexture("bg_floor.png"));
        bg_floor.setX(-Constants.RES_X / 2.0f);
        bg_floor.setY(-Constants.RES_Y / 2.0f);

        gaem.engine.addSystem(new InputSystem(gaem.engine));
        gaem.engine.addSystem(new SpawnSystem(1.0f, gaem.engine));
        gaem.engine.addSystem(new ItemSpawnSystem(25.0f, gaem.engine));
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
        float bboxX = 0.9f;
        float bboxY = 0.8f;
        spr = new Sprite(Utils.dumbSprite(10*4, 16*4));
        Controller controller = (Controllers.getControllers().size > 0) ?
                Controllers.getControllers().get(0) : null;
        players[0] = gaem.engine.createEntity()
                .add(new PositionComponent(-200, 0))
                .add(new VelocityComponent(0, 0))
                .add(new TypeComponent(TypeComponent.EntityType.Player))
                .add(new OwnerComponent(OwnerComponent.Owner.Player1))
                .add(new RenderComponent(spr, RenderComponent.Layer.Player))
                .add(new CollisionComponent(10*4*bboxX, 16*4*bboxY))
                .add(new AnimationComponent(AnimationFactory.playerRight(OwnerComponent.Owner.Player1)))
                .add(new InputComponent(controller));
        gaem.engine.addEntity(players[0]);

        if (numPlayers > 1) {
            controller = (Controllers.getControllers().size > 1) ?
                    Controllers.getControllers().get(1) : null;

            players[1] = gaem.engine.createEntity()
                    .add(new PositionComponent(200, 0))
                    .add(new VelocityComponent(0, 0))
                    .add(new TypeComponent(TypeComponent.EntityType.Player))
                    .add(new OwnerComponent(OwnerComponent.Owner.Player2))
                    .add(new RenderComponent(Utils.dumbSprite(10*4, 16*4), RenderComponent.Layer.Player))
                    .add(new CollisionComponent(10*4*bboxX, 16*4*bboxY))
                    .add(new AnimationComponent(AnimationFactory.playerLeft(OwnerComponent.Owner.Player2)))
                    .add(new InputComponent(controller));

            gaem.engine.addEntity(players[1]);
        }

        //Altar
        altar = ItemFactory.spawnAltar();
        altarLifeBack = new Sprite(TextureManager.getTexture("lifebar.png"), 0, 0, 16, 5);
        altarLifeFront = new Sprite(TextureManager.getTexture("lifebar.png"), 0, 5, 16, 5);
        altarLifeBack.setOrigin(0.0f, 0.0f);
        altarLifeBack.setPosition(-Constants.RES_X * 0.5f + 20.0f, Constants.RES_Y * 0.5f - 20.0f);
        altarLifeBack.setScale(20.0f, 3.0f);
        altarLifeFront.setOrigin(0.0f, 0.0f);
        altarLifeFront.setPosition(-Constants.RES_X * 0.5f + 20.0f, Constants.RES_Y * 0.5f - 20.0f);
        altarLifeFront.setScale(20.0f, 3.0f);

        // Dumb control points.
        createAltarPoints();

        //EnemyFactory.spawnWalker(120, 100);

        blackzor = Utils.dumbSprite((int)Constants.RES_X, (int)(Constants.RES_Y*0.25f));
        blackzor.setColor(Color.BLACK);
        blackzor.setX(-Constants.RES_X / 2);
        blackzor.setY(-Constants.RES_Y / 2);

        p1_faec = new Sprite(TextureManager.getTexture("p1_faec.png"));
        p1_faec.setX(-Constants.RES_X / 2 + 100);
        p1_faec.setY(-Constants.RES_Y / 2 + 100);
        p1_faec.scale(10);


        p2_faec = new Sprite(TextureManager.getTexture("p2_faec.png"));
        p2_faec.setX(-Constants.RES_X / 2 + 100);
        p2_faec.setY(-Constants.RES_Y / 2 + 100);
        p2_faec.scale(10);

        p3_faec = new Sprite(TextureManager.getTexture("p3_faec.png"));
        p3_faec.setX(-Constants.RES_X / 2 + 100);
        p3_faec.setY(-Constants.RES_Y / 2 + 100);
        p3_faec.scale(10);

        pinxo = new Sprite(TextureManager.getTexture("pinxo.png"));
        pinxo.setX(Constants.RES_X / 2 - 100);
        pinxo.setY(-Constants.RES_Y / 2 + 100);
        pinxo.scale(3);

        UltraManager.setState(UltraManager.State.IntroDialog);

        gameOverFade = Utils.dumbSprite((int)Constants.RES_X, (int)Constants.RES_Y);
        gameOverFade.setColor(0.2f, 0.0f, 0.0f, 0.7f);
        gameOverFade.setCenter(0.0f, 0.0f);
    }

    float le_timer = 0.0f;

    float timer_saltar_dialogs = 0.0f;

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

        // Draw Altar life
        gaem.batch.begin();
        altarLifeBack.draw(gaem.batch);
        int width = Math.round(16 * (float) Mappers.life.get(altar).life / Mappers.life.get(altar).maxLife);
        altarLifeFront.setRegion(0, 5, width, 5);
        altarLifeFront.setSize(width, 5);
        altarLifeFront.draw(gaem.batch);
        gaem.batch.end();

        /*
        gaem.batch.begin();
        UltraManager.lasthit_p1_spr.setRegion(UltraManager.lasthit_p1_anim.getKeyFrame(le_timer));
        UltraManager.lasthit_p1_spr.setX(-Constants.RES_X/2 + Constants.RES_X*0.05f);
        UltraManager.lasthit_p1_spr.setY((Constants.RES_Y/2) -(Constants.RES_Y/2)*0.25f );
        UltraManager. lasthit_p1_spr.draw(gaem.batch);
        gaem.batch.end();
        */

        if (!UltraManager.isGaemActive)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER) ||
                    XBox360Pad.anyControllerButton(XBox360Pad.BUTTON_START))
            {
                timer_saltar_dialogs += delta;
            }
            else
            {
                timer_saltar_dialogs = 0.0f;
            }


            gaem.batch.begin();


            if (timer_saltar_dialogs > 0.75f)
            {
                {
                    FontManager.dialog.draw(
                            gaem.batch,
                            "[RED]SIGUE PULSANDO PARA SALTAR EL DIALOGO[]",
                            -Constants.RES_X*0.45f,
                            Constants.RES_Y*0.45f);
                }
            }
            if (timer_saltar_dialogs > 3.0f)
            {
                UltraManager.setState(UltraManager.State.Game);
            }

            if (UltraManager.getState() == UltraManager.State.GameOverDialog)
            {
                gameOverFade.draw(gaem.batch);
            }

            blackzor.setAlpha(0.5f);
            blackzor.draw(gaem.batch);

            //Faec
            if (UltraManager.currentOwner() == UltraManager.DialogOwner.PLAYER_1)
                p1_faec.draw(gaem.batch);

            if (UltraManager.currentOwner() == UltraManager.DialogOwner.PLAYER_2)
                p2_faec.draw(gaem.batch);

            if (UltraManager.currentOwner() == UltraManager.DialogOwner.PLAYER_3)
                p3_faec.draw(gaem.batch);

            //Tecst
            UltraManager.textTimer += delta;
            FontManager.dialog.draw(gaem.batch,
                    UltraManager.getText(),
                    -Constants.RES_X*0.3f,
                    -Constants.RES_Y*0.3f);

            //Pinxo?
            if (UltraManager.isTextTimerFinished())
            {

                if (TimeUtils.millis()%500  < 250)
                    pinxo.draw(gaem.batch);

                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ||
                        XBox360Pad.anyControllerButton(XBox360Pad.BUTTON_A))
                {
                    if (!UltraManager.nextText())
                    {
                        UltraManager.isGaemActive = true;
                    }
                }

            }
            else
            {
                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ||
                        XBox360Pad.anyControllerButton(XBox360Pad.BUTTON_A))
                {
                    UltraManager.textTimer = UltraManager.textDuration;
                }
            }

            gaem.batch.end();
        }




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
        float alpha = MathUtils.PI / 2.0f - MathUtils.PI2 / 10.0f;
        for (int i = 0; i < numAltarPoints; ++i)
        {
            float posX = MathUtils.cos(alpha) * altarPointDistToCenter;
            float posY = MathUtils.sin(alpha) * altarPointDistToCenter;
            createAltarPoint(posX, posY, i);
            ItemFactory.spawnCandle(
                    MathUtils.cos(alpha) * altarPointDistToCenter * 0.3f,
                    MathUtils.sin(alpha) * altarPointDistToCenter * 0.3f
            );
            float calpha = alpha + MathUtils.PI2 / 10.0f;
            ItemFactory.spawnCandle(
                    MathUtils.cos(calpha) * altarPointDistToCenter,
                    MathUtils.sin(calpha) * altarPointDistToCenter
            );
            alpha += MathUtils.PI2 / 5.0f;
        }
    }
}
