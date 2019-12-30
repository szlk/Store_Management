package com.yunsai.ops.store_management.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.PhoneBase;

import java.util.List;

public class ListAdapter_Phone extends BaseAdapter {
    Context mContext;
    private List<PhoneBase.RECORDSBean> recordsBeans;


    public ListAdapter_Phone(Context context, List<PhoneBase.RECORDSBean> listbeansses) {
        this.mContext = context;
        this.recordsBeans = listbeansses;
    }

    @Override
    public int getCount() {
        return recordsBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.phone_listview, null);
            holder.mPhone = view.findViewById(R.id.phone);
            holder.mName = view.findViewById(R.id.name);
            holder.xxa = view.findViewById(R.id.xxa);
            holder.xxb = view.findViewById(R.id.xxb);
            holder.xxc = view.findViewById(R.id.xxc);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.mPhone.setText(recordsBeans.get(i).getUserAccount());
        holder.mName.setText(recordsBeans.get(i).getUserName());

        String userKey = "";

        userKey = recordsBeans.get(i).getUserKey();

        Log.i("秘钥", "getView: "+userKey);
        String s1 = "0";
        String s2 = "1";
        String s3 = "2";

        if (userKey!=null){
            if (userKey.equals(s1)){
                holder.xxa.setVisibility(View.VISIBLE);
                holder.xxb.setVisibility(View.VISIBLE);
                holder.xxc.setVisibility(View.VISIBLE);
            }if (userKey.equals(s2)){
                holder.xxa.setVisibility(View.VISIBLE);
                holder.xxb.setVisibility(View.VISIBLE);
            }if (userKey.equals(s3)){
                holder.xxa.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    class Holder {
        TextView mPhone;
        TextView mName;
        ImageView xxa;
        ImageView xxb;
        ImageView xxc;
    }


}
