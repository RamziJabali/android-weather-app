package com.example.androidweatherapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

class LoadingDialog(var activity: Activity) {

    private lateinit var dialog: AlertDialog

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)

        var inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_screen, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }

}