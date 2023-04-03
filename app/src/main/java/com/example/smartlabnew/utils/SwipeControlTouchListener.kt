package com.example.smartlabnew.utils

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

enum class SwipeDirection {
    ALL, LEFT, RIGHT, NONE
}

class SwipeControlTouchListener : RecyclerView.OnItemTouchListener {

    private var initialXValue = 0f
    private var direction: SwipeDirection = SwipeDirection.ALL

    fun setSwipeDirection(direction: SwipeDirection) {
        this.direction = direction
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return !isSwipeAllowed(e)
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    private fun isSwipeAllowed(event: MotionEvent): Boolean {
        if (direction == SwipeDirection.ALL) return true
        if (direction == SwipeDirection.NONE) return false
        if (event.action == MotionEvent.ACTION_DOWN) {
            initialXValue = event.x
            return true
        }

        if (event.action == MotionEvent.ACTION_MOVE) {
            val diffX = event.x - initialXValue
            if (diffX > 0 && direction == SwipeDirection.RIGHT) {
                return false
            } else if (diffX < 0 && direction == SwipeDirection.LEFT) {
                return false
            }
        }
        return true
    }

}