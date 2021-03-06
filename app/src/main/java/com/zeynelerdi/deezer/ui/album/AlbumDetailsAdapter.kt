package com.zeynelerdi.deezer.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.deezer.R
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.databinding.ItemAlbumBinding
import com.zeynelerdi.deezer.databinding.ItemArtistAlbumBinding
import kotlinx.android.synthetic.main.item_album.view.*
import timber.log.Timber


class AlbumDetailsAdapter(val listener:OnClick): RecyclerView.Adapter<AlbumDetailsAdapter.AlbumDetailsViewHolder>() {

    interface OnClick{
        fun onItemClickListener(v:View, pos:Int, item:Any)
    }

    private val items: MutableList<AlbumData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<ItemAlbumBinding>(inflater, R.layout.item_album, parent, false)

        return AlbumDetailsViewHolder(binding).apply {
            binding.root.cardView.setOnClickListener {
                val position = adapterPosition
                listener.onItemClickListener(it,position,items)
            }
            binding.root.ibn_fav.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                val item = items[position!!]
                listener.onItemClickListener(it,position,item)
                Toast.makeText(binding.root.context,"Add to Favorites!", Toast.LENGTH_LONG).show()}
            binding.root.ibn_share.setOnClickListener {
                val position = adapterPosition.takeIf { p -> p != RecyclerView.NO_POSITION }
                val item = items[position!!]
                listener.onItemClickListener(it,position,item)
                Toast.makeText(binding.root.context,"Sharing Tracks... Waiting.", Toast.LENGTH_LONG).show()}
        }
    }

    fun addAlbumTracks(albums: List<AlbumData>) {
        val previousSize = items.size
        items.addAll(albums)
        // Timber.d("GenreAdapter  size : $previousSize  \t genreList size : ${genreList.size} item size : ${items.size} ")
        notifyItemRangeChanged(previousSize, items.size)
    }

    override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
        //Timber.d("Items$position ${items[position].toString()}")
        holder.binding.apply {
            Timber.d("binding..")
            album = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class AlbumDetailsViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)
}