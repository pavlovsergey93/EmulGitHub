package com.gmail.pavlovsv93.emulgithub

import android.app.Application
import android.content.Context
import com.gmail.pavlovsv93.emulgithub.data.retrofit.RemoteDataSource
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface

class App : Application() {
	val repo: RepositoryInterface by lazy { RemoteDataSource() }
}

val Context.app: App
	get() = this.applicationContext as App