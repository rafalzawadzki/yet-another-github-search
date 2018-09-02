package com.rafalzawadzki.github

import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.data.models.User

fun newUser() = User(
        2222,
        "someLogin",
        "avatarUrl"
)

fun newRepository(): Repository {
    val someOwner = newUser()
    return Repository(11111,
            "name",
            someOwner,
            "description",
            10)
}

fun newRepositoriesResultOfSize(size: Int): ResultWrapper<Repository> {
    val output = mutableListOf<Repository>()
    for (i in 0 until size) {
        output.add(newRepository())
    }
    return ResultWrapper(output).apply { totalCount = items.count() }
}