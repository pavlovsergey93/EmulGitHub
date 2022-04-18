package com.gmail.pavlovsv93.emulgithub.ui.details.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.data.AccountRepo
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentDetailsAccountBinding
import com.gmail.pavlovsv93.emulgithub.ui.ViewModel

class DetailsAccountFragment : Fragment() {
	companion object {
		const val KEY_ACCOUNT = "KEY_ACCOUNT"
		fun newInstance(accountId: String) = DetailsAccountFragment().apply {
			arguments = Bundle().apply {
				putString(KEY_ACCOUNT, accountId)
			}
		}
	}
	private var _binding : FragmentDetailsAccountBinding? = null
	private val binding get() = _binding!!
	private val adapter: RepoListAdapter = RepoListAdapter()
	private val viewModel: DetailsAccountViewModelInterface by lazy {
		ViewModel(requireActivity().app.repo)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentDetailsAccountBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView: RecyclerView = binding.listRepoRecyclerView
		recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		arguments?.let {
			it.getString(KEY_ACCOUNT)?.let {
				viewModel.getDataAccount(it).let {
					binding.nameTextView.text = it.login
					adapter.setRepoList(it.list)
				}
			}
		}
	}
}