package com.bawei.lx_rcvitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Zhang on 2017/11/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

    private List<MyBean.DataBean> list;
    private Context context;

    private OnClickLisener onClickLisener;

    public OnClickLisener getOnClickLisener() {
        return onClickLisener;
    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    public interface OnClickLisener{
        void OnDainji(View v,int position);
        void OnLong(View v,int position);
    }

    public MyAdapter(List<MyBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));

        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {
        final MyBean.DataBean dataBean = list.get(position);
        holder.mtv.setText(dataBean.getNews_title());

        holder.mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisener.OnDainji(v,position);
            }
        });
       holder.mtv.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               onClickLisener.OnLong(v,position);
               return true;
           }
       });
        //holder.img.
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    static class MyViewHodler extends RecyclerView.ViewHolder {

        TextView mtv;

        public MyViewHodler(View itemView) {
            super(itemView);

            this.mtv = (TextView) itemView.findViewById(R.id.rv);
        }
    }
}
