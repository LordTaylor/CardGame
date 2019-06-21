package com.lordtaylor.cardgame.game_logic

import android.util.Log
import com.lordtaylor.cardgame.models.SimpleCard

object WinConditions {
    enum class Figures(val position: Int) {
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(1)
    }

    private var stairsCounter = 0;
    private val TAG = "WinConditions"
    private var colorMap = intArrayOf(0, 0, 0, 0)
    private var figureMap = mutableMapOf<Int, Int>()
    fun checkWinConditions(cards: List<SimpleCard>): Boolean {
        colorMap = intArrayOf(0, 0, 0, 0)
        figureMap = mutableMapOf<Int, Int>()
        cards.forEach {
            when (it.suit) {
                in "HEARTS" -> colorMap[0]++
                in "DIAMONDS" -> colorMap[1]++
                in "CLUBS" -> colorMap[2]++
                in "SPADES" -> colorMap[3]++
            }
            var num = figureMap.getOrPut(convertToInt(it.value), { 0 })
            figureMap.put(convertToInt(it.value), ++num)

        }
        figureMap = figureMap.toSortedMap()
        figureMap.forEach {
            Log.d(TAG, "key : ${it.key}, value: ${it.value}")
        }
        return checkColors()
    }

    private fun convertToInt(value: String): Int {
        return when (value) {
            in Figures.KING.name -> Figures.KING.position
            in Figures.QUEEN.name -> Figures.QUEEN.position
            in Figures.JACK.name -> Figures.JACK.position
            in Figures.ACE.name -> Figures.ACE.position
            else -> value.toInt()
        }
    }

    private fun checkColors(): Boolean {
        colorMap.forEach {
            if (it >= 3) {
                Log.d(TAG, "checkColors ${it}")
                return true
            }
        }
        return checkFigures()
    }

    private fun checkFigures(): Boolean {
        var fig = 0
        figureMap.forEach {
            when (it.key) {
                in Figures.JACK.position..Figures.KING.position -> fig++
                Figures.ACE.position -> fig++
                else -> Log.e(TAG, "error ${it.key}")
            }
            if (fig >= 3) {
                Log.d(TAG, "checkFigures $fig")
                return true
            }
        }
        return checkStairs()
    }

    private fun checkStairs(): Boolean {

        var check = false
        var tmpVal = -1
        figureMap.forEach {
            when (it.key) {
                Figures.KING.position -> {
                    check = checkIncreasing(tmpVal, Figures.KING.position)
                    tmpVal = Figures.KING.position;
                }
                Figures.QUEEN.position -> {
                    check = checkIncreasing(tmpVal, Figures.QUEEN.position)
                    tmpVal = Figures.QUEEN.position
                }
                Figures.JACK.position -> {
                    check = checkIncreasing(tmpVal, Figures.JACK.position)
                    tmpVal = Figures.JACK.position
                }
                Figures.ACE.position -> {
                    check = checkIncreasing(tmpVal, Figures.ACE.position)
                    tmpVal = Figures.ACE.position
                }
                it.key -> {
                    check = checkIncreasing(tmpVal, it.key)
                    tmpVal = it.key
                }
            }
            if (stairsCounter >= 3) {
                Log.d(TAG, "checkStairs")
                return true
            }
        }
        return checkSame()
    }

    private fun checkIncreasing(tmp: Int, curVal: Int): Boolean {
        if ((tmp - 1) == curVal) {
            stairsCounter++
            return true
        }
        stairsCounter = 0
        return false
    }


    private fun checkSame(): Boolean {
        figureMap.forEach {
            if (it.value > 3) {
                Log.d(TAG, "checkSame")
                return true
            }
        }
        return false
    }
}