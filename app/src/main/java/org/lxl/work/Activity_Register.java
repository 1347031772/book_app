package org.lxl.work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.lxl.work.entity.User;

public class Activity_Register extends AppCompatActivity {
    SQLiteOpenHp sqLiteOpenHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqLiteOpenHp = new SQLiteOpenHp(this);
    }

    public void save(View view) {
        EditText ed_user = findViewById(R.id.et_user);
        EditText ed_pass = findViewById(R.id.et_pass);
        EditText ed_pass2 = findViewById(R.id.et_pass2);
        String username = ed_user.getText().toString().trim();
        Cursor cursor = sqLiteOpenHp.queryregister(username);
        if (cursor.getCount() > 0) {
            //存在
            Toast.makeText(this, "该用户名已经被注册！", Toast.LENGTH_LONG).show();
        } else {
            if (ed_pass.getText().toString().equals(ed_pass2.getText().toString())) {
                //执行新增
                //将值存放到实体类对象中，做为整体发送给DAO层
                User user = new User(ed_user.getText().toString().trim(), ed_pass.getText().toString().trim());
                Long i = sqLiteOpenHp.insertusers(user);
                //就是说执行之后有返回值
                if (i > 0) {
                    //新增成功
                    SharedPreferences preferences = getSharedPreferences("my", MODE_PRIVATE);
                    //如果要里面写 还需要借助一个类 Edit
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("name", ed_user.getText().toString());
                    edit.putString("pass", ed_pass.getText().toString());
                    edit.commit();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Activity_Login.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    //新增成功
                    Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "两次密码不一样，请重新输入", Toast.LENGTH_LONG).show();
            }

        }
    }
}

