package com.example.mentalhelp.Database;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;
import static com.example.mentalhelp.Database.Database.DB_NAME;
import static com.example.mentalhelp.Database.Database.DB_VERSION;
import static com.example.mentalhelp.Database.Database.M_ID;
import static com.example.mentalhelp.Database.Database.M_MUSIC;
import static com.example.mentalhelp.Database.Database.M_TITLE;
import static com.example.mentalhelp.Database.Database.TABLE_MUSIC;
import static com.example.mentalhelp.Database.Database.query2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mentalhelp.Database.Objects.Music;
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
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void populateMusicTable() {
        List<Music> musicList = this.getAllMusic();
        int currentEntries = 5; //update this every time you insert new preset music.
        if (musicList.size() >= currentEntries) return; // if music table has more than or equal to the current entries, stop the function.

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

    public List<Music> getAllMusic() {

        List<Music> musicList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int ingredientID = cursor.getInt(cursor.getColumnIndex(M_ID));
                int music = cursor.getInt(cursor.getColumnIndex(M_MUSIC));
                String musicTitle = cursor.getString(cursor.getColumnIndex(M_TITLE));

                Music musicObject = new Music(ingredientID, music, musicTitle);
                musicList.add(musicObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return musicList;
    }

//    public Long updateFridgeAmount(Double amount, Ingredients ingredients){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        long ingredientID = storeIngredient(ingredients);
//
//        Cursor cursor = query(
//                TABLE_FRIDGE_INGREDIENTS,
//                null,
//                ING_ID+" = ? AND "+AU_ID+" = ? ",
//                new String[]{String.valueOf(ingredientID),
//                        String.valueOf(getUnitID(ingredients.getIngredientUnit()))},
//                null,
//                null,
//                null
//        );
//        if (cursor.moveToFirst()){
//            values.put(QUANTITY, amount);
//            long id = (long) db.update(TABLE_FRIDGE_INGREDIENTS,
//                    values,
//                    ING_ID + " = ? AND " + AU_ID + " = ? ",
//                    new String[]{String.valueOf(ingredientID),
//                            String.valueOf(getUnitID(ingredients.getIngredientUnit()))});
//            if (id != -1) return id;
//        }
//        return -1L;
//    }

    private Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}