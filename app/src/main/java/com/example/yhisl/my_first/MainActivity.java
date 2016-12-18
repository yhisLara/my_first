package com.example.yhisl.my_first;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.buttonMain);
        //casting para acceder a propiedades de un boton
        //ya que findViewById devuelve una vista y btn pide boton

        btn.setOnClickListener(this); //this, porque se le pasa la vista que se está implementando en la clase
        //está forma sive solo para cuando hay un solo boton
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this , "Button Cliked, use implements" , Toast.LENGTH_LONG).show();
    }
}
