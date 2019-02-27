package me.riseremi.utils;

import com.rovioli.starred.ApplicationKt;
import com.rovioli.starred.service.audio.AudioManager;

public class ComponentWrapperUtils {
    public static AudioManager getAudioManager() {
        return (AudioManager) ApplicationKt.getComponent("AudioManager");
    }
}
