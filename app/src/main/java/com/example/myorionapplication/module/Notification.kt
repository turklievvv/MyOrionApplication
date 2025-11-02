package com.example.myorionapplication.module



data class Notification(
    val titleText: String,
    val text : String,
    val photo: Int,
    val data: String,
    var newNotification: Boolean
)