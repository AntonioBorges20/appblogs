package com.example.appblog.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.appblog.core.Resource
import com.example.appblog.domain.camera.CameraRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class CameraViewModel(private val repo: CameraRepo): ViewModel() {

    fun uploadPhoto(imageBitmap: Bitmap, description: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.uploadPhoto(imageBitmap,description)))
        }catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class CameraViewModelFactory(private val repo: CameraRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CameraRepo::class.java).newInstance(repo)
    }
}