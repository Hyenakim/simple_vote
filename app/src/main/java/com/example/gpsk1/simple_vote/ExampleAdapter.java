package com.example.gpsk1.simple_vote;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    public static ArrayList<ExampleItem> mExampleList;
    private static int PICK_FROM_ALBUM = 0;
    public static Context mContext;
    public static boolean isText;

    public ExampleAdapter(ArrayList<ExampleItem> exampleList,boolean isTextFlag){
        mExampleList = exampleList;
        isText = isTextFlag;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, final int position) {
        ExampleItem currentItem = mExampleList.get(position);

        if(mExampleList.get(position).getItemContext() != null) {
            holder.editText.setText(mExampleList.get(position).getItemContext());
        }else {
            holder.editText.setHint("항목 입력");
            holder.editText.setText(null);
        }
        if(mExampleList.get(position).getItemImageUri() != null) {
            Uri uri = Uri.parse(mExampleList.get(position).getItemImageUri());
            holder.imageButton.setImageURI(uri);
        }else {
            holder.imageButton.setImageResource(R.drawable.selectimage);
            holder.imageButton.setImageURI(null);
        }
        holder.idText.setText(String.valueOf(mExampleList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageButton imageButton;
        public EditText editText;
        public TextView idText;
        final Calendar myCalendar = Calendar.getInstance();

        public ExampleViewHolder(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.input_context);
            imageButton = itemView.findViewById(R.id.input_image);
            idText = itemView.findViewById(R.id.input_id);

            if(isText) {
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
            else {
                final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }
                };
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog((Activity) mContext, dateListener, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });
            }
            imageButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    PICK_FROM_ALBUM = Integer.parseInt(String.valueOf(idText.getText()));
                    if(mExampleList.get(PICK_FROM_ALBUM).getItemImageUri() != null){
                        //이미지 삭제
                        mExampleList.get(PICK_FROM_ALBUM).setItemImageUri(null);
                        imageButton.setImageResource(R.drawable.selectimage);
                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                        ((Activity) mContext).startActivityForResult(intent, PICK_FROM_ALBUM);
                    }
                }
            });

        }
        private void updateLabel(){
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
            editText.setText(sdf.format(myCalendar.getTime()));
        }

    }

}
