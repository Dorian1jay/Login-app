package com.example.jay.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.loginapp.Models.Contact;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jay on 27/12/17.
 */

public class ContactActivity extends Activity {

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    public String data;


    List<Contact> Contacts = new ArrayList<Contact>();

    ListView contactListView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactview);
        contactListView = findViewById(R.id.clistview);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> userProfile = dataSnapshot.child("Profiles").getChildren();
                for (DataSnapshot uid : userProfile) {

                    String userID = uid.getKey();
                    String name =uid.child("name").getValue().toString();
                    String location = uid.child("Location").getValue().toString();
                    String instrument = uid.child("Instruments").getValue().toString();
                    addContacts(userID,name,location,instrument);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact current = Contacts.get(position);
                toastMessage(current.getName());
                Intent viewother = new Intent(ContactActivity.this,ViewOtherProfileActivity.class);
                viewother.putExtra("uid",current.getUID());
                startActivity(viewother);

            }
        });

        populateList();




    }

    private void addContacts(String UID, String name, String location, String instrument)
    {

        Contacts.add(new Contact(UID,name,location,instrument));
    }
    private void populateList()
    {
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
    }



    private class ContactListAdapter extends ArrayAdapter<Contact>{
        public ContactListAdapter ()
        {
            super(ContactActivity.this,R.layout.listview_item , Contacts);
        }
        @Override
        public View getView(int position , View view , ViewGroup parent)
        {

            if (view == null)
            {
                view = getLayoutInflater().inflate(R.layout.listview_item,parent, false);



            }

            Contact currentcontact = Contacts.get(position);

            TextView name = view.findViewById(R.id.Nametxt);
            name.setText("Name : "+ currentcontact.getName());


            TextView location = view.findViewById(R.id.Locationtxt);
            location.setText("Location : "+currentcontact.getLocation());

            TextView Instrument = view.findViewById(R.id.Instrumenttxt);
            Instrument.setText("Instrument : "+currentcontact.getInstrument());

            TextView UID = view.findViewById(R.id.UIDtxt);
            UID.setText("UserID : "+currentcontact.getUID());

            return view;

        }


    }




    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
