package com.example.form_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.math.log

class ReportFormDataActivity : AppCompatActivity() {

    lateinit var svBtn : Button
    lateinit var custNameEt: EditText
    lateinit var locEt: EditText
    lateinit var dispModEt: EditText
    lateinit var dispSerEt: EditText
    lateinit var potenSoluEt: EditText
    lateinit var addInfoEt: EditText


    lateinit var path : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_form_data)


        //sets the title of the activity
        val bundle : Bundle? = intent.extras
        val actTitle = bundle!!.getString("category") + " - " + bundle!!.getString("report")
        title = actTitle

        //Finding
        custNameEt = findViewById(R.id.cust_name_et)
        locEt = findViewById(R.id.location_et)
        dispModEt = findViewById(R.id.dispen_model_et)
        dispSerEt = findViewById(R.id.dispen_serial_et)
        potenSoluEt = findViewById(R.id.poten_solu_et)
        addInfoEt = findViewById(R.id.add_info_et)
        svBtn = findViewById(R.id.save_btn)




        //save button logic
        svBtn.setOnClickListener{
            val excelNew = createExcelFile()
            fillNewExcel(excelNew)
        }

    }

    private fun createExcelFile( ) : String {
        //Finds the excel template find in the app's storage
        val documentsDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

        if (documentsDir != null) {
            val files = documentsDir.listFiles()

            for (file in files) {
                if (file.name.toString() == "New Service Request.xlsx")
                    path = file.absolutePath
            }
        }

        //create a copy of the template file
        val excelCur = File(path)
        val excelNew  = File(documentsDir?.absolutePath + "/" + File.separator + "today.xlsx")

        excelCur.copyTo(excelNew,overwrite = true)

       return excelNew.path
    }

    private fun fillNewExcel(excelNew : String)
    {

        val excelNewInput = FileInputStream(excelNew)
        Log.i(TAG, "$excelNew")
        val workbook = XSSFWorkbook(excelNewInput) //gets the entire excel document



        val sheet = workbook.getSheetAt(0) //gets a specific sheet of the excel

        //Customer names
        var row = sheet.getRow(4)
        var cell = row.getCell(11)
        cell.setCellValue(custNameEt.text.toString())

        //location
        row = sheet.getRow(5)
        cell = row.getCell(11)
        cell.setCellValue(locEt.text.toString())

        //dispenser model
        row = sheet.getRow(10)
        cell = row.getCell(2)
        cell.setCellValue(dispModEt.text.toString())

        //dispenser serial
        row = sheet.getRow(11)
        cell = row.getCell(2)
        cell.setCellValue(dispSerEt.text.toString())

        //potential solution
        row = sheet.getRow(14)
        cell = row.getCell(1)
        cell.setCellValue(potenSoluEt.text.toString())

        //additonal notes
        row = sheet.getRow(35)
        cell = row.getCell(2)
        cell.setCellValue(addInfoEt.text.toString())

        val outputStream = FileOutputStream(excelNew) //opens an output stream to put the workbook inside
        workbook.write(outputStream)
        outputStream.close()
        workbook.close()
    }


}