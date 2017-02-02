package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

/**
 * Created by wesniemarcelin on 1/30/17.
 */
public class HomeInstructionFragment extends Fragment {
    View mroot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mroot = inflater.inflate(R.layout.home_instructions, container, false);
        return mroot;
    }
}
