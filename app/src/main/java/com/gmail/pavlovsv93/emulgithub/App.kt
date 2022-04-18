package com.gmail.pavlovsv93.emulgithub

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.emulgithub.data.InMemoryRepository
import com.gmail.pavlovsv93.emulgithub.domain.InMemoryRepositoryInterface

class App : AppCompatActivity() {
	val repo: InMemoryRepositoryInterface by lazy { InMemoryRepository() }
}

val Context.app: App get() = this.applicationContext as App