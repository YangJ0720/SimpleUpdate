package yangj.simpleupdate

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.yangj.update.UpdateService

private const val ARG_PARAM_URL = "url"
private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_CONTENT = "content"
private const val ARG_PARAM_MD5 = "md5"

/**
 * @author YangJ
 * @date 2018/11/5
 */
class UpdateDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "UpdateDialogFragment"
        @JvmStatic
        fun newInstance(url: String, title: String, content: String, md5: String) = UpdateDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_URL, url)
                putString(ARG_PARAM_TITLE, title)
                putString(ARG_PARAM_CONTENT, content)
                putString(ARG_PARAM_MD5, md5)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_MinWidth)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_update, container, false)
        view.findViewById<TextView>(R.id.tvTitle).text = arguments?.getString(ARG_PARAM_TITLE)
        view.findViewById<TextView>(R.id.tvContent).text = arguments?.getString(ARG_PARAM_CONTENT)
        view.findViewById<View>(R.id.button).setOnClickListener { start() }
        return view
    }

    fun start() {
        val url = arguments?.getString(ARG_PARAM_URL)
        val path = context?.externalCacheDir?.absolutePath + "/app-debug.apk"
        val md5 = arguments?.getString(ARG_PARAM_MD5)
        UpdateService.startUpdateService(context, url, path, md5)
    }

}