package yangj.simpleupdate

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author YangJ
 */
class MainActivity : AppCompatActivity() {

    private var mPermissions: RxPermissions? = null
    private var mDialogFragment: UpdateDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        mPermissions = RxPermissions(this)
    }

    private fun initView() {
        button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPermissions?.request(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )?.subscribe { b ->
                    if (b) {
                        showDialogFragment()
                    } else {
                        ToastUtils.short(this@MainActivity, "not permission")
                    }
                }
            } else {
                showDialogFragment()
            }
        }
    }

    /**
     * 显示升级对话框
     */
    private fun showDialogFragment() {
        if (mDialogFragment == null) {
            val url = "http://192.168.56.1:8080/app-debug.apk"
            val title = "版本升级"
            val content = "1.修复部分BUG\n2.优化性能"
            val md5 = "hello"
            mDialogFragment = UpdateDialogFragment.newInstance(url, title, content, md5)
        }
        mDialogFragment?.show(supportFragmentManager, UpdateDialogFragment.TAG)
    }

}