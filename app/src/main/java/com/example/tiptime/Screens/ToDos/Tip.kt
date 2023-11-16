package com.example.tiptime.Screens.ToDos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiptime.R
import com.example.tiptime.databinding.FragmentTipBinding
import java.text.NumberFormat


class Tip : Fragment() {

    private lateinit var binding: FragmentTipBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipBinding.inflate(inflater, container, false)
        binding.calculateButton.setOnClickListener{calculateTip()}
        return binding.root

    }
    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}