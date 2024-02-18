package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class login extends AppCompatActivity {

    EditText et_user, et_pass;
    Button bt_login;
    TextView tvlogin;
    DBManager dbManager;


    //  EditText eemail, epass;
    CheckBox showpassword;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbManager = new DBManager(this);
        dbManager.open();


//        et_user=findViewById(R.id.username);
//        et_pass=findViewById(R.id.password);
//        bt_login=findViewById(R.id.bt_log);
//        tvlogin=findViewById(R.id.tvlog);


        et_user=findViewById(R.id.username);
        et_pass=findViewById(R.id.password);

        showpassword = findViewById(R.id.showpass);
        imageView = findViewById(R.id.imageview);


        bt_login=findViewById(R.id.bt_log);
        tvlogin=findViewById(R.id.tvlog);


        et_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                Glide.with(getApplicationContext())
                        .asGif()
                        .load(R.drawable.g50)
                        .into(imageView);
            }
        });

        et_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                Glide.with(getApplicationContext())
                        .asGif()
                        .load(R.drawable.g56)
                        .into(imageView);
            }
        });
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpassword.isChecked())
                {
                    Glide.with(getApplicationContext())
                            .asGif()
                            .load(R.drawable.g55)
                            .into(imageView);
                    et_pass.setTransformationMethod(null);
                }
                else
                {
                    Glide.with(getApplicationContext())
                            .asGif()
                            .load(R.drawable.g56)
                            .into(imageView);
                    et_pass.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();

                Boolean checkuserpass = dbManager.checkusernamepassword(user, pass);

                if(et_user.length()==0)
                {
                    et_user.setError("Enter Email");
                }
                else if(et_pass.length()==0)
                {
                    et_pass.setError("Enter Password");
                }
                else {
                    if (checkuserpass == true) {

                        Intent i1 = new Intent(login.this, MainActivity.class);

                        //   i1.putExtra("Email",et_user.getText().toString());

                        i1.putExtra("Email",et_user.getText().toString());

                        startActivity(i1);

                        Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(login.this, "Wrong Email or password", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,Registration.class);
                startActivity(i);
            }
        });
    }

}
