package com.example.banlge_aduio_story.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banlge_aduio_story.R;
import com.example.banlge_aduio_story.model.AduioWriter;
import com.example.banlge_aduio_story.utils.RecyclerViewClickListener;

import java.util.List;

public class Popular_Program_Adapter extends RecyclerView.Adapter<Popular_Program_Adapter.ViewHolder> {
    private int listItemLayout;
    private List<AduioWriter> itemList;
    private static RecyclerViewClickListener itemListener;
    Context mContext;
    String type;
    // Constructor of the class
    public Popular_Program_Adapter(Context context,
                                   List<AduioWriter> itemList,String type, RecyclerViewClickListener itemListener) {
        //listItemLayout = layoutId;
        this.itemList = itemList;
        this.itemListener =  itemListener;
        this.type = type;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poplur_progrm_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

            String title = itemList.get(listPosition).getName();
            int storyCount =  itemList.get(listPosition).getCount();
            holder.itemText.setText(title+"");
            if(type.equals("Popular Writer"))
            {
                holder.imageView.setImageResource(R.drawable.ic_pen);
            }

            holder.storyCount.setText("( Story "+storyCount+""+" )");
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemText;
        public ImageView imageView;
        public TextView storyCount;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText =  itemView.findViewById(R.id.title);
            imageView= itemView.findViewById(R.id.imageView);
            storyCount = itemView.findViewById(R.id.storyCout);
        }
        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view,this.getPosition());
        }
    }
}
