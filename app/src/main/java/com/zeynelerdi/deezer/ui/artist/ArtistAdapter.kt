package com.zeynelerdi.deezer.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.deezer.R
import com.zeynelerdi.deezer.core.Env
import com.zeynelerdi.deezer.data.artist.ArtistData
import com.zeynelerdi.deezer.databinding.ItemArtistBinding
import timber.log.Timber


class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    private val items:MutableList<ArtistData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding= DataBindingUtil
            .inflate<ItemArtistBinding>(inflater, R.layout.item_artist,parent,false)

        return ArtistViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { p-> p != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener

                it.findNavController().navigate(
                    R.id.action_artist_details,
                    bundleOf(
                        Env.BUND_ID to items[position].id,
                        Env.BUND_NAME to items[position].name
                    )
                )
            }
        }
    }

    fun addArtistList(artistList:List<ArtistData>){
        val previousSize = items.size
        items.addAll(artistList)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize,items.size)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            artist = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class ArtistViewHolder(val binding: ItemArtistBinding): RecyclerView.ViewHolder(binding.root)
}