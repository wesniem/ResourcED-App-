package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import nyc.c4q.wesniemarcelin.resourcedapp.R
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.HomeScreenFragment

/**
 * Created by wesniemarcelin on 2/2/17.
 */
class BeginAppInstructionFragment : Fragment() {
    private lateinit var mroot: View
    private lateinit var beginImage: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mroot = inflater.inflate(R.layout.begin_frag_layout, container, false)
        beginImage = mroot.findViewById<View>(R.id.begin_button) as ImageView
        beginImage.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homeScreenFragment);
        }
        return mroot
    }
}