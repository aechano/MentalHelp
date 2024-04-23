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
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.mentalhelp.Database.Objects.Music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public List<Music> getAllMusic() {

        List<Music> musicList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int ingredientID = cursor.getInt(cursor.getColumnIndex(M_ID));
                String music = cursor.getString(cursor.getColumnIndex(M_MUSIC));
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

    private Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}