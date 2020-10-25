package com.example.android.lolamarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lolamarket.interfaces.GetData
import com.example.android.lolamarket.models.BaseModel
import com.example.android.lolamarket.models.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), StylesAdapter.Listener {

    private var myAdapter: StylesAdapter? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mStylesArrayList: ArrayList<Data>? = null
    private val BASE_URL = "https://sandbox-api.brewerydb.com/v2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()
    }

    /**
     * Initialise the RecyclerView
     */
    private fun initRecyclerView() {
        // LayoutManager to position the items to look like a standard ListView.
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        styles_list.layoutManager = layoutManager

    }

    private fun loadData() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)

        mCompositeDisposable?.add(requestInterface.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(cryptoList: BaseModel) {
        mStylesArrayList = cryptoList.data
        myAdapter = StylesAdapter(mStylesArrayList!!, this)
        styles_list.adapter = myAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

    override fun onItemClick(retroCrypto: Data) {
        Toast.makeText(this, "You clicked: ${retroCrypto.name}", Toast.LENGTH_LONG).show()
    }

}