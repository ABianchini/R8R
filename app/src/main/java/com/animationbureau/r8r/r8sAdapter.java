package com.animationbureau.r8r;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class r8sAdapter extends ArrayAdapter<R8s> {
    public r8sAdapter(Context context, ArrayList<R8s> r8s) {
        super(context, 0, r8s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        R8s r8s = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_r8s,parent,false);
        }
        TextView tvWhat = (TextView) convertView.findViewById(R.id.tvWhat);
        TextView tvR8 = (TextView) convertView.findViewById(R.id.tvR8);

        tvWhat.setText(r8s.getWhat());
        tvR8.setText(String.valueOf(r8s.getr8()));

        return convertView;
    }
}
