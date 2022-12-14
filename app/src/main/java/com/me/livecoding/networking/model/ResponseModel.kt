package com.me.livecoding.networking.model

data class ResponseModel (
    val categories: List<Object>,
    val created_at: String,
    val icon_url: String,
    val id: String,
    val updated_at: String,
    val url: String,
    val value: String,
)