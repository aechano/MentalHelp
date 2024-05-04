package com.example.mentalhelp.Database;

public interface Database {
    String DB_NAME = "mental_help.db";
    Integer DB_VERSION = 5;

    //TABLE 1 - Journal: journal_id, title, contents, date_created, date_modified
    String TABLE_JOURNAL = "journal";
    String J_ID = "journal_id"; //title, contents, date_created, date_modified
    String J_TITLE = "title";
    String J_CONTENTS = "contents";
    String J_DATE_CREATED = "date_created";
    String J_DATE_MODIFIED = "date_modified";

    //TABLE 2 - Music: music_id, music, music_title;
    String TABLE_MUSIC = "music";
    String M_ID = "music_id";
    String M_MUSIC = "music";
    String M_TITLE = "music_title";

    //TABLE 3 - Guides: guide_id, guide_title, contents
    String TABLE_GUIDES = "guides";
    String G_ID = "guide_id";
    String G_TITLE = "guide_title";
    String G_CONTENTS = "contents";

    //TABLE 4 - HappyFit: video_id, video, video_title, video_length
    String TABLE_HAPPY_FIT = "happy_fit";
    String HF_ID = "video_id";
    String HF_VIDEO = "video";
    String HF_TITLE = "video_title";
    String HF_LENGTH = "video_length";

    //TABLE 5 - Themes: theme_id, [theme data]
    String TABLE_THEMES = "themes";
    String T_ID = "theme_id";
    String T_DETAILS = "theme_details";

    //TABLE 6 - Events: event_id, starting_date, ending_date, starting_time, ending_time, event_name, event_descriptions, event_icon
    String TABLE_EVENTS = "events";
    String E_ID = "event_id";
    String E_STARTING_DATE = "starting_date";
    String E_ENDING_DATE = "ending_date";
    String E_STARTING_TIME = "starting_time";
    String E_ENDING_TIME = "ending_time";
    String E_NAME = "event_name";
    String E_DESCRIPTION = "event_descriptions";
    String E_ICON = "event_icon";

    //QUERIES
    String query1 = "CREATE TABLE " + TABLE_JOURNAL + "("
            + J_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + J_TITLE + " TEXT, "
            + J_CONTENTS + " TEXT, "
            + J_DATE_CREATED + " INTEGER, "
            + J_DATE_MODIFIED + " INTEGER);";

    String query2 = "CREATE TABLE " + TABLE_MUSIC + "("
            + M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + M_MUSIC + " INTEGER, "
            + M_TITLE + " TEXT);";

    String query3 = "CREATE TABLE " + TABLE_GUIDES + "("
            + G_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + G_TITLE + " TEXT UNIQUE, "
            + G_CONTENTS + " TEXT);";

    String query4 = "CREATE TABLE " + TABLE_HAPPY_FIT + "("
            + HF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HF_TITLE + " REAL, "
            + HF_VIDEO + " INTEGER, "
            + HF_LENGTH + " INTEGER);";

    String query5 = "CREATE TABLE " + TABLE_THEMES + "("
            + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + T_DETAILS + " REAL);";

    String query6 = "CREATE TABLE " + TABLE_EVENTS + "("
            + E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + E_STARTING_DATE + " INTEGER, "
            + E_ENDING_DATE + " INTEGER, "
            + E_STARTING_TIME + " INTEGER, "
            + E_ENDING_TIME + " INTEGER, "
            + E_NAME + " TEXT, "
            + E_DESCRIPTION + " TEXT, "
            + E_ICON + " TEXT);";
}