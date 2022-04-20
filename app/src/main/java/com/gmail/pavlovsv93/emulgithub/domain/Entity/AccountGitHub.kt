package com.gmail.pavlovsv93.emulgithub.domain.Entity

data class AccountGitHub(val token: String, val login: String, var password: String?, var list: MutableList<AccountRepo>? = null)
