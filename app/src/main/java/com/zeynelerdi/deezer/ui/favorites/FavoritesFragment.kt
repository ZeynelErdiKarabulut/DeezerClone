package com.zeynelerdi.deezer.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.zeynelerdi.deezer.R
import com.zeynelerdi.deezer.core.DataBindingFragment
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.databinding.FragmentFavoritesBinding
import com.zeynelerdi.deezer.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment:DataBindingFragment() {

    lateinit var binding:FragmentFavoritesBinding
    @VisibleForTesting
    val viewModel:FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_favorites,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FavoritesFragment
            vm = viewModel
            adapter = FavoritesAdapter(object : FavoritesAdapter.OnClick {
                override fun onItemClickListener(v: View,trackPos:Int, albumList:List<AlbumData>) {
                    ((this@FavoritesFragment).requireActivity() as MainActivity).viewModel.apply {
                        albumData.value = albumList
                        positionTrack = trackPos
                        isGoneMediaPlayer.set(true)
                        playMusic()
                    }
                }
            })
        }

        viewModel.fetchFavorites()

    }
}