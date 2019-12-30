package com.yunsai.ops.store_management.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.Listbean;
import com.yunsai.ops.store_management.base.Listbeanss;
import com.yunsai.ops.store_management.base.PhoneBase;
import com.yunsai.ops.store_management.login.SuperPhoneActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Listbeanss.RECORDSBean>listbeansses;
    private List<Listbeanss.RECORDSBean>listData;
    Filters filters;

    public ListAdapter(Context context, List<Listbeanss.RECORDSBean>listbeansses) {
        this.context = context;
        this.listbeansses = listbeansses;
        listData = listbeansses;
    }

    @Override
    public int getCount() {
        return listbeansses.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.listview, null);
            holder.mText = view.findViewById(R.id.list_shop_id);
            holder.Texx = view.findViewById(R.id.list_shop_name);
            holder.Time = view.findViewById(R.id.list_time);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.mText.setText(listbeansses.get(i).getShopId());
        holder.Texx.setText(listbeansses.get(i).getShopName());
        holder.Time.setText(listbeansses.get(i).getShopTime());
        return view;
    }

    class Holder {
        TextView mText;
        TextView Texx;
        TextView Time;
    }

    public void UpView(int i,View view){
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.listview, null);
            holder.mText = view.findViewById(R.id.list_shop_id);
            holder.Texx = view.findViewById(R.id.list_shop_name);
            holder.Time = view.findViewById(R.id.list_time);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.mText.setText(listbeansses.get(i).getShopId());
        holder.Texx.setText(listbeansses.get(i).getShopName());
        holder.Time.setText(listbeansses.get(i).getShopTime());
    }

    @Override
    public Filter getFilter() {
        if (filters==null){
           filters = new Filters(listbeansses);
        }
        return filters;
    }

    class Filters extends Filter{
        //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
        private List<Listbeanss.RECORDSBean> original;

        public Filters(List<Listbeanss.RECORDSBean> list) {
            this.original = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = original;
                results.count = original.size();
            } else {
                List<Listbeanss.RECORDSBean> mList = new ArrayList<>();
                for (Listbeanss.RECORDSBean recordsBean : original) {
                    if (recordsBean.getShopId().contains(constraint)
                            ||recordsBean.getShopName().contains(constraint)) {
                        mList.add(recordsBean);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }
        //在publishResults方法中告诉适配器更新界面
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listbeansses = (List<Listbeanss.RECORDSBean>)filterResults.values;
                notifyDataSetChanged();//通知数据发生了改变
        }
    }
}
