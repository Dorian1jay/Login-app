package com.example.jay.loginapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

/**
 * Created by jay on 10/01/18.
 */

public class MyContactsActivity extends Activity {

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
                Log.d(TAG, "onDataChange:  inside 1 "+dataSnapshot.toString());
                // dataSnapshot.toString();

                //DataSnapshot UIDSnapshot = dataSnapshot;
                Iterable<DataSnapshot> userProfile = dataSnapshot.child("Profiles").getChildren();
                for (DataSnapshot uid : userProfile) {
                    // String s = uid.getValue(String.class);
                    String userID = uid.getKey();
                    Log.d("uid","uid key is "+uid.getKey());
                    //String name = myRef.child("Profiles").child(userID).child("name").toString();
                    String name =uid.child("name").getValue().toString();
                    String location = uid.child("Location").getValue().toString();
                    String instrument = uid.child("Instruments").getValue().toString();
                    addContacts(userID,name,location,instrument);

                    //myRef.child("Profiles").child(userID).child("Instruments").setValue(instrument);
                    // myRef.child("Profiles").child(userID).child("Styles").setValue(Styles);

                    // uid.
                    Log.d(TAG, "onDataChange: test111111111111111111111111111111111111"+name+location+instrument);
                    //DataSnapshot uName = dataSnapshot.child(s);


                    // Log.d("uid_name_test",uName.child("name").getValue().toString() );

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        mAuth = FirebaseAuth.getInstance();
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String data;


//
//        addContacts("7XyRzKiNkJafyXi4pzYwpRjBgTl2","Jo","instrument22","vrvrvrv22");
//        addContacts("7jcadzO0pbVqWhGJUzpRpzSdyML2","James","In the ra","Guitar");
//        addContacts("xqOGoCnfc9RmSIdILj9DwIq05vA3","Homer","In the ra","Guitar");

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact current = Contacts.get(position);
                toastMessage(current.getName());
//                Intent viewother = new Intent(ContactActivity.this,ViewOtherProfileActivity.class);
//                viewother.putExtra("uid",current.getUID());
//                startActivity(viewother);

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
            super(MyContactsActivity.this,R.layout.listview_item , Contacts);
        }
        @Override
        public View getView(int position , View view , ViewGroup parent)
        {
            Log.d(TAG, "getView:  inside get view");
            if (view == null)
            {
                view = getLayoutInflater().inflate(R.layout.listview_item,parent, false);
                Log.d(TAG, "getView: inside");


            }

            Contact currentcontact = Contacts.get(position);


            TextView name = view.findViewById(R.id.Nametxt);
            name.setText(currentcontact.getName());
            Log.d(TAG, "getView: "+currentcontact.getName());

            TextView location = view.findViewById(R.id.Locationtxt);
            location.setText(currentcontact.getLocation());

            TextView Instrument = view.findViewById(R.id.Instrumenttxt);
            Instrument.setText(currentcontact.getInstrument());

            TextView UID = view.findViewById(R.id.UIDtxt);
            UID.setText(currentcontact.getUID());

            return view;

        }


    }




    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
