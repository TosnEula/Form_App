package com.example.form_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ReportFragment : Fragment(), RptHeaderAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listNameAndTitle :List<List<String>> = listOf(
        listOf("Vero", "Maintenance Report", "Daily Report"),
        listOf("Laundry", "Test Report"),
        listOf("Kitchen", "Test Report2", "Test Report3"),
        listOf("Ecoburner", "Test Report4", "Test Report5", "Test Report6")
    )

    //Header recycle view variables
    private lateinit var headerAdapter: RptHeaderAdapter
    private lateinit var headerRecyclerView: RecyclerView
    private lateinit var headerBtnArrayList: ArrayList<RptHeader>

    //Body recycle view variables
    private lateinit var bodyAdapter: RptBodyAdapter
    private lateinit var bodyRecyclerView: RecyclerView
    private lateinit var bodyBtnArrayList: ArrayList<RptBody>

    private var startPosition : Int= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //header recycler logic
        dataInitialize()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        headerRecyclerView = view.findViewById(R.id.reports_heading_rv)
        headerRecyclerView.layoutManager = layoutManager
        headerRecyclerView.setHasFixedSize(true)
        headerAdapter = RptHeaderAdapter(headerBtnArrayList, this@ReportFragment)
        headerRecyclerView.adapter = headerAdapter






        //body recycler logic
        bodyDataInitialize(startPosition)
        val bodLayoutManager = LinearLayoutManager(context)
        bodyRecyclerView = view.findViewById(R.id.reports_body_rv)
        bodyRecyclerView.layoutManager = bodLayoutManager
        bodyRecyclerView.setHasFixedSize(false)
        bodyAdapter = RptBodyAdapter(bodyBtnArrayList)
        bodyRecyclerView.adapter = bodyAdapter


    }

    private fun dataInitialize() {
        headerBtnArrayList = arrayListOf<RptHeader>()

        for (i in listNameAndTitle)
        {
            headerBtnArrayList.add(RptHeader(i[0]))

        }

    }


    private fun bodyDataInitialize(bodyPosition : Int) {
        bodyBtnArrayList = arrayListOf<RptBody>()
        var i: Int = 1

        while (i < listNameAndTitle[bodyPosition].size)
        {
            bodyBtnArrayList.add(RptBody(listNameAndTitle[bodyPosition][i]))
            i++
        }


    }

    override fun onItemClick(position: Int) {
        Toast.makeText(requireContext(), "this is: $position", Toast.LENGTH_SHORT).show()
        Log.i("console","what")
        bodyDataInitialize(position)
        bodyAdapter = RptBodyAdapter(bodyBtnArrayList)
        bodyRecyclerView.adapter = bodyAdapter
        bodyAdapter.notifyItemChanged(position)
    }
}