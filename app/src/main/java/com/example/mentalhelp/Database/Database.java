package com.example.mentalhelp.Database;

public interface Database {
    String DB_NAME = "mental_help.db";
    Integer DB_VERSION = 7;

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

    //TABLE 5 - Themes: theme_id, [theme data]
    String TABLE_THEMES = "themes";
    String T_ID = "theme_id";
    String T_DETAILS = "theme_details";

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

    String query5 = "CREATE TABLE " + TABLE_THEMES + "("
            + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + T_DETAILS + " STRING);";
}