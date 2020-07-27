package com.bombadu.mytesting.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.mytesting.R
import com.bombadu.mytesting.database.MyViewModel
import kotlinx.android.synthetic.main.fragment_my_list.*

class MyListFragment : Fragment() {


    private val adapter = MyAdapter()
    lateinit var myViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)//Don't Forget This for the Options Menu****
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getDataList()
    }


    private fun getDataList() {
        myViewModel.getAllData().observe(viewLifecycleOwner,
        Observer { list ->
            list?.let {
                adapter.submitList(it)

            }
        })

    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        this.myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getAllData().observe(viewLifecycleOwner, Observer { allData ->
            allData?.let { adapter.submitList(it) }
        })

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recycler_View: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.getDataAt(viewHolder.adapterPosition)
                    ?.let { myViewModel.deleteData(it) }
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recycler_view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                myViewModel.deleteAllData()
            }
        }
        return super.onOptionsItemSelected(item)
    }




}