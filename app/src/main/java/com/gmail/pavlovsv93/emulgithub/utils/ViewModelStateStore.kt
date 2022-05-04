package com.gmail.pavlovsv93.emulgithub.utils

import com.gmail.pavlovsv93.emulgithub.ui.details.account.viewmodel.DetailsAccountViewModelInterface

class ViewModelStateStore<T> {
	private val stores : HashMap<String, T> = HashMap()

	fun putViewModel(key : String ,model: T){
		stores[key] = model
	}
	fun getViewModel(key: String): T? = stores[key]
}