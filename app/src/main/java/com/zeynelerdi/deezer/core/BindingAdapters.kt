package com.zeynelerdi.deezer.core

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.data.artist.ArtistData
import com.zeynelerdi.deezer.data.artistdetails.ArtistAlbumData
import com.zeynelerdi.deezer.data.artistdetails.ArtistRelatedData
import com.zeynelerdi.deezer.data.genre.Data
import com.zeynelerdi.deezer.data.search.SearchData
import com.zeynelerdi.deezer.data.search.SearchQuery
import com.zeynelerdi.deezer.ui.album.AlbumDetailsAdapter
import com.zeynelerdi.deezer.ui.artist.ArtistAdapter
import com.zeynelerdi.deezer.ui.artistdetails.albums.ArtistAlbumAdapter
import com.zeynelerdi.deezer.ui.artistdetails.related.ArtistRelatedAdapter
import com.zeynelerdi.deezer.ui.favorites.FavoritesAdapter
import com.zeynelerdi.deezer.ui.genre.GenreAdapter
import com.zeynelerdi.deezer.ui.search.RecentSearchAdapter
import com.zeynelerdi.deezer.ui.search.SearchAlbumAdapter
import com.google.android.material.tabs.TabLayout
import timber.log.Timber



@BindingAdapter("adapter")
fun bindAdapter(view:RecyclerView, adapter:RecyclerView.Adapter<*>){
    view.adapter = adapter
}

@BindingAdapter("adapterGenreList")
fun bindingGenreList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as GenreAdapter).addGenreList(((results.value) as Result.Succes<List<Data>>).data)
        }
    }
}

@BindingAdapter("adapterArtistList")
fun bindingArtistList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistAdapter).addArtistList(((results.value) as Result.Succes<List<ArtistData>>).data)
        }
    }
}

@BindingAdapter("adapterTablayout")
fun bindingTabLayoutAdapter(view:TabLayout,viewPager: ViewPager){
    view.setupWithViewPager(viewPager)
}

@BindingAdapter("adapterViewPager")
fun bindingViewPagerAdapter(view:ViewPager,adapter:FragmentPagerAdapter){
    view.adapter = adapter
}


@BindingAdapter("adapterAAlbumsList")
fun bindingAAlbumsList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistAlbumAdapter).addAlbumList(((results.value) as Result.Succes<List<ArtistAlbumData>>).data)
        }
    }
}

@BindingAdapter("adapterARelatedList")
fun bindingARelatedList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as ArtistRelatedAdapter).addRelatedList(((results.value) as Result.Succes<List<ArtistRelatedData>>).data)
        }
    }
}

@BindingAdapter("adapterAlbumTracksList")
fun bindingAlbumTracksList(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("result : succes isSplash : false")
            (view.adapter as AlbumDetailsAdapter).addAlbumTracks(((results.value) as Result.Succes<List<AlbumData>>).data)
        }
    }
}

@BindingAdapter("adapterRecentSearch")
fun bindingRecentSeach(view:RecyclerView, results:LiveData<List<SearchQuery>>) {
    Timber.d("binding recentData : ${results.value}")
    if(!results.value.isNullOrEmpty()) {
        (view.adapter as RecentSearchAdapter).addRecentSearch(((results.value) as List<SearchQuery>))
    }
}

@BindingAdapter("adapterSearchAlbum")
fun bindingSearchAlbum(view:RecyclerView, results:LiveData<Result<Any>>) {
    when (results.value) {
        Result.Loading, Result.Error -> {/* Nothing */
        }
        is Result.Succes -> {
            Timber.d("adapterSearchAlbum")
            (view.adapter as SearchAlbumAdapter).addAlbumSearch(((results.value) as Result.Succes<List<SearchData>>).data)
        }
    }
}

@BindingAdapter("onEditorActionListener")
fun bindOnEditorActionListener(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(editorActionListener)
}

@BindingAdapter("adapterFavoritesList")
fun bindingFavoritesList(view:RecyclerView, results:LiveData<List<AlbumData>>) {
    if (!results.value.isNullOrEmpty()) {
        Timber.d("Favorites result :${results.value} ")
        (view.adapter as FavoritesAdapter).addFavoritesList(results.value!!)
    }
}