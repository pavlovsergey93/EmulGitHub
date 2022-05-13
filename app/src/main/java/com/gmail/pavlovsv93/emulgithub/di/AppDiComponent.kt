package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment
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
	fun inject(fragment: HomeFragment)
	fun inject(fragment: DetailsAccountFragment)
	@Named(REPOS_USED)
	fun getRepos(): RepositoryInterface
}