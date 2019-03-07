package org.yangj.update.utils

import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author YangJ
 * @date 2018/11/5
 */
object HttpUtil {

    private const val READ_TIMEOUT = 30000
    private const val CONNECT_TIMEOUT = 30000

    /**
     * 下载文件
     */
    fun downloadFile(url: String?, path: String?): Boolean {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            connection = URL(url).openConnection() as HttpURLConnection
            connection.readTimeout = READ_TIMEOUT
            connection.connectTimeout = CONNECT_TIMEOUT
            if (200 == connection.responseCode) {
                inputStream = connection.inputStream
                fos = FileOutputStream(path)
                var len: Int
                val buffer = ByteArray(1024)
                while (true) {
                    len = inputStream?.read(buffer)!!
                    if (len == -1) break
                    fos.write(buffer, 0, len)
                }
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            connection?.disconnect()
        }
        return false
    }

}
