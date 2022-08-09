package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import nyc.c4q.wesniemarcelin.resourcedapp.R
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.HomeInstructionFragment

/**
 * Created by wesniemarcelin on 1/29/17.
 */
class NavInstructionsFragment : Fragment() {
    private lateinit var mroot: View
    var nextButton: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mroot = inflater.inflate(R.layout.instruction_frag, container, false)
        nextButton = mroot.findViewById<View>(R.id.next_button1) as Button
        nextButton!!.setOnClickListener {
            val fragment = fragmentManager
            fragment!!.beginTransaction()
                .add(R.id.flContent, HomeInstructionFragment())
                .commit()
        }
        animations()
        return mroot
    }

    private fun animations() {
        val animation = AnimationUtils.loadAnimation(
            mroot.context, R.anim.anim_move_up
        )
        val imageView = mroot.findViewById<View>(R.id.nav_image) as ImageView
        imageView.animation = animation
        val handler = Handler()
        handler.postDelayed({
            val animation2 = AnimationUtils.loadAnimation(
                mroot.context, R.anim.anim_slide_in_left
            )
            val imageView = mroot.findViewById<View>(R.id.arrow) as ImageView
            imageView.animation = animation2
        }, 6000)
    }
}