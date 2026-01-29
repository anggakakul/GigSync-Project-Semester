package com.anggayoedis.gigsync

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anggayoedis.gigsync.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, HomeFragment())
                .commit()
        }

        // --- FITUR BARU: KONFIRMASI KELUAR ---
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Cek apakah user sedang membuka halaman Detail? (Tumpukan Fragment > 0)
                if (supportFragmentManager.backStackEntryCount > 0) {
                    // Kalau ya, biarkan dia kembali ke halaman sebelumnya
                    supportFragmentManager.popBackStack()
                } else {
                    // Kalau user sudah di Home (Halaman Depan), tanya dulu sebelum keluar
                    showExitDialog()
                }
            }
        })
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi? 🚪")
            .setMessage("Apakah kamu yakin ingin menutup Inpo Gigs?")
            .setPositiveButton("Ya, Keluar") { _, _ -> finish() } // Tutup Aplikasi
            .setNegativeButton("Batal", null) // Gak jadi keluar
            .show()
    }
}