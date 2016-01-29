package com.mygdx.ritualggj16;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by ThrepwooD on 29/01/2016.
 */

public class TextureManager
{
    static final HashMap<String, Texture> textureMap = new HashMap<String, Texture>();

    public static void preLoadAll()
    {

    }

    public static Texture getTexture(String name)
    {
        if (!textureMap.containsKey(name))
        {
            textureMap.put(name, new Texture(name));
        }
        return textureMap.get(name);
    }

}
