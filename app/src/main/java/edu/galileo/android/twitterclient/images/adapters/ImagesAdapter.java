package edu.galileo.android.twitterclient.images.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.libs.base.ImageLoader;

/**
 * Created by javie on 15/06/2016.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> imgs;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;
    public ImagesAdapter(List<Image> imgs, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.imgs = imgs;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = imgs.get(position);
        holder.setOnClickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        imageLoader.load(holder.imgMedia, imageTweet.getImageUrl());
    }

    public  void setItems(List<Image> newImages){
        for(Image newImage: newImages){
            if(!imgs.contains(newImage)){
                imgs.add(newImage);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imgMedia)
        ImageView imgMedia;
        @Bind(R.id.txtTweet)
        TextView txtTweet;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }
        public void setOnClickListener(final Image image, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}
