package com.rafalzawadzki.github.core.data.models

import com.google.gson.annotations.SerializedName

data class Repository(

    var id: Int,
    var name: String,
    var owner: User,
    var description: String,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("html_url")
    var htmlUrl: String

)