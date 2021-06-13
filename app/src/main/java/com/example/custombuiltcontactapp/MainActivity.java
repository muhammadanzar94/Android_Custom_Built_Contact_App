package com.example.custombuiltcontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding on click listener for add contact button to go to that page
        final Button addbtn = (Button) findViewById(R.id.addContactbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TO move to addContact activity
                Intent intent=new Intent(getApplicationContext(),addContact.class);
                startActivity(intent);
            }
        });

        //adding on click listener for show contact button to go to that page
        final Button showContactsBtn = (Button) findViewById(R.id.showContactsbtn);
        showContactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TO move to showContact activity
                Intent intent1=new Intent(getApplicationContext(),contactsList.class);
                startActivity(intent1);
            }
        });


    }


}

