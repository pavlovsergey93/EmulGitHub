package com.gmail.pavlovsv93.emulgithub.data

import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class MockRepos: RepositoryInterface {
	override fun getAllAccount(since: Int): Maybe<List<AccountGitHub>> {
		val mock = mutableListOf<AccountGitHub>(
			AccountGitHub(id = 1,
				avatar = R.drawable.ic_launcher_foreground.toString(),
				login = "it.login",
				htmlUrl = "it.htmlUrl",
				reposListUrl = "it.reposUrl")
		)
		return Maybe.just(mock)
	}

	override fun getItemAccount(login: String): Single<List<AccountRepo>> {
		val mock = mutableListOf<AccountRepo>(AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		))
		return Single.just(mock)
	}

}