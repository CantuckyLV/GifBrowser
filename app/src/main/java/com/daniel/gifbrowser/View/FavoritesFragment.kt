package com.daniel.gifbrowser.View


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.daniel.gifbrowser.Adapters.GifsAdapter
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Domain.GifSimpleObject
import com.daniel.gifbrowser.R
import com.daniel.gifbrowser.ViewModel.FavoritesFragmentViewModel
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    private lateinit var viewModel : FavoritesFragmentViewModel
    private lateinit var gifList : ArrayList<GifSimpleObject>
    private lateinit var rvFavGifs : RecyclerView
    private lateinit var rlLoadingFavs : RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onStart() {
        super.onStart()
        rvFavGifs = view!!.findViewById(R.id.rv_fav_gifs)
        rlLoadingFavs = view!!.findViewById(R.id.rl_loading_favs)
        val db = Room.databaseBuilder(
            activity!!.applicationContext,
            GifDB::class.java, "mediaPosts"
        ).build()
        rlLoadingFavs.visibility = View.VISIBLE
        viewModel = FavoritesFragmentViewModel(db)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoritesList()!!.observe(this, Observer <List<GifSimpleObject?>?>{ gifs -> setupGifs(gifs) })
    }

    private fun setupGifs(gifs : List<GifSimpleObject?>?){
        gifList = gifs as ArrayList<GifSimpleObject>
        val gifsAdapter = GifsAdapter(gifList,activity!!.applicationContext,object  : GifsAdapter.OnItemClickListener{
            override fun onItemClick(v: View, position: Int) {
                Log.e("eliminar",""+gifList[position])
                removeGif(gifList[position])
            }
        })
        rvFavGifs.adapter = gifsAdapter
        rvFavGifs!!.layoutManager = GridLayoutManager(activity, 3)
        rlLoadingFavs.visibility = View.GONE
    }
    private fun removeGif(gifSimpleObject:GifSimpleObject){
        viewModel.reMoveFavorite(gifSimpleObject)
        gifList.remove(gifSimpleObject)
        rvFavGifs.adapter!!.notifyDataSetChanged()
    }


}
