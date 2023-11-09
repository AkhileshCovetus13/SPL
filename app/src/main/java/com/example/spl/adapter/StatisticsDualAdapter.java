package com.example.spl.adapter;

import static com.example.spl.programmer.dualchamber.StatisticsFragment.openGraph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spl.R;
import com.example.spl.model.StatisticsDual;

import java.util.ArrayList;

public class StatisticsDualAdapter extends ArrayAdapter<StatisticsDual>  {

    private ArrayList<StatisticsDual> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView mTxtDate;
        TextView mTxtTime;
        TextView mTxtNoise;
        TextView mTxtNoisePace;
        TextView mTxtAtriumPaceCount;
        TextView mTxtAtriumSensCount;
        TextView mTxtVentricleSensCount;
        TextView mTxtVentriclePaceCount;
        TextView mTxtAtCount;
        TextView mTxtAfCount;
        ImageView mImgBarGraph;
    }

    public StatisticsDualAdapter(ArrayList<StatisticsDual> data, Context context) {
        super(context, R.layout.statistics_item_layout, data);
        this.dataSet = data;
        this.mContext = context;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StatisticsDual dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.statistics_item_layout, parent, false);
            viewHolder.mTxtDate = (TextView) convertView.findViewById(R.id.mTxtDate);
            viewHolder.mTxtTime = (TextView) convertView.findViewById(R.id.mTxtTime);
            viewHolder.mTxtNoise = (TextView) convertView.findViewById(R.id.mTxtNoise);
            viewHolder.mTxtNoisePace = (TextView) convertView.findViewById(R.id.mTxtNoisePace);
            viewHolder.mTxtAtriumPaceCount = (TextView) convertView.findViewById(R.id.mTxtAtriumPaceCount);
            viewHolder.mTxtAtriumSensCount = (TextView) convertView.findViewById(R.id.mTxtAtriumSensCount);
            viewHolder.mTxtVentricleSensCount = (TextView) convertView.findViewById(R.id.mTxtVentricleSensCount);
            viewHolder.mTxtVentriclePaceCount = (TextView) convertView.findViewById(R.id.mTxtVentriclePaceCount);
            viewHolder.mTxtAtCount = (TextView) convertView.findViewById(R.id.mTxtAtCount);
            viewHolder.mTxtAfCount = (TextView) convertView.findViewById(R.id.mTxtAfCount);
            viewHolder.mImgBarGraph = (ImageView) convertView.findViewById(R.id.mImgBarGraph);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.mTxtDate.setText(dataModel.getmStrDate());
        viewHolder.mTxtTime.setText(dataModel.getmStrTime());
        viewHolder.mTxtNoise.setText(dataModel.getmStrNoise());
        viewHolder.mTxtNoisePace.setText(dataModel.getmStrNoisePace());
        viewHolder.mTxtAtriumPaceCount.setText(dataModel.getmStrAtriumPaceCount());
        viewHolder.mTxtAtriumSensCount.setText(dataModel.getmStrAtriumSensCount());
        viewHolder.mTxtVentricleSensCount.setText(dataModel.getmStrVentricleSensCount());
        viewHolder.mTxtVentriclePaceCount.setText(dataModel.getmStrVentriclePaceCount());
        viewHolder.mTxtAtCount.setText(dataModel.getmStrAtCount());
        viewHolder.mTxtAfCount.setText(dataModel.getmStrAfCount());

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
