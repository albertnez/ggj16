package com.mygdx.ritualggj16.Factorys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.ritualggj16.Components.AnimationComponent;
import com.mygdx.ritualggj16.Components.OwnerComponent;
import com.mygdx.ritualggj16.TextureManager;

import javax.xml.soap.Text;

/**
 * Created by ThrepwooD on 30/01/2016.
 */
public class AnimationFactory
{
    public static Animation playerLeft(OwnerComponent.Owner owner)
    {
        Texture tex = TextureManager.getTexture(
                owner == OwnerComponent.Owner.Player1 ? "player_sheet.png" : "player2_sheet.png"
        );

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 0,   0, 10 ,16),
                new TextureRegion(tex, 0,  16, 10 ,16),
                new TextureRegion(tex, 0,   0, 10 ,16),
                new TextureRegion(tex, 0,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
    public static Animation playerRight(OwnerComponent.Owner owner)
    {
        Texture tex = TextureManager.getTexture(
                owner == OwnerComponent.Owner.Player1 ? "player_sheet.png" : "player2_sheet.png"
        );

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 10,   0, 10 ,16),
                new TextureRegion(tex, 10,  16, 10 ,16),
                new TextureRegion(tex, 10,   0, 10 ,16),
                new TextureRegion(tex, 10,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
    public static Animation playerDown(OwnerComponent.Owner owner)
    {
        Texture tex = TextureManager.getTexture(
                owner == OwnerComponent.Owner.Player1 ? "player_sheet.png" : "player2_sheet.png"
        );

        Animation anim = new Animation(0.5f/3,
                new TextureRegion(tex, 20,   0, 10 ,16),
                new TextureRegion(tex, 20,  16, 10 ,16),
                new TextureRegion(tex, 20,   0, 10 ,16),
                new TextureRegion(tex, 20,  32, 10 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public static Animation playerUp(OwnerComponent.Owner owner)
    {
        Texture tex = TextureManager.getTexture(
                owner == OwnerComponent.Owner.Player1 ? "player_sheet.png" : "player2_sheet.png"
        );

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
    public static Animation blobDie()
    {
        Texture tex = TextureManager.getTexture("blob.png");

        Animation anim = new Animation(1.5f/4,
                new TextureRegion(tex, 20,  0, 10 ,8),
                new TextureRegion(tex, 30, 0, 10 ,8),
                new TextureRegion(tex, 20,  8, 10 ,8),
                new TextureRegion(tex, 30, 8, 10 ,8)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);

        return anim;
    }

    public static Animation candle()
    {
        Texture tex = TextureManager.getTexture("items.png");

        Animation anim = new Animation(0.5f/4,
                new TextureRegion(tex, 0,  0, 8 ,16),
                new TextureRegion(tex, 8,  0, 8 ,16),
                new TextureRegion(tex, 16,  0, 8 ,16),
                new TextureRegion(tex, 24,  0, 8 ,16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);

        return anim;
    }

    public static Animation altarItem(int id)
    {
        Texture tex = TextureManager.getTexture("losetas.png");
        Animation anim = new Animation(0.5f/2.0f,
                new TextureRegion(tex, 32, 16*id, 16, 16),
                new TextureRegion(tex, 48, 16*id, 16, 16)
        );
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
}
