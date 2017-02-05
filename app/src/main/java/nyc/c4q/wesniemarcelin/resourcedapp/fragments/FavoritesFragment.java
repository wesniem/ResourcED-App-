package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

/**
 * Created by wesniemarcelin on 2/3/17.
 */

public class FavoritesFragment extends Fragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.favorites_layout, container, false);
        return rootView;
    }
}
