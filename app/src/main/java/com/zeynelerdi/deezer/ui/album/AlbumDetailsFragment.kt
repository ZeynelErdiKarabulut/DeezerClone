    package com.zeynelerdi.deezer.ui.album

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.zeynelerdi.deezer.R
import com.zeynelerdi.deezer.core.DataBindingFragment
import com.zeynelerdi.deezer.core.Env
import com.zeynelerdi.deezer.core.Result
import com.zeynelerdi.deezer.core.UIExtensions
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.databinding.FragmentAlbumDetailsBinding
import com.zeynelerdi.deezer.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_album_details.*
import timber.log.Timber

@AndroidEntryPoint
class AlbumDetailsFragment: DataBindingFragment() {

    lateinit var binding: FragmentAlbumDetailsBinding
    @VisibleForTesting val viewModel: AlbumDetailsViewModel by viewModels()
    var id = "0" // default value.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(inflater,R.layout.fragment_album_details,container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        id = arguments?.getString(Env.BUND_ID) ?: "0"

        Timber.d("id : $id")
        binding.apply {
            lifecycleOwner = this@AlbumDetailsFragment
            adapter = AlbumDetailsAdapter(object : AlbumDetailsAdapter.OnClick {
                override fun onItemClickListener(v: View, pos:Int, data: Any) {
                    when (v.id) {
                        R.id.ibn_fav -> {
                            val item = data as AlbumData
                            viewModel.favoritedToTrack(item)
                        }
                        R.id.ibn_share -> {
                            val item = data as AlbumData
                            (this@AlbumDetailsFragment.requireActivity() as MainActivity).apply {
                                val sharingIntent = Intent(Intent.ACTION_SEND)
                                    .putExtra(Intent.EXTRA_SUBJECT, Env.APP_NAME)
                                    .putExtra(Intent.EXTRA_TEXT, item.link)
                                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                            }
                        }
                        R.id.cardView -> {
                            val list = data as List<AlbumData>
                            ((this@AlbumDetailsFragment).requireActivity() as MainActivity).viewModel.apply {
                                albumData.value = list
                                positionTrack = pos
                                isGoneMediaPlayer.set(true)
                                playMusic()
                            }

                        }
                    }
                }
            })
            vm = viewModel
        }

        viewModel.fetchingAlbumDatas(id)
        viewModel.result.observe(viewLifecycleOwner, {
            when(it){
                //TODO  progress dialog add.
                Result.Loading->{ }
                Result.Error->{
                    UIExtensions.showSnackBar(this@AlbumDetailsFragment.lv_album_details,this@AlbumDetailsFragment.getString(R.string.unexpected_error))
                    Timber.d("result : error isSplash : false")
                }
                is Result.Succes->{
                    Timber.d("result : succes isSplash : false")
                }
            }
        })

        viewModel.isNetworkError.observe(viewLifecycleOwner,{
            if(it){
                UIExtensions.showSnackBar(this@AlbumDetailsFragment.lv_album_details,this@AlbumDetailsFragment.getString(R.string.network_error))
            }
        })
    }
}