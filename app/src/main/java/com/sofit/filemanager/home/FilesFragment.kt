package com.sofit.filemanager.home

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sofit.filemanager.BuildConfig
import com.sofit.filemanager.R
import com.sofit.filemanager.helpers.ContentProviderHelper
import com.sofit.filemanager.helpers.PreferencesHelper
import com.sofit.filemanager.home.adapters.FilesAdapter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.view_recylcer.*


class FilesFragment : Fragment() {


    private val fileType by lazy { arguments?.getParcelable<FileType>(KEY_FILE_TYPE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.view_recylcer, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val sharedPreferences =
            requireContext().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        val preferencesHelper = PreferencesHelper(sharedPreferences)


        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager
        recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        val audioFiles = ContentProviderHelper.getFilesByType(requireContext(), fileType!!)
        val imagesAdapter =
            FilesAdapter(audioFiles, preferencesHelper)
        recycler.adapter = imagesAdapter
    }


    companion object {
        private const val KEY_FILE_TYPE = "key_file_type"

        fun newInstance(fileType: FileType): FilesFragment {
            val fragment = FilesFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_FILE_TYPE, fileType)
            fragment.arguments = bundle
            return fragment;

        }
    }


}

