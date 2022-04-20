package com.gmail.pavlovsv93.emulgithub.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.domain.Entity.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTO
import com.gmail.pavlovsv93.emulgithub.domain.Entity.GitHubAccountsDTOItem

class AccountListAdapter(private val onClickedItemAccount: HomeFragment.onClickItemAccount) : RecyclerView.Adapter<AccountListAdapter.AccountListViewHolder>() {

	private val accountList: MutableList<GitHubAccountsDTOItem> = mutableListOf()

	fun setAccountList(accountList: GitHubAccountsDTO) {
		this.accountList.clear()
		this.accountList.addAll(accountList)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountListViewHolder =
		AccountListViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.fragment_home_rv_item, parent, false
			) as View
		)

	override fun onBindViewHolder(holder: AccountListViewHolder, position: Int) {
		holder.bind(accountList[position])
	}

	override fun getItemCount(): Int = accountList.size

	inner class AccountListViewHolder(item: View) : RecyclerView.ViewHolder(item) {
		fun bind(accountGitHub: GitHubAccountsDTOItem) {
			itemView.findViewById<CardView>(R.id.item_card_view).setOnClickListener {
				onClickedItemAccount.onClickedItemAccount(accountGitHub.login)
			}
			itemView.findViewById<TextView>(R.id.name_text_view).text = accountGitHub.login
//			itemView.findViewById<TextView>(R.id.count_repo_text_view).text = "Количество репозиториев:\n${accountGitHub.reposUrl?.size ?: 0}"
			itemView.findViewById<ImageView>(R.id.avatar_image_view).setImageResource(R.drawable.ic_launcher_foreground)
		}
	}
}