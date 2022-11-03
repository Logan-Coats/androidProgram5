package com.example.program5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private MainActivityViewModel mainActivityViewModel;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivityViewModel = new ViewModelProvider((MainActivity)context).get(MainActivityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        Button changeMax = v.findViewById(R.id.maxChangeBtn);
        changeMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText maxET = v.findViewById(R.id.maxET);
                if(!maxET.getText().toString().equals("")){
                    mainActivityViewModel.changeMax(Integer.parseInt(maxET.getText().toString()));
                }

            }
        });

        Button changeMin = v.findViewById(R.id.minChangeBtn);
        changeMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText minET = v.findViewById(R.id.minET);
                if(!minET.getText().toString().equals("")){
                    mainActivityViewModel.changeMin(Integer.parseInt(minET.getText().toString()));
                }
            }
        });
        return v;
    }
}