package com.sapan.webservices.models

import android.provider.ContactsContract.CommonDataKinds.Email
import org.json.JSONObject
import java.net.PasswordAuthentication

data class User (
    val username: String,
    val password: String
) {
    fun toJson(): String {
        return """{
            "username": "$username",
            "password" : "$password"
        }
        """.trimIndent()
    }

    companion object {
        fun fromJson(json: String) : User {
            val jsonObject: JSONObject = JSONObject(json)
            return User(
                username = jsonObject.getString("username"),
                password = jsonObject.getString("password")
            )
        }
    }
}