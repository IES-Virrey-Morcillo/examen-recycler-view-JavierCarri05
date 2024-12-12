package com.iesvirreymorcillo.rvmontes.FuncionJSON

import android.content.Context
import java.io.InputStream
import java.nio.charset.Charset

fun getJsonFromAssets(context: Context, file: String): String? {
    var json=""
    val stream: InputStream =context.assets.open(file)
    val size: Int = stream.available()
    val buffer = ByteArray(size)
    stream.read(buffer)
    stream.close()

    json = String(buffer, Charset.defaultCharset())
    return json
}