package com.daniel.gifbrowser


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.daniel.gifbrowser.Adapters.GifsAdapter
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Domain.*
import com.daniel.gifbrowser.ViewModel.Factory.TrendyGifsFragmentViewModelFactory
import com.daniel.gifbrowser.ViewModel.TrendyGifsFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class TrendyGifsFragment : Fragment() {

    private lateinit var viewModel : TrendyGifsFragmentViewModel
    private lateinit var trendyGifsRequest: TrendyGifsRequest
    private lateinit var gifSearchRequest: GifSearchRequest
    private lateinit var gifList : ArrayList<GifObject>
    private lateinit var gifSimpleList : ArrayList<GifSimpleObject>
    private lateinit var rvGifs : RecyclerView
    private lateinit var  etSearch : EditText
    private lateinit var  btnSearch : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trendy_gifs, container, false)
    }

    override fun onStart() {
        super.onStart()
        val db = Room.databaseBuilder(
            activity!!.applicationContext,
            GifDB::class.java, "mediaPosts"
        ).build()
        viewModel = ViewModelProvider(this,TrendyGifsFragmentViewModelFactory(db)).get(TrendyGifsFragmentViewModel::class.java)
        rvGifs = view!!.findViewById(R.id.rv_gifs)
        etSearch = view!!.findViewById(R.id.et_search)
        btnSearch = view!!.findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(View.OnClickListener {
            /*gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",etSearch.text.toString(),15,0,"g","en","")
            viewModel.getGifSearch(gifSearchRequest)!!.observe(this, Observer<GifListResponse>{gifs -> setupGifs(gifs)})*/
            getGifsTmp()
        })
        trendyGifsRequest = TrendyGifsRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",25,0,"g","")
        viewModel.getTrendyGifs(trendyGifsRequest)!!.observe(this, Observer<GifListResponse> { gifs -> setupGifs(gifs) })
    }

    private fun setupGifs(gifs:GifListResponse){
        gifList = gifs.data!!
        val gifsAdapter =  GifsAdapter(gifList,activity!!.applicationContext, object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(v:View, position: Int) {
                val tmpGifObj = gifList.get(position)
                Log.e("Save", "" + GifSimpleObject(tmpGifObj.id,tmpGifObj.images.original.url,false).toString())
                viewModel.saveGif(GifSimpleObject(tmpGifObj.id,tmpGifObj.images.original.url,false))
            }
        })
        rvGifs.adapter = gifsAdapter
        rvGifs!!.layoutManager = GridLayoutManager(activity, 3)

    }

    private fun getGifsTmp(){
        viewModel.getGifs().observe(this, Observer<List<GifSimpleObject?>?> { gifsDb -> tmpFunc(gifsDb as ArrayList<GifSimpleObject>) })
    }

    private fun tmpFunc(gifsDb : ArrayList<GifSimpleObject>){
        Log.e("in DB",""+gifsDb)

    }


}
