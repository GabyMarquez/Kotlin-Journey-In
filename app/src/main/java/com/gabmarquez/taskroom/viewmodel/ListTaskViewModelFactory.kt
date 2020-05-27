package com.gabmarquez.taskroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

class ListTaskViewModelFactory @Inject constructor(private val mapViewModelModule: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val model = mapViewModelModule[modelClass] ?: mapViewModelModule.entries.firstOrNull {

            modelClass.isAssignableFrom(it.key)
        } ?. value ?: throw  IllegalArgumentException("Unknown Model Class")

        try {
            @Suppress("UNCHECKED_CAST")
            return model.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}