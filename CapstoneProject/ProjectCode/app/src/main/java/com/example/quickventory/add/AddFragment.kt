package com.example.quickventory.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quickventory.R

import android.content.pm.PackageManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*


private const val CAMERA_REQUEST_CODE = 101

class AddFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setupPermissions()
        codeScanner(view)
        return view
    }

    private fun codeScanner(view: View){
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(activity!!, scannerView)

        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            activity!!.runOnUiThread {
                //onScan()
                //Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                //}
                onScan(view)
                var txt = view.findViewById(R.id.textView) as TextView
                txt.text = "Scan result: ${it.text}"
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            activity!!.runOnUiThread {
                Toast.makeText(activity!!, "Camera initialization error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener{
            codeScanner.startPreview()
        }
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }
    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    private fun setupPermissions() {
        val permission:Int = ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "You need the camera permission to be able to run this app!", Toast.LENGTH_SHORT)
                } else {
                    //successful
                }
            }
        }
    }
    fun onScan(view: View){
        var play = view.findViewById(R.id.button_send) as Button
        var play2 = view.findViewById(R.id.button_send2) as Button
        var txt = view.findViewById(R.id.textView) as TextView
        var txt2 = view.findViewById(R.id.tv_textView) as TextView
        txt.visibility = View.VISIBLE
        txt2.visibility = View.INVISIBLE
        play2.visibility = View.VISIBLE
        play.isClickable=true
        play.visibility= View.VISIBLE // v letter should be capital
    }

    fun sendMessage(view: View) {
        var play = view.findViewById(R.id.button_send) as Button
        var play2 = view.findViewById(R.id.button_send2) as Button
        var txt = view.findViewById(R.id.textView) as TextView
        var txt2 = view.findViewById(R.id.tv_textView) as TextView
        play.isClickable=false
        play2.visibility = View.INVISIBLE
        play.visibility = View.INVISIBLE
        txt.visibility = View.INVISIBLE
        txt2.visibility = View.VISIBLE

        codeScanner.startPreview()
    }


}