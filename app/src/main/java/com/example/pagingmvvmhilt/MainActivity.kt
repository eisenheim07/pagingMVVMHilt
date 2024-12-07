package com.example.pagingmvvmhilt

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingmvvmhilt.adapters.UsersAdapter
import com.example.pagingmvvmhilt.databinding.ActivityMainBinding
import com.example.pagingmvvmhilt.models.model_users.Data
import com.example.pagingmvvmhilt.utils.Resource
import com.example.pagingmvvmhilt.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private val appViewModel by viewModels<AppViewModel>()
    private var CURRENT_PAGE = 1
    private var TOTAL_PAGES: Int = 0
    private val PER_PAGE = "8"
    private var OLD_COUNT = 0
    private var dataArrayList: MutableList<Data> = ArrayList()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setObservers()
        setListeners()

    }

    private fun initViews() {
        usersAdapter = UsersAdapter(baseContext, dataArrayList)
        binding.rcvUsers.adapter = usersAdapter

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)

        appViewModel.getUsers(CURRENT_PAGE, PER_PAGE)
    }

    private fun setObservers() {
        appViewModel.getUsers.observe(this) { resources ->

            when (resources) {
                is Resource.Loading -> {
                    progressDialog.show()
                }

                is Resource.Success -> {
                    progressDialog.dismiss()
                    val result = resources.data
                    if (result != null && result.data.isNotEmpty()) {
                        TOTAL_PAGES = result.total_pages
                        OLD_COUNT += result.data.size
                        usersAdapter.addItems(OLD_COUNT, result.data)
                    }
                }

                is Resource.Failure -> {
                    progressDialog.dismiss()
                }
            }

        }
    }

    private fun setListeners() {
        binding.rcvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rcvUsers.canScrollVertically(1)) {
                    if (CURRENT_PAGE < TOTAL_PAGES) {
                        CURRENT_PAGE += 1
                        appViewModel.getUsers(CURRENT_PAGE, PER_PAGE)
                    }
                }
            }
        })
    }

}