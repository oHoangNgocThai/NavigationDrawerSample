package code.android.ngocthai.navigationdrawersample

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import code.android.ngocthai.navigationdrawersample.custom.CustomActivity
import code.android.ngocthai.navigationdrawersample.navcomponent.NavComponentActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        buttonWithNavigation.setOnClickListener {
            startActivity(Intent(this, NavComponentActivity::class.java))
        }

        buttonCustom.setOnClickListener {
            startActivity(Intent(this, CustomActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.homeFragment -> {
            }
            R.id.favoriteFragment -> {
            }
            R.id.searchFragment -> {
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
