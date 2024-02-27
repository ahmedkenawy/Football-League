package com.ahmedkenawy.footballleague.core.base

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineDispatchers @Inject constructor() {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
    open val Default: CoroutineContext by lazy { Dispatchers.Default }
}