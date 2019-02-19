package com.example.gpsk1.simple_vote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    public static ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.editText.setText(mExampleList.get(position).getItemContext());
        mExampleList.get(position).setItemImageUri(holder.imageButton.getResources().toString());

        //holder.editText.setHint(currentItem.getItemContext());
        //holder.imageButton.setImageResource(Integer.parseInt(currentItem.getItemImageUri()));
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageButton imageButton;
        public EditText editText;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.input_context);
            imageButton = itemView.findViewById(R.id.input_image);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    mExampleList.get(getAdapterPosition()).setItemContext(editText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

}
