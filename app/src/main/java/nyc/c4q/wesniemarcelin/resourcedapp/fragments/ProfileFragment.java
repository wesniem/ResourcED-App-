package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    String address;
    Button submitButton;
    EditText addressText;
    SharedPreferences sharedPref;
    SharedPreferences sharedPreferences;


    public EditText getAddressText() {
        return addressText;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profile_layout, container, false);
        addressText = (EditText) rootView.findViewById(R.id.address_text);
        submitButton = (Button) rootView.findViewById(R.id.submit_button);
        spinner = (Spinner) rootView.findViewById(R.id.radius_spinner);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
        spinnerInitializer();
        return rootView;
    }

    private void saveAddress() {
        address = (String) addressText.getText().toString();
        Log.d("Wesnie", address);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int radiusChosen = Integer.parseInt((String) spinner.getSelectedItem());
        Log.d("Wesnie2", String.valueOf(radiusChosen));
        editor.putInt("Radius", radiusChosen);
        editor.putString("Address", address);
        editor.apply();
    }

    private void spinnerInitializer() {

        List<String> spinnerList = new ArrayList<>();
        spinnerList.add("0");
        spinnerList.add("1");
        spinnerList.add("5");
        spinnerList.add("10");
        spinnerList.add("20");

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spinnerList);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    public void onItemSelected(AdapterView<?> parent, View view,
//                               int pos, long id) {
//        // An item was selected. You can retrieve the selected item using
//        // parent.getItemAtPosition(pos)
//
//        int radiusChosen = (int) parent.getItemAtPosition(pos);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Log.d("Wesnie2", String.valueOf(radiusChosen));
//        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
//        editor.putInt("Radius", radiusChosen);
//        editor.apply();
//    }
//
//    public void onNothingSelected(AdapterView<?> parent) {
//        // Another interface callback
//    }
}
