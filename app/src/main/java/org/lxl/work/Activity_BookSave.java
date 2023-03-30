package org.lxl.work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.lxl.work.entity.Book;
import org.lxl.work.main.BNB;

public class Activity_BookSave extends AppCompatActivity {
    SQLiteOpenHp sqLiteOpenHp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        sqLiteOpenHp = new SQLiteOpenHp(this);
    }
    public void addback(View view){
        Intent intent = new Intent(this, BNB.class);
        startActivity(intent);
    }
    public void bookadd(View view) {
        //获取图书名字和价格
        EditText bookname = findViewById(R.id.edt_bookname);
        EditText bookprice = findViewById(R.id.edt_bookprice);
        //判断是存在相同名字的书
        String bkname = bookname.getText().toString().trim();
        Cursor cursor = sqLiteOpenHp.querybookname(bkname);
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "已存在相同名字的书", Toast.LENGTH_LONG).show();
        } else {
            Book book = new Book(bookname.getText().toString().trim(), bookprice.getText().toString().trim());
            long i = sqLiteOpenHp.insertbooks(book);
            if (i > 0) {
                Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(this, BNB.class);
            startActivity(intent);
            this.finish();
        }
    }
}