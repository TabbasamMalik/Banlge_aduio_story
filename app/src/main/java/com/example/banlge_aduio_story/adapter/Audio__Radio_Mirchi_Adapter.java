package com.example.banlge_aduio_story.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banlge_aduio_story.R;
import com.example.banlge_aduio_story.model.BanglaAudioList;
import com.example.banlge_aduio_story.model.Title;
import com.example.banlge_aduio_story.utils.RecyclerViewClickListener;

import java.util.List;

public class Audio__Radio_Mirchi_Adapter extends RecyclerView.Adapter<Audio__Radio_Mirchi_Adapter.ViewHolder> {
    private int listItemLayout;
    private List<BanglaAudioList> itemList;
    private static RecyclerViewClickListener itemListener;
    Context mContext;
    // Constructor of the class
    public Audio__Radio_Mirchi_Adapter(Context context,
                                       List<BanglaAudioList> itemList,RecyclerViewClickListener itemListener) {
        //listItemLayout = layoutId;
        this.itemList = itemList;
        this.itemListener =  itemListener;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_mirchi_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

            Title title = itemList.get(listPosition).getTitle();
            List<String> audioInfo= itemList.get(listPosition).getaudio_info().get_audio_audio_stream_url();
            String plyingMpp3Url=audioInfo.get(0);
            String rendered= title.getRendered();
            if(rendered.contains("&#8211;"))
            {
                rendered=rendered.replace(" &#8211;","");
            }

//            AudioInfo info = list.get(index).getaudio_info();
//            String infoList =info.get_audio_audio_stream_url().get(0);
            String str = "";

        holder.itemText.setText(rendered+"");
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemText;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemText =  itemView.findViewById(R.id.title);
            imageView= itemView.findViewById(R.id.imageView);
        }
        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view,this.getPosition());
        }
    }
}
