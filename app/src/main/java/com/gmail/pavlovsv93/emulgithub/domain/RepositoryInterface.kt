package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import io.reactivex.rxjava3.core.Observable


interface RepositoryInterface {
	fun getAllAccount() : Observable<GitHubAccountsDTO>
	fun getItemAccount(login: String) : Observable<GitHubReposDTO>
}