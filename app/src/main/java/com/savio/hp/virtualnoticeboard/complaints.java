package com.savio.hp.virtualnoticeboard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class complaints extends Activity {
public Button com,academics,administration,mess,hostel,logout;
    public TextView show_gmail;

    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_complaint);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();

        com=(Button)findViewById(R.id.button10);
        show_gmail=(TextView)findViewById(R.id.gmail_view);
        academics=(Button)findViewById(R.id.button1);
        administration=(Button)findViewById(R.id.button2);
        mess=(Button)findViewById(R.id.button3);
        logout=(Button)findViewById(R.id.button111);
        hostel=(Button)findViewById(R.id.button4);

        final FirebaseUser user=mAuth.getCurrentUser();
        String users_email=user.getEmail();
        show_gmail.setText(users_email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(complaints.this,login.class));
                finish();
            }

        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(complaints.this,events.class));
            }
        });

        //
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(complaints.this, Chat_Room.class);
                intent.putExtra("pass", "academics_complaints");
                startActivity(intent);
            }
        });

        administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(complaints.this, Chat_Room.class);
                intent.putExtra("pass", "administration_complaints");
                startActivity(intent);
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(complaints.this, Chat_Room.class);
                intent.putExtra("pass", "mess");
                startActivity(intent);
            }
        });

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(complaints.this, Chat_Room.class);
                intent.putExtra("pass", "hostel");
                startActivity(intent);
            }
        });
//


    }
                     }
