package nyc.c4q.wesniemarcelin.resourcedapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.google_map.MapsActivity_Hakeem;

/**
 * Created by wesniemarcelin on 2/2/17.
 */
public class HomeScreenFragment extends Fragment {
    View root;
    CardView daycareCard;
    CardView prekCard;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_screen_layout,container, false);
        daycareCard = (CardView) root.findViewById(R.id.daycare_card_view);
        prekCard = (CardView) root.findViewById(R.id.prek_card_view);
        daycareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), MapsActivity_Hakeem.class);
                startActivity(intent);
            }
        });

        prekCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), MapsActivity_Hakeem.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
