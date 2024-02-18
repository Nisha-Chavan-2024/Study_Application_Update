package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;

public class ElementsMaterial extends AppCompatActivity {
    ImageView cr1;
    DBManager dbManager;
    DatabaseHelper dbHelper;
    Button bt;
    private static SQLiteDatabase database;
    TextView tv1;
    private CardView card1,card2,card3,card4,card5,card6;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_material);
        toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        card1 = (CardView) findViewById(R.id.notes);
        card2 = (CardView) findViewById(R.id.videos);
        card3 = (CardView) findViewById(R.id.manual);
        card4 = (CardView) findViewById(R.id.sample);

        fix();
        dbManager=new DBManager(this);
        dbManager.open();
        dbManager.fetch();
        dbHelper=new DatabaseHelper(ElementsMaterial.this);
        database=dbHelper.getWritableDatabase();

        cr1=(ImageView) findViewById(R.id.cr1);
        tv1=findViewById(R.id.tv1);

        //bt=findViewById(R.id.bt);

        String email=getIntent().getStringExtra("Email");
        viewImage(email);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ElementsMaterial.this,ElementsNotes.class);
                i.putExtra("Email",email);
                startActivity(i);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ElementsMaterial.this,ElementsVideo.class);
                i.putExtra("Email",email);
                startActivity(i);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://drive.google.com/file/d/13u93w8Qw4PxcH9ue_-RXPlmTQuG1nqpe/view?usp=sharing/EEC_MANUAL.pdf";
                File file = new File("/sdcard/example.pdf");

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(ElementsMaterial.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://drive.google.com/file/d/1FagvKps6Tc6mwN4MW04gzQKhDxIzJNxe/view?usp=sharing/EEC_SAMPLE.pdf";
                File file = new File("/sdcard/example.pdf");

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(ElementsMaterial.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));

            }
        });
    }
    public void viewImage(String s)
    {
        Cursor c=database.rawQuery("select * from regi where email=\'"+s+"\'",null);
        if(c.moveToNext())
        {
            String id=String.valueOf(c.getInt(0));
            String name=c.getString(1);
            String mobile=c.getString(2);
            String email=c.getString(3);

            byte[] image=c.getBlob(4);

            Bitmap bmp= BitmapFactory.decodeByteArray(image,0,image.length);

            tv1.setText(name);

            bmp=Bitmap.createScaledBitmap(bmp,100,100,true);

            cr1.setImageBitmap(bmp);

          //  Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();

        }

    }

    public static void fix()
    {
        try
        {
            Field field= CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null,102400 * 1024);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}