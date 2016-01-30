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

    public static Animation blob()
    {
        Texture tex = TextureManager.getTexture("blob.png");

        Animation anim = new Animation(0.7f/4,
                new TextureRegion(tex, 0,  0, 10 ,8),
                new TextureRegion(tex, 10, 0, 10 ,8),
                new TextureRegion(tex, 0,  8, 10 ,8),
                new TextureRegion(tex, 10, 8, 10 ,8)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);

        return anim;
    }


}
