package com.example.jay.loginapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.loginapp.Models.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * Created by jay on 09/01/18.
 */

public class ChatActivity extends AppCompatActivity {
    private LinearLayout layout;
    private RelativeLayout layout_2;
    private ImageView sendButton;
    private EditText messageArea;
    private ScrollView scrollView;
    private String OtherUserID;
    private String CurrentUserID;
    private String Date;
    private String conversationID;
    private String ConversationContactName;
    private static final String TAG = "ChatActivity";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference reference1,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout) findViewById(R.id.layout2);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();



        FirebaseUser user = mAuth.getCurrentUser();
        OtherUserID = getIntent().getExtras().getString("otheruid");

        CurrentUserID = user.getUid();

         conversationID = generateConverstaionID(CurrentUserID,OtherUserID);
        reference1 = mFirebaseDatabase.getReference().child("messages").child(conversationID);
        reference2 = mFirebaseDatabase.getReference().child("Profiles").child(OtherUserID);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ConversationContactName = dataSnapshot.child("name").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Toast.makeText(getApplicationContext(),"Current User :"+CurrentUserID +",   other user :"+OtherUserID,Toast.LENGTH_SHORT).show();
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date = sdf.format(c.getTime());

                    Message m1 = new Message(CurrentUserID,OtherUserID,messageText,Date);
                    reference1.push().setValue(m1);

                    messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Iterable<DataSnapshot> Texts = dataSnapshot.child("messages").child(conversationID).getChildren();

                Log.d(TAG, "oCA: 66666 "
                        + dataSnapshot.child("messages").child(conversationID).getClass());

                Message m = dataSnapshot.getValue(Message.class);

                String message = m.getMessagecontent();
                String userName = m.getSender();


                if(userName.equals(CurrentUserID)){
                    addMessageBox("You:-\n" + message, 1);
                }
                else{
                    addMessageBox(ConversationContactName + ":-\n" + message, 2);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError firebasedatabaseError) {

            }
        });
    }

    private void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        }
        else{
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }


    // Deterministically generate conversation id
    private String generateConverstaionID(String UserID1, String UserID2){

        // Sort User ID Strings in order
        List<String> list = new ArrayList();
        list.add(CurrentUserID);
        list.add(OtherUserID);
        Collections.sort(list);

        return list.get(0) + list.get(1);
    }
}
