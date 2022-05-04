package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL_RETROFIT = "https://api.github.com/"

class RemoteDataSource : RepositoryInterface {
	private val gitHubApi = Retrofit.Builder().baseUrl(BASE_URL_RETROFIT)
		.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(GitHubAPI::class.java)

	override fun getAllAccount(since: Int): Maybe<List<GitHubAccountsDTOItem>> {
		return gitHubApi.accountsList(since)
	}

	override fun getItemAccount(login: String): Single<List<GitHubReposDTOItem>> {
		return gitHubApi.accountRepoList(login)
	}
}