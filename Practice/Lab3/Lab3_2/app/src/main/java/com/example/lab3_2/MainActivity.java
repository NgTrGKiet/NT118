package com.example.lab3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;

    private void initUi() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact( "Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        ContactAdapter adapter = new ContactAdapter(MainActivity.this, android.R.layout.simple_list_item_1, contacts);
        lvContact.setAdapter(adapter);

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
                databaseHandler.deleteContact(contacts.get(pos));
                contacts.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}