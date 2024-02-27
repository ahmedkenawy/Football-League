package com.ahmedkenawy.footballleague.core.navigation

import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController

/**
 * The viewModel must be created in the owner fragment for the first time using hiltNavGraphViewModels as following :
```
private val mViewModel by hiltNavGraphViewModels<SomeViewModel>(R.id.someDestination)
```
The same instance of the viewModel can then be retrieved in any other later fragment using backStackViewModels as following:
```
private val mViewModel by backStackViewModels<SomeViewModel>(R.id.someDestination)
```
Note :
- Destination can be either a nav graph's id or a destination's id for any fragment in any nav graph.
- Destination must be same in both fragments to retrieve the same instance of the cached viewModel.
- In case the destination doesn't exist in the later fragment's back stack then null would be returned.
 * **/
@MainThread
public inline fun <reified VM : ViewModel> Fragment.backStackViewModels(
    @IdRes destinationId: Int
): Lazy<VM?> = lazy {
    val owner: ViewModelStoreOwner by lazy(LazyThreadSafetyMode.NONE) { this }
    findNavController().currentBackStack.value.lastOrNull { it.destination.id == destinationId }
        ?.let { backStackEntry ->
            ViewModelLazy(
                VM::class,
                { backStackEntry.viewModelStore },
                { HiltViewModelFactory(requireActivity(), backStackEntry) },
                { defaultViewModelCreationExtras }
            ).value
        } ?: run { null }
}