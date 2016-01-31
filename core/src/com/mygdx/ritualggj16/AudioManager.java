package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by anon on 1/31/16.
 */
public class AudioManager
{
    public static Music menuMusic;
    public static Music gameMusic;

    public static Sound playerShoot;

    public static void init()
    {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/haran.mp3"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/neocrey.mp3"));
        gameMusic.setLooping(true);

        playerShoot = Gdx.audio.newSound((Gdx.files.internal("audio/player1shoot.mp3")));
    }

    public static void stopAll()
    {
        if (menuMusic.isPlaying())
        {
            menuMusic.stop();
        }
        if (gameMusic.isPlaying())
        {
            gameMusic.stop();
        }
    }
}
