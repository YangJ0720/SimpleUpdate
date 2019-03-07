package org.yangj.update.utils

import java.io.File
import java.io.FileInputStream
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by YangJ on 2018/12/6.
 */
object MD5Utils {

    /**
     * 获取文件MD5
     */
    fun getMD5(path: String): String? {
        val md = MessageDigest.getInstance("MD5")
        var fis: FileInputStream? = null
        try {
            val file = File(path)
            if (!file.exists()) {
                return null
            }
            fis = FileInputStream(file)
            var buffer = ByteArray(8192)
            var len = 0
            while (true) {
                len = fis?.read(buffer)
                if (len == -1) {
                    break
                }
                md.update(buffer, 0, len)
            }
            return BigInteger(1, md.digest()).toString(16)
        } finally {
            fis?.close()
        }
        return null
    }
}