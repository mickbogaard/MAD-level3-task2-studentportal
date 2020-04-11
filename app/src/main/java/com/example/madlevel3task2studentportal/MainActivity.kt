package com.example.madlevel3task2studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_portal.*

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    val builder = CustomTabsIntent.Builder()

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portalItemClicked(it)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener{
            startCreatePortalActivity()
        }

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startCreatePortalActivity() {
        val intent = Intent(this, CreatePortal::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        rvPortals.adapter = portalAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data?.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portal?.let { addPortal(it) }
                }
            }
        }

    }

    private fun addPortal(portal: Portal) {
        portals.add(portal)
        portalAdapter.notifyDataSetChanged()
    }

    private fun portalItemClicked(portal: Portal) {
        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(this, Uri.parse(portal.link))
    }


}
