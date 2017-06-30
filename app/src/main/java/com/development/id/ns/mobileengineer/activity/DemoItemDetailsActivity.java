package com.development.id.ns.mobileengineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.development.id.ns.mobileengineer.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Drago on 6/29/2017.
 */

public class DemoItemDetailsActivity extends AppCompatActivity {
    private ImageView ivItemImage;
    private TextView tvTitle;
    private TextView tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_item_details);

        initViews();
        Intent intent = getIntent();
        if (intent != null) {
            tvTitle.setText(intent.getStringExtra(MainActivity.ITEM_TITLE));
            tvDescription.setText(intent.getStringExtra(MainActivity.ITEM_DESCRIPTION));
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(intent.getStringExtra(MainActivity.ITEM_TITLE));
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            Picasso.with(getApplicationContext())
                    .load(intent.getStringExtra(MainActivity.ITEM_IMAGE))
                    .placeholder(R.drawable.placeholder_image)
                    .into(ivItemImage);
        }

    }

    private void initViews() {
        ivItemImage = (ImageView) findViewById(R.id.iv_details_item_image);
        tvTitle = (TextView) findViewById(R.id.tv_details_title);
        tvDescription = (TextView) findViewById(R.id.tv_details_description);
    }
}
