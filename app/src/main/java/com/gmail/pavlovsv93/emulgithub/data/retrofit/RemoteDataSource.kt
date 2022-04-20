package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
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

	override fun getAllAccount(): Observable<GitHubAccountsDTO> {
		return gitHubApi.accountsList()
	}

	override fun getItemAccount(login: String): Observable<GitHubReposDTO> {
		return gitHubApi.accountRepoList(login)
	}
}