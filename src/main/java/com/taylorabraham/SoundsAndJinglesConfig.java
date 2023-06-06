package com.taylorabraham;

import net.runelite.client.config.*;

@ConfigGroup(SoundsAndJinglesConfig.GROUP)
public interface SoundsAndJinglesConfig extends Config {
    String GROUP = "soundsandjingles";

    @Range(min = 0, max = 200)
    @ConfigItem(
            keyName = "jingleVolume",
            name = "Jingle volume",
            description = "Adjust how loud this plugin's sounds & jingles are played!",
            position = 0
    )
    default int jingleVolume() {
        return 100;
    }

    @ConfigItem(
            keyName = "playTest",
            name = "Test",
            description = "Should a jingle play when you say 'Testing'",
            position = 1
    )
    default Boolean playTest() {
        return true;
    }

    @ConfigItem(
            keyName = "testJingle",
            name = "Test jingle",
            description = "The jingle to play when test.",
            position = 2
    )
    default Sound testJingle() {
        return Sound.CASTLE_WARS_WIN;
    }
}
