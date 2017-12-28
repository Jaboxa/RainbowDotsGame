package jessi.rainbowdots2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String TABLE_SCORES = "scores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    public DataBaseHandler(Context context) {
        super(context, "scoreboard.db", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE2= "create table scores ( _id integer primary key autoincrement, name text not null, score text not null);";
        db.execSQL(DATABASE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ");
        onCreate(db);
    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void addScore(User user){
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.COLUMN_NAME, user.getName());
        values.put(DataBaseHandler.COLUMN_SCORE,user.getScore());
        long insertID = db.insert(DataBaseHandler.TABLE_SCORES, null, values);
    }
    public List<User> getAllUsers(){

        List<User> bookList = new ArrayList<User>();

        String[] allColumns ={COLUMN_ID, COLUMN_NAME, COLUMN_SCORE};
        Cursor cursor = db.query(DataBaseHandler.TABLE_SCORES, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = cursorToUser(cursor);
            bookList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return bookList;
    }

    private User cursorToUser (Cursor cursor) {
        User user = new User();
        user.setDatabaseId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setScore(cursor.getLong(2));
        return user;
    }

    public void defaultlist (){
        addScore(new User("Jessika",20));
        addScore(new User("Malcolm",20));
    }

}
