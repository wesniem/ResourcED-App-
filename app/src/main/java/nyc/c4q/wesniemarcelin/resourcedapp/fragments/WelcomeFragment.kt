package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

/**
 * Created by wesniemarcelin on 1/29/17.
 */

public class WelcomeFragment extends Fragment {
    View mroot;
    Button nextButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mroot=inflater.inflate(R.layout.welcome_layout,container,false);
        nextButton = (Button) mroot.findViewById(R.id.next_button_);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.flContent, new NavInstructionsFragment())
                        .commit();
            }
        });
        return mroot;
    }
}
