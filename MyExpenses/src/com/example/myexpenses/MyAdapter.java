package com.example.myexpenses;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class	MyAdapter extends BaseAdapter {

    Context context;
    List<String> data;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, List<String> data) {
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
        
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        
        //vi.setBackgroundColor(Color.YELLOW);
        
        
        //if(!vi.getBackground().equals(vi.getResources().getDrawable(R.drawable.green_button)))
        //vi.setBackgroundResource(R.drawable.red_button);

        TextView text = (TextView) vi.findViewById(R.id.textRow);
        
        if(CommonView.currentItem!=null)
        	Log.d("error",CommonView.currentItem);
        if(CommonView.currentItem!=null && CommonView.currentItem.equals(data.get(position))){
        	text.setBackgroundResource(R.drawable.green_button);
        	CommonView.selectedView = text;
        }
        else
        	text.setBackgroundResource(R.drawable.red_button);
        
        	
        
        text.setTypeface(null, Typeface.BOLD);
        text.setTextColor(Color.WHITE);
        text.setText(data.get(position));
            
        return vi;
    }
}
