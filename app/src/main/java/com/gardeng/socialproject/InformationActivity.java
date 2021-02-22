package com.gardeng.socialproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.link.LinkClient;
import com.kakao.sdk.link.model.LinkResult;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.Link;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class InformationActivity extends AppCompatActivity {
    TextView textView_contents;
    TextView textView_name;
    ImageView imageView_informationIcon;
    ImageView imageView_list;
    ImageView imageView_share;
    String name;
    int image;
    String content;
    AdView mAdView;
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        textView_contents = findViewById(R.id.information_contents);
        textView_name = findViewById(R.id.information_name);
        imageView_informationIcon = findViewById(R.id.information_icon);
        imageView_list = findViewById(R.id.list);
        imageView_share= findViewById(R.id.share);

        name = getIntent().getStringExtra("name");
        image = getIntent().getIntExtra("image",1);
        content = getIntent().getStringExtra("content");


        KakaoSdk.init(this, "cc10fc76a107c1d6842d6efffee4af0f");//초기화

        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-5329828677760196/8529628493");
        mAdView = findViewByI(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        textView_name.setText(name);
        imageView_informationIcon.setImageResource(image);
        //textView_contents.setText("클럽 룸살롱 등 유흥,단란,감성주점\n콜라텍, 헌팅포차가 포함되며\n시설 면적 4m제곱당\n1명 인원제한\n기본 방역수칙 의무\n\n*기본 방역수칙이란?\n마스크 착용, 출입자 명단관리,\n환기/소독 수칙 의무화");
        textView_contents.setText(content);

        imageView_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final FeedTemplate feedTemplate = new FeedTemplate(new Content("거리두기 지침"+ " ("+name+")","https://ifh.cc/g/UOy5bo.jpg"
                ,new Link("market://details?id=com.gardeng.socialproject","market://details?id=com.gardeng.socialproject"),content));


        imageView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkClient.getInstance().defaultTemplate(getApplicationContext(), feedTemplate, new Function2<LinkResult, Throwable, Unit>() {
                    @Override
                    public Unit invoke(LinkResult linkResult, Throwable throwable) {
                        if(linkResult!=null){
                            startActivity(linkResult.getIntent());
                            //Log.d("error",linkResult.toString());
                        }else if(throwable!=null){
                            Log.d("error",throwable.toString());
                        }
                        return null;
                    }
                });
            }
        });

    }
}