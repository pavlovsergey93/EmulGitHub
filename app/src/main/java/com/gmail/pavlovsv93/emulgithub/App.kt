package com.gmail.pavlovsv93.emulgithub

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.emulgithub.data.MockRepos
import com.gmail.pavlovsv93.emulgithub.data.retrofit.RemoteDataSource
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.BaseViewModel
import com.gmail.pavlovsv93.emulgithub.utils.ViewModelStateStore

class App : Application() {
	//val repo: RepositoryInterface by lazy { RemoteDataSource() }
	val repo: RepositoryInterface by lazy { MockRepos() }
	val viewModelStore by lazy { ViewModelStateStore<BaseViewModel>() }
}

val Context.app: App
	get() = this.applicationContext as App