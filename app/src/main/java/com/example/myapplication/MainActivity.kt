package com.example.myapplication

import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var blinking = false
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        binding!!.button.setOnClickListener {
            if (binding!!.button.text == getString(R.string.turnon)) {
                binding!!.button.setText(R.string.turn_off)
                binding!!.flashimage.setImageResource(R.drawable.on)
                changeLightState(true)
            } else {
                binding!!.button.setText(R.string.turnon)
                binding!!.flashimage.setImageResource(R.drawable.off)
                changeLightState(false)
            }
        }
        binding!!.startBlinkflash.setOnClickListener {
            if (blinking) {
                stopBlinking()
            } else {
                startBlinking()
            }
        }
    }
    private fun startBlinking() {
        blinking = true
        binding!!.startBlinkflash.setText(R.string.stop_blinking)
        blinkflashlight()
    }

    private fun stopBlinking() {
        blinking = false
        handler?.removeCallbacksAndMessages(null)
        binding!!.startBlinkflash.setText(R.string.start_blinking)
        binding!!.flashimage.setImageResource(R.drawable.off)
        changeLightState(false)
    }

    private fun blinkflashlight() {
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        handler = Handler(Looper.getMainLooper())
        val mystring = "1010101010101010101010101010101010101010"
        val repetitions = 10

        val blinkRunnable = object : Runnable {
            var index = 0
            var repeat = 0

            override fun run() {
                if (!blinking) {
                    return
                }
                if (mystring[index] == '1') {
                    cameraManager.setTorchMode(cameraId, true)
                    binding!!.flashimage.setImageResource(R.drawable.on)
                }
                else {
                    cameraManager.setTorchMode(cameraId, false)
                    binding!!.flashimage.setImageResource(R.drawable.off)
                }
                index++

                if (index >= mystring.length) {
                    index = 0
                    repeat++
                    if (repeat >= repetitions) {
                        stopBlinking()
                        return
                    }
                }
                handler?.postDelayed(this, 100)
            }
        }
        handler?.post(blinkRunnable)
    }

    private fun changeLightState(state: Boolean) {
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, state)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error changing flashlight state", e)
        }
    }
    override fun onStart() {
        super.onStart()
        binding!!.button.setText(R.string.turnon)
        binding!!.startBlinkflash.setText(R.string.start_blinking)
    }
    override fun onDestroy() {
        super.onDestroy()
        changeLightState(false)
        binding = null
        handler?.removeCallbacksAndMessages(null)
    }
}
