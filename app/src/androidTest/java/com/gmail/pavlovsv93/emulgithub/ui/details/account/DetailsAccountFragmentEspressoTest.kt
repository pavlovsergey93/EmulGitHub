package com.gmail.pavlovsv93.emulgithub.ui.details.account

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


const val KEY_ACCOUNT = "KEY_ACCOUNT"
private val account: AccountGitHub = AccountGitHub(82917171, "https://avatars.githubusercontent.com/u/82917171?v=4", "pavlovsergey93", "https://github.com/pavlovsergey93", "https://api.github.com/users/pavlovsergey93/repos")

@RunWith(AndroidJUnit4::class)
class DetailsAccountFragmentEspressoTest {

	private lateinit var scenario: FragmentScenario<DetailsAccountFragment>

	@Before
	fun setup(){
		scenario = launchFragmentInContainer(themeResId = R.style.Theme_EmulGitHub)
	}

	@After
	fun close(){
		scenario.close()
	}
	@Test
	fun fragment_TestBundle(){
		val fragmentArgs = bundleOf(KEY_ACCOUNT to account)
		scenario = launchFragmentInContainer<DetailsAccountFragment>(themeResId = R.style.Theme_EmulGitHub, fragmentArgs = fragmentArgs)

		val assertion = ViewAssertions.matches(ViewMatchers.withText("pavlovsergey93"))
		Espresso.onView(withId(R.id.name_text_view)).check(assertion)
	}
}