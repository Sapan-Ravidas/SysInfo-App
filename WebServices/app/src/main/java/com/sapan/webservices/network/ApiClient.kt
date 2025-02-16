package com.sapan.webservices.network

import com.sapan.webservices.BuildConfig
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ApiClient {
    /**
     * import java.net.HttpURLConnection
     * import java.net.URL
     *
     * class ApiClient {
     *     companion object {
     *         private const val CONNECT_TIMEOUT = 15000
     *         private const val READ_TIMEOUT = 10000
     *
     *         fun postRequest(endpoint: String, jsonBody: String): String {
     *             val url = URL(BuildConfig.BASE_URL + endpoint)
     *             val connection = url.openConnection() as HttpURLConnection
     *
     *             try {
     *                 connection.apply {
     *                     requestMethod = "POST"
     *                     connectTimeout = CONNECT_TIMEOUT
     *                     readTimeout = READ_TIMEOUT
     *                     doOutput = true
     *                     setRequestProperty("Content-Type", "application/json")
     *
     *                     outputStream.use { os ->
     *                         os.write(jsonBody.toByteArray())
     *                         os.flush()
     *                     }
     *
     *                     return inputStream.bufferedReader().use { it.readText() }
     *                 }
     *             } finally {
     *                 connection.disconnect()
     *             }
     *         }
     *     }
     * }
     */
    companion object {
        private const val CONNECTION_TIMEOUT = 15000
        private const val READ_TIMEOUT = 10000

        fun postRequest(endPoint: String, jsonBody: String) : String {
            val url = URL(BuildConfig.BASE_URL + endPoint)
            val connnection = url.openConnection() as HttpURLConnection
            try {
                connnection.apply {
                    requestMethod = "POST"
                    connectTimeout = CONNECTION_TIMEOUT
                    readTimeout = READ_TIMEOUT
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    setRequestProperty("Accept", "application/json")
                    OutputStreamWriter(outputStream).use { writer ->
                        writer.write(jsonBody)
                        writer.flush()
                    }

                    if (responseCode == HttpURLConnection.HTTP_CREATED) {
                        return inputStream.bufferedReader().use {
                            it.readText()
                        }
                    } else {

                        val errorMsg = errorStream.bufferedReader().use {it.readText()}
                        throw Exception("Http error: responseCode=$responseCode - $errorMsg")
                    }

                }
            } finally {
                connnection.disconnect()
            }
        }
    }
}