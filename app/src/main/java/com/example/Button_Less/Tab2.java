package com.example.Button_Less;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class Tab2 extends Fragment implements View.OnClickListener
{

    private Button buttonScan;
    private TextView textViewFloor;
    public IntentIntegrator qrScan;

    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);

        buttonScan= (Button) view.findViewById(R.id.scanqr);
        textViewFloor = (TextView) view.findViewById(R.id.scanCurrentFloor);




        buttonScan.setOnClickListener(this);

        //QR SCAN
        qrScan = new IntentIntegrator(getActivity());

        return view;
    }
    //QR Code
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //void qrcode
        if (result != null) {
            if (result.getContents() == null) {

                Toast.makeText(getActivity(), "No result", Toast.LENGTH_LONG).show();
            } else {
                try {

                    JSONObject obj = new JSONObject(result.getContents());

                    Toast.makeText(getActivity(),""+ obj.getString("name"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();


                    Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_LONG).show();
                }

            }
        } else { super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
        ((MainActivity) getActivity()).selectedTab(1);
    }


    }

