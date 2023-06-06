package com.taylorabraham;

import com.google.inject.Provides;

import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;

import static net.runelite.api.Varbits.DIARY_KARAMJA_EASY;
import static net.runelite.api.Varbits.DIARY_KARAMJA_HARD;
import static net.runelite.api.Varbits.DIARY_KARAMJA_MEDIUM;

import net.runelite.api.annotations.Varbit;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import okhttp3.OkHttpClient;

import javax.inject.Inject;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
        name = "More Sounds & Jingles",
        description = "Adds customizable sound effects for missing sounds like achievement diary tasks, farming contracts, and more!"
)
public class SoundsAndJinglesPlugin extends Plugin {
    @Inject
    private Client client;

    @Getter(AccessLevel.PACKAGE)
    @Inject
    private ClientThread clientThread;

    @Inject
    private ChatMessageManager chatMessageManager;

    @Inject
    private SoundEngine soundEngine;

    @Inject
    private SoundsAndJinglesConfig config;

    @Inject
    private ScheduledExecutorService executor;

    @Inject
    private OkHttpClient okHttpClient;

    private final int[] varbitsAchievementDiaries = {
            Varbits.DIARY_ARDOUGNE_EASY, Varbits.DIARY_ARDOUGNE_MEDIUM, Varbits.DIARY_ARDOUGNE_HARD, Varbits.DIARY_ARDOUGNE_ELITE,
            Varbits.DIARY_DESERT_EASY, Varbits.DIARY_DESERT_MEDIUM, Varbits.DIARY_DESERT_HARD, Varbits.DIARY_DESERT_ELITE,
            Varbits.DIARY_FALADOR_EASY, Varbits.DIARY_FALADOR_MEDIUM, Varbits.DIARY_FALADOR_HARD, Varbits.DIARY_FALADOR_ELITE,
            Varbits.DIARY_KANDARIN_EASY, Varbits.DIARY_KANDARIN_MEDIUM, Varbits.DIARY_KANDARIN_HARD, Varbits.DIARY_KANDARIN_ELITE,
            DIARY_KARAMJA_EASY, DIARY_KARAMJA_MEDIUM, DIARY_KARAMJA_HARD, Varbits.DIARY_KARAMJA_ELITE,
            Varbits.DIARY_KOUREND_EASY, Varbits.DIARY_KOUREND_MEDIUM, Varbits.DIARY_KOUREND_HARD, Varbits.DIARY_KOUREND_ELITE,
            Varbits.DIARY_LUMBRIDGE_EASY, Varbits.DIARY_LUMBRIDGE_MEDIUM, Varbits.DIARY_LUMBRIDGE_HARD, Varbits.DIARY_LUMBRIDGE_ELITE,
            Varbits.DIARY_MORYTANIA_EASY, Varbits.DIARY_MORYTANIA_MEDIUM, Varbits.DIARY_MORYTANIA_HARD, Varbits.DIARY_MORYTANIA_ELITE,
            Varbits.DIARY_VARROCK_EASY, Varbits.DIARY_VARROCK_MEDIUM, Varbits.DIARY_VARROCK_HARD, Varbits.DIARY_VARROCK_ELITE,
            Varbits.DIARY_WESTERN_EASY, Varbits.DIARY_WESTERN_MEDIUM, Varbits.DIARY_WESTERN_HARD, Varbits.DIARY_WESTERN_ELITE,
            Varbits.DIARY_WILDERNESS_EASY, Varbits.DIARY_WILDERNESS_MEDIUM, Varbits.DIARY_WILDERNESS_HARD, Varbits.DIARY_WILDERNESS_ELITE
    };

    // Killcount and new pb patterns from runelite/ChatCommandsPlugin
    private static final String ZULRAH = "Zulrah";
    private static final String C_ENGINEER = "C Engineer";
    private static final String SKILL_SPECS = "Skill Specs";
    private static final Pattern KILLCOUNT_PATTERN = Pattern.compile("Your (?:completion count for |subdued |completed )?(.+?) (?:(?:kill|harvest|lap|completion) )?(?:count )?is: <col=ff0000>(\\d+)</col>");
    private static final Pattern NEW_PB_PATTERN = Pattern.compile("(?i)(?:(?:Fight |Lap |Challenge |Corrupted challenge )?duration:|Subdued in) <col=[0-9a-f]{6}>(?<pb>[0-9:]+(?:\\.[0-9]+)?)</col> \\(new personal best\\)");
    private static final Pattern MAHOMES_PATTERN = Pattern.compile("You have completed <col=ff0000>(\\d+)</col> contracts with a total of <col=ff0000>(\\d+)</col> points\\.");

    private static final Pattern STRAY_DOG_GIVEN_BONES_REGEX = Pattern.compile("You give the dog some nice.*bones.*");
    private static final Pattern TEST_REGEX = Pattern.compile("Testing");
    private static final Pattern COLLECTION_LOG_ITEM_REGEX = Pattern.compile("New item added to your collection log:.*");
    private static final Pattern COMBAT_TASK_REGEX = Pattern.compile("Congratulations, you've completed an? (?:\\w+) combat task:.*");
    private static final Pattern QUEST_REGEX = Pattern.compile("Congratulations, you've completed a quest:.*");

    private static final Random random = new Random();

    private static final WorldArea FALADOR_HAIRDRESSER = new WorldArea(new WorldPoint(2942, 3377, 0), 8, 12);
    private static final int ID_OBJECT_LUMCASTLE_GROUND_LEVEL_STAIRCASE = 16671;
    private static final int WORLD_POINT_LUMCASTLE_STAIRCASE_NORTH_X = 3204;
    private static final int WORLD_POINT_LUMCASTLE_STAIRCASE_NORTH_Y = 3229;

    private static final Set<Integer> badCollectionLogNotificationSettingValues = new HashSet<Integer>() {{
        add(0);
        add(2);
    }};

    private final Map<Skill, Integer> oldExperience = new EnumMap<>(Skill.class);
    private final Map<Integer, Integer> oldAchievementDiaries = new HashMap<>();

    private int lastLoginTick = -1;
    private int lastZulrahKillTick = -1;
    private int lastColLogSettingWarning = -1;

    private Player cEngineerPlayer = null;
    private boolean gameStateLoggedIn = false;

    @Override
    protected void startUp() throws Exception {
        clientThread.invoke(this::setupOldMaps);
        lastLoginTick = -1;
        executor.submit(() -> {
            SoundFileManager.ensureDownloadDirectoryExists();
            SoundFileManager.downloadAllMissingSounds(okHttpClient);
        });
    }

    @Override
    protected void shutDown() throws Exception {
        oldExperience.clear();
        oldAchievementDiaries.clear();
        soundEngine.close();
    }

    private void setupOldMaps() {
        if (client.getGameState() != GameState.LOGGED_IN) {
            oldExperience.clear();
            oldAchievementDiaries.clear();
        } else {
            for (final Skill skill : Skill.values()) {
                oldExperience.put(skill, client.getSkillExperience(skill));
            }
            for (@Varbit int diary : varbitsAchievementDiaries) {
                int value = client.getVarbitValue(diary);
                oldAchievementDiaries.put(diary, value);
            }
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        gameStateLoggedIn = event.getGameState() == GameState.LOGGED_IN;
        switch (event.getGameState()) {
            case LOGIN_SCREEN:
            case HOPPING:
            case LOGGING_IN:
            case LOGIN_SCREEN_AUTHENTICATOR:
                oldExperience.clear();
                oldAchievementDiaries.clear();
            case CONNECTION_LOST:
                // set to -1 here in-case of race condition with varbits changing before this handler is called
                // when game state becomes LOGGED_IN
                lastLoginTick = -1;
                lastColLogSettingWarning = client.getTickCount(); // avoid warning during DC
                break;
            case LOGGED_IN:
                lastLoginTick = client.getTickCount();
                break;
        }
    }

    @Subscribe
    public void onChatMessage(ChatMessage chatMessage) {
        if (config.playTest() && TEST_REGEX.matcher(chatMessage.getMessage()).matches()) {
            soundEngine.playClip(config.testJingle());
        }
        if (chatMessage.getType() != ChatMessageType.GAMEMESSAGE && chatMessage.getType() != ChatMessageType.SPAM) {
            return;
        }

        if (COLLECTION_LOG_ITEM_REGEX.matcher(chatMessage.getMessage()).matches()) {
            soundEngine.playClip(Sound.FARMING_AMULET);
        } else if (COMBAT_TASK_REGEX.matcher(chatMessage.getMessage()).matches()) {
            soundEngine.playClip(Sound.GNOMEBALL_GOAL);
        } else if (MAHOMES_PATTERN.matcher(chatMessage.getMessage()).matches()) {
            soundEngine.playClip(Sound.CASTLE_WARS_WIN);
        }
    }

    private void checkAndWarnForCollectionLogNotificationSetting(int newVarbitValue) {
//        if (!config.announceCollectionLog())
//            return;

        if (!gameStateLoggedIn)
            return;

        if (badCollectionLogNotificationSettingValues.contains(newVarbitValue)) {
            if (lastColLogSettingWarning == -1 || client.getTickCount() - lastColLogSettingWarning > 16) {
                lastColLogSettingWarning = client.getTickCount();
                sendHighlightedMessage("Please enable \"Collection log - New addition notification\" in your game settings for C Engineer to know when to announce it! (The chat message one, pop-up doesn't matter)");
            }
        }
    }

    @Subscribe
    public void onVarbitChanged(VarbitChanged varbitChanged) {
        if (varbitChanged.getVarbitId() == Varbits.COLLECTION_LOG_NOTIFICATION) {
            checkAndWarnForCollectionLogNotificationSetting(varbitChanged.getValue());
        }

        // As we can't listen to specific varbits, we get a tonne of events BEFORE the game has even set the player's
        // diary varbits correctly, meaning it assumes every diary is on 0, then suddenly every diary that has been
        // completed gets updated to the true value and tricks the plugin into thinking they only just finished it.
        // To avoid this behaviour, we make sure the current tick count is sufficiently high that we've already passed
        // the initial wave of varbit changes from logging in.
        if (lastLoginTick == -1 || client.getTickCount() - lastLoginTick < 8) {
            return; // Ignoring varbit change as only just logged in
        }

        // Apparently I can't check if it's a particular varbit using the names from Varbits enum, so this is the way
        for (@Varbit int diary : varbitsAchievementDiaries) {
            int newValue = client.getVarbitValue(diary);
            int previousValue = oldAchievementDiaries.getOrDefault(diary, -1);
            oldAchievementDiaries.put(diary, newValue);
            if (previousValue != -1 && previousValue != newValue && isAchievementDiaryCompleted(diary, newValue)) { // && config.announceAchievementDiary()
                // value was not unknown (we know the previous value), value has changed, and value indicates diary is completed now
                soundEngine.playClip(Sound.MM1_JUNGLE_DEMON_DEFEATED);
            }
        }
    }

    @Subscribe
    public void onPlayerSpawned(PlayerSpawned playerSpawned) {
        Player player = playerSpawned.getPlayer();

        if (C_ENGINEER.equals(player.getName())) {
            cEngineerPlayer = player;
        }
    }

    @Subscribe
    public void onPlayerDespawned(PlayerDespawned playerDespawned) {
        Player player = playerDespawned.getPlayer();

        if (C_ENGINEER.equals(player.getName())) {
            cEngineerPlayer = null;
        }
    }


    private boolean isAchievementDiaryCompleted(int diary, int value) {
        switch (diary) {
            case DIARY_KARAMJA_EASY:
            case DIARY_KARAMJA_MEDIUM:
            case DIARY_KARAMJA_HARD:
                return value == 2; // jagex, why?
            default:
                return value == 1;
        }
    }

    @Provides
    SoundsAndJinglesConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(SoundsAndJinglesConfig.class);
    }

    private void sendHighlightedMessage(String message) {
        String highlightedMessage = new ChatMessageBuilder()
                .append(ChatColorType.HIGHLIGHT)
                .append(message)
                .build();

        chatMessageManager.queue(QueuedMessage.builder()
                .type(ChatMessageType.CONSOLE)
                .runeLiteFormattedMessage(highlightedMessage)
                .build());
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        if (SoundsAndJinglesConfig.GROUP.equals(event.getGroup())) {

            if ("announceCollectionLog".equals(event.getKey())) {
                clientThread.invokeLater(() ->
                        checkAndWarnForCollectionLogNotificationSetting(client.getVarbitValue(Varbits.COLLECTION_LOG_NOTIFICATION)));
            }
        }
    }
}
