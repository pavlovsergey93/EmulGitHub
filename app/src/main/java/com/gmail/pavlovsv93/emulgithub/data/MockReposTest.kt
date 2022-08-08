package com.gmail.pavlovsv93.emulgithub.data

import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterfaceTest
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class MockReposTest: RepositoryInterfaceTest {
	override fun getAllAccount(since: Int): List<AccountGitHub>{
		return mutableListOf<AccountGitHub>(
			AccountGitHub(id = 1,
				avatar = R.drawable.ic_launcher_foreground.toString(),
				login = "it.login1",
				htmlUrl = "it.htmlUrl1",
				reposListUrl = "it.reposUrl1"),
			AccountGitHub(id = 2,
				avatar = R.drawable.ic_launcher_foreground.toString(),
				login = "it.login2",
				htmlUrl = "it.htmlUrl2",
				reposListUrl = "it.reposUrl2"),
			AccountGitHub(id = 3,
				avatar = R.drawable.ic_launcher_foreground.toString(),
				login = "it.login3",
				htmlUrl = "it.htmlUrl3",
				reposListUrl = "it.reposUrl3"),
			AccountGitHub(id = 1,
				avatar = R.drawable.ic_launcher_foreground.toString(),
				login = "it.login4",
				htmlUrl = "it.htmlUrl4",
				reposListUrl = "it.reposUrl4")
		)
	}

	override fun getItemAccount(login: String): List<AccountRepo> {
		return mutableListOf<AccountRepo>(AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		),AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		))
	}

}