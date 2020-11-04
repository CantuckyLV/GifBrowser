package com.daniel.gifbrowser


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniel.gifbrowser.Adapters.GifsAdapter
import com.daniel.gifbrowser.Domain.GifObject
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSearchRequest
import com.daniel.gifbrowser.ViewModel.TrendyGifsFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class TrendyGifsFragment : Fragment() {

    private val viewModel = TrendyGifsFragmentViewModel()
    private lateinit var trendyGifsRequest: TrendyGifsRequest
    private lateinit var gifSearchRequest: GifSearchRequest
    private lateinit var gifList : ArrayList<GifObject>
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
        rvGifs = view!!.findViewById(R.id.rv_gifs)
        etSearch = view!!.findViewById(R.id.et_search)
        btnSearch = view!!.findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(View.OnClickListener {
            gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",etSearch.text.toString(),15,0,"g","en","")
            viewModel.getGifSearch(gifSearchRequest)!!.observe(this, Observer<GifListResponse> { gifs -> setupGifs(gifs) })
        })
        trendyGifsRequest = TrendyGifsRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",25,0,"g","")
        viewModel.getTrendyGifs(trendyGifsRequest)!!.observe(this, Observer<GifListResponse> { gifs -> setupGifs(gifs) })
    }

    private fun setupGifs(gifs:GifListResponse){
        gifList = gifs.data!!
        val gifsAdapter =  GifsAdapter(gifs.data,activity!!.applicationContext)
        rvGifs.adapter = gifsAdapter
        rvGifs!!.layoutManager = GridLayoutManager(activity, 3)

    }


}
