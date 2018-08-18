package com.example.administrator.testjson;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jit.lib.SmartImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31 0031.
 */

public class imageadapter extends RecyclerView.Adapter<imageadapter.ViewHolder> {
    private int w;
    private int h;
    private int scale;
    private List<jsonresult> imageresult;

    private Context context = myApplication.getContext();

    static class ViewHolder extends RecyclerView.ViewHolder {
        View imageview;
        SmartImageView smartImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageview = itemView;
            smartImageView = (SmartImageView) itemView.findViewById(R.id.image);
        }
    }

    public imageadapter(List<jsonresult> iamge, int w, int h, int scale) {
        imageresult = iamge;
        this.w = w;
        this.h = h;
        this.scale = scale;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forviewholder, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(viewHolder.imageview, "scaleX", 1f, 1.05f).setDuration(500).start();
                ObjectAnimator.ofFloat(viewHolder.imageview, "scaleY", 1f, 1.05f).setDuration(500).start();
                ObjectAnimator.ofFloat(viewHolder.imageview, "scaleX", 1.05f, 1f).setDuration(500).start();
                ObjectAnimator.ofFloat(viewHolder.imageview, "scaleY", 1.05f, 1f).setDuration(500).start();
                int position = viewHolder.getAdapterPosition();
                jsonresult jsonresult2 = imageresult.get(position);
                String url = jsonresult2.getUrl();
                Intent intent = new Intent("android.intent.action.activity2");
                intent.putExtra("URL", url);
                context.startActivity(intent);
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        jsonresult jsonresult1 = imageresult.get(position);
        holder.smartImageView.setImageUrl(jsonresult1.getUrl(), w, h, scale);
    }

    @Override
    public int getItemCount() {
        return imageresult.size();
    }

}
