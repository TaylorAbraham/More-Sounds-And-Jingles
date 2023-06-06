package com.taylorabraham;

public enum Sound {
    AGILITY_PYRAMID_PYRAMID_TOP("Agility_Pyramid_Pyramid_Top.wav"),
    AIR_GUITAR("Air_Guitar.wav"),
    BARB_ASS_WAVE_COMPLETE("Barb_Ass_Wave_Complete.wav"),
    BARROWS_COMPLETE("Barrows_Complete.wav"),
    BURTHORPE_GAMES_ROOM_DRAW("Burthorpe_Games_Room_Draw.wav"),
    CACTI_HEALTH_CHECK("Cacti_Health_Check.wav"),
    CASTLE_WARS_WIN("Castle_Wars_Win.wav"),
    DELIVERY_GNOME_RESTAURANT("Delivery_Gnome_Restaurant.wav"),
    DICE_WIN("Dice_Win.wav"),
    DORGESHUUN_FIRST_SUNSHINE("Dorgeshuun_First_Sunshine.wav"),
    DUEL_ARENA_START_OF_DUEL("Duel_Arena_Start_of_Duel.wav"),
    EASTER_2005_SCAPE_SCRAMBLED("Easter_2005_Scape_Scrambled.wav"),
    FARMING_AMULET("Farming_Amulet.wav"),
    FIGHT_CAVES_WAVE_COMPLETE("Fight_Caves_Wave_Complete.wav"),
    FIGHT_PITS_CHAMPION("Fight_Pits_Champion.wav"),
    FORGETTABLE_PUZZLE_COMPLETED("Forgettable_Puzzle_Completed.wav"),
    FREMENNIK_BALLAD_COMPLETED("Fremennik_Ballad_Completed.wav"),
    FREMENNIK_BALLAD_OPENING("Fremennik_Ballad_Opening.wav"),
    FREMENNIK_BALLAD_REFRAIN("Fremennik_Ballad_Refrain.wav"),
    FREMENNIK_BERATING_THE_KING("Fremennik_Berating_the_King.wav"),
    GIANTS_FOUNDRY_HANDING_IN("Giants_Foundry_Handing_In.wav"),
    GNOMEBALL_GOAL("Gnomeball_Goal.wav"),
    GNOME_SUCCESS_SPEEDY("Gnome_Success_Speedy.wav"),
    GOTR_RIFT_CLOSES("GOTR_Rift_Closes.wav"),
    JORMUNGAND_DEFEATED("Jormungand_Defeated.wav"),
    KELDAGRIM_TRADING_VICTORY("Keldagrim_Trading_Victory.wav"),
    KINGS_RANSOM_TRIAL_VERDICT("King's_Ransom_Trial_Verdict.wav"),
    LEAGUE_AREA_UNLOCK_JINGLE("League_Area_Unlock_Jingle.wav"),
    LEAGUE_RELIC_UNLOCK_JINGLE("League_Relic_Unlock_Jingle.wav"),
    LEAGUE_TASK_COMPLETION_JINGLE("League_Task_Completion_Jingle.wav"),
    LEAGUE_TUTORIAL_COMPLETE("League_Tutorial_Complete.wav"),
    MAZE_RANDOM_COMPLETE("Maze_Random_Complete.wav"),
    MM1_JUNGLE_DEMON_DEFEATED("MM1_Jungle_Demon_Defeated.wav"),
    MOURNINGS_END_II_CRYSTAL("Mourning's_End_II_Crystal.wav"),
    PEST_CONTROL_WIN("Pest_Control_Win.wav"),
    PICKPOCKETING_FAIRY_GODFATHER("Pickpocketing_Fairy_Godfather.wav"),
    POSTIE_PETE_THEME("Postie_Pete_theme.wav"),
    PRISON_PETE_RANDOM_SUCCESS("Prison_Pete_Random_Success.wav"),
    RATCATCHERS_KING_RAT_DIES("Ratcatchers_King_Rat_Dies.wav"),
    REACTIVATING_THE_WATCHTOWER("Reactivating_the_Watchtower.wav"),
    RECRUITMENTDRIVE_HYNN_TERPRETT("Recruit_Drive_Hynn_Terprett.wav"),
    RECRUITMENTDRIVE_KUAM_FERENTSE("Recruit_Drive_Kuam_Ferentse.wav"),
    RECRUITMENTDRIVE_LADY_TABLE("Recruit_Drive_Lady_Table.wav"),
    RECRUITMENTDRIVE_SIR_SPISHYUS("Recruit_Drive_Sir_Spishyus.wav"),
    RFD_LUMBRIDGE_GUIDE_QUIZ("RFD_Lumbridge_Guide_Quiz.wav"),
    SCHEMATICS_COMPLETED("Schematics_Completed.wav"),
    SECURITY_BOX_OF_HEALTH("Security_Box_of_Health.wav"),
    SHAIKAHAN_DEFEATED("Shaikahan_Defeated.wav"),
    SHOOTING_STARS_DISCOVERER("Shooting_Stars_Discoverer.wav"),
    SOUL_WARS_WIN("Soul_Wars_Win.wav"),
    SWAMP_BOATY("Swamp_Boaty.wav"),
    TEMPLE_TREKKING_EVENT_SUCCESS("Temple_Trekking_Event_Success.wav"),
    TEMPLE_TREKKING_TREK_COMPLETE("Temple_Trekking_Trek_Complete.wav"),
    THEATRE_OF_BLOOD_WAVE_COMPLETE("Theatre_of_Blood_Wave_Complete.wav"),
    TIADECHE_RETURNS("Tiadeche_Returns.wav"),
    TINSAY_RETURNS("Tinsay_Returns.wav"),
    TOA_PATH_CHALLENGE_COMPLETE("TOA_Path_Challenge_Complete.wav"),
    WEREWOLF_SKULLBALL_GOAL("Werewolf_Skullball_Goal.wav"),
    WILY_CAT_THEME("Wily_Cat_Theme.wav");

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