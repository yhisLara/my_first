package com.example.yhisl.my_first;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText Name;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private Button buttonCamera;
    private final int PHONE_CALL_CODE = 100;
    // cuando se hace uso de microfonos, o demases, se requiere de que los permisos sean escritos en el manifest

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //flecha atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        Name = (EditText) findViewById(R.id.Name);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        buttonCamera = (Button) findViewById(R.id.buttonCamera);

        //boton para llamada
        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString(); //RECUPERAR CADENA DEL EDITTEXT
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    //comprobar versión actual de android que está corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(CheckPermission(Manifest.permission.CALL_PHONE)){
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                            if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                startActivity(i);
                            }
                        }
                        else {
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            } else {
                                //Ha Denegado
                                Toast.makeText(ThirdActivity.this, "Please, enable the request permission", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
                else{
                    Toast.makeText(ThirdActivity.this, "Please enter a phone number", Toast.LENGTH_LONG).show();
                }
            }

            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                //uriparse, tipo de referencia que se le pasa, como una url, para continuar con el action
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You don't have permission", Toast.LENGTH_LONG).show();
                }
            }
        });

        //boton para navegacion web

        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Name.getText().toString(); //recupera la url
                if(url != null && !url.isEmpty()){
                    Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    startActivity(intentweb);
                }
                else{

                }
            }
        });

        //Para pasar a la siguiente actividad

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(ThirdActivity.this, FourtyActivity.class);
                startActivity(intentNext);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // este es el caso del telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0]; // se puede hacer un for en caso de que se quieran verificar mas permisos
                int result = grantResults[0]; //resultado del usuario, si fue aceptado o no por el usuario

                if (permission.equals(Manifest.permission.CALL_PHONE)) { // se comprueba que el permiso que llegó sea el mismo que está en el manifest
                    //comprobar si el permiso fue aceptado o denegado por el usuario
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Usuario Aceptó
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    }
                    else{
                        //no Aceptó
                        Toast.makeText(ThirdActivity.this, "You don't have permission", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }
    }

    private boolean CheckPermission(String Permission){
        int result = this.checkCallingOrSelfPermission(Permission); //chequea si el permiso está permitido o denegado
        return result == PackageManager.PERMISSION_GRANTED; //granted = 1 , denegado = 0
    }
}
