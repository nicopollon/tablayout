package com.example.Button_Less;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private Context context;
    private MainActivity mContext;

    public RecyclerViewAdapter(ArrayList<String> mNames, MainActivity mContext) {
        this.mNames = mNames;
        this.mContext = mContext;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);

        ViewHolder v= new ViewHolder(view, mListener );
        return v;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {



        Log.d(TAG, "onBindViewHolder: done");

        holder.piano.setText(mNames.get(position));
        holder.cardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
              final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

              builder.setTitle("Confirm floor " + mNames.get(position) + "?");
              builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Snackbar.make(view,  mNames.get(position) + " Floor confirmed.", Snackbar.LENGTH_LONG).show();

                  }

              });

              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {


                  }
              });
              AlertDialog alertDialog = builder.create();
              alertDialog.show();
            }
        });




    }



    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView piano;
        CardView cardbutton;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            piano = itemView.findViewById(R.id.Piano);
            cardbutton = itemView.findViewById(R.id.cardbutton);
        }
    }



}

