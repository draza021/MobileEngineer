
/**
 * Created by Drago on 6/29/2017.
 */

package com.development.id.ns.mobileengineer.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.development.id.ns.mobileengineer.R;
import com.development.id.ns.mobileengineer.adapter.DemoItemAdapter;
import com.development.id.ns.mobileengineer.backend.ApiEndpointInterfaces;
import com.development.id.ns.mobileengineer.backend.RestApi;
import com.development.id.ns.mobileengineer.backend.json.DemoItem;
import com.development.id.ns.mobileengineer.helper.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String ITEM_TITLE = "item_title";
    public static final String ITEM_DESCRIPTION = "item_description";
    public static final String ITEM_IMAGE = "item_image";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        callService();
    }

    private void initViews() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.main_title);
        }
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setLayoutManager(layoutManager);
    }

    private void callService() {
        showProgress(true);
        ApiEndpointInterfaces networkService = RestApi.getRestService();
        Observable<List<DemoItem>> call = networkService.getItems();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DemoItem>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DemoItem> value) {
                        Log.e("", "items OK");
                        showProgress(false);
                        gotItems(value);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "items NOK");
                        showProgress(false);
                        Toast.makeText(getApplicationContext(), R.string.no_data_received, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void gotItems(List<DemoItem> items) {
        if (items == null || items.size() == 0) {
            Toast.makeText(getApplicationContext(), R.string.no_data_received, Toast.LENGTH_LONG).show();
        } else {
            ArrayList<DemoItem> demoItems = new ArrayList<>(items);
            DemoItemAdapter adapter = new DemoItemAdapter(demoItems);
            recyclerView.setAdapter(adapter);
        }
    }

    private void showProgress(boolean showIt) {
        if (showIt) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }
}
