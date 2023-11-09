package com.example.spl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.spl.helper.CommonData;

import java.io.File;
import java.util.ArrayList;

public class FolderListActivity extends Activity {


    private final ArrayList<String> btArrayList = new ArrayList<>();
    private ArrayAdapter<String> btArrayAdapter;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folderlist_popup);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            path = bundle.getString("filepath");
            System.out.println("<><><>@@@@  " + path);
        }
        ListView btListView = findViewById(R.id.btdevice_list);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenwd = dm.widthPixels;
        int screenht = dm.heightPixels;
        getWindow().setLayout((int) (screenwd * 0.6), (int) (screenht * 0.5)); //Adjust popup layout size

        File f = new File(path);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if(!inFile.getName().contains("pdf")){
                btArrayList.add(inFile.getName());
            }
        }

        btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, btArrayList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View lView = super.getView(position, convertView, parent);


                return lView;
            }
        };
        btListView.setAdapter(btArrayAdapter);


        btListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File f = new File(FolderListActivity.this.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/" + btArrayList.get(position));
                if (f.isDirectory()) {
                    Intent intent = new Intent(FolderListActivity.this, FolderListActivity.class);
                    intent.putExtra("filepath", FolderListActivity.this.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/" + btArrayList.get(position) + "/");
                    startActivity(intent);
                    finish();
                } else {
                    CommonData.tempFile = path + btArrayList.get(position);
                    if (CommonData.tempFile.contains("log")) {
                        CommonData.tempFileOption = 1;
                        finish();
                    } else {

                    }

                }

            }
        });


    }
}
