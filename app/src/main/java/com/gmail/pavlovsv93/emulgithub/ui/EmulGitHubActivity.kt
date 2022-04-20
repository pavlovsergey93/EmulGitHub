package com.gmail.pavlovsv93.emulgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment

const val TAG_HOME_FRAGMENT = "TAG.HomeFragment"

class EmulGitHubActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_emul_git_hub)

		if(supportFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT) == null) {
			supportFragmentManager.beginTransaction()
				.replace(
					R.id.main_fragment_container_view,
					//DetailsAccountFragment.newInstance("pavlovsergey93"),
					HomeFragment.newInstance(),
					TAG_HOME_FRAGMENT
				)
				.commitAllowingStateLoss()
		}
	}
}