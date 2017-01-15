package com.example.yhisl.my_first;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static java.util.logging.Level.parse;

public class FourtyActivity extends AppCompatActivity {

    private EditText editTextMail;
    private TextView editTextContact;
    private ImageButton buttonMail;
    private ImageButton buttonContact;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourty);

        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextContact = (EditText) findViewById(R.id.editTextContact);
        buttonContact = (ImageButton) findViewById(R.id.imageButtonContact);
        buttonMail = (ImageButton) findViewById(R.id.imageButtonMail);

        //boton para agregar nuevo contacto
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = editTextContact.getText().toString();
                if (contact != null && !contact.isEmpty()) {
                    //Intent intentContactView = new Intent(Intent.ACTION_VIEW),Uri.parse("content://contacts/people");
                    Intent intentContact = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact));
                    startActivity(intentContact);
                }
            }
        });

        //boton para abrir mail
        buttonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextMail.getText().toString();
                if (email != null && email.isEmpty()) {
                    //mail rapido
                    Intent intentMailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" +email));
                    //mail completo
                    Intent intentMail = new Intent(Intent.ACTION_VIEW, Uri.parse(email));
                    //intentMail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    intentMail.setType("plain/text");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail's title");
                    intentMail.putExtra(Intent.EXTRA_TEXT, "Hi there, I love");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"yhis.laraf@gmail.com", "yhis.laraf@outlook.com"});
                    startActivity(Intent.createChooser(intentMail,"Elige cliente de correo"));
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Fourty Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
