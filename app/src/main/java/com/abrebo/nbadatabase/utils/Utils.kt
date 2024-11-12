package com.abrebo.nbadatabase.utils

import android.os.SystemClock
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.abrebo.nbadatabase.data.model.Player

object BackPressUtils {

    private var lastBackPressedTime: Long = 0
    fun setBackPressCallback(fragment: Fragment, lifecycleOwner: LifecycleOwner) {
        val backButtonCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = SystemClock.elapsedRealtime()
                if (lastBackPressedTime + 2000 > currentTime) {
                    fragment.activity?.finishAffinity()
                } else {
                    Toast.makeText(fragment.context, "Çıkmak için tekrar basın", Toast.LENGTH_SHORT).show()
                }
                lastBackPressedTime = currentTime
            }
        }

        fragment.requireActivity().onBackPressedDispatcher.addCallback(lifecycleOwner, backButtonCallback)
    }
}

fun Player.sumAllIntAttributes(): Int {
    return closeShot + midRangeShot + threePointShot + freeThrow + shotIQ + offensiveConsistency +
            layup + standingDunk + drivingDunk + postHook + postFade + postControl + drawFoul + hands +
            interiorDefense + perimeterDefense + steal + block + helpDefenseIQ + passPerception +
            defensiveConsistency + speed + agility + strength + vertical + stamina + hustle +
            overallDurability + passAccuracy + ballHandle + speedWithBall + passIQ + passVision +
            offensiveRebound + defensiveRebound
}


