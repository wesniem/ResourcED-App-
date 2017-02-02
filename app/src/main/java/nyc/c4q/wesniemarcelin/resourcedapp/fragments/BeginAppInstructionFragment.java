package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

/**
 * Created by wesniemarcelin on 2/2/17.
 */
public class BeginAppInstructionFragment extends Fragment {
    View mroot;
    ImageView beginImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mroot = inflater.inflate(R.layout.begin_frag_layout,container, false);
        beginImage = (ImageView) mroot.findViewById(R.id.begin_button);
        beginImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.flContent, new HomeScreenFragment())
                        .commit();
            }
        });
        return mroot;
    }
}
