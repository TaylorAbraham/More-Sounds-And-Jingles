package com.taylorabraham;

public enum Sound {
    AIR_GUITAR("Air_Guitar.wav"),
    BARROWS_COMPLETE("Barrows_complete.wav"),
    BA_WAVE_COMPLETE("Wave_complete_(Barbarian_Assault).wav"),
    BOATY("Boaty.wav"),
    BURTHORPE_GAMES_ROOM_DRAW("Draw_(Burthorpe_Games_Room).wav"),
    CACTI_HEALTH_CHECK("Checking_health_of_cacti.wav"),
    CASTLE_WARS_WIN("Castle_Wars_win.wav"),
    DELIVERY_GNOME_RESTAURANT("Reward_token_delivery_(Gnome_Restaurant).wav"),
    DICE_WIN("Dice_win_(Death_Plateau).wav"),
    DORGESHUUN_FIRST_SUNSHINE("First_sunshine_(Death_to_the_Dorgeshuun).wav"),
    DUEL_ARENA_START_OF_DUEL("Start_of_duel_(Duel_Arena).wav"),
    FARMING_AMULET("Farming_Amulet.wav"),
    FIGHT_CAVES_WAVE_COMPLETE("Wave_complete_(TzHaar_Fight_Cave).wav"),
    FIGHT_PITS_CHAMPION("Champion_(TzHaar_Fight_Pits).wav"),
    FORGETTABLE_PUZZLE_COMPLETED("Puzzle_Completed_(Forgettable_Tale...).wav"),
    FREMENNIK_BALLAD_COMPLETED("Ballad_completed_(The_Fremennik_Trials).wav"),
    FREMENNIK_BALLAD_OPENING("Ballad_opening_(The_Fremennik_Trials).wav"),
    FREMENNIK_BALLAD_REFRAIN("Ballad_refrain_(The_Fremennik_Trials).wav"),
    FREMENNIK_BERATING_THE_KING("Berating_the_King_end_(The_Fremennik_Isles).wav"),
    GIANTS_FOUNDRY_HANDING_IN("Giants'_Foundry_-_handing_in_sword.wav"),
    GNOMEBALL_GOAL("Goal_(Gnomeball).wav"),
    GNOME_SUCCESS_SPEEDY("Speedy_Gnome_success.wav"),
    GOTR_RIFT_CLOSES("Guardians_of_the_Rift_-_Abyssal_Rift_closes.wav"),
    JORMUNGAND_DEFEATED("The_Jormungand_defeated!.wav"),
    KELDAGRIM_TRADING_VICTORY("Victory_(Keldagrim_Trading).wav"),
    KINGS_RANSOM_TRIAL_VERDICT("Trial_verdict_(King's_Ransom).wav"),
    LEAGUE_AREA_UNLOCK_JINGLE("Trailblazer_League_area_unlock_jingle.wav"),
    LEAGUE_RELIC_UNLOCK_JINGLE("Trailblazer_League_relic_unlock_jingle.wav"),
    LEAGUE_TASK_COMPLETION_JINGLE("Trailblazer_League_task_completion_jingle.wav"),
    LEAGUE_TUTORIAL_COMPLETE("Trailblazer_League_tutorial_complete.wav"),
    MAZE_RANDOM_COMPLETE("Maze_complete.wav"),
    MM1_JUNGLE_DEMON_DEFEATED("Jungle_Demon_defeated_(Monkey_Madness_I).wav"),
    MOURNINGS_END_II_CRYSTAL("Obtaining_a_crystal_(Mourning's_End_Part_II).wav"),
    PEST_CONTROL_WIN("Pest_Control_Win.wav"),
    PICKPOCKETING_FAIRY_GODFATHER("Pickpocketing_Fairy_Godfather_(A_Fairy_Tale_Part_II).wav"),
    POSTIE_PETE_THEME("Postie_Pete_theme.wav"),
    PRISON_PETE_RANDOM_SUCCESS("Prison_Pete_success.wav"),
    PYRAMID_TOP_AGILITY_PYRAMID("Pyramid_top_(Agility_Pyramid).wav"),
    RATCATCHERS_KING_RAT_DIES("King_Rat_dies_(Ratcatchers).wav"),
    REACTIVATING_THE_WATCHTOWER("Reactivating_the_Watchtower.wav"),
    RECRUITMENTDRIVE_HYNN_TERPRETT("Ms._Hynn_Terprett's_puzzle_(Recruitment_Drive).wav"),
    RECRUITMENTDRIVE_KUAM_FERENTSE("Sir_Kuam_Ferentse's_puzzle_(Recruitment_Drive).wav"),
    RECRUITMENTDRIVE_LADY_TABLE("Lady_Table's_puzzle_(Recruitment_Drive).wav"),
    RECRUITMENTDRIVE_SIR_SPISHYUS("Sir_Spishyus'_puzzle_(Recruitment_Drive).wav"),
    RFD_LUMBRIDGE_GUIDE_QUIZ("Lumbridge_Guide_quiz_complete_(Recipe_for_Disaster).wav"),
    SCAPE_SCRAMBLED_2005_EASTER("Scape_Scrambled_(2005_Easter_Event).wav"),
    SCHEMATICS_COMPLETED("Completed_Schematics_(Between_a_Rock).wav"),
    SECURITY_BOX_OF_HEALTH("Box_of_Health_(Stronghold_of_Security).wav"),
    SHAIKAHAN_DEFEATED("The_Shaikahan_defeated_(Tai_Bwo_Wannai_Trio).wav"),
    SHOOTING_STARS_DISCOVERER("Shooting_Stars_discoverer.wav"),
    SOUL_WARS_WIN("Soul_Wars_win.wav"),
    TEMPLE_TREKKING_EVENT_SUCCESS("Event_success_(Temple_Trekking).wav"),
    TEMPLE_TREKKING_TREK_COMPLETE("Trek_complete_(Temple_Trekking).wav"),
    THEATRE_OF_BLOOD_WAVE_COMPLETE("Theatre_of_Blood_-_wave_complete.wav"),
    TIADECHE_RETURNS("Tiadeche_returns_(Tai_Bwo_Wannai_Trio).wav"),
    TINSAY_RETURNS("Tinsay_returns_(Tai_Bwo_Wannai_Trio).wav"),
    TOA_PATH_CHALLENGE_COMPLETE("Tombs_of_Amascut_-_Path_challenge_complete.wav"),
    WEREWOLF_SKULLBALL_GOAL("Goal_(Werewolf_Skullball).wav"),
    WILY_CAT_THEME("Wily_cat_theme.wav");

    private final String resourceName;

    Sound(String resNam) {
        this(resNam, false);
    }

    Sound(String resNam, boolean streamTroll) {
        resourceName = resNam;
    }

    String getResourceName() {
        return resourceName;
    }
}