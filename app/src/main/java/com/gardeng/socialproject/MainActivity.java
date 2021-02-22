package com.gardeng.socialproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kakao.sdk.common.util.Utility;

public class MainActivity extends AppCompatActivity {
    Button phase1;
    Button phase1_5;
    Button phase2;
    Button phase2_5;
    Button phase3;
    TextView textView_currentStep;
    AdView mAdView;

    

    @Override
    protected void onResume() {
        super.onResume();
        textView_currentStep = findViewById(R.id.step);
        FirebaseDatabase.getInstance().getReference().child("CurrentStep").child("-MOFBUsZXKGjwqn9wCr9").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String currentStep = dataSnapshot.getValue(String.class);
                switch (currentStep){
                    case "1":
                        textView_currentStep.setTextColor(Color.parseColor("#538A2E"));
                        break;
                    case "1.5":
                        textView_currentStep.setTextColor(Color.parseColor("#538A2E"));
                        break;
                    case "2":
                        textView_currentStep.setTextColor(Color.parseColor("#CB7462"));
                        break;
                    default:
                        break;
                }
                textView_currentStep.setText(" '"+currentStep+"단계' ");
                //Log.d("step",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//pc,스터디,헬스,결혼식,학원,마트,실내공연
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  byte[] sha1 = {
                (byte)0xA7, (byte)0xC2, (byte)0xBE, (byte)0xD1, (byte)0xC4, 0x68, (byte)0xD0, 0x7D, (byte)0xC1, 0x53, 0x5D, (byte)0xE4, (byte)0xC2, (byte)0x82, 0x18, 0x1F, (byte)0xD2, 0x05, (byte)0xC2, (byte)0xB4
        };
        Log.d("keyHash:" , Base64.encodeToString(sha1, Base64.NO_WRAP));*/


        /*String keyhash = Utility.INSTANCE.getKeyHash(this);
        Log.d("tag1",keyhash);*/

        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-5329828677760196~5530277670");
        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111"); //test
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("main","success");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d("main","fail");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

/*        *//*Log.d("draw","유흥시설"+String.valueOf(R.drawable.icon_dance));
        Log.d("draw","코노"+String.valueOf(R.drawable.ic_sing));
        Log.d("draw","식당"+String.valueOf(R.drawable.icon_food));
        Log.d("draw","카페"+String.valueOf(R.drawable.icon_cafe));*//*
        Log.d("draw","pc방"+String.valueOf(R.drawable.icon_pcroom));
//        Log.d("draw","종교시설"+String.valueOf(R.drawable.icon_cross));
        Log.d("draw","스터디카페"+String.valueOf(R.drawable.icon_studycafe));
        Log.d("draw","헬스장"+String.valueOf(R.drawable.icon_health));
        *//*Log.d("draw","목욕탕"+String.valueOf(R.drawable.icon_bath));
        Log.d("draw","영화관"+String.valueOf(R.drawable.icon_cnema));
        Log.d("draw","오락실"+String.valueOf(R.drawable.icon_arcade));
        Log.d("draw","놀이공원"+String.valueOf(R.drawable.icon_amusement));
        Log.d("draw","미용실"+String.valueOf(R.drawable.icon_hair));*//*
        Log.d("draw","결혼식장"+String.valueOf(R.drawable.icon_wedding));
//        Log.d("draw","장례식장"+String.valueOf(R.drawable.icon_funeral));
        Log.d("draw","학원"+String.valueOf(R.drawable.icon_studyschool));
        Log.d("draw","마트"+String.valueOf(R.drawable.icon_scale));
        Log.d("draw","실내공연장"+String.valueOf(R.drawable.icon_show));*/




        //String currentStep  = FirebaseDatabase.getInstance().getReference().push().getKey();

        //#70614E
        /*List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.icon_dance,"유흥시설","1"));//클럽,룸살롱,유흥.감성.단란주점,콜라텍,헌팅포차
        itemList.add(new Item(R.drawable.ic_sing,"노래연습장","1"));
        itemList.add(new Item(R.drawable.icon_food,"식당","1"));
        itemList.add(new Item(R.drawable.icon_cafe,"카페","1"));
        itemList.add(new Item(R.drawable.icon_pcroom,"PC방","1"));
        itemList.add(new Item(R.drawable.icon_cross,"종교시설","1"));
        itemList.add(new Item(R.drawable.icon_studycafe,"스터디카페/독서실","1"));
        itemList.add(new Item(R.drawable.icon_health,"헬스장/실내체육시설","1"));
        itemList.add(new Item(R.drawable.icon_bath,"목욕탕","1"));
        itemList.add(new Item(R.drawable.icon_cnema,"영화관","1"));
        itemList.add(new Item(R.drawable.icon_arcade,"오락실/멀티방","1"));
        itemList.add(new Item(R.drawable.icon_amusement,"놀이공원/워터파크","1"));
        itemList.add(new Item(R.drawable.icon_haircut,"미용실/이발소","1"));
        itemList.add(new Item(R.drawable.icon_wedding,"결혼식장","1"));
        itemList.add(new Item(R.drawable.icon_funeral,"장례식장","1"));
        itemList.add(new Item(R.drawable.icon_studyschool,"학원/교습소","1"));
        itemList.add(new Item(R.drawable.icon_mart,"마트/백화점","1"));
        itemList.add(new Item(R.drawable.icon_show,"실내 스탠딩공연장","1"));
        itemList.add(new Item(R.drawable.icon_scale,"방문판매/직접판매홍보관","1"));


        for(int i = 0; i <18 ; i++){
            FirebaseDatabase.getInstance().getReference().child("3").push().setValue(itemList.get(i));
        }*/


        phase1 = findViewById(R.id.phase1);
        phase1_5 = findViewById(R.id.phase1_5);
        phase2 = findViewById(R.id.phase2);
        phase2_5 = findViewById(R.id.phase2_5);
        phase3 = findViewById(R.id.phase3);



        phase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhaseActivity.class);
                intent.putExtra("step","1");
                startActivity(intent);
            }
        });

        phase1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhaseActivity.class);
                intent.putExtra("step","1_5");
                startActivity(intent);

            }
        });


        phase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhaseActivity.class);
                intent.putExtra("step","2");
                startActivity(intent);
            }
        });


        phase2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhaseActivity.class);
                intent.putExtra("step","2_5");
                startActivity(intent);
            }
        });

        phase3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhaseActivity.class);
                intent.putExtra("step","3");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle("앱을 종료하시겠습니까?");
        builder.setMessage(" 종료하시기 전에,\n 소중한 리뷰를 남겨주세요:)");
        builder.setPositiveButton("종료",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"앱 종료를 취소합니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNeutralButton("리뷰 쓰러가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
                marketLaunch.setData(Uri.parse("market://details?id=com.gardeng.socialproject"));
                startActivity(marketLaunch);
            }
        });
        builder.show();
    }


}