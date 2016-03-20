package com.example.myexpenses;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class	MyYearAdapter extends BaseAdapter {

    Context context;
    List<String> data;
    private static LayoutInflater inflater = null;

    public MyYearAdapter(Context context, List<String> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.yearrow, null);
        
        vi.setBackgroundColor(Color.BLACK);

        TextView text = (TextView) vi.findViewById(R.id.yearRow);
        text.setTextColor(Color.WHITE);
        text.setTypeface(null, Typeface.BOLD_ITALIC);
        
        text.setText(data.get(position));
       
        
        
        return vi;
    }
}
