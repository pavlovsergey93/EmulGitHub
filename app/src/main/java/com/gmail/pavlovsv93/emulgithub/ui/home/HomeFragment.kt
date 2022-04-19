package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.emulgithub.R
import com.gmail.pavlovsv93.emulgithub.app
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding
import com.gmail.pavlovsv93.emulgithub.ui.ViewModel
import com.gmail.pavlovsv93.emulgithub.ui.details.account.DetailsAccountFragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : Fragment() {

	interface onClickItemAccount {
		fun onClickedItemAccount(accountId: String)
	}

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val adapter: AccountListAdapter = AccountListAdapter(object : onClickItemAccount {
		override fun onClickedItemAccount(accountId: String) {
			requireActivity().supportFragmentManager.beginTransaction()
				.replace(
					R.id.main_fragment_container_view,
					DetailsAccountFragment.newInstance(accountId)
				)
				.addToBackStack(accountId)
				.commit()
		}
	})
	private val viewModel: AccountsViewModelInterface by lazy {
		ViewModel(requireActivity().app.repo)
	}
	private lateinit var compositeDisposable: CompositeDisposable

	companion object {
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
		compositeDisposable.dispose()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		compositeDisposable = CompositeDisposable()
		val recyclerView: RecyclerView = binding.accountsRecyclerView
		recyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter
		compositeDisposable.add(
			viewModel.processState
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe { shouldShow ->
				with(requireActivity().findViewById<ProgressBar>(R.id.main_progress_bar)) {
					visibility = if (shouldShow) {
						View.VISIBLE
					} else {
						View.GONE
					}
				}
			}
		)
		compositeDisposable.add(viewModel.errorState
			.subscribeOn(AndroidSchedulers.mainThread())
			.subscribe() { exception ->
			Snackbar.make(binding.root, exception.toString(), Snackbar.LENGTH_INDEFINITE).show()
		})
		compositeDisposable.add( viewModel.successesState
			.subscribeOn(AndroidSchedulers.mainThread())
			.subscribe() { accountList ->
			adapter.setAccountList(accountList)
		})
	}

}