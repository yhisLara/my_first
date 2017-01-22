package com.example.yhisl.my_first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FifthActivity extends AppCompatActivity {

    private ListView listViewOne;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        listViewOne = (ListView) findViewById(R.id.listViewOne);

        //se crea la lista con los datos a mostrar
        names = new ArrayList<String>();
        names.add("Klein");
        names.add("Neil");
        names.add("Kelin");
        names.add("nikel");


        //Adaptador, la forma visual de mostrar los datos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        //enlazar adaptador con el listView
        listViewOne.setAdapter(adapter);

        listViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FifthActivity.this, "Clicked:" +names.get(position), Toast.LENGTH_LONG).show();
            }
        });

        //enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        listViewOne.setAdapter(myAdapter);

    }
}

