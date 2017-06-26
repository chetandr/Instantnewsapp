package com.ramghat.newsbychannels.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ramghat.newsbychannels.R;
import com.ramghat.newsbychannels.activity.ArticleActivity;
import com.ramghat.newsbychannels.model.Source;

import java.util.List;

/**
 * Created by chetan_rane on 5/31/2017.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    private List<Source> sources;
    private Context context;
    public ChannelAdapter(List<Source> sourceList) {
        this.sources = sourceList;
    }

    @Override
    public int getItemCount(){
        return this.sources.size();
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(this.context).inflate(R.layout.channel_card, viewGroup, false);
        ChannelViewHolder channelViewHolder = new ChannelViewHolder(view);
        return channelViewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder channelViewHolder, int i) {
        channelViewHolder.setmChannel(sources.get(i).getId());
        channelViewHolder.channelTitle.setText(sources.get(i).getName());
        channelViewHolder.channelCategory.setText(sources.get(i).getCategory());
        channelViewHolder.channelDesc.setText(sources.get(i).getDescription());
        channelViewHolder.channelURL.setText(sources.get(i).getUrl());
        channelViewHolder.channelCountry.setText(sources.get(i).getCountry());
        channelViewHolder.channelLang.setText(sources.get(i).getLanguage());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView channelCardView;
        TextView channelTitle, channelCategory, channelURL, channelDesc, channelCountry, channelLang;
        public static final String SOURCE_CHANNEL = "com.newsmesh.myfirstapp.CHANNEL";
        String mChannel;
        private final Context context;

        ChannelViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context = itemView.getContext();

            channelCardView = (CardView) itemView.findViewById(R.id.channelCardView);
            channelTitle = (TextView) itemView.findViewById(R.id.channelTitle);
            channelCategory = (TextView) itemView.findViewById(R.id.channelCategory);
            channelURL = (TextView) itemView.findViewById(R.id.channelURL);
            channelDesc = (TextView) itemView.findViewById(R.id.channelDesc);
            channelCountry = (TextView) itemView.findViewById(R.id.channelCountry);
            channelLang = (TextView) itemView.findViewById(R.id.channelLang);

        }

        public void setmChannel(String mChannel) {
            this.mChannel = mChannel;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ArticleActivity.class);
            intent.putExtra(SOURCE_CHANNEL, this.mChannel);
            context.startActivity(intent);
        }

    }
}

