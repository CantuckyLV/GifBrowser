package com.daniel.gifbrowser

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Data.TrendyGifsRepository
import com.daniel.gifbrowser.Domain.*
import com.daniel.gifbrowser.ViewModel.TrendyGifsFragmentViewModel
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TrendyGifsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var trendyGifsFragmentViewModel : TrendyGifsFragmentViewModel

    private lateinit var mockResponse:GifListResponse
    @Mock
    private lateinit var appContext: Context
    @Mock
    private lateinit var observer: Observer<GifListResponse>
    @Before
    fun beforeTest() {
        val testList = ArrayList<GifObject>()
        val userObject = UserObject("a","b","c","d","e")
        val renditionObject = RenditionObject("https://media3.giphy.com/media/3o7TKKZZqfYzYWLTA4/giphy.gif?cid=dbd15ab27jwkyhp4ukn8dhea8rrszzu2teghzc0e79nd7wdn&rid=giphy.gif","","","","","","","")
        val imagesObject = ImagesObject(renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject,renditionObject)
        testList.add(GifObject("gif","1","","","","poncho","","","",userObject,"","","","","","",imagesObject,""))
        testList.add(GifObject("gif","2","","","","poncho","","","",userObject,"","","","","","",imagesObject,""))
        testList.add(GifObject("gif","3","","","","poncho","","","",userObject,"","","","","","",imagesObject,""))
        mockResponse = GifListResponse(testList,
            PaginationObject("","",""), MetaObject
        ("","","")
        )
        val mockedDB = Room.databaseBuilder(
            appContext,
            GifDB::class.java, "mediaPosts"
        ).build()
        trendyGifsFragmentViewModel = TrendyGifsFragmentViewModel(mockedDB)
        trendyGifsFragmentViewModel.mutableLiveData.observeForever(observer)
    }
    @Test
    fun getTrendyGifsTest() {
        val trendyGifsRequest = TrendyGifsRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF",25,0,"g","")
        trendyGifsFragmentViewModel.getTrendyGifs(trendyGifsRequest,mockResponse)
        val captor = ArgumentCaptor.forClass(GifListResponse::class.java)
        captor.run {
            Mockito.verify(observer).onChanged(capture())
        }
    }
    @Test
    fun getGifSearchTest() {
        val gifSearchRequest = GifSearchRequest("HbjDDROEXryOkYhSygrKODfKvko95NyF","test",15,0,"g","en","")
        trendyGifsFragmentViewModel.getGifSearch(gifSearchRequest,mockResponse)
        val captor = ArgumentCaptor.forClass(GifListResponse::class.java)
        captor.run {
            Mockito.verify(observer).onChanged(capture())
        }
    }
}