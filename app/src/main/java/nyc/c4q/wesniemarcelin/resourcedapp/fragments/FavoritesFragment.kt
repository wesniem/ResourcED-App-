package nyc.c4q.wesniemarcelin.resourcedapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import nyc.c4q.wesniemarcelin.resourcedapp.R

/**
 * Created by wesniemarcelin on 2/3/17.
 */
class FavoritesFragment : Fragment() {
    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.favorites_layout, container, false)
        return rootView
    }
}