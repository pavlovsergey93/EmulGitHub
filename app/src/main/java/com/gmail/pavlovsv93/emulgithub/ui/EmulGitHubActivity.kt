package com.gmail.pavlovsv93.emulgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment

class EmulGitHubActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_emul_git_hub)
		supportFragmentManager.beginTransaction()
			.replace(R.id.main_fragment_container_view, HomeFragment.newInstance())
			.commit()
	}
}