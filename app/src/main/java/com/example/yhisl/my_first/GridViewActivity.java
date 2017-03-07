package com.example.yhisl.my_first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView1;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView1 = (GridView) findViewById(R.id.gridView1);

        //se crea la lista con los datos a mostrar
        names = new ArrayList<String>();
        names.add("Klein");
        names.add("Neil");
        names.add("Kelin");
        names.add("nikel");
        names.add("Klein");
        names.add("Neil");
        names.add("Kelin");
        names.add("nikel");
        names.add("Klein");
        names.add("Neil");
        names.add("Kelin");
        names.add("nikel");


        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this, "Clicked:" +names.get(position), Toast.LENGTH_LONG).show();
            }
        });

        MyAdapter myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView1.setAdapter(myAdapter);

    }
}
