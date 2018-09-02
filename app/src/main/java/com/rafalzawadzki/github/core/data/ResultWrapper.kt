package com.rafalzawadzki.github.core.data

import com.google.gson.annotations.SerializedName

data class ResultWrapper<T>(var items: List<T> = listOf(),
                            @SerializedName("total_count")
                            var totalCount: Int = 0,
                            @SerializedName("incomplete_results")
                            var incompleteResults: Boolean = false
)
