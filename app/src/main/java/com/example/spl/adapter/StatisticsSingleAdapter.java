package com.example.spl.adapter;

import static com.example.spl.programmer.singlechamber.StatisticsSingleFragment.openGraph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spl.R;
import com.example.spl.model.StatisticsSingle;

import java.util.ArrayList;

public class StatisticsSingleAdapter extends ArrayAdapter<StatisticsSingle>  {

    private ArrayList<StatisticsSingle> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView mTxtDate;
        TextView mTxtTime;
        TextView mTxtPace;
        TextView mTxtSensMV;
        TextView mTxtS80Ppm;
        TextView mTxtS100Ppm;
        TextView mTxtS120Ppm;
        TextView mTxtS140Ppm;
        TextView mTxtS140PpmB;
        TextView mTxtNoisePpm;
        TextView mTxtNoisePacePpm;
        ImageView mImgBarGraph;
    }

    public StatisticsSingleAdapter(ArrayList<StatisticsSingle> data, Context context) {
        super(context, R.layout.statistics_single_item_layout, data);
        this.dataSet = data;
        this.mContext = context;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StatisticsSingle dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.statistics_single_item_layout, parent, false);
            viewHolder.mTxtDate = (TextView) convertView.findViewById(R.id.mTxtDate);
            viewHolder.mTxtTime = (TextView) convertView.findViewById(R.id.mTxtTime);
            viewHolder.mTxtPace = (TextView) convertView.findViewById(R.id.mTxtPace);
            viewHolder.mTxtSensMV = (TextView) convertView.findViewById(R.id.mTxtSensMV);
            viewHolder.mTxtS80Ppm = (TextView) convertView.findViewById(R.id.mTxtS80Ppm);
            viewHolder.mTxtS100Ppm = (TextView) convertView.findViewById(R.id.mTxtS100Ppm);
            viewHolder.mTxtS120Ppm = (TextView) convertView.findViewById(R.id.mTxtS120Ppm);
            viewHolder.mTxtS140Ppm = (TextView) convertView.findViewById(R.id.mTxtS140Ppm);
            viewHolder.mTxtS140PpmB = (TextView) convertView.findViewById(R.id.mTxtS140PpmB);
            viewHolder.mTxtNoisePpm = (TextView) convertView.findViewById(R.id.mTxtNoisePpm);
            viewHolder.mTxtNoisePacePpm = (TextView) convertView.findViewById(R.id.mTxtNoisePacePpm);
            viewHolder.mImgBarGraph = (ImageView) convertView.findViewById(R.id.mImgBarGraph);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.mTxtDate.setText(dataModel.getmStrDate());
        viewHolder.mTxtTime.setText(dataModel.getmStrTime());
        viewHolder.mTxtPace.setText(dataModel.getmStrPace());
        viewHolder.mTxtSensMV.setText(dataModel.getmStrSensMV());
        viewHolder.mTxtS80Ppm.setText(dataModel.getmStrS80Ppm());
        viewHolder.mTxtS100Ppm.setText(dataModel.getmStrS100Ppm());
        viewHolder.mTxtS120Ppm.setText(dataModel.getmStrS120Ppm());
        viewHolder.mTxtS140Ppm.setText(dataModel.getmStrS140Ppm());
        viewHolder.mTxtS140PpmB.setText(dataModel.getmStrS140PpmB());
        viewHolder.mTxtNoisePpm.setText(dataModel.getmStrNoisePpm());
        viewHolder.mTxtNoisePacePpm.setText(dataModel.getmStrNoisePacePpm());

        viewHolder.mImgBarGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraph(position);
            }
        });
       // viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
