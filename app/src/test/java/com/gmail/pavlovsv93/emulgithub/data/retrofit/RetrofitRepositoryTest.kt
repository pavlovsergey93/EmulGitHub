package com.gmail.pavlovsv93.emulgithub.data.retrofit

import com.gmail.pavlovsv93.emulgithub.data.MockReposTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RetrofitRepositoryTest {
	@Test
	fun getAllAccount() {
		var expected = 4
		val actual = MockReposTest().getAllAccount(0).size
		assertEquals(expected, actual)
	}

	@Test
	fun getItemAccount() {

	}
}