package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.animation.TypeConverter;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    public boolean FlageEdit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText age = findViewById(R.id.age);
        FlageEdit=false;


        if (getage() != 0) {
            int a = getage();
            age.setText(String.valueOf(a));
        }
        if (getName() != "No Name provided!") {
            name.setText(getName());
        }
        if (getEmail() != "No Email provided!") {
            email.setText(getEmail());
        }
        final Button btnedit = findViewById(R.id.btnEdit);
        final Button btnSave = findViewById(R.id.btnSave);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (FlageEdit==false)
               {
                   EditText name = findViewById(R.id.name);
                   EditText email = findViewById(R.id.email);
                   EditText age = findViewById(R.id.age);
                   name.setEnabled(true);
                   email.setEnabled(true);
                   age.setEnabled(true);

                   name.setText(null);
                   email.setText(null);
                   age.setText(null);
                  // btnedit.setEnabled(false);

                  // btnSave.setEnabled(true);
                   btnedit.setBackgroundColor(Color.GREEN);
                   btnSave.setBackgroundColor(Color.GRAY);
                   FlageEdit=true;
               }
               else if (FlageEdit==true)
               {
                   Toast.makeText(ProfileActivity.this, "لطفا اطلاعات خود را وارد کرده >دخیره< را تاچ کنید.", Toast.LENGTH_LONG).show();
               }


            }
        });

        if (getEmail() != "No Email provided!") {
            // btnSave.setEnabled(false);
            if  (FlageEdit==false)
            {
                Toast.makeText(ProfileActivity.this, "اطلاعات شما قبلا ذخیره شده است.", Toast.LENGTH_LONG).show();
                name.setEnabled(false);
                email.setEnabled(false);
                age.setEnabled(false);
               // btnedit.setEnabled(true);
                //btnSave.setEnabled(false);
                btnedit.setBackgroundColor(Color.GRAY);
                btnSave.setBackgroundColor(Color.GREEN);

            }

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getEmail() != "No Email provided!") {
                    // btnSave.setEnabled(false);
                    if  (FlageEdit==false)
                    {
                        Toast.makeText(ProfileActivity.this, "اطلاعات شما قبلا ذخیره شده است.", Toast.LENGTH_LONG).show();
                        return;
                    }


                }
                EditText name = findViewById(R.id.name);
                EditText email = findViewById(R.id.email);
                EditText age = findViewById(R.id.age);

                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "نام و نام خانوادگی خود را وارد کنید", Toast.LENGTH_LONG).show();
                } else {
                    saveName(name.getText().toString());
                }
                if (age.getText().toString().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "سن خود را وارد کنید", Toast.LENGTH_LONG).show();
                } else {
                    saveAge(Integer.parseInt(age.getText().toString()));
                }

                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "ایمیل خود را وارد کنید", Toast.LENGTH_LONG).show();
                } else {
                    saveEmail(email.getText().toString());
                    String names = getName();
                    showNotification("اطلاعات شما با موفقیت ذخیره شد.", names + " اطلاعات شما با موفقیت در نرم افزار ما با موفقیت ذخیره شد. ");
                    name.setEnabled(false);
                    email.setEnabled(false);
                    age.setEnabled(false);

                   // btnedit.setEnabled(true);
                    //btnSave.setEnabled(false);
                    btnSave.setBackgroundColor(Color.GREEN);
                    btnedit.setBackgroundColor(Color.GRAY);
                    FlageEdit=true;

                }

            }
        });
    }

    void saveName(String name) {
        getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .edit()
                .putString("name", name)
                .apply();
    }

    void saveAge(int age) {
        getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .edit()
                .putInt("age", age)
                .apply();
    }

    void saveEmail(String Email) {
        getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .edit()
                .putString("Email", Email)
                .apply();
    }

    String getName() {
        String name = getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .getString("name", "No Name provided!");
        return name;
    }

    int getage() {
        int age = getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .getInt("age", 0);
        return age;
    }

    String getEmail() {
        String Email = getSharedPreferences("prefsnew2", Context.MODE_PRIVATE)
                .getString("Email", "No Email provided!");
        return Email;
    }

    void showNotification(String title, String message) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ProfileActivity.this, "YOUR_CHANNEL_ID")
                .setSmallIcon(R.drawable.aa) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}