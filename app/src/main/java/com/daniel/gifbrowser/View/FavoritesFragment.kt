package com.daniel.gifbrowser.View


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.daniel.gifbrowser.Adapters.GifsAdapter
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Domain.GifSimpleObject
import com.daniel.gifbrowser.R
import com.daniel.gifbrowser.ViewModel.Factory.FavoritesFragmentViewModelFactory
import com.daniel.gifbrowser.ViewModel.Factory.TrendyGifsFragmentViewModelFactory
import com.daniel.gifbrowser.ViewModel.FavoritesFragmentViewModel
import com.daniel.gifbrowser.ViewModel.TrendyGifsFragmentViewModel
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
    /**
     * Overrides the OnStart method.
     * Initializes the DB and initializes the viewModel.
     */

    override fun onStart() {
        super.onStart()
        rvFavGifs = view!!.findViewById(R.id.rv_fav_gifs)
        rlLoadingFavs = view!!.findViewById(R.id.rl_loading_favs)
        val db = Room.databaseBuilder(
            activity!!.applicationContext,
            GifDB::class.java, "gifs"
        ).build()
        rlLoadingFavs.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this, FavoritesFragmentViewModelFactory(db)).get(
            FavoritesFragmentViewModel::class.java)

    }
    /**
     * Overrides the OnResume method.
     * Starts observing the getFavoritesList method from the viewModel.
     */

    override fun onResume() {
        super.onResume()
        viewModel.getFavoritesList()!!.observe(this, Observer <List<GifSimpleObject?>?>{ gifs -> setupGifs(gifs) })
    }

    /**
     * Recieves a List of GifObjects and transformsit into a list of GifsSimpleObject that should be displayed on the RecyclerVIew.
     * Initializes the adapter for the RecyclerView with the list of gifs.
     * @param  gifs  a List of GifObject to display on the recycler View
     */
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
    /**
     * Removes the GifSimpleObject from gifList and notifies the adpter to reload the recyclerview.
     * Calls the reMoveFavorite method from the viewModel.
     * @param  gifSimpleObject  the GifSimpleObject that is to be removed from the DB
     */
    private fun removeGif(gifSimpleObject:GifSimpleObject){
        viewModel.reMoveFavorite(gifSimpleObject)
        gifList.remove(gifSimpleObject)
        rvFavGifs.adapter!!.notifyDataSetChanged()
    }


}
