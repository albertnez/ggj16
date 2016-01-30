package com.mygdx.ritualggj16.Factorys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.TextureManager;

import javax.xml.soap.Text;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class AnimationFactory
{
    public static Animation playerLeft()
    {
        Texture tex = TextureManager.getTexture("player_sheet.png");

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 0,   0, 10 ,16),
                new TextureRegion(tex, 0,  16, 10 ,16),
                new TextureRegion(tex, 0,   0, 10 ,16),
                new TextureRegion(tex, 0,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
    public static Animation playerRight()
    {
        Texture tex = TextureManager.getTexture("player_sheet.png");

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 10,   0, 10 ,16),
                new TextureRegion(tex, 10,  16, 10 ,16),
                new TextureRegion(tex, 10,   0, 10 ,16),
                new TextureRegion(tex, 10,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
    public static Animation playerDown()
    {
        Texture tex = TextureManager.getTexture("player_sheet.png");

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 20,   0, 10 ,16),
                new TextureRegion(tex, 20,  16, 10 ,16),
                new TextureRegion(tex, 20,   0, 10 ,16),
                new TextureRegion(tex, 20,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public static Animation playerUp()
    {
        Texture tex = TextureManager.getTexture("player_sheet.png");

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 30,   0, 10 ,16),
                new TextureRegion(tex, 30,  16, 10 ,16),
                new TextureRegion(tex, 30,   0, 10 ,16),
                new TextureRegion(tex, 30,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }


    public static Animation blob()
    {
        Texture tex = TextureManager.getTexture("blob.png");

        Animation anim = new Animation(1.5f/4,
                new TextureRegion(tex, 0,  0, 10 ,8),
                new TextureRegion(tex, 10, 0, 10 ,8),
                new TextureRegion(tex, 0,  8, 10 ,8),
                new TextureRegion(tex, 10, 8, 10 ,8)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);

        return anim;
    }


}
