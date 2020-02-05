package com.sofit.filemanager.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sofit.filemanager.home.FileType
import com.sofit.filemanager.home.FilesFragment


class FileTypesAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FilesFragment.newInstance(FileType.IMAGES)
            1 -> FilesFragment.newInstance(FileType.AUDIO)
            2 -> FilesFragment.newInstance(FileType.VIDEO)
            else -> FilesFragment.newInstance(FileType.PDF)
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}
