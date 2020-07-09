package com.example.practice4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgProfile =findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout Dr = (DrawerLayout)findViewById(R.id.activity_main);
                Dr.openDrawer(Gravity.RIGHT);
            }

        });

        Button btnVideoView =findViewById(R.id.btnVideoView);
        btnVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVideoviewAct = new Intent(MainActivity.this,VideoViewActivity.class);
                startActivity(intentVideoviewAct);
            }
        });
        Button btnWebView =findViewById(R.id.btnWebView);
        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWebviewAct = new Intent(MainActivity.this,WebviewActivity.class);
                startActivity(intentWebviewAct);
            }
        });
        Button btnCall =findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCallAct = new Intent(MainActivity.this,CallActivity.class);
                startActivity(intentCallAct);
              /*  String phone = "+34666777888";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);*/

            }
        });

    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.hammenu, menu);
            return super.onCreateOptionsMenu(menu);
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent intentprofileAct = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intentprofileAct);
                break;
           /* case R.id.webviewAct:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

}