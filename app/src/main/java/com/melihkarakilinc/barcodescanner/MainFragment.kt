package com.melihkarakilinc.barcodescanner

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.melihkarakilinc.barcodescanner.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            integrator.setPrompt("TARA")
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(false)
            integrator.initiateScan()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireActivity(),"NULL", Toast.LENGTH_SHORT).show()
            } else {

                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage(result.contents)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    .setPositiveButton("Ok", DialogInterface.OnClickListener {
                            dialog, id ->
                        dialog.dismiss()
                    })

                val alert = dialogBuilder.create()
                alert.setTitle("Test")
                alert.show()
                Toast.makeText(requireActivity(), result.contents, Toast.LENGTH_SHORT).show()
//                text_qr_code_sonuc.setText("Kod Sonucu:")
//                txt_sonuc.setText(result.contents)
//                kodsonucu = txt_sonuc.getText().toString()
//                txt_code_kind.setText("Kod Türü:")
//                txt_qr_code_kind_result.setText(result.formatName)
            }
        }
    }
}