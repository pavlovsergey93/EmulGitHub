package com.gmail.pavlovsv93.emulgithub

import android.app.Application
import android.content.Context
import com.gmail.pavlovsv93.emulgithub.di.AppDiComponent
import com.gmail.pavlovsv93.emulgithub.di.DaggerAppDiComponent


class App : Application() {
	lateinit var appDiComponent: AppDiComponent
	override fun onCreate() {
		super.onCreate()
		appDiComponent = DaggerAppDiComponent.builder().build()
	}
}

val Context.app: App  get() = applicationContext as App