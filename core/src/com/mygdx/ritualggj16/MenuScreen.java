package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by anon on 1/31/16.
 */
public class MenuScreen implements Screen
{
    Gaem game;
    BitmapFont menu_font_big;
    BitmapFont menu_font;

    Stage stage;

    private static final String title = "TEH RITUAL";
    private static int numShadows = 8;
    private static float titleX = 180.0f;
    private static float titleY = 500.0f;
    private static float shadowX = 10.0f;
    private static float shadowY = 10.0f;
    private static float wavePeriod = 2.0f;
    private static float waveTime = 0.0f;
    Label shadowLabels[];

    public MenuScreen(Gaem game)
    {
        AudioManager.stopAll();
        AudioManager.menuMusic.play();

        this.game = game;

        stage = new Stage(new FitViewport(Constants.RES_X, Constants.RES_Y));
        Gdx.input.setInputProcessor(stage);

        menu_font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BitDarling.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        menu_font = generator.generateFont(parameter);
        menu_font.getData().markupEnabled = true;



        FreeTypeFontGenerator.FreeTypeFontParameter parameter_big_title = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter_big_title.size = 90;
        parameter_big_title.color = Color.WHITE;
        parameter_big_title.borderWidth = 2;
        parameter_big_title.borderColor = Color.BLACK;
        menu_font_big = generator.generateFont(parameter_big_title);
        menu_font_big.getData().markupEnabled = true;

        shadowLabels = new Label[numShadows];
        for (int i = 0; i < numShadows; ++i)
        {
            shadowLabels[i] = new Label(title, new Label.LabelStyle(menu_font_big, Color.WHITE));
            shadowLabels[i].setAlignment(Align.center);
            shadowLabels[i].setPosition(titleX, titleY);
            shadowLabels[i].setFontScale(0.5f + 0.1f*i);
            stage.addActor(shadowLabels[i]);
        }

        Label.LabelStyle style_intro = new Label.LabelStyle(menu_font, Color.WHITE);
        Label label_intro = new Label("PRESS [YELLOW]START[] OR [GREEN]A[] TO PLAY", style_intro);
        label_intro.setAlignment(Align.left);
        label_intro.setAlignment(Align.left, Align.left);
        label_intro.setX(100.0f);
        label_intro.setY(Constants.RES_Y / 2);
        stage.addActor(label_intro);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        waveTime += delta;
        if (waveTime > wavePeriod)
        {
            waveTime = 0.0f;
        }
        float alpha = MathUtils.PI2 * (waveTime / wavePeriod);
        for (int i = 0; i < numShadows; ++i)
        {
            shadowLabels[i].setX(titleX + shadowX * MathUtils.cos(alpha) * i);
            shadowLabels[i].setY(titleY + shadowY * MathUtils.sin(alpha) * i);
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
