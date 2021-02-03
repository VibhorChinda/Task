package com.example.miskaaandroidtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miskaaandroidtask.adapter.CountryAdapter
import com.example.miskaaandroidtask.api.RetrofitInstance
import com.example.miskaaandroidtask.databinding.ActivityMainBinding
import com.example.miskaaandroidtask.models.Country
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        lifecycleScope.launchWhenCreated {
            binding.progressBar.visibility = View.VISIBLE
           val response: Response<List<Country>> =  try {
               RetrofitInstance.api.getCountry()
           } catch (e: IOException) {
               Log.e(TAG, "IOException occured")
               binding.progressBar.visibility = View.GONE
               return@launchWhenCreated
           } catch (e: HttpException) {
               Log.e(TAG, "IOException occured")
               binding.progressBar.visibility = View.GONE
               return@launchWhenCreated
           }

            if(response.isSuccessful && response.body() != null) {
                binding.countryRecyclerView.apply {
                    countryAdapter = CountryAdapter(response.body()!!, this@MainActivity)
                    adapter = countryAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}