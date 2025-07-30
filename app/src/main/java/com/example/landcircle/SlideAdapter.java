//package com.example.landcircle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.landcircle.SlideItem;

import java.util.List;

/*public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {
    private List<SlideItem> slideItems;

    public SlideAdapter(List<SlideItem> slideItems) {
        this.slideItems = slideItems;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_slide, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        SlideItem item = slideItems.get(position);
        holder.imageView.setImageResource(item.image);
        holder.textView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    static class SlideViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}*/
