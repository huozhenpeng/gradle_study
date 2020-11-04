package com.example.gradle_plugin_study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> data = new ArrayList<>();
    private TextView topView;
    CusAdapter cusAdapter;
    static String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topView = findViewById(R.id.topView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cusAdapter = new CusAdapter(this,data);
        recyclerView.setAdapter(cusAdapter);

        for (int i= 0;i < 20;i++) {
            data.add("第"+i+"个item");
        }
        cusAdapter.notifyDataSetChanged();
        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusAdapter.notifyDataSetChanged();
            }
        });
    }

    static class CusAdapter extends RecyclerView.Adapter<CusViewHolder>{
        private List<String> data;
        private Context context;
        public CusAdapter(Context context,List<String> data) {
            this.context = context;
            this.data = data;
        }
        @NonNull
        @Override
        public CusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new CusViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CusViewHolder holder, final int position) {
            String str = data.get(position);
            holder.item.setText(str);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "item--->"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onViewAttachedToWindow(@NonNull CusViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.e(TAG, "onViewAttachedToWindow--->"+holder.item.getText().toString());
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull CusViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            Log.e(TAG, "onViewDetachedFromWindow--->"+holder.item.getText().toString());
        }
    }

    static class CusViewHolder extends RecyclerView.ViewHolder {
        protected TextView item;
        public CusViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }
}