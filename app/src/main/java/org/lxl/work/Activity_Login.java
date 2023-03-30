package org.lxl.work;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.lxl.work.MyFragment.MyFragment3;
import org.lxl.work.main.BNB;

public class Activity_Login extends Activity {
    SQLiteOpenHp sqLiteOpenHp;
    private EditText ed_user, ed_pass;
    private CheckBox cd_remember;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        sqLiteOpenHp = new SQLiteOpenHp(this);

      //判断是否记住密码
        SharedPreferences preferences = getSharedPreferences("remember_account", MODE_PRIVATE);
            String user1 = preferences.getString("name", "");
            String pswd1 = preferences.getString("pswd", "");
            ed_user.setText(user1);
            ed_pass.setText(pswd1);
            cd_remember.setChecked(true);
    }
    public void initData(){
         ed_user = findViewById(R.id.et_user);
         ed_pass = findViewById(R.id.et_pass);
         cd_remember = findViewById(R.id.cd_remember);
    }
    public void login(View view) {
        sqLiteOpenHp = new SQLiteOpenHp(this);
        //获取输入的用户名和密码
        String name = ed_user.getText().toString().trim();
        String pswd = ed_pass.getText().toString().trim();
        //判断是否为空
        if (name.equals("") || pswd.equals("")) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            //判断是否存在
            Cursor cursor = sqLiteOpenHp.query(name, pswd);
            if (cursor.getCount() > 0) {
                //判断是否记住密码
                if (cd_remember.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("remember_account", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", name);
                    editor.putString("pswd", pswd);
                    editor.putBoolean("checkboxButton", true);
                    editor.commit();
                    finish();
                }
                else {
                    SharedPreferences preferences = getSharedPreferences("remember_account", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("checkboxButton", false);
                    edit.commit();
                }
                Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
                //跳转到主页面
                Intent intent = new Intent(this, BNB.class);
                startActivity(intent);
                finish();
            } else {
                //不存在
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void toRegister(View view){
        Intent intent = new Intent(this,Activity_Register.class);
        startActivity(intent);
    }
}