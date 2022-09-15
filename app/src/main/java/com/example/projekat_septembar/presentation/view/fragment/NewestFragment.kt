package com.example.projekat_septembar.presentation.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat_septembar.R
import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.databinding.FragmentNewestBinding
import com.example.projekat_septembar.presentation.contract.CarContract
import com.example.projekat_septembar.presentation.view.recycler.adapters.CarAdapter
import com.example.projekat_septembar.presentation.view.states.CarState
import com.example.projekat_septembar.presentation.viewmodel.CarViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class NewestFragment: Fragment() {


    private val employeeViewModel: CarContract.ViewModel by sharedViewModel<CarViewModel>()
    private var _binding: FragmentNewestBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CarAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        employeeViewModel.fetchAllCarsFromServer()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        binding.carRv.layoutManager = LinearLayoutManager(context)
        adapter = CarAdapter(::openDetailed)//callback za on click
        binding.carRv.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        binding.carRv.adapter = adapter


//        binding.carRv.addOnScrollListener(object : RecyclerView.OnScrollListener() { todo paginacija
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1)){
//                    employeeViewModel.load10Employees(false)
//                }
//            }
//        })
    }


    private fun openDetailed(car: Car){
        if (!car.availability){
            Toast.makeText(context, "Car is not available", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = AlertDialog.Builder(activity,R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.options_dialog_box,null)

        val  cancelBtn = view.findViewById<Button>(R.id.cancelBtn)
        val  okBtn = view.findViewById<Button>(R.id.okBtn)
        val  radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupEmployees)

        builder.setView(view)

        cancelBtn.setOnClickListener {
            builder.dismiss()
        }

        okBtn.setOnClickListener {
            val radioButton =view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            if (radioButton == null) {
                Toast.makeText(context, "Please select option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when(radioButton.text.toString()){
                "Contact seller" -> println("contact button")
                "Save" -> println("save button")
                else -> println("error")
            }
            builder.dismiss()
        }

        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    private fun startUpdateActivity(id: Long) {
//        val intent = Intent(activity, UpdateEmployeeActivity::class.java)
//        intent.putExtra("id", id)
//        doAction.launch(intent)
    }

    private val doAction: ActivityResultLauncher<Intent> = registerForActivityResult( ActivityResultContracts.StartActivityForResult()) {
//        if (it.resultCode == Activity.RESULT_OK) {
//            val data = it.data!!
//
//            val id = data.getLongExtra("id",0)
//            val newName = data.getStringExtra("name")!!
//            val newSalary = data.getIntExtra("salary",0)
//            val newAge = data.getIntExtra("age",0)
//
//            employeeViewModel.updateEmployee(id,newName,newSalary,newAge)
//        }
    }

//    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        employeeViewModel.carState.observe(viewLifecycleOwner) { carState ->
            Timber.e(carState.toString())
            renderState(carState)
        }

//        employeeViewModel.gradualRvList.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//            adapter.notifyDataSetChanged()
//        }
//
//        employeeViewModel.getAllEmployees()
    }


    private fun renderState(state: CarState) {
        when (state) {
            is CarState.Success -> {
                adapter.submitList(state.cars)
            }
            is CarState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_SHORT).show()
            }
            is CarState.Loading -> {
                println("Loading")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}