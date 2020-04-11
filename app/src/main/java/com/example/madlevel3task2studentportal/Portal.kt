package com.example.madlevel3task2studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    val title: String,
    val link: String
) : Parcelable