package nyc.c4q.wesniemarcelin.resourcedapp

import android.Manifest
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import nyc.c4q.wesniemarcelin.resourcedapp.backend.ChildCareClient
import androidx.appcompat.app.ActionBarDrawerToggle
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.WelcomeFragment
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.HomeScreenFragment
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.ProfileFragment
import nyc.c4q.wesniemarcelin.resourcedapp.fragments.FavoritesFragment
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var nvDrawer: NavigationView

    /*
     HAKEEM: adding a childcare client object to the main activity this will be where we store
      the data obtained after the retrofit call
    */
    private var childCareClient: ChildCareClient? = null
    var data: ArrayList<ArrayList<String>>? = null

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        childCareClient = ChildCareClient()
        data = childCareClient!!.data
        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        // Find our drawer view
        mDrawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout // ...From section above...
        // Find our drawer view
        nvDrawer = findViewById<View>(R.id.nvView) as NavigationView // Setup drawer view
        setupDrawerContent(nvDrawer)
        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.setDrawerListener(drawerToggle)
        drawerToggle = setupDrawerToggle()
        val headerLayout = nvDrawer.getHeaderView(0)
        val menu = nvDrawer.menu
        //        MenuItem menuItem = menu.findItem(R.id.nav_switch);
//        View actionView = MenuItemCompat.getActionView(menuItem);
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        fragmentManager = supportFragmentManager
        fragmentManager!!.beginTransaction()
            .add(R.id.flContent, WelcomeFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView?) {
        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        val fragment: Fragment? = null
        val fragmentClass: Class<*>? = null
        when (menuItem.itemId) {
            R.id.nav_first_fragment -> if (fragmentManager!!.findFragmentByTag("home_fragment") == null) fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, HomeScreenFragment(), "home_fragment")
                .commit() else fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("home_fragment")!!)
                .commit()
            R.id.nav_second_fragment -> if (fragmentManager!!.findFragmentByTag("profile_fragment") == null) fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, ProfileFragment(), "profile_fragment")
                .commit() else fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("profile_fragment")!!)
                .commit()
            R.id.nav_third_fragment -> if (fragmentManager!!.findFragmentByTag("favorites_fragment") == null) fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, FavoritesFragment(), "favorites_fragment")
                .commit() else fragmentManager!!.beginTransaction().replace(
                R.id.flContent,
                fragmentManager!!.findFragmentByTag("favorites_fragment")!!
            ).commit()
            else -> if (fragmentManager!!.findFragmentByTag("home_fragment") == null) fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, HomeScreenFragment(), "home_fragment")
                .commit() else fragmentManager!!.beginTransaction()
                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("home_fragment")!!)
                .commit()
        }
        //
        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        mDrawer.closeDrawers()
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(
            this,
            mDrawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle!!.onConfigurationChanged(newConfig)
    }
}