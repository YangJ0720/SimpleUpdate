package yangj.simpleupdate

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by YangJ on 2018/12/6.
 */
object ToastUtils {

    fun short(context: Context, @StringRes resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

    fun short(context: Context, text: CharSequence) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun long(context: Context, @StringRes resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }

    fun long(context: Context, text: CharSequence) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}