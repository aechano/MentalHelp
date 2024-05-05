package com.example.mentalhelp.Database;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;
import static com.example.mentalhelp.Database.Database.DB_NAME;
import static com.example.mentalhelp.Database.Database.DB_VERSION;
import static com.example.mentalhelp.Database.Database.G_CONTENTS;
import static com.example.mentalhelp.Database.Database.G_ID;
import static com.example.mentalhelp.Database.Database.G_TITLE;
import static com.example.mentalhelp.Database.Database.J_CONTENTS;
import static com.example.mentalhelp.Database.Database.J_DATE_CREATED;
import static com.example.mentalhelp.Database.Database.J_DATE_MODIFIED;
import static com.example.mentalhelp.Database.Database.J_ID;
import static com.example.mentalhelp.Database.Database.J_TITLE;
import static com.example.mentalhelp.Database.Database.M_ID;
import static com.example.mentalhelp.Database.Database.M_MUSIC;
import static com.example.mentalhelp.Database.Database.M_TITLE;
import static com.example.mentalhelp.Database.Database.TABLE_EVENTS;
import static com.example.mentalhelp.Database.Database.TABLE_GUIDES;
import static com.example.mentalhelp.Database.Database.TABLE_HAPPY_FIT;
import static com.example.mentalhelp.Database.Database.TABLE_JOURNAL;
import static com.example.mentalhelp.Database.Database.TABLE_MUSIC;
import static com.example.mentalhelp.Database.Database.TABLE_THEMES;
import static com.example.mentalhelp.Database.Database.query1;
import static com.example.mentalhelp.Database.Database.query2;
import static com.example.mentalhelp.Database.Database.query3;
import static com.example.mentalhelp.Database.Database.query4;
import static com.example.mentalhelp.Database.Database.query5;
import static com.example.mentalhelp.Database.Database.query6;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mentalhelp.Database.Objects.Music;
import com.example.mentalhelp.Model.GuideListModel;
import com.example.mentalhelp.Model.JournalListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class DB extends SQLiteOpenHelper {

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUIDES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HAPPY_FIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS journey");

        // trigger onCreate
        this.onCreate(db);
    }

    public void populateMusicTable() {
        List<Music> musicList = this.getAllMusic();
        int currentEntries = 5; //update this every time you insert new preset music.
        if (musicList.size() == currentEntries)
            return; // if music table rows is equal to the current entries, stop the function.

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MUSIC, null, null);

        ContentValues values = new ContentValues();

        //first music
        values.put(M_MUSIC, R.raw.alpha);
        values.put(M_TITLE, "Meditation Music: Alpha");
        db.insertWithOnConflict(TABLE_MUSIC, null, values, CONFLICT_IGNORE);
        values.clear();

        //second music
        values.put(M_MUSIC, R.raw.beta);
        values.put(M_TITLE, "Meditation Music: Beta");
        db.insertWithOnConflict(TABLE_MUSIC, null, values, CONFLICT_IGNORE);
        values.clear();

        //third music
        values.put(M_MUSIC, R.raw.birds);
        values.put(M_TITLE, "Meditation Music: Birds");
        db.insertWithOnConflict(TABLE_MUSIC, null, values, CONFLICT_IGNORE);
        values.clear();

        //fourth music
        values.put(M_MUSIC, R.raw.lofi);
        values.put(M_TITLE, "Meditation Music: Lofi");
        db.insertWithOnConflict(TABLE_MUSIC, null, values, CONFLICT_IGNORE);
        values.clear();

        //fifth music
        values.put(M_MUSIC, R.raw.rain);
        values.put(M_TITLE, "Meditation Music: Rain");
        db.insertWithOnConflict(TABLE_MUSIC, null, values, CONFLICT_IGNORE);
        values.clear();
    }

    public void populateGuidesTable() {
        List<Music> musicList = this.getAllMusic();
        int currentEntries = 11; //update this every time you insert new preset music.
        if (musicList.size() == currentEntries)
            return; // if music table rows is equal to the current entries, stop the function.

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_GUIDES, null, null);

        ContentValues values = new ContentValues();

        //first guide
        values.put(G_CONTENTS, "substance_use.pdf");
        values.put(G_TITLE, "Substance Use Problems");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //second guide
        values.put(G_CONTENTS, "gambling_harm.pdf");
        values.put(G_TITLE, "Gambling Harm");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //third guide
        values.put(G_CONTENTS, "depression.pdf");
        values.put(G_TITLE, "Depression");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //fourth guide
        values.put(G_CONTENTS, "panic_attacks.pdf");
        values.put(G_TITLE, "Panic Attacks");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //fifth guide
        values.put(G_CONTENTS, "eating_disorders.pdf");
        values.put(G_TITLE, "Eating Disorders");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //sixth guide
        values.put(G_CONTENTS, "non_suicidal_injury.pdf");
        values.put(G_TITLE, "Non-Suicidal Self-Injury");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //seventh guide
        values.put(G_CONTENTS, "traumatic_events.pdf");
        values.put(G_TITLE, "Traumatic Events (Adults)");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //eight guide
        values.put(G_CONTENTS, "suicidal_thoughts.pdf");
        values.put(G_TITLE, "Suicidal Thoughts and Behaviours");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //ninth guide
        values.put(G_CONTENTS, "suicide.pdf");
        values.put(G_TITLE, "Suicide First Aid Guidelines for the Philippines");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //tenth guide
        values.put(G_CONTENTS, "lgbtq.pdf");
        values.put(G_TITLE, "Considerations When Providing Mental Health First Aid to an LGBTIQ+ Person");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

        //eleventh guide
        values.put(G_CONTENTS, "intellectual_disability.pdf");
        values.put(G_TITLE, "Considerations When Offering Mental Health First Aid to a Person with an Intellectual Disability");
        db.insertWithOnConflict(TABLE_GUIDES, null, values, CONFLICT_IGNORE);
        values.clear();

    }

    public List<Music> getAllMusic() {

        List<Music> musicList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(M_ID));
                int music = cursor.getInt(cursor.getColumnIndex(M_MUSIC));
                String musicTitle = cursor.getString(cursor.getColumnIndex(M_TITLE));

                Music musicObject = new Music(id, music, musicTitle);
                musicList.add(musicObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return musicList;
    }

    public List<GuideListModel> getAllGuides() {

        List<GuideListModel> guidesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_GUIDES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String contents = cursor.getString(cursor.getColumnIndex(G_CONTENTS));
                String title = cursor.getString(cursor.getColumnIndex(G_TITLE));

                GuideListModel guideObject = new GuideListModel(title, contents);
                guidesList.add(guideObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return guidesList;
    }

    public ArrayList<JournalListModel> getAllJournals() {
        ArrayList<JournalListModel> journals = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_JOURNAL;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Long id = cursor.getLong(cursor.getColumnIndex(J_ID));
                String contents = cursor.getString(cursor.getColumnIndex(J_CONTENTS));
                String title = cursor.getString(cursor.getColumnIndex(J_TITLE));
                Long date_created = cursor.getLong(cursor.getColumnIndex(J_DATE_CREATED));
                Long date_modified = cursor.getLong(cursor.getColumnIndex(J_DATE_MODIFIED));

                JournalListModel journalObject = new JournalListModel(id, title, contents, date_created, date_modified);
                journals.add(journalObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return journals;
    }

    public Long addJournal(String title, String content, Long dateCreated) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(J_TITLE, title);
        values.put(J_CONTENTS, content);
        values.put(J_DATE_CREATED, dateCreated);
        values.put(J_DATE_MODIFIED, dateCreated);
        return db.insertWithOnConflict(TABLE_JOURNAL, null, values, CONFLICT_IGNORE);
    }

    public Integer editJournal(Long id, String title, String content, Long dateModified) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(J_TITLE, title);
        values.put(J_CONTENTS, content);
        values.put(J_DATE_MODIFIED, dateModified);
        Integer rowsAffected = db.update(TABLE_JOURNAL,
                values,
                J_ID + " = ? ",
                new String[]{String.valueOf(id)});
        if (rowsAffected == 0) return -1;
        return rowsAffected;
    }

    public Integer deleteJournal(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_JOURNAL,
                J_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    private Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}