package com.sofit.filemanager.helpers

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesHelper constructor(val prefs: SharedPreferences) {


    fun isCheckedByFileId(fileId: String): Boolean {
        return prefs.getBoolean(fileId, false)
    }

    fun setCheckedByFileId(fileId: String, isChecked: Boolean) {
        prefs.edit {
            putBoolean(fileId, isChecked)
        }
    }
}