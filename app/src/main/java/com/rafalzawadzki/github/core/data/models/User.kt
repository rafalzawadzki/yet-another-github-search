package com.rafalzawadzki.github.core.data.models

import com.google.gson.annotations.SerializedName

data class User (

    var id: Int,
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String

)