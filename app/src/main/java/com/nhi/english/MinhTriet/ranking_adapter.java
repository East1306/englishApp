package com.nhi.english.MinhTriet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhi.english.R;

import java.util.ArrayList;

public class ranking_adapter extends BaseAdapter {
    ArrayList<ranking> rankingList;
    Context context;
    int layout;

    public ranking_adapter(ArrayList<ranking> rankingList1, Context context, int layout) {
        this.rankingList = rankingList1;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return rankingList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView TvTop, TvSoCau, TvSao, TvTime;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.TvTop = view.findViewById(R.id.txt_Top);
            holder.TvSoCau = view.findViewById(R.id.txt_Diem);
            holder.TvSao = view.findViewById(R.id.txt_Star);
            holder.TvTime = view.findViewById(R.id.txt_Time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ranking Ranking = rankingList.get(i);
        holder.TvTop.setText(String.valueOf(i+1));
        holder.TvSoCau.setText("Số câu : " + String.valueOf(Ranking.getSoCau()));
        holder.TvSao.setText(""+String.valueOf(Ranking.getCorrect_Star()));
        holder.TvTime.setText(String.valueOf(Ranking.getEar_Finish_Time()) + "s");


        return view;
    }
}
