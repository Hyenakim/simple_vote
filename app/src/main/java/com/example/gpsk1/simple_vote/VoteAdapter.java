package com.example.gpsk1.simple_vote;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {

    public static ArrayList<ExampleItem> mExampleList;
    public static Context mContext;
    public static ExampleAdapter mAdapter;

    public VoteAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList = exampleList;
        mAdapter = new ExampleAdapter(mExampleList,false);
        if(mExampleList.size() > 2) { //투표 항목이 2개 이상은 유지돼야함
            for (int i = 0; i < mExampleList.size(); i++) {
                if (mExampleList.get(i).getItemContext().isEmpty()
                        && String.valueOf(mExampleList.get(i).getItemImageUri()) == "null") {
                    mExampleList.remove(i);
                    for(int j=i;j<mExampleList.size();j++){
                        mExampleList.get(j).setId(mExampleList.get(j).getId()-1);
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_item,parent,false);
        VoteViewHolder vvh = new VoteViewHolder(v);
        return vvh;
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        if(mExampleList.get(position).getItemContext() != null) {
            holder.button.setText(mExampleList.get(position).getItemContext());
        }
        if(mExampleList.get(position).getItemImageUri() != null) {
            Uri uri = Uri.parse(mExampleList.get(position).getItemImageUri());
            holder.image.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class VoteViewHolder extends RecyclerView.ViewHolder{
        public Button button;
        public ImageView image;

        public VoteViewHolder(View itemView) {
            super(itemView);
            button= itemView.findViewById(R.id.item_context);
            image = itemView.findViewById(R.id.item_img);
        }
    }
}
