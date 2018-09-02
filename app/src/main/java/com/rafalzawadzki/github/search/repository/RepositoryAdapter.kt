package com.rafalzawadzki.github.search.repository

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafalzawadzki.github.R
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.service.ImageService
import kotlinx.android.synthetic.main.item_search_repository.view.*
import javax.inject.Inject

class RepositoryAdapter
@Inject constructor(private val imageService: ImageService)
    : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var repositories = listOf<Repository>()
    var clickListener: ClickListener = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_repository, parent, false)
        return RepositoryViewHolder(view, imageService)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository, clickListener)
    }

    fun setItems(items: List<Repository>) {
        repositories = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = repositories.count()

    class RepositoryViewHolder(view: View, private val imageService: ImageService): RecyclerView.ViewHolder(view) {

        fun bind(repository: Repository, clickListener: ClickListener) {
            itemView.setOnClickListener { clickListener?.invoke(repository) }

            imageService.loadUserImage(repository.owner, itemView.imgOwnerAvatar)
            itemView.txtOwnerName.text = repository.owner.login
            itemView.txtRepositoryTitle.text = repository.name
            itemView.txtRepositoryDescription.text = repository.description
            itemView.txtForksCount.text = itemView.resources.getQuantityString(R.plurals.x_forks, repository.forksCount, repository.forksCount)
        }
    }
}

typealias ClickListener = ((Repository) -> Unit)?