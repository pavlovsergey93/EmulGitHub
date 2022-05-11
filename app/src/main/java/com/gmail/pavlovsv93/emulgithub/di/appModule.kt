package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.data.MockRepos
import com.gmail.pavlovsv93.emulgithub.data.retrofit.GitHubAPI
import com.gmail.pavlovsv93.emulgithub.data.retrofit.RemoteDataSource
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.home.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val REPOS_USED = "mock_repos"

val appModule = module {
	single<RepositoryInterface>(named("mock_repos")) { MockRepos() }

	single<String>(named("base_url")) { "https://api.github.com/" }
	factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }
	factory<Converter.Factory>(named("gson_converter")) { GsonConverterFactory.create() }
	single<Retrofit> {
		Retrofit.Builder()
			.baseUrl(get<String>(named("base_url")))
			.addCallAdapterFactory(get<CallAdapter.Factory>())
			.addConverterFactory(get<Converter.Factory>())
			.build()
	}
	single<GitHubAPI> { get<Retrofit>().create(GitHubAPI::class.java) }
	single<RepositoryInterface>(named("retrofit_repos")) { RemoteDataSource(get<GitHubAPI>()) }

	viewModel(named("accounts_view_model")) { AccountsViewModel(get<RepositoryInterface>(named(REPOS_USED)))}
}
