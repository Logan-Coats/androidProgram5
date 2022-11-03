package com.example.program5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottleCounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottleCounterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityViewModel mainActivityViewModel;
    public BottleCounterFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivityViewModel = new ViewModelProvider((MainActivity)context).get(MainActivityViewModel.class);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottleCounterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottleCounterFragment newInstance(String param1, String param2) {
        BottleCounterFragment fragment = new BottleCounterFragment();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottle_counter, container, false);

        Button addBtn = v.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call inc method
                boolean inc = mainActivityViewModel.incCurrent();
                if(!inc){
                    Toast.makeText(getActivity(), "Max capacity reached!",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button removeBtn= v.findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call dec method
                boolean dec = mainActivityViewModel.decCurrent();
                if(!dec){
                    Log.d("TAG",getActivity().getLocalClassName());

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Min Capacity Reached!", Toast.LENGTH_SHORT).show();
                        }
                    });
                   //Toast.makeText(getActivity().getApplicationContext(), "Min capacity reached!",Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}