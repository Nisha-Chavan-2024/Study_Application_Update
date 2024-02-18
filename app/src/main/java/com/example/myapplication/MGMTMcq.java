package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class MGMTMcq extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    Button b1,b2,b3,b4,b5,b6;
    ImageView cr1;
    DBManager dbManager;
    DatabaseHelper dbHelper;
    Button bt;
    private static SQLiteDatabase database;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgmtmcq);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        fix();
        dbManager=new DBManager(this);
        dbManager.open();
        dbManager.fetch();
        dbHelper=new DatabaseHelper(MGMTMcq.this);
        database=dbHelper.getWritableDatabase();

        cr1=(ImageView) findViewById(R.id.cr1);
        tv1=findViewById(R.id.tv1);

        //bt=findViewById(R.id.bt);

        String email=getIntent().getStringExtra("Email");
        viewImage(email);

        b1=findViewById(R.id.bt_1);
        b2=findViewById(R.id.bt_2);
        b3=findViewById(R.id.bt_3);
        b4=findViewById(R.id.bt_4);
        b5=findViewById(R.id.bt_5);
        b6=findViewById(R.id.bt_6);


        //String[] pdfFiles={"Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5"};
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://drive.google.com/file/d/1fBKNZUs8R6ismZIx5NHDnHr6v47EzLRk/view?usp=sharing/MAN_CH_1.pdf";
                File file = new File("/sdcard/example.pdf");

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MGMTMcq.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));
            }


        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "https://drive.google.com/file/d/1r8aDLu1sEb3NMSsf4U9znKkDw4RAlbwX/view?usp=sharing/MAN_CH_2.pdf";
                File file = new File("/sdcard/example.pdf");
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MGMTMcq.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "https://drive.google.com/file/d/1mm5Y6SYsCYZ3csCr1RbrAIWkCGb7jfgs/view?usp=sharing/MAN_CH_3.pdf";
                File file = new File("/sdcard/example.pdf");
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MGMTMcq.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://drive.google.com/file/d/1XYMukkGZ1cN_CcchoOXvZMoc4mmr2QzV/view?usp=sharing/MAN_CH_4.pdf";
                File file = new File("/sdcard/example.pdf");
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MGMTMcq.this, "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL

                )));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "https://drive.google.com/file/d/1BUcOClV30ePPYIDV9JOQvBpeVt2ItaDl/view?usp=sharing/MAN_CH_5.pdf";
                File file = new File("/sdcard/example.pdf");
                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MGMTMcq.this, "No Application Available to View PDF",
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