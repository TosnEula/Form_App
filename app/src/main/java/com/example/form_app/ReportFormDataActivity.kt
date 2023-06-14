package com.example.form_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReportFormDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_form_data)



        val bundle : Bundle? = intent.extras
        val actTitle = bundle!!.getString("category") + " - " + bundle!!.getString("report")

        title = actTitle

    }
}