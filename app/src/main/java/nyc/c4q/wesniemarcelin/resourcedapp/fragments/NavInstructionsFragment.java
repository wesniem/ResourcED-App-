package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

public class NavInstructionsFragment extends Fragment {
    View mroot;
    Button nextButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mroot = inflater.inflate(R.layout.instruction_frag,container,false);
        nextButton = (Button) mroot.findViewById(R.id.next_button1);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragment = getFragmentManager();
                fragment.beginTransaction()
                        .add(R.id.flContent, new HomeInstructionFragment())
                        .commit();
            }
        });

        animations();
        return mroot;
    }

    private void animations() {
        Animation animation = AnimationUtils.loadAnimation(mroot.getContext(), R.anim.anim_move_up);
        ImageView imageView = (ImageView) mroot.findViewById(R.id.nav_image);
        imageView.setAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation2 = AnimationUtils.loadAnimation(mroot.getContext(), R.anim.anim_slide_in_left);
                ImageView imageView = (ImageView) mroot.findViewById(R.id.arrow);
                imageView.setAnimation(animation2);
            }
        }, 6000);
    }
}
