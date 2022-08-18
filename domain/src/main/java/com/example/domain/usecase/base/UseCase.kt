package com.example.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class UseCase<PARAMS, RESULT>(
    protected val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    abstract suspend fun execute(params: PARAMS? = null): RESULT
}