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

        //View Holder Pattern
        ViewHolder holder;
        //copiar la vista
        if(convertView == null){
            //inflar la vista que llego con nuestro layout personalizado

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);

            holder = new ViewHolder();
            //se referencia el elemento a modificar y se rellena
           holder.nameTextView = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        // traer el valor actual dependiente de la posición
        String currentName = names.get(position);

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.nameTextView.setText(currentName);

        //devolvemos la vista inflada y modificada con nuestros datos
        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;
    }
}
