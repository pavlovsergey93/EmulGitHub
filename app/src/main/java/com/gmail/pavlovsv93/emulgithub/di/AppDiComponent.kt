package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppDiModul::class
	]
)
interface AppDiComponent {
	@Named(REPOS_USED)
	fun inject(repos: RepositoryInterface)
}