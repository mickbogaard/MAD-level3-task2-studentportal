package com.example.madlevel3task2studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"


class CreatePortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)


        btnAddPortal.setOnClickListener{
            onAddPortalClick()
        }
    }

    private fun onAddPortalClick(){
        if(etTitle.text.toString().isNotBlank() && etUrl.text.toString().isNotBlank()){
            if (URLUtil.isValidUrl(etUrl.text.toString()))
            {
                val portal = Portal(etTitle.text.toString(), etUrl.text.toString())
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_PORTAL, portal)

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Snackbar.make(etUrl, R.string.invalid_link_error, Snackbar.LENGTH_SHORT).show()
            }
        } else{
            Snackbar.make(etUrl, getString(R.string.fill_all_fields_error), Snackbar.LENGTH_SHORT).show()
        }
    }
}
