package com.gmail.pavlovsv93.emulgithub.ui.home

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.pavlovsv93.emulgithub.BuildConfig
import org.junit.Before
import org.junit.runner.RunWith
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.data.MockReposTest
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.ui.EmulGitHubActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class HomeFragmentRecyclerViewTest {
	private lateinit var scenario: FragmentScenario<HomeFragment>
	private lateinit var repository: MockReposTest

	@Before
	fun setup() {
		scenario = launchFragmentInContainer(themeResId = R.style.Theme_EmulGitHub)
		repository = MockReposTest()
	}

	@After
	fun close() {
		scenario.close()
	}

	@Test
	fun recyclerView_ScrollToTest() {
		scenario.onFragment { fragment ->
			fragment.adapter.setAccountList(loadFaceList())
		}
		onView(withId(R.id.accounts_recycler_view)).perform(
			RecyclerViewActions.scrollTo<AccountListAdapter.AccountListViewHolder>(
				hasDescendant(withText("30 login"))
			)
		)
	}

	@Test
	fun recyclerView_ActionOnItemAtPositionTest() {
		scenario.onFragment { fragment ->
			fragment.adapter.setAccountList(loadFaceList())
		}
		onView(withId(R.id.accounts_recycler_view)).perform(
			RecyclerViewActions.actionOnItemAtPosition<AccountListAdapter.AccountListViewHolder>(
				20, click()
			)
		)
	}
	@Test
	fun recyclerView_CustomActionOnItemAtPositionTest() {
		scenario.onFragment { fragment ->
			fragment.adapter.setAccountList(loadFaceList())
		}
		onView(withId(R.id.accounts_recycler_view)).perform(
			RecyclerViewActions.actionOnItemAtPosition<AccountListAdapter.AccountListViewHolder>(
				20, tapOnItemWithId(R.id.name_text_view)
			)
		)
	}

	private fun tapOnItemWithId(id: Int) = object : ViewAction {
		override fun getConstraints(): Matcher<View>? = null

		override fun getDescription(): String = "Метод tapOnItemWithId по элементу $id"

		override fun perform(uiController: UiController?, view: View?) {
			val v = view?.findViewById(id) as View
			v.performClick()
		}

	}

	private fun loadFaceList(): List<AccountGitHub> {
		val listAccountGitHub = mutableListOf<AccountGitHub>()
		for (index in 1..50) {
			val account = AccountGitHub(
				index,
				"https://avatars.githubusercontent.com/u/82917171?v=4",
				"$index login",
				"https://github.com/pavlovsergey93",
				"https://api.github.com/users/pavlovsergey93/repos"
			)
			listAccountGitHub.add(account)
		}
		return listAccountGitHub
	}
}