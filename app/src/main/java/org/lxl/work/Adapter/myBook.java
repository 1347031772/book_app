package org.lxl.work.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.lxl.work.R;
import org.lxl.work.SQLiteOpenHp;
import org.lxl.work.entity.Book;

import java.util.List;


public class myBook extends BaseAdapter {
    private Context mContext; // 声明一个上下文对象
    private List<Book> booksList;

    public myBook(Context mContext, List<Book> booksList) {
        this.mContext = mContext;
        this.booksList = booksList;
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int i) {
        return booksList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public View getView(int i, View view , ViewGroup viewGroup) {
        ViewHolder holder;
        // 判断视图为空
        if (view == null) {
            holder = new ViewHolder(); // 创建一个新的视图持有者
            // 根据布局文件item_list.xml生成转换视图对象
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_desc = view.findViewById(R.id.tv_price);
            holder.btn=view.findViewById(R.id.btn);
            view.setTag(holder); // 将视图持有者保存到转换视图当中
        } else { // 转换视图非空
        //从转换视图中获取之前保存的视图持有者
           holder = (ViewHolder) view.getTag();
        }
        Book books = booksList.get(i);
        holder.tv_name.setText("名称："+books.getBooks_name());
        holder.tv_desc.setText("价格："+books.getBooks_price()+"元");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHp sqLiteOpenHp = new SQLiteOpenHp(mContext);
                int i = sqLiteOpenHp.deletebooks(books.getBooks_name());
                if (i!=0){
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
    // 定义一个视图持有者，以便重用列表项的视图资源
    public final class ViewHolder {
        public TextView tv_name; // 声明名称的文本视图对象
        public TextView tv_desc;
        public Button btn;
    }

}
