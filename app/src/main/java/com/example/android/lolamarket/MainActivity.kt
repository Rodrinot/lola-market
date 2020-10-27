package com.example.android.lolamarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lolamarket.adapters.BeersAdapter
import com.example.android.lolamarket.adapters.StylesAdapter
import com.example.android.lolamarket.interfaces.APIService
import com.example.android.lolamarket.models.Beers
import com.example.android.lolamarket.models.Styles
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity(), StylesAdapter.Listener, BeersAdapter.Listener {

    private var mStylesAdapter: StylesAdapter? = null
    private var mBeersAdapter: BeersAdapter? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mStylesArrayList: ArrayList<Styles.Style>? = null
    private var mBeersArrayList: ArrayList<Beers.Beer>? = null
    private val BASE_URL = "https://sandbox-api.brewerydb.com/v2/"
    private val API_KEY = "a1d4112b073924d9b42aa486ab6b5c2a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadStyles()
    }

    /**
     * Initialise the RecyclerView. LayoutManager to position the items to look like a standard ListView.
     */
    private fun initRecyclerView() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
    }

    /**
     * Load styles list.
     */
    private fun loadStyles() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(APIService::class.java)

        mCompositeDisposable?.add(requestInterface.getStyles(API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(stylesList: Styles) {
        mStylesArrayList = stylesList.data
        mStylesAdapter = StylesAdapter(mStylesArrayList!!, this)
        list.adapter = mStylesAdapter
    }

    /**
     * Load beers of to the style selected.
     */
    private fun loadBeers(style: Styles.Style) {
        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(APIService::class.java)

        mCompositeDisposable?.add(requestInterface.getBeers(style.id, API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse))
    }

    private fun handleResponse(beersList: Beers) {
        try {
            mBeersArrayList = beersList.data
            if (beersList.totalResults > 0) {
                mBeersAdapter = BeersAdapter(mBeersArrayList!!, this)
                list.adapter = mBeersAdapter
            } else {
                Toast.makeText(this, "There are no beers of this style. Please, try another one.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onItemClick(style: Styles.Style) {
        Toast.makeText(this, "Loading ${style.name} beers.", Toast.LENGTH_LONG).show()
        loadBeers(style)
    }

    override fun onItemClick(beer: Beers.Beer) {
        Toast.makeText(this, "Displaying ${beer.name} information.", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

}