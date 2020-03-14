package com.syrous.yccestudentlibraryjava.ui.recents;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsViewHolder> {

    private List<ModelRecents> RecentList;
    private Context context;

    public RecentsAdapter(Context context, List Recents) {
        this.context= context;
        this.RecentList = Recents;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofeaturerow,
                parent, false);
        return new RecentsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.setImageView(RecentList.get(position).getFeatureLogo());
        holder.setTextView(RecentList.get(position).getFeatureName());

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(-65536);
        builder.setShowTitle(true);
        CustomTabsIntent customTabsIntent = builder.build();

    }

    @Override
    public int getItemCount() {
        return RecentList.size();
    }
}

class RecentsViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public RecentsViewHolder(@NonNull View itemView, Context context) {
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