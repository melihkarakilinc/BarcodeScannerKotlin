package com.melihkarakilinc.barcodescanner

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator
import android.R.attr.data
import android.widget.Toast

import com.google.zxing.integration.android.IntentResult







class MainActivity : AppCompatActivity() {
    private lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById(R.id.button)
        button.setOnClickListener {
            //Bu activity içinde çalıştırıyoruz.
            //Bu activity içinde çalıştırıyoruz.
            val integrator = IntentIntegrator(this)
            //Kütüphanede bir kaç kod tipi var biz hepsini tarayacak şekilde çalıştırdık.
            //integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            //şeklindede sadece qr code taratabilirsiniz.
            //Kütüphanede bir kaç kod tipi var biz hepsini tarayacak şekilde çalıştırdık.
            //integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            //şeklindede sadece qr code taratabilirsiniz.
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            //Kamera açıldığında aşağıda yazı gösterecek
            //Kamera açıldığında aşağıda yazı gösterecek
            integrator.setPrompt("TARA")
            //telefonun kendi kamerasını kullandırıcaz
            //telefonun kendi kamerasını kullandırıcaz
            integrator.setCameraId(0)
            //okuduğunda 'beep' sesi çıkarır
            //okuduğunda 'beep' sesi çıkarır
            integrator.setBeepEnabled(true)
            //okunan barkodun image dosyasını kaydediyor
            //okunan barkodun image dosyasını kaydediyor
            integrator.setBarcodeImageEnabled(false)
            //scan başlatılıyor
            //scan başlatılıyor
            integrator.initiateScan()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {

            } else {
                Toast.makeText(this,"{${result.contents}}",Toast.LENGTH_SHORT).show()
//                text_qr_code_sonuc.setText("Kod Sonucu:")
//                txt_sonuc.setText(result.contents)
//                kodsonucu = txt_sonuc.getText().toString()
//                txt_code_kind.setText("Kod Türü:")
//                txt_qr_code_kind_result.setText(result.formatName)
                button.text = "KAYDET"
            }
        }
    }
}