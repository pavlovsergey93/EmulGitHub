package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
	@GET("users")
	fun accountsList(
		@Query("since") since: Int
	): Single<List<GitHubAccountsDTOItem>>

	@GET("users/{user}/repos")
	fun accountRepoList(
		@Path("user") user: String
	): Single<List<GitHubReposDTOItem>>
}