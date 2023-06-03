package com.example.form_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ReportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Header recycle view variables
    private lateinit var headerAdapter: RptHeaderAdapter
    private lateinit var headerRecyclerView: RecyclerView
    private lateinit var headerBtnArrayList: ArrayList<RptHeader>

    lateinit var titleHeaders : Array<String>

    //Body recycle view variables
    private lateinit var bodyAdapter: RptBodyAdapter
    private lateinit var bodyRecyclerView: RecyclerView
    private lateinit var bodyBtnArrayList: ArrayList<RptBody>

    lateinit var bodyNames :  Array<String>

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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReportFragment.
         */
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
        headerAdapter = RptHeaderAdapter(headerBtnArrayList)
        headerRecyclerView.adapter = headerAdapter

        //body recycler logic
        bodyDataInitialize()
        val bodLayoutManager = LinearLayoutManager(context)
        bodyRecyclerView = view.findViewById(R.id.reports_body_rv)
        bodyRecyclerView.layoutManager = bodLayoutManager
        bodyRecyclerView.setHasFixedSize(true)
        bodyAdapter = RptBodyAdapter(bodyBtnArrayList)
        bodyRecyclerView.adapter = bodyAdapter

    }

    private fun dataInitialize() {
        headerBtnArrayList = arrayListOf<RptHeader>()

        titleHeaders = arrayOf(
            "Vero",
            "Laundry",
            "Kitchen",
            "Ecoburner"
        )

        for (i in titleHeaders)
        {
            headerBtnArrayList.add(RptHeader(i))
        }
    }

    private fun bodyDataInitialize() {
        bodyBtnArrayList = arrayListOf<RptBody>()

        bodyNames = arrayOf(
            "Maintenance Report",
            "Daily Report",
        )

        for (i in bodyNames)
        {
            bodyBtnArrayList.add(RptBody(i))
        }
    }
}