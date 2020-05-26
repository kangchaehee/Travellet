package com.example.example.feature.report;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.example.R;

public class Report extends Fragment {
    ReportDaily fragment1;
    ReportCategory fragment2;


    LinearLayout btn_daily, btn_category;
    TextView txt1, txt2;
    View line1, line2;

    int mainPosition;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_report, container, false);
        fragment1 = new ReportDaily();
        fragment2 = new ReportCategory();

        if(getArguments() != null){
            mainPosition = getArguments().getInt("mainPosition", 0);
        }

        getChildFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        line1 = rootView.findViewById(R.id.line1);
        line2 = rootView.findViewById(R.id.line2);

        btn_daily = rootView.findViewById(R.id.btn_daily);
        txt1 = rootView.findViewById(R.id.txt1);
        btn_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                getChildFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                txt2.setTextColor(getResources().getColor(R.color.soft_grey));
                txt1.setTextColor(getResources().getColor(R.color.black));
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
            }

        });

        btn_category = rootView.findViewById(R.id.btn_category);
        txt2 = rootView.findViewById(R.id.txt2);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Bundle bundle = new Bundle();
                bundle.putInt("mainPosition", mainPosition);
                fragment2.setArguments(bundle);
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
                txt1.setTextColor(getResources().getColor(R.color.soft_grey));
                txt2.setTextColor(getResources().getColor(R.color.black));
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }
}
