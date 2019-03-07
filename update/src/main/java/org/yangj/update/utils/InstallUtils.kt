package org.yangj.update.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import java.io.File

/**
 * @author YangJ
 * @date 2018/11/5
 */
object InstallUtils {

    /**
     * 安装APK文件
     */
    fun installApk(context: Context, path: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        var uri: Uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            FileProvider.getUriForFile(context, "${context.packageName}.fileProvider", File(path))
        } else {
            Uri.fromFile(File(path))
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        context.startActivity(intent)
    }

}