package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitRepository(private val gitHubApi: GitHubAPI) : RepositoryInterface {
	
	override fun getAllAccount(since: Int): Maybe<List<AccountGitHub>> {
		return gitHubApi.accountsList(since)
			.subscribeOn(Schedulers.io())
			.map { convertAccount(it) }
	}

	override fun getItemAccount(login: String): Single<List<AccountRepo>> {
		return gitHubApi.accountRepoList(login)
			.subscribeOn(Schedulers.io())
			.map { convertRepos(it) }
	}

	private fun convertRepos(reposListDTO: List<GitHubReposDTOItem>): List<AccountRepo> {
		val reposList: MutableList<AccountRepo> = mutableListOf()
		reposListDTO.forEach {
			val accountRepo = AccountRepo(
				idRepos = it.nodeId,
				title = it.name,
				description = it.description,
				urlHtmlRepos = it.htmlUrl
			)
			reposList.add(accountRepo)
		}
		return reposList
	}

	private fun convertAccount(accountsDtoList: List<GitHubAccountsDTOItem>): List<AccountGitHub> {
		val accountsList: MutableList<AccountGitHub> = mutableListOf()
		accountsDtoList.forEach {
			val accountGitHub = AccountGitHub(
				id = it.id,
				avatar = it.avatarUrl,
				login = it.login,
				htmlUrl = it.htmlUrl,
				reposListUrl = it.reposUrl
			)
			accountsList.add(accountGitHub)
		}
		return accountsList
	}
}