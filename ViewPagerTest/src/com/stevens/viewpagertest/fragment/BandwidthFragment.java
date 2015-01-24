
package com.stevens.viewpagertest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.stevens.viewpagertest.FragmentController;
import com.stevens.viewpagertest.R;

public class BandwidthFragment extends Fragment {
    private static final String TAG = "BandwidthFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bandwidth_layout, null);
        Button bt = (Button) view.findViewById(R.id.bandwidth);
        bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ((FragmentController) BandwidthFragment.this.getActivity()).changeFragment();

            }

        });
        return view;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

}
