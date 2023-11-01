package com.example.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    TextView detaildes,detailTitle,detailLang;
    String imageUrl="";
    String key="";
    FloatingActionButton editButton;
    ImageView detailImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detaildes= (TextView) findViewById(R .id.detaildesc);
        detailImage= (ImageView) findViewById(R.id.detailImage);
        detailTitle= (TextView) findViewById(R.id.detailTitle);
        editButton= (FloatingActionButton) findViewById(R.id.editButton );
        detailLang= (TextView) findViewById(R.id.detailLang);
        Bundle bundle=getIntent().getExtras();

        if(bundle !=null)
        {
            detaildes.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("Language"));
            key = bundle.getString("key");
            imageUrl=bundle.getString("image");
            Glide.with(this).load(bundle.getString("image")).into(detailImage);


        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                   .putExtra("Title", detailTitle.getText().toString())
                   .putExtra("Description", detaildes.getText().toString())
                   .putExtra("Language", detailLang.getText().toString())
                        .putExtra("image", imageUrl)
                        .putExtra("key",key);
                startActivity(intent);

            }
        });
    }
}