package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wesniemarcelin on 2/4/17.
 */

public class ProfileFragment extends Fragment {
    View rootView;
    Spinner spinner;
    String address;
    ImageView profilePic;
    Button submitButton;
    EditText addressText;
    private static Bitmap mBitmap;
    private int PICK_IMAGE_REQUEST = 1;

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
        profilePic = (ImageView) rootView.findViewById(R.id.avatar);
        spinner = (Spinner) rootView.findViewById(R.id.radius_spinner);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.flContent, new HomeScreenFragment())
                        .commit();

            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        spinnerInitializer();
        return rootView;
    }

    private void saveAddress() {
        address = addressText.getText().toString();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

//                        ImageView imageView = (ImageView) findViewById(R.id.placeholder);
//                sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt()
                profilePic.setImageBitmap(mBitmap);
//                mPlaceHolder = (LinearLayout) findViewById(R.id.placeholder);
//                Drawable d = new BitmapDrawable(getResources(), mBitmap);
//                mPlaceHolder.setBackground(d);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
