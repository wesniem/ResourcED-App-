package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import nyc.c4q.wesniemarcelin.resourcedapp.R

/**
 * Created by wesniemarcelin on 1/29/17.
 */
class MapInstructionsFragment : Fragment() {
    private lateinit var mroot: View
    var mapNextButton: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mroot = inflater.inflate(R.layout.map_instruction_layout, container, false)
        mapNextButton = mroot.findViewById<View>(R.id.next_button2) as Button
        mapNextButton!!.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.beginAppInstructionsFragment);
        }
        //        beginAnimation();
        return mroot
    }

    private fun beginAnimation() {
        val animation = AnimationUtils.loadAnimation(
            mroot.context, R.anim.anim_hyperspace_jump
        )
        val imageView = mroot.findViewById<View>(R.id.google_gif) as ImageView
        imageView.animation = animation
    }
}