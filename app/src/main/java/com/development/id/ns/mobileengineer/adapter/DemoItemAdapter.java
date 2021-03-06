
/**
 * Created by Drago on 6/29/2017.
 */

package com.development.id.ns.mobileengineer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.development.id.ns.mobileengineer.R;
import com.development.id.ns.mobileengineer.activity.DemoItemDetailsActivity;
import com.development.id.ns.mobileengineer.activity.MainActivity;
import com.development.id.ns.mobileengineer.backend.json.DemoItem;

import java.util.ArrayList;

public class DemoItemAdapter extends RecyclerView.Adapter<DemoItemAdapter.ViewHolder> {
    private ArrayList<DemoItem> demoItems;

    public DemoItemAdapter(ArrayList<DemoItem> demoItems) {
        this.demoItems = demoItems;
    }

    @Override
    public DemoItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(demoItems.get(i).getTitle());
        viewHolder.tvDescription.setText(demoItems.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return demoItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitle, tvDescription;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvDescription = (TextView)view.findViewById(R.id.tv_description);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Context context = v.getContext();
            Intent intent = new Intent(context, DemoItemDetailsActivity.class);
            intent.putExtra(MainActivity.ITEM_TITLE,demoItems.get(position).getTitle());
            intent.putExtra(MainActivity.ITEM_DESCRIPTION,demoItems.get(position).getDescription());
            intent.putExtra(MainActivity.ITEM_IMAGE,demoItems.get(position).getImageUrl());
            context.startActivity(intent);
        }
    }
}
