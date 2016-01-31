package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by anon on 1/31/16.
 */
public class MenuScreen implements Screen
{
    private static class LabelActor extends Group
    {
        LabelActor(Label label)
        {
            this.addActor(label);
        }
    }

    Gaem game;
    BitmapFont menu_font_big;
    BitmapFont menu_font;

    BitmapFont menu_font_footer;

    Stage stage;

    private LabelActor la;
    private static final String title = "TEH RITUAL";
    private static int numShadows = 8;
    private static float titleX = 180.0f;
    private static float titleY = 450.0f;
    private static final float originShadowX = 10.0f;
    private static final float originShadowY = 10.0f;
    private static float shadowX = 4.0f;
    private static float shadowY = 4.0f;
    private static float wavePeriod = 2.0f;
    private static float waveTime = 0.0f;
    private static float rotPeriod = 10.0f;
    private static float rotTime = 0.0f;
    private static float totalTime = 0.0f;
    LabelActor shadowLabels[];

    public MenuScreen(Gaem game)
    {
        AudioManager.stopAll();
        AudioManager.menu.play();

        this.game = game;

        stage = new Stage(new FitViewport(Constants.RES_X, Constants.RES_Y));
        Gdx.input.setInputProcessor(stage);

        menu_font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BitDarling.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 35;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        menu_font = generator.generateFont(parameter);
        menu_font.getData().markupEnabled = true;

        FreeTypeFontGenerator.FreeTypeFontParameter parameter_big_title = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter_big_title.size = 80;
        parameter_big_title.color = Color.WHITE;
        parameter_big_title.borderWidth = 5;
        parameter_big_title.borderColor = Color.BLACK;
        menu_font_big = generator.generateFont(parameter_big_title);
        menu_font_big.getData().markupEnabled = true;

        FreeTypeFontGenerator.FreeTypeFontParameter parameter_foot_title = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter_foot_title.size = 20;
        parameter_foot_title.color = Color.WHITE;
        parameter_foot_title.borderWidth = 2;
        parameter_foot_title.borderColor = Color.BLACK;
        menu_font_footer = generator.generateFont(parameter_foot_title);
        menu_font_footer.getData().markupEnabled = true;

//        shadowLabels = new Label[numShadows];
        shadowLabels = new LabelActor[numShadows];
        for (int i = 0; i < numShadows; ++i)
        {
            Color c = new Color(Color.RED);
            c.a = (float)(i+1)/numShadows;
            Label label = new Label(title, new Label.LabelStyle(menu_font_big, c));
            label.setAlignment(Align.center);
          //  label.setPosition(titleX, titleY);
            //label.setFontScale(0.5f + 0.1f * i);
            shadowLabels[i] = new LabelActor(label);
            shadowLabels[i].setOrigin(200f, 0.0f);
            stage.addActor(shadowLabels[i]);
        }

        la = new LabelActor(new Label("TEH RITUAL - GGJ BCN 2016 \n BY: PNKTHREPWOOD, ALBERTNEZ",
                new Label.LabelStyle(menu_font_footer, Color.WHITE)));
        la.setX(20);
        la.setY(20);
        stage.addActor(la);

        Label.LabelStyle style_intro = new Label.LabelStyle(menu_font, Color.WHITE);
        Label label_intro = new Label("PRESS [YELLOW]START[] OR [GREEN]A[] TO FUN", style_intro);
        label_intro.setAlignment(Align.left);
        label_intro.setAlignment(Align.left, Align.left);
        label_intro.setX(150.0f);
        label_intro.setY(Constants.RES_Y * 0.25f);
        stage.addActor(label_intro);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        waveTime += delta;
        rotTime += delta;
        totalTime += delta;
        if (waveTime >= wavePeriod)
        {
            waveTime -= wavePeriod;
        }
        if (rotTime >= rotPeriod)
        {
            rotTime -= rotPeriod;
        }
        float alpha = MathUtils.PI2 * (waveTime / wavePeriod);
        float rot = MathUtils.PI2 * (rotTime / rotPeriod);

        shadowX = originShadowX + 1.0f * MathUtils.sin(totalTime);
        shadowY = originShadowY + 1.0f * MathUtils.sin(totalTime);
        for (int i = 0; i < numShadows; ++i)
        {
            shadowLabels[i].setX(titleX + shadowX * MathUtils.cos((alpha + 0.4f*i) * 2.0f) * i );
            shadowLabels[i].setY(titleY + shadowY * MathUtils.sin(alpha + 0.4f * i)*1.5f * i);
            shadowLabels[i].setScale(0.1f + 0.13f * i);
            shadowLabels[i].setRotation(MathUtils.sin(rot*3.0f) * 10.0f);
        }

        if (XBox360Pad.anyControllerButtonPressed(XBox360Pad.BUTTON_START) ||
                XBox360Pad.anyControllerButtonPressed(XBox360Pad.BUTTON_A))
        {
            stage.dispose();
            game.setScreen(new PlayScreen(game));
            return;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }
}
