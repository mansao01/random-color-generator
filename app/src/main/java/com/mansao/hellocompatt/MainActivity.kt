package com.mansao.hellocompatt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.core.content.ContextCompat
import com.mansao.hellocompatt.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private val arrayColor = arrayOf(
        "purple_200",
        "purple_500",
        "purple_700",
        "teal_200",
        "teal_700",
        "white",
        "pink",
        "orange",
        "mint",
        "yellow"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.tvGreeting.setTextColor(savedInstanceState.getInt("color"))
        }

        binding.btnChangeColor.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_change_color -> {
                val random = Random()
                val colorName = arrayColor[random.nextInt(10)]

                val colorResourceName = resources.getIdentifier(colorName, "color", packageName)

                val colorResource = ContextCompat.getColor(this, colorResourceName)

                binding.apply {
                    tvGreeting.setTextColor(colorResource)
                    tvName.setTextColor(colorResource)
                    btnChangeColor.setBackgroundColor(colorResource)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("color", binding.tvGreeting.currentTextColor)
    }
}