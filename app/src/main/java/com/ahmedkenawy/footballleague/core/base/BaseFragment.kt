package com.ahmedkenawy.footballleague.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<E> : Fragment() {

    abstract val mTag: String
    abstract val mBinding: ViewBinding
    open val mViewModel: BaseViewModel<E>? = null


    protected abstract fun onMyViewCreated(view: View, savedInstanceState: Bundle?)
    protected open fun renderEvent(event: E) {}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadInitialData()
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onMyViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mViewModel?.eventQueue?.getFor(mTag)?.collect { event ->
                    renderEvent(event)
                }
            }
        }
    }


    private fun loadInitialData() {
        mViewModel?.loadInitialData()
    }

    fun back() {
        findNavController().navigateUp()
    }

    protected fun finish() {
        activity?.finish()
    }

    abstract fun setUpViews()
}
