package com.example.yhisl.my_first;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText Name;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private final int PHONE_CALL_CODE = 100;
    // cuando se hace uso de microfonos, o demases, se requiere de que los permisos sean escritos en el manifest

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        Name = (EditText) findViewById(R.id.Name);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);


        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString(); //RECUPERAR CADENA DEL EDITTEXT
                if (phoneNumber != null) {
                    //comprobar versión actual de android que está corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
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

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // este es el caso del telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
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
