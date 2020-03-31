package com.syrous.yccestudentlibraryjava.ui.other_features;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.more_features.Activity_more_features;
import com.syrous.yccestudentlibraryjava.ui.upload.ActivityUpload;

import java.util.List;

public class OFAdapter extends RecyclerView.Adapter<OFViewHolder> {

    private List<ModelOF> otherFeatures;
    private Context context;

    public OFAdapter(Context context, List OtherFeatures) {
        this.context= context;
        this.otherFeatures = OtherFeatures;
    }

    @NonNull
    @Override
    public OFViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofeaturerow,
                parent, false);
        return new OFViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OFViewHolder holder, int position) {
        holder.setImageView(otherFeatures.get(position).getFeatureLogo());
        holder.setTextView(otherFeatures.get(position).getFeatureName());

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(-65536);
        builder.setShowTitle(true);
        CustomTabsIntent customTabsIntent = builder.build();
        holder.itemView.setOnClickListener((View) -> {
            if(position == 0){
                String url = "http://www.ycce.edu";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
            else if(position == 1){
                String url = "https://ycce.in/";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
            else if(position == 2){
                Intent intent = ActivityUpload.newIntent(context);
                context.startActivity(intent);
            }
            else if(position == 3){
                String url = "http://www.ycce.ac.in/Default.aspx";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
            else if(position == 4){
                Intent intent = Activity_more_features.newIntent(context);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherFeatures.size();
    }
}

class OFViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public OFViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imageView = itemView.findViewById(R.id.other_features_logo);
        textView = itemView.findViewById(R.id.feature_name);
        this.context = context;

    }

    @SuppressLint("ResourceType")
    public void setImageView(@IdRes int imageId){
        imageView.setImageResource(imageId);
    }

    public void setTextView(String text){
        textView.setText(text);
    }

}