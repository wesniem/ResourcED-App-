package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import nyc.c4q.wesniemarcelin.resourcedapp.R
import android.content.Intent
import android.app.Activity
import android.content.Context
import android.provider.MediaStore
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import java.io.IOException
import java.util.ArrayList

/**
 * Created by wesniemarcelin on 2/4/17.
 */
class ProfileFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var spinner: Spinner
    private lateinit var address: String
    private lateinit var profilePic: ImageView
    private lateinit var submitButton: Button
    private lateinit var addressText: EditText
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var sharedPref: SharedPreferences
    var sharedPreferences: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.profile_layout, container, false)
        addressText = rootView.findViewById(R.id.address_text) as EditText
        submitButton = rootView.findViewById<View>(R.id.submit_button) as Button
        profilePic = rootView.findViewById<View>(R.id.avatar) as ImageView
        spinner = rootView.findViewById<View>(R.id.radius_spinner) as Spinner
        submitButton.setOnClickListener {
            saveAddress()
            Navigation.findNavController(it).navigate(R.id.homeScreenFragment);
        }
        profilePic.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }
        spinnerInitializer()
        return rootView
    }

    private fun saveAddress() {
        address = addressText.text.toString()
        Log.d("Wesnie", address)
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val radiusChosen: Int = spinner.selectedItem as Int
        Log.d("Wesnie2", radiusChosen.toString())
        editor.putInt("Radius", radiusChosen)
        editor.putString("Address", address)
        editor.apply()
    }

    private fun spinnerInitializer() {
        val spinnerList: MutableList<String> = ArrayList()
        spinnerList.add("0")
        spinnerList.add("1")
        spinnerList.add("5")
        spinnerList.add("10")
        spinnerList.add("20")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri = data.data
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)

//                        ImageView imageView = (ImageView) findViewById(R.id.placeholder);
//                sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt()
                profilePic.setImageBitmap(mBitmap)
                //                mPlaceHolder = (LinearLayout) findViewById(R.id.placeholder);
//                Drawable d = new BitmapDrawable(getResources(), mBitmap);
//                mPlaceHolder.setBackground(d);
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var mBitmap: Bitmap? = null
    }
}