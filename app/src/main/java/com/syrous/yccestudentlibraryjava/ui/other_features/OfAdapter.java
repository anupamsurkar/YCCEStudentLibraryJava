package com.syrous.yccestudentlibraryjava.ui.other_features;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.syrous.yccestudentlibraryjava.R;

import java.util.List;

public class OfAdapter extends RecyclerView.Adapter<OfAdapter.ViewHolder> {

    private List<ActivityOtherFeaturesHome> activityOtherFeatureHomes;
    private Context context;

    public OfAdapter(Context context, List<ActivityOtherFeaturesHome> activityOtherFeatureHomes) {
        this.context= context;
        this.activityOtherFeatureHomes = activityOtherFeatureHomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofeaturerow,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(activityOtherFeatureHomes.get(position).getFeaturelogo());
        holder.textView.setText(activityOtherFeatureHomes.get(position).getFeaturename());
    }

    @Override
    public int getItemCount() {
        return activityOtherFeatureHomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.other_features_logo);
            textView= itemView.findViewById(R.id.feature_name);
            cardView = itemView.findViewById(R.id.id_card);

        }
    }
}
