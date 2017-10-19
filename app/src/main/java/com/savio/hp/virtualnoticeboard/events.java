package com.savio.hp.virtualnoticeboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class events extends Activity {

    public Button events,clubs,interships,some,others,logout;
    public TextView show_gmail;

    public FirebaseAuth mAuth;
    public Firebase mref;
    ProgressDialog p;
    public Firebase.AuthStateListener mAuthstate;
public String dname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_events);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        mref=new Firebase("https://chatapp-dbb94.firebaseio.com/");

        events=(Button)findViewById(R.id.button11);
        show_gmail=(TextView)findViewById(R.id.gmail_view);
        clubs=(Button)findViewById(R.id.button1);
        interships=(Button)findViewById(R.id.button2);
        some=(Button)findViewById(R.id.button3);
        others=(Button)findViewById(R.id.button4);
        logout=(Button)findViewById(R.id.button111);
int i=0;

        final FirebaseUser user=mAuth.getCurrentUser();
        String users_email=user.getEmail();
        show_gmail.setText(users_email);


        String display_name=user.getDisplayName();
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(events.this,complaints.class));
            }
        });

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(events.this, clubs.class);
               // intent.putExtra("pass", "technical");
                startActivity(intent);
            }


        });

        interships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(events.this, Chat_Room.class);
                intent.putExtra("pass", "internships");
                startActivity(intent);
            }

        });

        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(events.this, Chat_Room.class);
                intent.putExtra("pass", "academics");
                startActivity(intent);
            }

        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(events.this, Chat_Room.class);
                intent.putExtra("pass", "others");
                startActivity(intent);
            }

        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(events.this,login.class));
                finish();
            }

        });
        //

        //



    }//on create
@Override
    protected void onStart(){
    super.onStart();

}
}//end
