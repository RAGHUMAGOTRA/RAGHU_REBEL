package com.example.admin.vibring.addedlocations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.vibring.R;

import java.util.ArrayList;

/**
 * Created by admin on 5/3/2017.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SingleRow> singleRowArrayList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<SingleRow> singleRowArrayList) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
    }

    /*public MyAdapter( ViewLocActivity mainActivity, ArrayList<SingleRow> singleRowArrayList) {
    }*/

    @Override
    public int getCount() {
        return singleRowArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return singleRowArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.singlerown, parent, false);
        //finding all the view of single row
        TextView txtSno = (TextView) convertView.findViewById(R.id.Sno);
        TextView txtLocationName = (TextView) convertView.findViewById(R.id.LocationName);
      //  TextView txtLatitude = (TextView) convertView.findViewById(R.id.LocationName);
        TextView txtMode = (TextView) convertView.findViewById(R.id.mode);

        //  ImageView image= (ImageView) convertView.findViewById(R.id.img1);
        //getting data from view
        SingleRow singleRow = singleRowArrayList.get(position);
        int uid = singleRow.getUid();
        String name = singleRow.getLocationName();
        String mode = singleRow.getMode();
        // String img=singleRow.getImage();
        //setting data in the view
        txtSno.setText(String.valueOf(uid));
        txtLocationName.setText(name);
        txtMode.setText(mode);
        // image.setImageResource(img.t);

        return convertView;

    }
}
