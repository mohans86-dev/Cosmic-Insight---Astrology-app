package com.example.astrologyapp.ui.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.astrologyapp.R
import com.example.astrologyapp.data.model.BirthChart
import com.example.astrologyapp.data.model.ZodiacSign

class KundliChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var chart: BirthChart? = null
    private var onSignClickListener: ((ZodiacSign) -> Unit)? = null

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.cosmic_gold)
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }

    private val textPaintGold = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.cosmic_gold)
        textSize = 32f
        textAlign = Paint.Align.CENTER
    }

    private val textPaintWhite = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 28f
        textAlign = Paint.Align.CENTER
    }
    
    private val ascendantPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.cosmic_gold_dim)
        style = Paint.Style.FILL
    }

    // Grid representing South Indian chart:
    // [Pi][Ar][Ta][Ge]
    // [Aq]        [Ca]
    // [Cp]        [Le]
    // [Sg][Sc][Li][Vi]
    private val signGrid = arrayOf(
        arrayOf(ZodiacSign.PISCES, ZodiacSign.ARIES, ZodiacSign.TAURUS, ZodiacSign.GEMINI),
        arrayOf(ZodiacSign.AQUARIUS, null, null, ZodiacSign.CANCER),
        arrayOf(ZodiacSign.CAPRICORN, null, null, ZodiacSign.LEO),
        arrayOf(ZodiacSign.SAGITTARIUS, ZodiacSign.SCORPIO, ZodiacSign.LIBRA, ZodiacSign.VIRGO)
    )
    
    private var cellWidth = 0f
    private var cellHeight = 0f
    
    fun setChart(birthChart: BirthChart) {
        this.chart = birthChart
        invalidate()
    }
    
    fun setOnSignClickListener(listener: (ZodiacSign) -> Unit) {
        this.onSignClickListener = listener
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cellWidth = w / 4f
        cellHeight = h / 4f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Draw grid
        for (i in 0..4) {
            canvas.drawLine(i * cellWidth, 0f, i * cellWidth, height.toFloat(), linePaint)
            canvas.drawLine(0f, i * cellHeight, width.toFloat(), i * cellHeight, linePaint)
        }
        
        // Fill center space
        val centerPaint = Paint().apply { color = ContextCompat.getColor(context, R.color.cosmic_deep_navy) }
        canvas.drawRect(cellWidth, cellHeight, cellWidth * 3, cellHeight * 3, centerPaint)
        
        val chart = chart ?: return
        
        // Draw Ascendant text in center
        canvas.drawText("Lagna:", width / 2f, height / 2f - 20f, textPaintGold)
        canvas.drawText(chart.ascendant.abbreviation, width / 2f, height / 2f + 20f, textPaintWhite)

        for (row in 0..3) {
            for (col in 0..3) {
                val sign = signGrid[row][col] ?: continue
                
                val cx = col * cellWidth + cellWidth / 2f
                val cy = row * cellHeight + cellHeight / 2f
                
                // Highlight ascendant house
                if (sign == chart.ascendant) {
                    canvas.drawRect(
                        col * cellWidth, row * cellHeight,
                        (col + 1) * cellWidth, (row + 1) * cellHeight,
                        ascendantPaint
                    )
                }
                
                // Draw Sign abbreviation
                canvas.drawText(sign.abbreviation, cx, row * cellHeight + 40f, textPaintGold)
                
                // Find planets in this sign
                val planetsInSign = chart.planets.filter { it.sign == sign }
                
                // Draw planet abbreviations
                var textY = cy
                for (p in planetsInSign) {
                    canvas.drawText(p.planet.abbreviation, cx, textY, textPaintWhite)
                    textY += 30f
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val col = (event.x / cellWidth).toInt().coerceIn(0, 3)
            val row = (event.y / cellHeight).toInt().coerceIn(0, 3)
            
            val sign = signGrid[row][col]
            if (sign != null) {
                onSignClickListener?.invoke(sign)
                return true
            }
        }
        return true
    }
}
