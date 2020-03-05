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

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.UploadActivity;

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
                String url = "https://www.ycce.edu";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
            if(position == 1){
                String url = "http://www.ycce.in";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
//            if(position == 2){
//                Intent intent = new Intent(context, UploadActivity.class);
//                Context.startActivity(intent);
//            }
            if(position == 3){
                String url = "http://www.ycce.ac.in";
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherFeatures.size();
    }
}

class OFViewHolder extends RecyclerView.ViewHolder{
//            implements View.OnClickListener{

    private ImageView imageView;
    private TextView textView;
    private Context context;

    public OFViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imageView = itemView.findViewById(R.id.other_features_logo);
        textView = itemView.findViewById(R.id.feature_name);
        this.context = context;
       // itemView.setOnClickListener(this);
    }

    @SuppressLint("ResourceType")
    public void setImageView(@IdRes int imageId){
        imageView.setImageResource(imageId);
    }

    public void setTextView(String text){
        textView.setText(text);
    }
//
//    @Override
////    public void onClick(View v) {
////        String url = "https://www.google.in";
////        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
////        builder.setToolbarColor(-65536);
////        builder.setShowTitle(true);
////        CustomTabsIntent customTabsIntent = builder.build();
////        customTabsIntent.launchUrl(context, Uri.parse(url));
////    }
}