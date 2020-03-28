package com.abstractghoul.coronameter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {

    private TextView cases;
    private Button getBtn;

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stats, container, false);
        cases = (TextView) view.findViewById(R.id.total_cases);
        getBtn = (Button) view.findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        return view;
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus").get();
                    Elements mainNumbers = doc.getElementsByClass("maincounter-number");
                    String mainCases = mainNumbers.get(0).getElementsByClass("maincounter-number").text();
                    String mainDeaths = mainNumbers.get(1).getElementsByClass("maincounter-number").text();
                    String mainRecovered = mainNumbers.get(2).getElementsByClass("maincounter-number").text();
                    builder.append(mainCases);
                } catch (IOException e) {
                    builder.append("Error :").append(e.getMessage()).append("\n");
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cases.setText(builder.toString());
                    }
                });
            }
        }).start();
    }
}
