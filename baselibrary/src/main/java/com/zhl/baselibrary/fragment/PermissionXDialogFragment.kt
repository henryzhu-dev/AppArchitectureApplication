package com.zhl.baselibrary.fragment

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.permissionx.guolindev.dialog.RationaleDialogFragment
import com.zhl.baselibrary.R

/**
 *    author : zhuhl
 *    date   : 2021/7/22
 *    desc   : guolin's permissionx library custom dialog
 *    refer  :
 */

@TargetApi(30)
class PermissionXDialogFragment(private val mContext: Context, private val message: String, private val permissions: MutableList<String>) : RationaleDialogFragment() {

    private val permissionMap = mapOf(
        Manifest.permission.READ_CALENDAR to Manifest.permission_group.CALENDAR,
        Manifest.permission.WRITE_CALENDAR to Manifest.permission_group.CALENDAR,
        Manifest.permission.READ_CALL_LOG to Manifest.permission_group.CALL_LOG,
        Manifest.permission.WRITE_CALL_LOG to Manifest.permission_group.CALL_LOG,
        Manifest.permission.PROCESS_OUTGOING_CALLS to Manifest.permission_group.CALL_LOG,
        Manifest.permission.CAMERA to Manifest.permission_group.CAMERA,
        Manifest.permission.READ_CONTACTS to Manifest.permission_group.CONTACTS,
        Manifest.permission.WRITE_CONTACTS to Manifest.permission_group.CONTACTS,
        Manifest.permission.GET_ACCOUNTS to Manifest.permission_group.CONTACTS,
        Manifest.permission.ACCESS_FINE_LOCATION to Manifest.permission_group.LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION to Manifest.permission_group.LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION to Manifest.permission_group.LOCATION,
        Manifest.permission.RECORD_AUDIO to Manifest.permission_group.MICROPHONE,
        Manifest.permission.READ_PHONE_STATE to Manifest.permission_group.PHONE,
        Manifest.permission.READ_PHONE_NUMBERS to Manifest.permission_group.PHONE,
        Manifest.permission.CALL_PHONE to Manifest.permission_group.PHONE,
        Manifest.permission.ANSWER_PHONE_CALLS to Manifest.permission_group.PHONE,
        Manifest.permission.ADD_VOICEMAIL to Manifest.permission_group.PHONE,
        Manifest.permission.USE_SIP to Manifest.permission_group.PHONE,
        Manifest.permission.ACCEPT_HANDOVER to Manifest.permission_group.PHONE,
        Manifest.permission.BODY_SENSORS to Manifest.permission_group.SENSORS,
        Manifest.permission.ACTIVITY_RECOGNITION to Manifest.permission_group.ACTIVITY_RECOGNITION,
        Manifest.permission.SEND_SMS to Manifest.permission_group.SMS,
        Manifest.permission.RECEIVE_SMS to Manifest.permission_group.SMS,
        Manifest.permission.READ_SMS to Manifest.permission_group.SMS,
        Manifest.permission.RECEIVE_WAP_PUSH to Manifest.permission_group.SMS,
        Manifest.permission.RECEIVE_MMS to Manifest.permission_group.SMS,
        Manifest.permission.READ_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION to Manifest.permission_group.STORAGE
    )

    private val groupSet = HashSet<String>()

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.permissionx_tip_dialog, container, false)
        initView()
//        setStyle(R.style.commonDialogStyle)
        return rootView
    }

    private fun initView() {
        rootView.findViewById<TextView>(R.id.messageText).text = message
        buildPermissionsLayout(rootView.findViewById(R.id.permissionsLayout))
    }

    private fun buildPermissionsLayout(permissionsLayout: LinearLayout) {
        for (permission in permissions) {
            val permissionGroup = permissionMap[permission]
            if (permissionGroup != null && !groupSet.contains(permissionGroup)) {
                val textView = LayoutInflater.from(mContext).inflate(R.layout.permissionx_show_item, permissionsLayout, false) as TextView
                textView.text = mContext.packageManager.getPermissionGroupInfo(permissionGroup, 0).loadLabel(mContext.packageManager)
                permissionsLayout.addView(textView)
                groupSet.add(permissionGroup)
            }
        }
    }

    override fun getNegativeButton(): View? {
        return rootView.findViewById<Button>(R.id.negativeBtn)
    }

    override fun getPositiveButton(): View {
        return rootView.findViewById<Button>(R.id.positiveBtn)
    }

    override fun getPermissionsToRequest(): MutableList<String> {
        return permissions
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setBackgroundDrawableResource(R.drawable.common_dialog_bg)
    }

}