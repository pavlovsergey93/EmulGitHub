package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountRepo
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubReposDTOItem

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder>() {

	private val repoList: MutableList<GitHubReposDTOItem> = mutableListOf()

	fun setRepoList(repoList: GitHubReposDTO?) {
		this.repoList.clear()
		repoList?.let {
			this.repoList.addAll(repoList)
		}
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder =
		RepoListViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.fragment_details_accuont_rv_item, parent, false
			) as View
		)

	override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
		holder.bind(repoList[position])
	}

	override fun getItemCount(): Int = repoList.size

	inner class RepoListViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		fun bind(accountRepo: GitHubReposDTOItem) {
			itemView.findViewById<TextView>(R.id.title_repo_text_view).text = accountRepo.name
			itemView.findViewById<TextView>(R.id.description_text_view).text = accountRepo.description
		}

	}
}
