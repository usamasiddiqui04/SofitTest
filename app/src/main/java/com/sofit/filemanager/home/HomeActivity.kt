package com.sofit.filemanager.home


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.sofit.filemanager.R
import com.sofit.filemanager.favourite.FavouriteActivity
import com.sofit.filemanager.home.adapters.FileTypesAdapter
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        initActionBarToggel()

        view_pager.adapter =
            FileTypesAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(
            tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Photos"
                    1 -> tab.text = "Music"
                    2 -> tab.text = "Video"
                    3 -> tab.text = "Documents"
                }
            }
        )

        tabLayoutMediator.attach()
        navigation_view.setNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.fav) {
                val intent = Intent(this, FavouriteActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(
            item
        )
    }

    private fun initActionBarToggel() {
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.open,
            R.string.close
        )
        actionBarDrawerToggle.drawerArrowDrawable.color = Color.WHITE
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }


}