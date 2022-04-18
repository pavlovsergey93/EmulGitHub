package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding
import com.gmail.pavlovsv93.emulgithub.ui.ViewModel
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment

class HomeFragment : Fragment() {

	interface onClickItemAccount{
		fun onClickedItemAccount(accountId: String)
	}

	private var _binding : FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val adapter: AccountListAdapter = AccountListAdapter(object : onClickItemAccount{
		override fun onClickedItemAccount(accountId: String) {
			requireActivity().supportFragmentManager.beginTransaction()
				.replace(R.id.main_fragment_container_view, DetailsAccountFragment.newInstance(accountId))
				.addToBackStack(accountId)
				.commit()
		}
	})
	private val viewModel: AccountsViewModelInterface by lazy {
		ViewModel(requireActivity().app.repo)
	}

	companion object{
		fun newInstance() = HomeFragment()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.accountsRecyclerView
		recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		adapter.setAccountList(viewModel.getAllAccounts())
	}

}