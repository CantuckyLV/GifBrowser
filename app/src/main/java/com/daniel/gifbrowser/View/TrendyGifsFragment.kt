package com.daniel.gifbrowser.View


import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
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
    private  var gifList : ArrayList<GifObject> = ArrayList<GifObject>()
    private val gifSimpleList = ArrayList<GifSimpleObject>()
    private lateinit var rvGifs : RecyclerView
    private lateinit var  etSearch : EditText
    private lateinit var  btnSearch : Button
    private lateinit var gifsObserver : Observer<GifListResponse>
    private  var favsgifList : List<GifSimpleObject?> = emptyList()
    private var currentPage = 0
    private var offset = 0
    private var isLoadingMore = false
    private lateinit var recyclerViewState : Parcelable
    private lateinit var searchTerm : String

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
        rvGifs!!.layoutManager = GridLayoutManager(activity, 3)
        rvGifs.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    isLoadingMore = true
                    recyclerViewState = rvGifs.layoutManager!!.onSaveInstanceState()!!
                    if(currentPage == 0){
                        requestTrending()
                    }else if(currentPage == 1){
                        fetchGifs()
                    }
                    Log.e("Loading more items","dfwf")

                }
            }
        })

        etSearch = view!!.findViewById(R.id.et_search)
        btnSearch = view!!.findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(View.OnClickListener {
            searchTerm = etSearch.text.toString()
            offset = 0
            isLoadingMore = false
            fetchGifs()
        })
        rlLoading = view!!.findViewById(R.id.rl_loading)
        rlLoading.visibility = View.VISIBLE
        gifsObserver = Observer { gifs -> setupGifs(gifs.data) }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getGifs()!!.observe(this, Observer { gifs -> favsgifList = gifs!! } )
        fetchGifs()
    }

    fun setupGifs(gifs:ArrayList<GifObject>?):Int{
        offset +=25
        gifList = gifs!!
        if(!isLoadingMore){
            gifSimpleList.clear()
        }

        for(gifObject in gifList){
            val gifSimpleObject = GifSimpleObject(gifObject.id,gifObject.images.original.url,false)
            var isFav:Boolean
            for (favGif in favsgifList){
                if(favGif?.id == gifSimpleObject.id) gifSimpleObject.isFav = true
            }
            gifSimpleList.add(gifSimpleObject)
        }
        try{
            val gifsAdapter =  GifsAdapter(gifSimpleList,activity!!.applicationContext, object : GifsAdapter.OnItemClickListener {
                override fun onItemClick(v:View, position: Int) {
                    val tmpGifObj = gifSimpleList.get(position)
                    gifSimpleList.get(position).isFav = true
                    viewModel.saveGif(tmpGifObj)
                    rvGifs.adapter!!.notifyDataSetChanged()
                }
            })
            rvGifs.adapter = gifsAdapter
            if(isLoadingMore){
                rvGifs.layoutManager!!.onRestoreInstanceState(recyclerViewState)
            }

        }catch(e:Exception){
            e.printStackTrace()
        }

        rlLoading.visibility = View.GONE
        return 3

    }

    private fun fetchGifs(){
        currentPage = 1
        rlLoading.visibility = View.VISIBLE
        if(etSearch.text.length>0){
            gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",searchTerm,25,offset,"g","en","")
            viewModel.getGifSearch(gifSearchRequest)!!.observe(this, gifsObserver)
        }
        else if(etSearch.text.length==0){
            if(isLoadingMore){
                gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",searchTerm,25,offset,"g","en","")
                viewModel.getGifSearch(gifSearchRequest)!!.observe(this, gifsObserver)
            }else{
                offset = 0
                requestTrending()
            }
        }
    }
    private fun requestTrending(){
        currentPage = 0
        rlLoading.visibility = View.VISIBLE
        trendyGifsRequest = TrendyGifsRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",25,offset,"g","")
        viewModel.getTrendyGifs(trendyGifsRequest)!!.observe(this, gifsObserver )

    }


}
