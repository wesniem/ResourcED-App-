package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import nyc.c4q.wesniemarcelin.resourcedapp.R
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.MapInstructionsFragment

/**
 * Created by wesniemarcelin on 1/30/17.
 */
class HomeInstructionFragment : Fragment() {
    private lateinit var mroot: View
    private lateinit var next: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mroot = inflater.inflate(R.layout.home_instructions, container, false)
        next = mroot.findViewById<View>(R.id.next_button3) as Button
        next.setOnClickListener {
            val fragmentManager = fragmentManager
            fragmentManager!!.beginTransaction()
                .add(R.id.flContent, MapInstructionsFragment())
                .commit()
        }
        return mroot
    }
}