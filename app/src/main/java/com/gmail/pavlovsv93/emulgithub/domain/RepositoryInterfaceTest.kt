package com.gmail.pavlovsv93.emulgithub.domain

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


interface RepositoryInterfaceTest {
	fun getAllAccount(since: Int): List<AccountGitHub>
	fun getItemAccount(login: String): List<AccountRepo>
}