package com.example.fyp_survey_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private Context mContext;
    SurveyAdapter(Context context){
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_survey_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //TODO: LIST FOR SINGLE ITEM

        public ViewHolder(View itemView){
            super(itemView);
        }
    }
}
