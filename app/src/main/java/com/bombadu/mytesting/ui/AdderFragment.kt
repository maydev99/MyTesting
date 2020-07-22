package com.bombadu.mytesting.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.bombadu.mytesting.R
import com.bombadu.mytesting.database.MyData
import com.bombadu.mytesting.database.MyViewModel
import com.bombadu.mytesting.util.Util
import kotlinx.android.synthetic.main.fragment_adder.*


class AdderFragment : Fragment(), NumberPicker.OnValueChangeListener {

    private var num1: Int = 0
    private var num2: Int = 0
    private var numberMax = 20 //default max
    private lateinit var myViewModel: MyViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//Don't Forget This for the Options Menu****

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNumberPickers()
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)


    }


    private fun setupNumberPickers() {
        numPicker1.minValue = 0
        numPicker1.maxValue = numberMax
        numPicker2.minValue = 0
        numPicker2.maxValue = numberMax
        numPicker1.setOnValueChangedListener(this)
        numPicker2.setOnValueChangedListener(this)


        //Using values from SavedInstance to handle device rotation
        loadNpValues()
        numPicker1.value = num1
        numPicker2.value = num2
        textView.text = "${calculate(numPicker1.value, numPicker2.value)}"


    }

    fun calculate(num1: Int, num2: Int): Int {
        return num1 + num2
    }



    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        saveNpValues()
        textView.text = "${calculate(numPicker1.value, numPicker2.value)}"

    }

    private fun saveNpValues() {
        val sharedPrefs: SharedPreferences = this.requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt("num1_prefs", numPicker1.value)
        editor.putInt("num2_prefs", numPicker2.value)
        editor.apply()

    }

    private fun loadNpValues() {
        val sharedPrefs: SharedPreferences = this.requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        num1 = sharedPrefs.getInt("num1_prefs", 0)
        num2 = sharedPrefs.getInt("num2_prefs", 0)
    }

    override fun onStart() {
        super.onStart()
        //Sets Max Number in NumberPicker to value from Settings Screen
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        numberMax = sharedPreferences.getInt("max_number", 0)
        setupNumberPickers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.adder_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save -> {

                saveData(textView.text.toString().toInt())
                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveData(number: Int) {
        val utils = Util()

        val myDate = utils.getCurrentDate()
        val myTime = utils.getCurrentTime()
        //val number = textView.text.toString().toInt()
        val newData = MyData(
            myDate,
            myTime,
            number
        )

        println(newData.number)
        myViewModel.insertData(newData)

    }


}