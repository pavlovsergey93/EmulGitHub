package com.gmail.pavlovsv93.emulgithub.ui.home.adapter

import android.annotation.SuppressLint
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
import com.gmail.pavlovsv93.emulgithub.ui.home.HomeFragment
import com.squareup.picasso.Picasso

class AccountListAdapter(private val onClickedItemAccount: HomeFragment.onClickItemAccount) : RecyclerView.Adapter<AccountListAdapter.AccountListViewHolder>() {

	private val accountList: MutableList<AccountGitHub> = mutableListOf()
	fun getAccountList(): List<AccountGitHub> = accountList

	@SuppressLint("NotifyDataSetChanged")
	fun setAccountList(accountList: List<AccountGitHub>) {
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
		fun bind(accountGitHub: AccountGitHub) {
			itemView.findViewById<CardView>(R.id.item_card_view).setOnClickListener {
				onClickedItemAccount.onClickedItemAccount(accountGitHub)
			}
			itemView.findViewById<TextView>(R.id.name_text_view).text = accountGitHub.login
			itemView.findViewById<TextView>(R.id.count_repo_text_view).text = accountGitHub.htmlUrl
			Picasso.with(itemView.context)
				.load(accountGitHub.avatar)
				.resize(500, 500)
				.centerCrop()
				.placeholder(R.drawable.ic_launcher_foreground)
				.into(itemView.findViewById<ImageView>(R.id.avatar_image_view))
		}
	}
}