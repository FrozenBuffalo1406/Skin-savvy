package com.capstone.skinsavvy.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.ui.ui.home.HomeViewModel
import com.capstone.skinsavvy.view.history.HistoryViewModel
import com.capstone.skinsavvy.view.product.ProductViewModel
import com.capstone.skinsavvy.view.scan.ScanViewModel


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        } else if (modelClass.isAssignableFrom(ScanViewModel::class.java)) {
            return ScanViewModel() as T
        } else if (modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            return HistoryViewModel() as T
        } else if (modelClass.isAssignableFrom((ProductViewModel::class.java))) {
            return ProductViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}