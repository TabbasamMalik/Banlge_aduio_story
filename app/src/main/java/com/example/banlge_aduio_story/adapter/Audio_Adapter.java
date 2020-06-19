package com.example.banlge_aduio_story.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banlge_aduio_story.PopularProgramActivity;
import com.example.banlge_aduio_story.R;
import com.example.banlge_aduio_story.Radio_MirchiActivity;
import com.example.banlge_aduio_story.WebViewActivity;
import com.example.banlge_aduio_story.model.Audioname;

import java.util.List;

public class Audio_Adapter extends RecyclerView.Adapter<Audio_Adapter.ViewHolder> {
    private int listItemLayout;
    private List<Audioname> itemList;
    Context mContext;
    // Constructor of the class
    public Audio_Adapter(Context context, List<Audioname> itemList) {
        //listItemLayout = layoutId;
        this.itemList = itemList;
        this.mContext= context;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_row_layout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        holder.itemText.setText(itemList.get(listPosition).getAudioname()+"");
        holder.imageView.setImageResource(itemList.get(listPosition).getImageDrawable());
        holder.id.setText(itemList.get(listPosition).getId()+"");
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemText;
        public ImageView imageView;
        public TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText =  itemView.findViewById(R.id.title);
            imageView= itemView.findViewById(R.id.imageView);
            id=  itemView.findViewById(R.id.id);
        }
        @Override
        public void onClick(View view) {
            if(itemText.getText().toString().equals("Popular Program"))
            {
                Intent intent = new Intent(view.getContext(), PopularProgramActivity.class);
                intent.putExtra("Category", itemText.getText().toString());
                view.getContext().startActivity(intent);
            }
            else if(itemText.getText().toString().equals("Popular Writer"))
            {
                Intent intent = new Intent(view.getContext(), PopularProgramActivity.class);
                intent.putExtra("Category", itemText.getText().toString());
                view.getContext().startActivity(intent);
            }
            else if(itemText.getText().toString().equals("Popular Series"))
            {
                Intent intent = new Intent(view.getContext(), PopularProgramActivity.class);
                intent.putExtra("Category", itemText.getText().toString());
                view.getContext().startActivity(intent);
            }
            else if(itemText.getText().toString().equals("All Audio Story"))
            {
                Intent intent = new Intent(view.getContext(), Radio_MirchiActivity.class);
                intent.putExtra("Category", itemText.getText().toString());
                view.getContext().startActivity(intent);
            }
            else if(itemText.getText().toString().equals("Feedback"))
            {
                Intent intent = new Intent(view.getContext(), WebViewActivity.class);
               // intent.putExtra("Category", itemText.getText().toString());
                view.getContext().startActivity(intent);
            }
            else {
                Intent intent = new Intent(view.getContext(), Radio_MirchiActivity.class);
                intent.putExtra("Category", itemText.getText().toString());
                intent.putExtra("id",id.getText().toString());
                view.getContext().startActivity(intent);
            }
        }
    }
}
