package org.yangj.update

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.support.annotation.Nullable
import android.text.TextUtils
import org.yangj.update.utils.HttpUtil
import org.yangj.update.utils.InstallUtils
import org.yangj.update.utils.MD5Utils

private const val EXTRA_PARAM_URL = "url"
private const val EXTRA_PARAM_PATH = "path"
private const val EXTRA_PARAM_MD5 = "md5"

private const val ACTION_DOWNLOAD = "download"
private const val ACTION_NOTIFICATION = "notification"

/**
 * @author YangJ
 */
class UpdateService : IntentService("UpdateService") {

    // private var mNotificationManager: NotificationManager? = null

    override fun onCreate() {
        super.onCreate()
        // initNotify()
        isRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra(EXTRA_PARAM_URL)
        val path = intent?.getStringExtra(EXTRA_PARAM_PATH)!!
        val md5 = intent?.getStringExtra(EXTRA_PARAM_MD5)
        if (!TextUtils.isEmpty(md5) && TextUtils.equals(md5, MD5Utils.getMD5(path))) {
            // 安装apk文件
            InstallUtils.installApk(this, path)
            return
        }
        if (HttpUtil.downloadFile(url, path)) {
            // 安装apk文件
            InstallUtils.installApk(this, path)
        } else {

        }
    }

//    private fun initNotify() {
//        mNotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//    }

    companion object {
        /**
         * 当前APK下载服务是否正在运行
         */
        var isRunning = false

        /**
         * 启动应用程序更新服务
         * @param context 参数为当前上下文对象
         * @param url     参数为apk文件下载链接
         * @param path    参数为apk文件下载路径
         */
        @JvmStatic
        fun startUpdateService(context: Context?, @Nullable url: String?, @Nullable path: String, md5: String?) {
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path)) {
                throw NullPointerException("url and path not null")
            }
            if (isRunning) {
                return
            }
            val intent = Intent(context, UpdateService::class.java).apply {
                action = ACTION_DOWNLOAD
                putExtra(EXTRA_PARAM_URL, url)
                putExtra(EXTRA_PARAM_PATH, path)
                putExtra(EXTRA_PARAM_MD5, md5)
            }
            context?.startService(intent)
        }

        /**
         * 打开通知消息
         */
        fun notification() {
            if (isRunning) {

            } else {

            }
        }

    }
}