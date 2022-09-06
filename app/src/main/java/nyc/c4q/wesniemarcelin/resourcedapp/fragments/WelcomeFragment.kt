package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import nyc.c4q.wesniemarcelin.resourcedapp.R
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.NavInstructionsFragment

/**
 * Created by wesniemarcelin on 1/29/17.
 */
class WelcomeFragment : Fragment() {
    private lateinit var mroot: View
    private var nextButton: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mroot = inflater.inflate(R.layout.welcome_layout, container, false)
        nextButton = mroot.findViewById<View>(R.id.next_button_) as Button
        nextButton!!.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.navigationInstructionsFragment);
        }
        return mroot
    }
}