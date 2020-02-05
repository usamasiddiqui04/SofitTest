package com.sofit.filemanager.home.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.sofit.filemanager.R
import com.sofit.filemanager.helpers.PreferencesHelper
import com.sofit.filemanager.home.FileItem
import com.sofit.filemanager.home.FileType
import kotlinx.android.synthetic.main.item_file.view.*
import java.util.*


class FilesAdapter(
    private val files: ArrayList<FileItem>,
    private val preferencesHelper: PreferencesHelper
) :
    RecyclerView.Adapter<FilesAdapter.FileItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileItemHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileItemHolder(v)
    }

    override fun onBindViewHolder(holder: FileItemHolder, position: Int) {
        val fileItem = files[position]
        holder.bind(fileItem)


    }

    override fun getItemCount(): Int {
        return files.size
    }


    inner class FileItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(fileItem: FileItem) {

            when (fileItem.fileType) {
                FileType.IMAGES -> itemView.image_view.setImageURI(Uri.parse(fileItem.path))
                FileType.AUDIO -> itemView.image_view.setImageResource(R.drawable.audio_file)
                FileType.VIDEO -> itemView.image_view.setImageResource(R.drawable.video)
                FileType.PDF -> itemView.image_view.setImageResource(R.drawable.pdf)

            }
            itemView.text_display_name.text = fileItem.displayName
            val fileIdWithType = fileItem.fileType.toString() + "_" + fileItem.id.toString()
            itemView.checkbox.setOnCheckedChangeListener(null)
            itemView.checkbox.isChecked =
                preferencesHelper.isCheckedByFileId(fileId = fileIdWithType)
            itemView.checkbox.setOnCheckedChangeListener { _, isChecked ->
                Log.d("FilesAdapter", "isChecked: $isChecked")
                preferencesHelper.setCheckedByFileId(
                    fileId = fileIdWithType,
                    isChecked = isChecked
                )
            }


        }
    }


}
