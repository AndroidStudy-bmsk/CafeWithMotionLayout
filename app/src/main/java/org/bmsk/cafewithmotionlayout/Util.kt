package org.bmsk.cafewithmotionlayout

import android.content.Context
import com.google.gson.Gson
import org.bmsk.cafewithmotionlayout.data.model.Home
import java.io.IOException

fun Context.readData(): Home? {
    return try {
        val inputStream = this.resources.assets.open("home.json")

        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        String(buffer)

        val gson = Gson()
        gson.fromJson(String(buffer), Home::class.java)
    } catch (e: IOException) {
        null
    }
}
