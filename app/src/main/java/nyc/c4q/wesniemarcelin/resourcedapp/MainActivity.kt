package nyc.c4q.wesniemarcelin.resourcedapp

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nyc.c4q.wesniemarcelin.resourcedapp.backend.ChildCareClient
import nyc.c4q.wesniemarcelin.resourcedapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    /*
     HAKEEM: adding a childcare client object to the main activity this will be where we store
      the data obtained after the retrofit call
    */
    private var childCareClient: ChildCareClient? = null
    var data: ArrayList<ArrayList<String>>? = null

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
//    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        childCareClient = ChildCareClient()
        data = childCareClient!!.data
        // Set a Toolbar to replace the ActionBar.
        setSupportActionBar(binding.toolbarLayout.toolbar)
        // Find our drawer view
        drawerLayout =
            findViewById<View>(R.id.drawer_layout) as DrawerLayout // ...From section above...
        // Find our drawer view
        navigationView = findViewById<View>(R.id.nvView) as NavigationView // Setup drawer view
        // Tie DrawerLayout events to the ActionBarToggle
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbarLayout.toolbar, 0, 0
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.nvView.setNavigationItemSelectedListener(this)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setupActionBarWithNavController(navController)
        binding.toolbarLayout.toolbar.setupWithNavController(navController, binding.drawerLayout)
        val headerLayout = navigationView.getHeaderView(0)
        val menu = navigationView.menu
    }

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

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}