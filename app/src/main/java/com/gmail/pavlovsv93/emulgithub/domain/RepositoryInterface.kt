package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface RepositoryInterface {
	fun getAllAccount(since: Int = 0): Single<List<GitHubAccountsDTOItem>>
	fun getItemAccount(login: String): Single<List<GitHubReposDTOItem>>
}