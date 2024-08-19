package com.example.inovasi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle back arrow click to return to MainActivity
        findViewById<ImageView>(R.id.imageView2).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Handle the purchase button click
        findViewById<Button>(R.id.addToCartBtn).setOnClickListener {
            showPurchaseConfirmationDialog()
        }
    }

    private fun showPurchaseConfirmationDialog() {
        // Create an AlertDialog
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Pembelian")
            .setMessage("Apakah Anda yakin ingin membeli produk ini?")
            .setPositiveButton("Beli") { dialog, _ ->
                dialog.dismiss()
                showSuccessMessage()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSuccessMessage() {
        // Show a success message
        Toast.makeText(this, "Pembelian berhasil!", Toast.LENGTH_SHORT).show()

        // Delay and then redirect back to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2-second delay
    }
}
