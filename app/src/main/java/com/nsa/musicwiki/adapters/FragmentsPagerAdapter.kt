package com.nsa.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nsa.musicwiki.extra.FragmentDetailsViewPagerCallbacks
import com.nsa.musicwiki.ui.frags.genreTopFrags.FragmentTopAlbums
import com.nsa.musicwiki.ui.frags.genreTopFrags.FragmentTopArtists
import com.nsa.musicwiki.ui.frags.genreTopFrags.FragmentTopTracks

//using this adapter to add three fragment to the view pager in genre details
class FragmentsPagerAdapter(fragment: Fragment,private val genreName: String,
                            private val fragmentDetailsViewPagerCallbacks: FragmentDetailsViewPagerCallbacks
                            ) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentTopAlbums(genreName,fragmentDetailsViewPagerCallbacks)
            }
            1 -> {
                FragmentTopTracks(genreName)
            }
            else -> {
                FragmentTopArtists(genreName,fragmentDetailsViewPagerCallbacks)
            }
        }
    }
}