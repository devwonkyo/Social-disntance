package com.gardeng.socialproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PhaseActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<Item> itemList = new ArrayList<>();
    TextView textView;
    AdView mAdView;
    @Override
    protected void onResume() {
        super.onResume();
        textView = findViewById(R.id.text);
        String step = getIntent().getStringExtra("step");
        textView.setText("'"+step +"단계'  지침이 궁금한 항목을 선택해주세요.");

        FirebaseDatabase.getInstance().getReference().child(step).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    Item items = item.getValue(Item.class);
                    itemList.add(items);
                    Log.d("gg", String.valueOf(item.getValue()));
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase);
        recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter(itemList);
        recyclerView.setAdapter(customAdapter);

        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-5329828677760196/8529628493");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<Item> adapterItemList;


        public CustomAdapter(List<Item> adapterItemList) {
            this.adapterItemList = adapterItemList;
        }

        public class CumtomViewHolder extends RecyclerView.ViewHolder{
            ImageView itemImage;
            TextView itemName;
            public CumtomViewHolder(@NonNull View itemView) {
                super(itemView);
                itemImage = itemView.findViewById(R.id.image);
                itemName = itemView.findViewById(R.id.name);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
            return new CumtomViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ((CumtomViewHolder)holder).itemImage.setImageResource(adapterItemList.get(position).image);
            //((CumtomViewHolder)holder).itemImage.setImageResource(R.drawable.icon_hair);
            //((CumtomViewHolder)holder).itemImage.setImageResource(R.drawable.icon_arcade);

            ((CumtomViewHolder)holder).itemName.setText(adapterItemList.get(position).name);

            ((CumtomViewHolder)holder).itemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                    intent.putExtra("name",adapterItemList.get(position).getName());
                    intent.putExtra("content",adapterItemList.get(position).getContent());
                    intent.putExtra("image",adapterItemList.get(position).getImage());
                    startActivity(intent);
                }
            });
            ((CumtomViewHolder)holder).itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                    intent.putExtra("name",adapterItemList.get(position).getName());
                    intent.putExtra("content",adapterItemList.get(position).getContent());
                    intent.putExtra("image",adapterItemList.get(position).getImage());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return adapterItemList.size();
        }
    }
}