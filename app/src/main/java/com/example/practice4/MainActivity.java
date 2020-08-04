package com.example.practice4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity implements RecycleAdapter.ItemClickListener {

    MyBroadCast myBroadCast = new MyBroadCast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgProfile = findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout Dr = (DrawerLayout) findViewById(R.id.activity_main);
                Dr.openDrawer(Gravity.RIGHT);
            }

        });

//        Button btnVideoView =findViewById(R.id.btnVideoView);
//        btnVideoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentVideoviewAct = new Intent(MainActivity.this,VideoViewActivity.class);
//                startActivity(intentVideoviewAct);
//            }
//        });
//        Button btnWebView =findViewById(R.id.btnWebView);
//        btnWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentWebviewAct = new Intent(MainActivity.this,WebviewActivity.class);
//                startActivity(intentWebviewAct);
//            }
//        });
//        Button btnCall =findViewById(R.id.btnCall);
//        btnCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentCallAct = new Intent(MainActivity.this,CallActivity.class);
//                startActivity(intentCallAct);
//              /*  String phone = "+34666777888";
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
//                startActivity(intent);*/
//
//            }
//        });
        List<String> names = new ArrayList<>();
        names.add("پخش فیلم");
        names.add("سایت آپارات");
        names.add("تماس");
        names.add("سرویس");
        names.add("Custom BroadCast");

        RecyclerView recycler = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);
        RecycleAdapter adapter = new RecycleAdapter(this, names);
        adapter.setClickListener(this);
        recycler.setAdapter(adapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(), new LinearLayoutManager(this).getOrientation());
        recycler.addItemDecoration(dividerItemDecoration);


        //*********************************************************************
        try {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://pouyaheydari.com/vehicles.json", null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                try {

                    JsonTest dto_empty = new JsonTest();
                RecyclerView recyclerViewJson = findViewById(R.id.my_recyclerJson_view);
                recyclerViewJson.setHasFixedSize(true);
                //recyclerViewJson.LayoutManager layoutManager = new LinearLayoutManager(this);
                //recyclerViewJson.setLayoutManager(layoutManager);
                recyclerViewJson.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                MyRecyclerViewJsonAdapter adapterJson = new MyRecyclerViewJsonAdapter(MainActivity.this, dto_empty.getVehicles());
                recyclerViewJson.setAdapter(adapterJson);
                //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewJson.getContext(), new LinearLayoutManager(this).getOrientation());
                //recyclerViewJson.addItemDecoration(dividerItemDecoration);
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this,
                            ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {


                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    JsonTest dto_jsonTest = gson.fromJson(responseString, JsonTest.class);

                    RecyclerView recyclerViewJson = findViewById(R.id.my_recyclerJson_view);
                    recyclerViewJson.setHasFixedSize(true);
                    //recyclerViewJson.LayoutManager layoutManager = new LinearLayoutManager(this);
                    //recyclerViewJson.setLayoutManager(layoutManager);
                    recyclerViewJson.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    MyRecyclerViewJsonAdapter adapterJson = new MyRecyclerViewJsonAdapter(MainActivity.this, dto_jsonTest.getVehicles());
                    recyclerViewJson.setAdapter(adapterJson);
                    //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewJson.getContext(), new LinearLayoutManager(this).getOrientation());
                    //recyclerViewJson.addItemDecoration(dividerItemDecoration);
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this,
                            ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    } catch (Exception ex) {
        Toast.makeText(MainActivity.this,
                ex.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
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
                Intent intentprofileAct = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentprofileAct);
                break;
           /* case R.id.webviewAct:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


    //------------------------
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this, WebviewActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this, CallActivity.class);
                startActivity(intent);
                break;
            case 3:
                Intent intentService = new Intent(MainActivity.this, ForgroundService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intentService);
                }
                break;

            case 4:
                IntentFilter filter = new IntentFilter();
                filter.addAction("سلام گلای تو خونه");
                registerReceiver(myBroadCast, filter);

                intent = new Intent();
                intent.setAction("سلام گلای تو خونه");
                sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myBroadCast);
        super.onPause();
    }

}