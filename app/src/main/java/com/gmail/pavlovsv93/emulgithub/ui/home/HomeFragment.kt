package com.gmail.pavlovsv93.emulgithub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.emulgithub.data.AccountGitHub
import com.gmail.pavlovsv93.emulgithub.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

	interface onClickItemAccount{
		fun onClickedItemAccount(account: AccountGitHub)
	}

	private var _binding : FragmentHomeBinding? = null
	private val binding get() = _binding!!

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

}