package com.gmail.pavlovsv93.emulgithub.domain.Entity

data class AccountRepo(
	val idRepos: String,
	var title: String,
	var description: String? = null,
	var urlHtmlRepos: String
)
