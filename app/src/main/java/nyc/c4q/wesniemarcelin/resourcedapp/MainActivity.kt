package nyc.c4q.wesniemarcelin.resourcedapp

import android.Manifest
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nyc.c4q.wesniemarcelin.resourcedapp.backend.ChildCareClient


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController

    /*
     HAKEEM: adding a childcare client object to the main activity this will be where we store
      the data obtained after the retrofit call
    */
    private var childCareClient: ChildCareClient? = null
    var data: ArrayList<ArrayList<String>>? = null

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
//    private var drawerToggle: ActionBarDrawerToggle? = null
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
        drawerLayout =
            findViewById<View>(R.id.drawer_layout) as DrawerLayout // ...From section above...
        // Find our drawer view
        navigationView = findViewById<View>(R.id.nvView) as NavigationView // Setup drawer view
//        setupDrawerContent(nvDrawer)
        // Tie DrawerLayout events to the ActionBarToggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
//        drawerLayout.setDrawerListener(drawerToggle)
//        drawerToggle = setupDrawerToggle()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        toolbar.setupWithNavController(navController, drawerLayout)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        val config = AppBarConfiguration.Builder(
//            navController.graph
//        ).setDrawerLayout(
//            drawerLayout
//        ).build()
//        NavigationUI.setupWithNavController(toolbar, navController,config)
        val headerLayout = navigationView.getHeaderView(0)
        val menu = navigationView.menu
        //        MenuItem menuItem = menu.findItem(R.id.nav_switch);
//        View actionView = MenuItemCompat.getActionView(menuItem);
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        fragmentManager = supportFragmentManager
//        fragmentManager!!.beginTransaction()
//            .add(R.id.flContent, WelcomeFragment())
//            .commit()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (drawerToggle!!.onOptionsItemSelected(item)) {
//            true
//        } else super.onOptionsItemSelected(item)
//    }

//    private fun setupDrawerContent(navigationView: NavigationView?) {
//        navigationView!!.setNavigationItemSelectedListener { menuItem ->
//            selectDrawerItem(menuItem)
//            true
//        }
//    }

//    private fun selectDrawerItem(menuItem: MenuItem) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
//        val fragment: Fragment? = null
//        val fragmentClass: Class<*>? = null
//        when (menuItem.itemId) {
//            R.id.nav_first_fragment -> if (fragmentManager!!.findFragmentByTag("home_fragment") == null) fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, HomeScreenFragment(), "home_fragment")
//                .commit() else fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("home_fragment")!!)
//                .commit()
//            R.id.nav_second_fragment -> if (fragmentManager!!.findFragmentByTag("profile_fragment") == null) fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, ProfileFragment(), "profile_fragment")
//                .commit() else fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("profile_fragment")!!)
//                .commit()
//            R.id.nav_third_fragment -> if (fragmentManager!!.findFragmentByTag("favorites_fragment") == null) fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, FavoritesFragment(), "favorites_fragment")
//                .commit() else fragmentManager!!.beginTransaction().replace(
//                R.id.flContent,
//                fragmentManager!!.findFragmentByTag("favorites_fragment")!!
//            ).commit()
//            else -> if (fragmentManager!!.findFragmentByTag("home_fragment") == null) fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, HomeScreenFragment(), "home_fragment")
//                .commit() else fragmentManager!!.beginTransaction()
//                .replace(R.id.flContent, fragmentManager!!.findFragmentByTag("home_fragment")!!)
//                .commit()
//        }
//        //
//        // Highlight the selected item has been done by NavigationView
//        menuItem.isChecked = true
//        // Set action bar title
//        title = menuItem.title
//        // Close the navigation drawer
//        mDrawer.closeDrawers()
//    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        drawerToggle!!.syncState()
//    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        // Pass any configuration change to the drawer toggles
//        drawerToggle!!.onConfigurationChanged(newConfig)
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_first_fragment -> {
                navController.navigate(R.id.homeScreenFragment)
            }
            R.id.nav_second_fragment -> {
                navController.navigate(R.id.profileFragment)
            }
            R.id.nav_third_fragment -> {
                navController.navigate(R.id.favoritesFragment)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}