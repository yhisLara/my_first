package com.example.yhisl.my_first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //copiar la vista

        View v = convertView;

        //inflar la vista que llego con nuestro layout personalizado

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item, null);

        //traer el valor actual dependiente de la posición
        String currentName = names.get(position);

        //se referencia el elemento a modificar y se rellena
        TextView textView = (TextView) v.findViewById(R.id.textView1);
        textView.setText(currentName);

        //devolvemos la vista inflada y modificada con nuestros datos
        return v;
    }
}
