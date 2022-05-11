package com.gmail.pavlovsv93.emulgithub.di

import com.gmail.pavlovsv93.emulgithub.domain.RepositoryInterface
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

val appModuleDetails = module {
	single<String>(named("key_details_view_model")) { UUID.randomUUID().toString() }
	viewModel(named("details_view_model")) {
		DetailsAccountViewModel(
			repo = get<RepositoryInterface>(named(REPOS_USED)),
//			key = get(named("key_details_view_model"))
		)
	}
}