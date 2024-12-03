package com.example.spinnerautomulticompletetextview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private val workers = mutableListOf<Person>()

    private val positions = mutableListOf(
        "Инженер",
        "Конструктор",
        "Энергетик"
    )

    private lateinit var spinner : Spinner
    private lateinit var workerListLV : ListView
    private lateinit var nameET : EditText
    private lateinit var lastNameET : EditText
    private lateinit var ageET : EditText
    private lateinit var saveButtonBTN : Button
    private lateinit var toolbarTB : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameET = findViewById(R.id.nameET)
        lastNameET = findViewById(R.id.last_nameET)
        ageET = findViewById(R.id.ageET)
        saveButtonBTN = findViewById(R.id.saveButtonBTN)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        workerListLV = findViewById(R.id.workerListLV)
        val listViewAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            workers
        )
        workerListLV.adapter = listViewAdapter

        spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            positions
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        var item = ""
        val itemSelectedListener : AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    item = parent?.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        spinner.onItemSelectedListener = itemSelectedListener

        saveButtonBTN.setOnClickListener {
            workers.add(Person(nameET.text.toString(), lastNameET.text.toString(), ageET.text.toString().toInt(), item))
            listViewAdapter.notifyDataSetChanged()
            nameET.text.clear()
            lastNameET.text.clear()
            ageET.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exitmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exit -> {
                finish()
                exitProcess(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}