package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.data.MockReposTest
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import org.junit.Assert.*
import org.junit.Test

class RetrofitRepositoryTest {
	@Test
	fun getAllAccount_notNull() {
		val actual = MockReposTest().getAllAccount(0)
		assertNotNull(actual)
	}

	@Test
	fun getAllAccount_Null_ReturnFalse() {
		val actual = MockReposTest().getAllAccount(0).size
		assertFalse(actual == null)
	}

	@Test
	fun getAllAccount_isEmpty_ReturnFalse() {
		var expected = 0
		val actual = MockReposTest().getAllAccount(0).size
		assertFalse(actual == expected)
	}

	@Test
	fun getItemAccount_Equels() {
		val expected = mockRepo
		val actual = MockReposTest().getItemAccount("")
		assertTrue("Длинны массивов одинаковы",expected.size == actual.size)
		for (i in actual.indices){
			assertEquals(actual[i].idRepos, expected[i].idRepos)
		}
	}
	@Test
	fun getItemAccount_NotEquels() {
		val expected = mockRepo2
		val actual = MockReposTest().getItemAccount("")
		for (i in expected.indices){
			assertNotEquals(actual[i].idRepos, expected[i].idRepos)
		}
	}
	@Test
	fun getItemAccount_Same() {
		val actual = MockReposTest().getItemAccount("")
		val expected = actual
		assertSame(expected, actual)
	}

	val mockRepo = listOf<AccountRepo>(
		AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.nodeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		)
	)
	val mockRepo2 = listOf<AccountRepo>(
		AccountRepo(
			idRepos = "it.noId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.noeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.noeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.noeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.noeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		), AccountRepo(
			idRepos = "it.ndeId",
			title = "it.name",
			description = "it.description",
			urlHtmlRepos = "it.htmlUrl"
		)
	)

	val mockAccount = listOf<AccountGitHub>(
		AccountGitHub(
			id = 1,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login1",
			htmlUrl = "it.htmlUrl1",
			reposListUrl = "it.reposUrl1"
		),
		AccountGitHub(
			id = 2,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login2",
			htmlUrl = "it.htmlUrl2",
			reposListUrl = "it.reposUrl2"
		),
		AccountGitHub(
			id = 3,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login3",
			htmlUrl = "it.htmlUrl3",
			reposListUrl = "it.reposUrl3"
		),
		AccountGitHub(
			id = 1,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login4",
			htmlUrl = "it.htmlUrl4",
			reposListUrl = "it.reposUrl4"
		)
	)
	val mockAccount2 = listOf<AccountGitHub>(

		AccountGitHub(
			id = 2,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login2",
			htmlUrl = "it.htmlUrl2",
			reposListUrl = "it.reposUrl2"
		),
		AccountGitHub(
			id = 3,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login3",
			htmlUrl = "it.htmlUrl3",
			reposListUrl = "it.reposUrl3"
		),
		AccountGitHub(
			id = 1,
			avatar = R.drawable.ic_launcher_foreground.toString(),
			login = "it.login4",
			htmlUrl = "it.htmlUrl4",
			reposListUrl = "it.reposUrl4"
		)
	)
	val mockListIsEmpty = listOf<AccountGitHub>()
	val mockListNull = null

}