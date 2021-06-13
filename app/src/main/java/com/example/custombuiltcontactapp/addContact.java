/*Add Contact Class in which you can input your Name,Number
and SELECT your image from the gallery
and save your contact in the list*/
package com.example.custombuiltcontactapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class addContact extends AppCompatActivity {

    private static final String TAG = "contactsList";
    private static int RESULT_LOAD_IMAGE = 1;

    // PICK_PHOTO_CODE is a constant integer
    public final static int PICK_PHOTO_CODE = 1046;

    EditText nameET;
    EditText numberET;
    ImageView userIV;
    ImageButton imageButton;
    boolean isSaved=false;
    //for saving the selected image URI
    String imageURI="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);

        nameET=(EditText) findViewById(R.id.name);
        numberET=(EditText)findViewById(R.id.number);
        userIV=(ImageView) findViewById(R.id.image);


        //image_button for image input

        imageButton= (ImageButton)findViewById(R.id.image);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT to Select an image from Gallery
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //CHECKing if the intent is not NULL
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Bring up gallery to select a photo
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                    }

                    //make the image button invisible
                    imageButton.setEnabled(false);
                    imageButton.setVisibility(View.INVISIBLE);
                    ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) imageButton.getLayoutParams();
                    margin.setMargins(0, 0, 0, 0);
                    imageButton.requestLayout();
            }

        });

        //adding on click listener for add contact button to go to that page
        final Button addbtn = (Button) findViewById(R.id.saveBtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // on clicking save button
                // make isSaved true so that data will be sent to contact list
                Log.d(TAG, "onClick: in here 3");
                Toast.makeText(getApplicationContext(),"Contact Saved",Toast.LENGTH_SHORT).show();
                isSaved=true;
            }
        });

        //adding on click listener for show contact button to go to that page
        final Button showContactsBtn = (Button) findViewById(R.id.viewContactBtn);
        showContactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d(TAG, "onClick: value of isSaved"+isSaved);
                if(isSaved){
                    String name = nameET.getText().toString().trim();
                    String number = numberET.getText().toString().trim();


                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("number", number);
                    if(imageURI!="")
                    bundle.putString("imageURI", imageURI);
                    Intent intent = new Intent(getApplicationContext(), contactsList.class);
                    intent.putExtras(bundle);
                    Log.d(TAG, "onClick: After save btn ");
                    isSaved=false;
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(getApplicationContext(), contactsList.class);
                    Log.d(TAG, "onClick: before save btn ");
                    startActivity(intent);
                }
            }
        });
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

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if ((data != null) && requestCode == PICK_PHOTO_CODE) {
                Uri photoUri = data.getData();

                Log.d(TAG, "onActivityResult: photoURI:"+photoUri.toString());
                // Load the image located at photoUri into selectedImage
                Bitmap selectedImage = loadFromUri(photoUri);
                //saving the image URI
                imageURI=photoUri.toString();

                // Display the loaded image
                ImageView imageView = (ImageView) findViewById(R.id.Userimage);
                imageView.setImageBitmap(selectedImage);
            }
        }



    }


