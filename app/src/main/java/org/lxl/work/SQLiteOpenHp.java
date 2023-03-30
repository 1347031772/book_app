package org.lxl.work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.lxl.work.entity.Book;
import org.lxl.work.entity.User;


public class SQLiteOpenHp extends SQLiteOpenHelper {
    private static  final String TABNAME = "users";
    private  static final  String TABNAME2="books";
    private  static final  String TABNAME3="sy";

    String dropTab = "DROP TABLE IF EXISTS " + TABNAME + ";";
    String dropTab2 = "DROP TABLE IF EXISTS " + TABNAME2 + ";";
    String dropTab3 = "DROP TABLE IF EXISTS " + TABNAME3 + ";";

    String createTab = "CREATE TABLE IF NOT EXISTS " + TABNAME +"(id integer primary key autoincrement," +
            "name text,password text)";
    String createTab2 = "CREATE TABLE IF NOT EXISTS " + TABNAME2 +"( books_name text ," +
            "books_price text )";


    private String TAG = "tag";
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    /**
     *
     * 上下文
     * db文件名
     *  工厂
     *  数据库版本
     */
    public SQLiteOpenHp(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库 存在则删除，不存在则创建
        db.execSQL(dropTab);
        db.execSQL(createTab);
        db.execSQL(dropTab2);
        db.execSQL(createTab2);
        /*db.execSQL(dropTab3);
        db.execSQL(createTab3);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//更新版本
    }

    public Long insertusers(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("password",user.getPassword());
        return db.insert(TABNAME,null,values);
    }
    public Long insertbooks(Book books) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("books_name",books.getBooks_name());
        values.put("books_price",books.getBooks_price());
        return db.insert(TABNAME2,null,values);
    }
    public int update(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",user.getPassword());
        return db.update(TABNAME,values,"phone= ? and name=?",new String[]{});
    }

    public int delete(User user) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABNAME,"phone= ? and name=?",new String[]{});
    }
    public Cursor querybooks() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABNAME2, null,null,null,null,null,null);
    }

    public Cursor query(String name, String pswd) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABNAME,null,"name = ? and password = ?",new String[]{name,pswd},null,null,null);
    }
    public Cursor queryregister(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABNAME,null,"name = ?",new String[]{name},null,null,null);
    }
    public Cursor querybookname(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABNAME2,null,"books_name = ?",new String[]{name},null,null,null);
    }
    public int deletebooks(String name) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABNAME2,"books_name = ?",new String[]{name});
    }
    public Cursor querybook() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABNAME2, null,null,null,null,null,null);
    }
    //所有的增删改方法，我们写在这里，然后进行调用
}
