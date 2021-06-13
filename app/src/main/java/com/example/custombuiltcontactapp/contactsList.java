/*This Function initializes the recyclerView with contactList
* which contains all the contacts
* */
package com.example.custombuiltcontactapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class contactsList extends AppCompatActivity implements addtoList {

    static List<contact> contactList=new ArrayList<>();
    private static final String TAG = "contactsList";
    contactsListAdapter ListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcontacts);

        //load some default dummy contacts
        if(contactList.size()==0){
            load_default_contacts();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle!= null) {
            String name = bundle.getString("name");
            String number = bundle.getString("number");
            String imageURI = bundle.getString("imageURI");
            if(imageURI!=""){
                Uri myURI = Uri.parse(bundle.getString("imageURI"));
                Bitmap selectedImage = loadFromUri(myURI);
                contactList.add(new contact(name,number,selectedImage));

                Log.d(TAG, "onCreate: contact Added1");
            }
            else{
            Bitmap image2= BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.elon_musk);
            contactList.add(new contact(name,number,image2));
            Log.d(TAG, "onCreate: contact Added");
        }
        }

        //adding on click listener for add contact button to go to that page
        final Button backBtn = (Button) findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TO move to addContact activity
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        Log.d(TAG, "onCreate: i am here1");
             RecyclerView crview = (RecyclerView) findViewById(R.id.rcview);
             crview.setLayoutManager(new LinearLayoutManager(this));
             ListAdapter=new contactsListAdapter(contactList);
             crview.setAdapter(ListAdapter);
        Log.d(TAG, "onCreate: i am here2");
            }
//

    @Override
            public void addtoContactList(String name, String number, Bitmap image) {

        Log.d(TAG, "addtoContactList: in here 41");
        contactList.add(new contact(name,number,image));
        Log.d(TAG, "addtoContactList: in here 42");
        ListAdapter.notifyDataSetChanged();
//
    }

    //utility functions for image transfer
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;

        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    void load_default_contacts(){
        Bitmap image1= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.bill_gates);
        contactList.add(new contact("Bill Gates","032123013123",image1));
        Bitmap image2= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.elon_musk);
        contactList.add(new contact("Elon Musk","12343456678",image2));
        Bitmap image3= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.mark_zuckerberg);
        contactList.add(new contact("Mark ZuckerBerg","23455635746572",image3));
        Bitmap image4= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.khabib);
        contactList.add(new contact("Khabib","7869075653334",image4));
        Bitmap image5= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.messi);
        contactList.add(new contact("Messi","23433413412134",image5));
        Bitmap image6= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.mo_salah);
        contactList.add(new contact("Mo Salah","+123413412134",image6));

    }

    //function for loading a URI to bitmap of an image
    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if(Build.VERSION.SDK_INT > 27){
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}




