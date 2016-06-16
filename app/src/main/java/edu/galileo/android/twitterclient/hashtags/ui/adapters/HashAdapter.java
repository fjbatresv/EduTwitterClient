package edu.galileo.android.twitterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.entities.Hash;

/**
 * Created by javie on 15/06/2016.
 */
public class HashAdapter extends RecyclerView.Adapter<HashAdapter.ViewHolder>{
    private List<Hash> dataset;
    private OnItemClickListener listener;

    public HashAdapter(List<Hash> dataset, OnItemClickListener listener) {
        this.dataset = dataset;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtags, parent, false);
        return new ViewHolder(viewHolder, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hash hash = dataset.get(position);
        holder.setOnClickListener(hash, listener);
        holder.txtTweet.setText(hash.getTweetText());
        holder.setItems(hash.getTags());
    }

    public  void setItems(List<Hash> newHashes){
        for(Hash newHash: newHashes){
            if(!dataset.contains(newHash)){
                dataset.add(newHash);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

        private View view;
        private HashtagListAdapter adapter;
        private List<String> items;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
            items = new ArrayList<String>();
            adapter = new HashtagListAdapter(items);
            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public void setItems(List<String> newItems){
            for(String newHash: newItems){
                if(!items.contains(newHash)){
                    items.add(newHash);
                }
            }
            adapter.notifyDataSetChanged();
        }

        public void setOnClickListener(final Hash hash, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(hash);
                }
            });
        }
    }

}
