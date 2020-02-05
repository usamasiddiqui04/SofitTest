package com.sofit.filemanager.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
enum class FileType : Parcelable { IMAGES, AUDIO, VIDEO, PDF }