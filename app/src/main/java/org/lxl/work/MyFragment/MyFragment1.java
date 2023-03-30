package org.lxl.work.MyFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.lxl.work.Adapter.myBook;
import org.lxl.work.R;
import org.lxl.work.SQLiteOpenHp;
import org.lxl.work.entity.Book;
import java.util.ArrayList;
import java.util.List;


public class MyFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bookmanage, null);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //循环获取每个列表中的值 获取之后存到 list中
        SQLiteOpenHp sqLiteOpenHp = new SQLiteOpenHp(getActivity());
        List<Book> booksList = new ArrayList<>();
        Cursor books = sqLiteOpenHp.querybook();
        if (books != null) {
            //遍历得到每个值
            while (books.moveToNext()) {
                String name = books.getString(0);
                String price = books.getString(1);
                Book books1 = new Book(name, price);
                booksList.add(books1);
            }
        }
        List<Book> defaultList =booksList;
        //拿到我们重写后的适配器
        myBook booksAdapter = new myBook(getActivity(), defaultList);
        // 对列表进行设置 使用 基本适配器
        ListView lv_01 = getActivity().findViewById(R.id.lv_01);
        //设置相关适配器内容
        lv_01.setAdapter(booksAdapter);
        lv_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //打印一句话
            }
        });
    }

}