package nyc.c4q.wesniemarcelin.resourcedapp;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by wesniemarcelin on 2/4/17.
 */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
