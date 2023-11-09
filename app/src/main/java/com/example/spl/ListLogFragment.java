package com.example.spl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.spl.helper.CommonData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ListLogFragment extends Fragment {

    ListView mListLogs;
    private final ArrayList<String> logArrayList = new ArrayList<>();
    private ArrayAdapter<String> logArrayAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_log_list, container, false);
        mListLogs=v.findViewById(R.id.mListLogs);

        return v;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, logArrayList);
        mListLogs.setAdapter(logArrayAdapter);
        fillLogList();

        mListLogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String mStrRead = logArrayList.get(position);
               if(mStrRead.contains("Interrogation") || mStrRead.contains("Programming")){


               }

            }
        });
    }

    private void fillLogList() {
        boolean bDir = true, bFile = true;
        int nReadBytes ;
        File dir;
        // ***** Read Log Files *******
        if(CommonData.tempFile.length()>0){
             dir = new File((CommonData.tempFile));
        }else {
             dir = new File(CommonData.filePath);
        }
        if (!dir.exists()) {
            bDir = dir.mkdirs();
            //Toast.makeText(this, "Dir Created", Toast.LENGTH_SHORT).show();
        }
        if (bDir) {
            InputStream inputStream;
            try {
                File file1;
                if(CommonData.tempFile.length()>0){
                    file1 = new File((CommonData.tempFile));
                    CommonData.tempFile = "";
                }else {
                    file1 = new File(dir, CommonData.fileName);
                }
                if (!file1.exists())
                    bFile = file1.createNewFile();

                if (bFile) {
                    inputStream = new FileInputStream(file1);
                    byte[] rdBytes = new byte[(int) file1.length()];
                    int nBytes;
                    nReadBytes = inputStream.read(rdBytes);

                    if (nReadBytes>0) {
                        logArrayList.clear(); // Clear Log List
                        for (int p = 0; p < file1.length(); p++) {

                            if (rdBytes[p] == 0x2) {
                                nBytes = rdBytes[p + 1]; // no of Bytes
                                byte[] activity = new byte[nBytes];
                                for (int i = 0; i < (nBytes); i++) {
                                    activity[i] = (byte) (0x99 ^ (byte) (0xFF & rdBytes[p + i + 2]));
                                    // activity[i] =  ((byte) (0xFF & rdBytes[p + i + 2]));
                                }

                                String rdFile = new String(activity);
                                if(!rdFile.contains("Decode")){
                                    logArrayList.add(rdFile);
                                }
                                Collections.reverse(logArrayList);
                                logArrayAdapter.notifyDataSetChanged();

                                p += nBytes;

                            }
                            //Check for para bytes to skip
                            if (rdBytes[p] == 0x03) {
                                nBytes = rdBytes[p + 1]; // no of Bytes to skip
                                p += nBytes;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //**************

    }
}