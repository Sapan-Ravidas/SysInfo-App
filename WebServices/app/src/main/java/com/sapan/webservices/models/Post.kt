package com.sapan.webservices.models

import org.json.JSONObject

data class Post(
    val id: String,
    val username: String,
    val content: String,
    val timestamp: Long
) {
    fun toJson() : String {
        return """{
            "username" : "$username",
            "content" : "$content",
            "timeStamp" : "$timestamp"
        """.trimIndent()
    }

    companion object {
        fun fromJson(json: String) : Post {
            val jsonObject = JSONObject(json)
            return Post(
                id = jsonObject.getString("id"),
                username = jsonObject.getString("username"),
                content = jsonObject.getString("content"),
                timestamp = jsonObject.getLong("timestamp")
            )
        }
    }
}