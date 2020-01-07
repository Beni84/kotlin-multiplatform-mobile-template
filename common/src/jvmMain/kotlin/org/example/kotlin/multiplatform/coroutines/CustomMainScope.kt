package org.example.kotlin.multiplatform.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal actual fun getCustomMainScope(): CoroutineScope = MainScope()
