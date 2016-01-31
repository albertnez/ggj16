package com.mygdx.ritualggj16;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by anon on 1/31/16.
 */
public class AudioManager
{
    public static Music menu;
    public static Music game;

    public static Sound player;
    public static Sound pickup;
    public static Sound hit;

    public static void init()
    {
        menu = loadMusic("audio/haran.mp3");
        game = loadMusic("audio/neocrey.mp3");
        game.setLooping(true);

        player = loadSound("audio/player1shoot.mp3");
        pickup = loadSound("audio/pickup.mp3");
        hit = loadSound("audio/hit.mp3");
    }

    public static void stopAll()
    {
        if (menu.isPlaying())
        {
            menu.stop();
        }
        if (game.isPlaying())
        {
            game.stop();
        }
    }

    private static Music loadMusic(String filename)
    {
        return Gdx.audio.newMusic(Gdx.files.internal(filename));
    }

    private static Sound loadSound(String filename)
    {
        return Gdx.audio.newSound(Gdx.files.internal(filename));
    }
}
