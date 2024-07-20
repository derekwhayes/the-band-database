package com.zybooks.thebanddatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;


public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        LinearLayout layout = (LinearLayout) rootView;

        List<Band> bandList = BandRepository.getInstance(requireContext()).getBands();
        for (Band band : bandList) {
            Button button = new Button(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,0,10);
            button.setLayoutParams(layoutParams);

            button.setText(band.getName());
            button.setTag(band.getId());

            button.setOnClickListener(buttonView -> {
                int selectedBandId = (int) buttonView.getTag();
                Bundle args = new Bundle();
                args.putInt(DetailFragment.ARG_BAND_ID, selectedBandId);

                Navigation.findNavController(buttonView).navigate(R.id.show_item_detail, args);
            });

            layout.addView(button);
        }

        return rootView;
    }
}