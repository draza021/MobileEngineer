package com.development.id.ns.mobileengineer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.development.id.ns.mobileengineer.R;
import com.development.id.ns.mobileengineer.adapter.DemoItemAdapter;
import com.development.id.ns.mobileengineer.backend.ApiEndpointInterfaces;
import com.development.id.ns.mobileengineer.backend.RestApi;
import com.development.id.ns.mobileengineer.backend.json.DemoItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<DemoItem> demoItems;
    private DemoItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        callService();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void callService() {
        ApiEndpointInterfaces networkService = RestApi.getRestService();
        networkService.getItems();

        Call<List<DemoItem>> call = networkService.getItems();
        call.enqueue(new Callback<List<DemoItem>>() {
            @Override
            public void onResponse(Call<List<DemoItem>> call, Response<List<DemoItem>> response) {
                Log.e("", "items ok");
                demoItems = new ArrayList<>(response.body());
                adapter = new DemoItemAdapter(demoItems);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DemoItem>> call, Throwable t) {
                Log.e("", "items NOK");
            }
        });
    }

}
