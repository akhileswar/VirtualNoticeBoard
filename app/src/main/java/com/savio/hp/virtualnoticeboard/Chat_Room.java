package com.savio.hp.virtualnoticeboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




/**
 * Created by filipp on 6/28/2016.
 */
public class Chat_Room  extends AppCompatActivity{

    private Button buttons;
    private EditText edit_text;
    public ListView list;
public String dname;
    FirebaseAuth mAuth;
    Firebase mreff;
    FirebaseAuth.AuthStateListener mAuthstate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Firebase.setAndroidContext(this);
        mreff=new Firebase("https://chatapp-dbb94.firebaseio.com/");
        mAuth=FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
       final String passed = bundle.getString("pass");

           final FirebaseUser user=mAuth.getCurrentUser();
           final String id=user.getUid();
           list=(ListView)findViewById(R.id.listt);
           edit_text= (EditText) findViewById(R.id.editText);
           buttons=(Button)findViewById(R.id.button);





        final ArrayList<String> ar=new ArrayList<String>();
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,ar);

        if((passed.equals("technical"))||(passed.equals("cultural"))||(passed.equals("sports"))||(passed.equals("trendels"))) {
            //
            mreff.child("chat").child("events").child("clubs").child(passed).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String mail = ds.child("email").getValue(String.class);
                        String mes = ds.child("message").getValue(String.class);
                        String dates = ds.child("date").getValue(String.class);
                        ar.add("~" + mail + "\n " + "  " + mes + "\n" +"\n"+ "                          " + dates);

                    }
                    // Collections.reverse(ar);

                    list.setAdapter(adapter);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String msg = edit_text.getText().toString();
if(!TextUtils.isEmpty(msg)) {
    DateFormat anots = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss");
    final String date1 = anots.format(Calendar.getInstance().getTime());


    DateFormat anot = new SimpleDateFormat("d/MMM/yyyy,HH:mm");
    final String date2 = anot.format(Calendar.getInstance().getTime());

    mreff.child("chat").child("events").child("clubs").child(passed).child(date1).child("email").setValue(user.getEmail());
    mreff.child("chat").child("events").child("clubs").child(passed).child(date1).child("date").setValue(date2);
    mreff.child("chat").child("events").child("clubs").child(passed).child(date1).child("message").setValue(msg);

    ar.add("~" + user.getEmail() + "\n " + "  " + msg + "\n" + "\n" + "                          " + date2);
    Toast.makeText(Chat_Room.this, "message sent", Toast.LENGTH_SHORT).show();
    edit_text.setText(null);
}else
    Toast.makeText(Chat_Room.this, "Text some message", Toast.LENGTH_SHORT).show();
                }
            });


        }else if((passed.equals("internships"))||(passed.equals("academics"))||(passed.equals("others"))) {

//
            mreff.child("chat").child("events").child(passed).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String mail = ds.child("email").getValue(String.class);
                        String mes = ds.child("message").getValue(String.class);
                        String dates = ds.child("date").getValue(String.class);
                        ar.add("~" + mail + "\n " + "  " + mes + "\n" +"\n"+  "                          "+ dates);

                    }
                    // Collections.reverse(ar);

                    list.setAdapter(adapter);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String msg = edit_text.getText().toString();
if(!TextUtils.isEmpty(msg)) {
    DateFormat anots = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss");
    final String date1 = anots.format(Calendar.getInstance().getTime());


    DateFormat anot = new SimpleDateFormat("d/MMM/yyyy,HH:mm");
    final String date2 = anot.format(Calendar.getInstance().getTime());

    mreff.child("chat").child("events").child(passed).child(date1).child("email").setValue(user.getEmail());
    mreff.child("chat").child("events").child(passed).child(date1).child("date").setValue(date2);
    mreff.child("chat").child("events").child(passed).child(date1).child("message").setValue(msg);

    ar.add("~" + user.getEmail() + "\n " + "  " + msg + "\n" + "\n" + "                          " + date2);
    Toast.makeText(Chat_Room.this, "message sent", Toast.LENGTH_SHORT).show();
    edit_text.setText(null);
}else
    Toast.makeText(Chat_Room.this, "Text some message", Toast.LENGTH_SHORT).show();

                }
            });




//
        }else{
//
            if (user != null) {
                for (UserInfo profile : user.getProviderData()) {
                    // Name, email address, and profile photo Url
                    dname = profile.getDisplayName();

                };

            }

            //
            //
            mreff.child("chat").child("complaints").child(passed).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String mail = ds.child("email").getValue(String.class);
                        String mes = ds.child("message").getValue(String.class);
                        String dates = ds.child("date").getValue(String.class);
                        ar.add("~" + mail + "\n " + "  " + mes + "\n" +"\n"+ "                          "+ dates);

                    }
                    // Collections.reverse(ar);

                    list.setAdapter(adapter);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String msg = edit_text.getText().toString();
if(!TextUtils.isEmpty(msg)) {
    DateFormat anots = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss");
    final String date1 = anots.format(Calendar.getInstance().getTime());


    DateFormat anot = new SimpleDateFormat("d/MMM/yyyy,HH:mm");
    final String date2 = anot.format(Calendar.getInstance().getTime());

    mreff.child("chat").child("complaints").child(passed).child(date1).child("email").setValue(dname);
    mreff.child("chat").child("complaints").child(passed).child(date1).child("date").setValue(date2);
    mreff.child("chat").child("complaints").child(passed).child(date1).child("message").setValue(msg);

    ar.add("~" + dname + "\n " + "  " + msg + "\n" + "\n" + "                          " + date2);
    Toast.makeText(Chat_Room.this, "message sent", Toast.LENGTH_SHORT).show();
    edit_text.setText(null);
}else
    Toast.makeText(Chat_Room.this, "Text some message", Toast.LENGTH_SHORT).show();

                }
            });



            //

        }

    }

}
