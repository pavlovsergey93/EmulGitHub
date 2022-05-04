package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


interface RepositoryInterface {
	fun getAllAccount(since: Int): Maybe<List<GitHubAccountsDTOItem>>
	fun getItemAccount(login: String): Single<List<GitHubReposDTOItem>>
}