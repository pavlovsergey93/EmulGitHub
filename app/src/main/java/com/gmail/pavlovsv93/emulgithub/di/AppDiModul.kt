package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.data.MockRepos
import com.gmail.pavlovsv93.emulgithub.data.retrofit.GitHubAPI
import com.gmail.pavlovsv93.emulgithub.data.retrofit.RetrofitRepository
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

//const val REPOS_USED = "mock_repos"
const val REPOS_USED = "retrofit_repos"
const val BASE_URL = "https://api.github.com/"

@Module
class AppDiModul {

	@Singleton
	@Provides
	@Named("mock_repos")
	fun provideMockRepository() : RepositoryInterface{
		return MockRepos()
	}

	@Provides
	fun provideBaseUrl(): String = BASE_URL
//	@Provides
//	fun provideRxCallAdapter(): CallAdapter.Factory =
//	@Provides
//	fun provideGsonConvert(): Converter.Factory =

	@Singleton
	@Provides
	fun provideRetrofit(baseUrl: String, /*rxCallAdapter: CallAdapter.Factory, gsonConvert:Converter.Factory*/): Retrofit {
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	@Singleton
	@Provides
	fun provideGitHubApi(retrofit: Retrofit) : GitHubAPI{
		return retrofit.create(GitHubAPI::class.java)
	}

	@Singleton
	@Provides
	@Named("retrofit_repos")
	fun provideRepository(api: GitHubAPI): RepositoryInterface{
		return RetrofitRepository(api)
	}

}

//	viewModel(named("account_view_model")) {
//		AccountsViewModel(get(named(REPOS_USED)))
//	}
//	viewModel(named("details_view_model")) {
//		DetailsAccountViewModel(get(named(REPOS_USED)))
//	}
//}