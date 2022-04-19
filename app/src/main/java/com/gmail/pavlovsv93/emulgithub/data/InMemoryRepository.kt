package com.gmail.pavlovsv93.emulgithub.data

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class InMemoryRepository : RepositoryInterface {
	private val repoList: MutableList<AccountRepo> = mutableListOf(
		AccountRepo(id = "ewqpoe1", "первый", "Первый репозиторий"),
		AccountRepo(id = "ewqpoe2", "второй", "Второй репозиторий"),
		AccountRepo(id = "ewqpoe3", "третий", "Третий репозиторий"),
		AccountRepo(id = "ewqpoe4", "четвертый", "Четвертый репозиторий"),
		AccountRepo(id = "ewqpoe5", "Пятый", "Пятый репозиторий"),
		AccountRepo(id = "ewqpoe6", "Шестой", "Шестой репозиторий")
	)
	private val accountList: MutableList<AccountGitHub> = mutableListOf(
		AccountGitHub(token = "sdlkfwkeok5445", "111", "111", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5446", "222", "222", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5447", "333", "333", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5448", "444", "444", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5449", "555", "555", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5440", "666", "666", list = repoList),
		AccountGitHub(token = "sdlkfwkeok5441", "777", "777", list = repoList)
	)

	override fun getAllAccount(): Observable<MutableList<AccountGitHub>> {
		return Observable
			.fromCallable { accountList }
			.subscribeOn(Schedulers.io())
	}

	override fun getItemAccount(uid: String): Observable<AccountGitHub> {
		return Observable
			.fromCallable {
				acc(uid)!!
			}
			.subscribeOn(Schedulers.io())

	}
	fun acc(uid: String): AccountGitHub? {
		accountList.forEach {
			if (it.token == uid) {
				return it
			}
		}
		return null
	}

	override fun addAccount(account: AccountGitHub): Observable<Boolean> {
		return Observable
			.fromCallable { accountList.add(account) }
			.subscribeOn(Schedulers.io())
	}
}