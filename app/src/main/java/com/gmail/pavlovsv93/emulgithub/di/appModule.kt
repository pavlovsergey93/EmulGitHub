package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.data.MockRepos
import com.gmail.pavlovsv93.emulgithub.data.retrofit.GitHubAPI
import com.gmail.pavlovsv93.emulgithub.data.retrofit.RetrofitRepository
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountViewModel
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//const val REPOS_USED = "mock_repos"
const val REPOS_USED = "retrofit_repos"
const val BASE_URL = "https://api.github.com/"

val appModule = module {
	single<RepositoryInterface>(named("mock_repos")) { MockRepos() }
	factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }
	factory<Converter.Factory>(named("gson_converter")) { GsonConverterFactory.create() }
	single<Retrofit>{
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addCallAdapterFactory(get())
			.addConverterFactory(get())
			.build()
	}

	single<GitHubAPI> { get<Retrofit>().create(GitHubAPI::class.java) }
	single(named("retrofit_repos")) { RetrofitRepository(get<GitHubAPI>()) }

	viewModel {
		AccountsViewModel(get(named(REPOS_USED)))
	}
	viewModel {
		DetailsAccountViewModel(get(named(REPOS_USED)) )
	}
}