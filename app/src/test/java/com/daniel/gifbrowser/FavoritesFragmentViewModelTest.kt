package com.daniel.gifbrowser

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Data.FavoritesRepository
import com.daniel.gifbrowser.Domain.GifSimpleObject
import com.daniel.gifbrowser.ViewModel.FavoritesFragmentViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritesFragmentViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var favoritesFragmentViewModel: FavoritesFragmentViewModel
    val testList = ArrayList<GifSimpleObject>()
    @Mock
    private lateinit var mockRepo: FavoritesRepository
    @Mock
    private lateinit var appContext: Context
    @Mock
    private lateinit var observer: Observer<ArrayList<GifSimpleObject>>
    @Before
    fun beforeTest() {
        val mockedDB = Room.databaseBuilder(
            appContext,
            GifDB::class.java, "mediaPosts"
        ).build()
        favoritesFragmentViewModel = FavoritesFragmentViewModel(mockedDB)
        favoritesFragmentViewModel.mutableLiveData.observeForever(observer)
        testList.add(GifSimpleObject("1","",false))
        testList.add(GifSimpleObject("2","",false))
        testList.add(GifSimpleObject("3","",false))
    }
    @Test
    fun getFavoritesTest() {
        favoritesFragmentViewModel.getFavoritesList(testList)
        val captor = ArgumentCaptor.forClass(ArrayList::class.java)
        captor.run {
            verify(observer).onChanged(capture() as ArrayList<GifSimpleObject>?)
        }
    }

}