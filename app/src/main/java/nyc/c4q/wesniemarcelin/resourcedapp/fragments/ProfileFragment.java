package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.SpinnerActivity;

/**
 * Created by wesniemarcelin on 2/4/17.
 */

public class ProfileFragment extends Fragment {
    View rootView;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profile_layout, container, false);
        spinnerInitializer();
        return rootView;
    }

    private void spinnerInitializer() {

        List<String> spinnerList = new ArrayList<>();
        spinnerList.add("1");
        spinnerList.add("5");
        spinnerList.add("10");
        spinnerList.add("20");

        spinner = (Spinner) rootView.findViewById(R.id.radius_spinner);

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spinnerList);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
    }
}
