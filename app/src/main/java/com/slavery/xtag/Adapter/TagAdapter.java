package com.slavery.xtag.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.slavery.xtag.R;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder>{

    private Context mContext;
    private List<String> mTag, mTagsCount;

    public TagAdapter(Context mContext, List<String> mTag, List<String> mTagsCount) {
        this.mContext = mContext;
        this.mTag = mTag;
        this.mTagsCount = mTagsCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, parent, false);

        return new TagAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tag.setText("#"+ mTag.get(position));
        holder.noOfPost.setText(mTagsCount.get(position));
    }

    @Override
    public int getItemCount() {
        return mTag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tag, noOfPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tag = itemView.findViewById(R.id.hash_tag);
            noOfPost = itemView.findViewById(R.id.no_of_post);
        }
    }

    public void filter(List<String> filterTag, List<String> filterTagCount){
        this.mTag = filterTag;
        this.mTagsCount = filterTagCount;
        notifyDataSetChanged();
    }
}
