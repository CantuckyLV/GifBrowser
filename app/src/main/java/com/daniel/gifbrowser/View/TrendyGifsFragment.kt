package com.daniel.gifbrowser.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.daniel.gifbrowser.Adapters.GifsAdapter
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Domain.*
import com.daniel.gifbrowser.R
import com.daniel.gifbrowser.ViewModel.Factory.TrendyGifsFragmentViewModelFactory
import com.daniel.gifbrowser.ViewModel.TrendyGifsFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class TrendyGifsFragment : Fragment() {

    private lateinit var viewModel : TrendyGifsFragmentViewModel
    private lateinit var trendyGifsRequest: TrendyGifsRequest
    private lateinit var rlLoading : RelativeLayout
    private lateinit var gifSearchRequest :GifSearchRequest
    private lateinit var gifList : ArrayList<GifObject>
    private val gifSimpleList = ArrayList<GifSimpleObject>()
    private lateinit var rvGifs : RecyclerView
    private lateinit var  etSearch : EditText
    private lateinit var  btnSearch : Button
    private lateinit var gifsObserver : Observer<GifListResponse>
    private lateinit var favsgifList : List<GifSimpleObject?>

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
            fetchGifs()
        })
        rlLoading = view!!.findViewById(R.id.rl_loading)
        rlLoading.visibility = View.VISIBLE
        viewModel.getGifs()!!.observe(this, Observer { gifs -> favsgifList = gifs!! } )
        gifsObserver = Observer { gifs -> setupGifs(gifs) }

    }

    override fun onResume() {
        super.onResume()
        fetchGifs()
    }

    private fun setupGifs(gifs:GifListResponse){
        gifList = gifs.data!!
        gifSimpleList.clear()
        for(gifObject in gifList){
            val gifSimpleObject = GifSimpleObject(gifObject.id,gifObject.images.original.url,false)
            var isFav:Boolean
            for (favGif in favsgifList){
                if(favGif?.id == gifSimpleObject.id) gifSimpleObject.isFav = true
            }
            gifSimpleList.add(gifSimpleObject)
        }
        val gifsAdapter =  GifsAdapter(gifSimpleList,activity!!.applicationContext, object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(v:View, position: Int) {
                val tmpGifObj = gifSimpleList.get(position)
                gifSimpleList.get(position).isFav = true
                viewModel.saveGif(tmpGifObj)
                rvGifs.adapter!!.notifyDataSetChanged()
            }
        })
        rvGifs.adapter = gifsAdapter
        rvGifs!!.layoutManager = GridLayoutManager(activity, 3)
        rlLoading.visibility = View.GONE

    }

    private fun fetchGifs(){
        rlLoading.visibility = View.VISIBLE
        if(etSearch.text.length>0){
            gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",etSearch.text.toString(),15,0,"g","en","")
            viewModel.getGifSearch(gifSearchRequest)!!.observe(this, gifsObserver)
        }else if(etSearch.text.length==0){
            requestTrending()
        }
    }
    private fun requestTrending(){
        rlLoading.visibility = View.VISIBLE
        trendyGifsRequest = TrendyGifsRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",25,0,"g","")
        viewModel.getTrendyGifs(trendyGifsRequest)!!.observe(this, gifsObserver )
    }


}
