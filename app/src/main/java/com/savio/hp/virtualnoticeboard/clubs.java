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

public class clubs extends Activity {
public Button home,tec,cul,spo,trend,logout;
    public TextView show_gmail;
    public FirebaseAuth mAuth;
    public Firebase.AuthStateListener mAuthstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clubs);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
       // mref=new Firebase("https://chatapp-dbb94.firebaseio.com/");

        home=(Button)findViewById(R.id.button10);
show_gmail=(TextView)findViewById(R.id.gmail_view) ;
        tec=(Button)findViewById(R.id.button1);
        cul=(Button)findViewById(R.id.button2);
        spo=(Button)findViewById(R.id.button3);
        logout=(Button)findViewById(R.id.button111);
        trend=(Button)findViewById(R.id.button4);

        final FirebaseUser user=mAuth.getCurrentUser();
        String users_email=user.getEmail();
        show_gmail.setText(users_email);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(clubs.this,login.class));
                finish();
            }

        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clubs.this,events.class));
            }
        });
        //

        tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clubs.this, Chat_Room.class);
                intent.putExtra("pass", "technical");
                startActivity(intent);
            }
        });

        cul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clubs.this, Chat_Room.class);
                intent.putExtra("pass", "cultural");
                startActivity(intent);
            }
        });

        spo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clubs.this, Chat_Room.class);
                intent.putExtra("pass", "sports");
                startActivity(intent);
            }
        });

        trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clubs.this, Chat_Room.class);
                intent.putExtra("pass", "trendels");
                startActivity(intent);
            }
        });

        //
    }//oncreate
}//end
