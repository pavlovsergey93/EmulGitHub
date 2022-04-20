package com.gmail.pavlovsv93.emulgithub.utils

interface MyCallback<T> {
	fun onSuccess(result: T)
	fun onError(exception: Exception)
}