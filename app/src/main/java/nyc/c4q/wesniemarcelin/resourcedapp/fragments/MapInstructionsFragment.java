package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

/**
 * Created by wesniemarcelin on 1/29/17.
 */

public class MapInstructionsFragment extends Fragment {
    View mroot;
    Button mapNextButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mroot = inflater.inflate(R.layout.map_instruction_layout,container,false);
        mapNextButton = (Button) mroot.findViewById(R.id.next_button2);
//        beginAnimation();


        return mroot;
    }

    private void beginAnimation() {
            Animation animation = AnimationUtils.loadAnimation(mroot.getContext(), R.anim.anim_hyperspace_jump);
            ImageView imageView = (ImageView) mroot.findViewById(R.id.google_gif);
            imageView.setAnimation(animation);

        }
}
