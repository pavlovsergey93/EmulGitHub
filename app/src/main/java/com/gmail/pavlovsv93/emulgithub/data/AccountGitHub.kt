package com.gmail.pavlovsv93.emulgithub.data

data class AccountGitHub(val token: String ,val login: String, var password: String, var list: List<AccountRepo>? = null)
