package com.gmail.pavlovsv93.emulgithub.data

import com.gmail.pavlovsv93.emulgithub.domain.InMemoryRepositoryInterface

class InMemoryRepository: InMemoryRepositoryInterface {
	private val accountList: MutableList<AccountGitHub> = mutableListOf(
		AccountGitHub(token = "sdlkfwkeok5445", "111","111"),
		AccountGitHub(token = "sdlkfwkeok5446", "222","222"),
		AccountGitHub(token = "sdlkfwkeok5447", "333","333"),
		AccountGitHub(token = "sdlkfwkeok5448", "444","444"),
		AccountGitHub(token = "sdlkfwkeok5449", "555","555"),
		AccountGitHub(token = "sdlkfwkeok5440", "666","666"),
		AccountGitHub(token = "sdlkfwkeok5441", "777","777")
	)

	override fun getAllAccount(): MutableList<AccountGitHub> {
		return accountList
	}

	override fun getItemAccount(uid: String): AccountGitHub? {
		accountList.forEach {
			if(it.token == uid){
				return it
			}
		}
		return null
	}

	override fun addAccount(account: AccountGitHub) {
		accountList.add(account)
	}
}