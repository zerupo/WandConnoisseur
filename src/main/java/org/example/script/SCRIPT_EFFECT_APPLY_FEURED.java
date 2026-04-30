package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_APPLY_FEURED extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Feured";
        this.imageFile = "feured.png";
        this.emote = staticEmote;
    }
}