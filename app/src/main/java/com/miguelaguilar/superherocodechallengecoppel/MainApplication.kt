package com.miguelaguilar.superherocodechallengecoppel

import android.app.Application
import android.util.Log
import com.orhanobut.hawk.BuildConfig
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application(){
    companion object{
        var offsetQuery : Int = 0
    }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Hawk.init(this).build()
    }

    fun getOffsetQueryNumber() : Int {
        return offsetQuery
    }

    fun changeOffsetQueryNumber() {
        offsetQuery += 50
    }
}