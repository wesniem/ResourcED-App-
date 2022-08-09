package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import nyc.c4q.wesniemarcelin.resourcedapp.R
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import nyc.c4q.wesniemarcelin.resourcedapp.google_map.MapsActivity_Hakeem

/**
 * Created by wesniemarcelin on 2/2/17.
 */
class HomeScreenFragment : Fragment() {
    private lateinit var root: View
    private lateinit var daycareCard: CardView
    private lateinit var prekCard: CardView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.home_screen_layout, container, false)
        daycareCard = root.findViewById<View>(R.id.daycare_card_view) as CardView
        prekCard = root.findViewById<View>(R.id.prek_card_view) as CardView
        daycareCard.setOnClickListener {
            val intent = Intent(root.context, MapsActivity_Hakeem::class.java)
            startActivity(intent)
        }
        prekCard.setOnClickListener {
            val intent = Intent(root.context, MapsActivity_Hakeem::class.java)
            startActivity(intent)
        }
        return root
    }
}