package com.example.myapplication;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    EditText et_name,et_mob,et_email,MainActivityConfirmPassword;
    Button bt_submit, bt_set;
    DBManager dbManager;
    ImageButton imgbt,glrybt;
    byte[] imageData;
    private SQLiteDatabase database;
    boolean isAllFieldsChecked = false;
    public static final int REQUEST_IO_MULTIPLE_PERMISSIONS=101;


    private static final int CAMERA_REQUEST=1888;
    ImageView imageView;



    // Button MainActivityChangePassword;
    //  EditText MainActivityEmail,MainActivityPassword,MainActivityConfirmPassword;
    TextView MainActivityEmailerror,MainActivityPasswordError,MainActivityConfirmPassError;
    TextView MainActivityImageerror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imageView=(ImageView) this.findViewById(R.id.img);
        MainActivityImageerror=findViewById(R.id.ImageAlert);


        glrybt=findViewById(R.id.cmr);
        //=findViewById(R.id.glr);
        // Button photoButton=(Button) this.findViewById(R.id.take);



        glrybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAndRequestPermissions(Registration.this))
                {
                    chooseImage(Registration.this);
                }
            }
        });

//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (requestCode==1 && resultCode == RESULT_OK && null !=data)
//            {
//                Uri selectedImg=data.getData();
//                String[] filepath={MediaStore.Images.Media.DATA};
//
//                Cursor cursor=getContentResolver().query(selectedImg,filepath,null,null,null);
//                cursor.moveToFirst();
//                int columneIndex=cursor.getColumnIndex(filepath[0]);
//                String picturepath=cursor.getString(columneIndex);
//                cursor.close();
//
//
//                imageView.setImageBitmap(BitmapFactory.decodeFile(picturepath));
//
//            }
//        }








        dbManager = new DBManager(this);
        dbManager.open();


        bt_submit=findViewById(R.id.submit);

        et_name=findViewById(R.id.name);
        et_email=findViewById(R.id.email);
        et_mob=findViewById(R.id.mobile);


        MainActivityConfirmPassword=findViewById(R.id.MainActivityConfirm);
        //message
//        MainActivityEmailerror=findViewById(R.id.EmailAlert);
//        MainActivityPasswordError=findViewById(R.id.PaswordAlert);
        MainActivityConfirmPassError=findViewById(R.id.ConfirmPassAlert);


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                validateEmail();
//                validatePassword();

                //  validImage();

//                if((CheckAllFields()&&validateEmail()&&validatePassword())==true)
//                {
//
//
//                    Intent i = new Intent(MainActivity.this, login.class);
//                    startActivity(i);
//
//
//                }
                isAllFieldsChecked = CheckAllFields();


                if (isAllFieldsChecked) {
                    Intent i = new Intent(Registration.this, login.class);
                    startActivity(i);

                    String name = et_name.getText().toString();
                    String mob = et_mob.getText().toString();
                    String email = et_email.getText().toString();
                    //   byte image=imageView.();

//
                    String image = imageView.toString();
                    ByteArrayOutputStream s = new ByteArrayOutputStream();
                    byte[] imageAsBytes = s.toByteArray();
                    Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);

                    dbManager.insert(name, mob, email, imageViewToByte(imageView));
                    //   Toast.makeText(reg.this, "Usename is your entered Email & Password is your entered Phone no.", Toast.LENGTH_LONG).show();
                    et_name.setText("");
                    et_email.setText("");
                    et_mob.setText("");
                    imageView.setImageBitmap(bmp);
                }





            }
        });



    }


    private boolean CheckAllFields() {

        ImageView myImage = (ImageView) findViewById(R.id.img);
        String emailInput = et_email.getText().toString().trim();
        String passwordInput = et_mob.getText().toString().trim();
        String ConfitmpasswordInput = MainActivityConfirmPassword.getText().toString().trim();

        if (myImage.getDrawable() == null){

            MainActivityImageerror.setText("Set your Profile Photo");
            return false;
        }
        if (et_name.length() == 0) {
            et_name.setError("Name is required");
            return false;
        }
        if (et_email.length() == 0) {
            et_email.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            et_email.setError("Please enter a valid email address");
            return false;
        }
        if (et_mob.length() == 0) {
            et_mob.setError("Password is required");
            return false;
        }
        if (et_mob.length() < 5) {
            et_mob.setError("Password must be of 5 Characters");
            return false;
        }
        if (!passwordInput.equals(ConfitmpasswordInput)) {
            MainActivityConfirmPassError.setText("Password Would Not be matched");
            return false;

        }
        // after all validation return true.
        return true;
    }



//    private boolean validImage() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.img);
//
//        if (bitmap==null)
//        {
//            MainActivityImageerror.setText("field empty");
//            return false;
//        }
//
//        else
//        {
//            MainActivityImageerror.setError(null);
//            return true;
//        }
//
//    }






//    original

//    private boolean validateEmail() {
//        String emailInput = et_email.getText().toString().trim();
//        if (emailInput.isEmpty()) {
//            MainActivityEmailerror.setText("Field can't be empty");
//            return false;
//
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            MainActivityEmailerror.setText("Please enter a valid email address");
//            return false;
//        } else {
//            MainActivityEmailerror.setError(null);
//            return true;
//        }
//    }
//    private boolean validatePassword() {
//        String passwordInput = et_mob.getText().toString().trim();
//        String ConfitmpasswordInput = MainActivityConfirmPassword.getText().toString().trim();
//        if (passwordInput.isEmpty()) {
//            MainActivityPasswordError.setText("Field can't be empty");
//            return false;
//        }  if (passwordInput.length()<5) {
//            MainActivityPasswordError.setText("Password must be at least 5 characters");
//            return false;
//        }
//        if (!passwordInput.equals(ConfitmpasswordInput)) {
//            MainActivityConfirmPassError.setText("Password Would Not be matched");
//            return false;
//
//        }else {
//            MainActivityConfirmPassError.setText("Password Matched");
//            return true;
//
//        }
//    }



    private void chooseImage(Context context) {
        final CharSequence[] optionMenu = {"Take Photo", "Choose from gallery", "exit"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(optionMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (optionMenu[i].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if (optionMenu[i].equals("Choose from gallery")) {
                    Intent pickphoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickphoto, 1);
                } else if (optionMenu[i].equals("exit")) {
                    dialogInterface.dismiss();
                }
            }
        });

        builder.show();
    }
    public static boolean checkAndRequestPermissions(final Activity context)
    {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission=ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA);


        List<String> listPemissionsNeeded=new ArrayList<>();
        if(cameraPermission!= PackageManager.PERMISSION_DENIED)
        {
            listPemissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if(WExtstorePermission!=PackageManager.PERMISSION_DENIED)
        {
            listPemissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!listPemissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(context,listPemissionsNeeded.toArray(new String[listPemissionsNeeded.size()]),REQUEST_IO_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case REQUEST_IO_MULTIPLE_PERMISSIONS:

                if(ContextCompat.checkSelfPermission(Registration.this, android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Flag up requires access to camera",Toast.LENGTH_SHORT).show();

                }
                else if(ContextCompat.checkSelfPermission(Registration.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Flag up requires access to storage",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    chooseImage(Registration.this);
                }break;

        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_CANCELED)
        {
            switch (requestCode)
            {
                case 0:
                    if(resultCode==RESULT_OK && data!=null)
                    {
                        Bitmap selectedImage=(Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);

                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,stream);
                        imageData=stream.toByteArray();
                    }break;

                case 1:
                    if(resultCode==RESULT_OK && data!=null)
                    {
                        Uri selectedImage=data.getData();
                        String[] filePathColumn={MediaStore.Images.Media.DATA};

                        if(selectedImage!=null)
                        {
                            Cursor cursor=getContentResolver().query(selectedImage,filePathColumn,null,null,null);

                            if(cursor!=null)
                            {
                                cursor.moveToFirst();
                                int columnIndex=cursor.getColumnIndex(filePathColumn[0]);

                                String picturePath=cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                BitmapFactory.decodeFile(picturePath).compress(Bitmap.CompressFormat.JPEG,100,stream);
                                imageData=stream.toByteArray();

                                cursor.close();
                            }
                        }
                    }break;
            }
        }



    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}