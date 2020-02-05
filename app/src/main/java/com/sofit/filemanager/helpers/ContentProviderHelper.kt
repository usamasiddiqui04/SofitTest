package com.sofit.filemanager.helpers

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.sofit.filemanager.home.FileItem
import com.sofit.filemanager.home.FileType
import java.util.*


object ContentProviderHelper {

    private val filesProjection = arrayOf(
        MediaStore.MediaColumns._ID,
        MediaStore.MediaColumns.DATA
    )

    fun getFilesByType(context: Context, fileType: FileType): ArrayList<FileItem> {

        return when (fileType) {
            FileType.IMAGES -> getImages(context)
            FileType.AUDIO -> getAudios(context)
            FileType.VIDEO -> getVideos(context)
            FileType.PDF -> getDocuments(context)
        }
    }

    private fun getImages(context: Context): ArrayList<FileItem> {

        val listOfAllImages =
            ArrayList<FileItem>()
        listOfAllImages.addAll(
            getFilesFromUri(
                context,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                FileType.IMAGES
            )
        )
        return listOfAllImages
    }

    private fun getAudios(context: Context): ArrayList<FileItem> {

        val listOfAllImages =
            ArrayList<FileItem>()
        listOfAllImages.addAll(
            getFilesFromUri(
                context,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                FileType.AUDIO
            )
        )
        return listOfAllImages
    }

    private fun getVideos(context: Context): ArrayList<FileItem> {

        val listOfAllImages =
            ArrayList<FileItem>()
        listOfAllImages.addAll(
            getFilesFromUri(
                context,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                FileType.VIDEO
            )
        )
        return listOfAllImages
    }

    private fun getDocuments(context: Context): ArrayList<FileItem> {
        val pdfExt = "_data LIKE '%.pdf'"
        val documentsUri = MediaStore.Files.getContentUri("external")
        val listOfAllImages =
            ArrayList<FileItem>()
        listOfAllImages.addAll(
            getFilesFromUri(
                context,
                documentsUri,
                pdfExt,
                FileType.PDF
            )
        )
        return listOfAllImages
    }


    private fun getFilesFromUri(
        context: Context,
        uri: Uri,
        selection: String?,
        fileType: FileType
    ): ArrayList<FileItem> {

        val columnIndexId: Int
        val columnIndexData: Int
        val listAllFiles = ArrayList<FileItem>()


        val cursor = context.contentResolver.query(uri, filesProjection, selection, null, null)
        if (cursor != null) {
            columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)

            columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(columnIndexId)
                val filePath = cursor.getString(columnIndexData)
                val fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                listAllFiles.add(FileItem(id, fileName, filePath, fileType))
            }
            cursor.close()
        }
        return listAllFiles
    }


}