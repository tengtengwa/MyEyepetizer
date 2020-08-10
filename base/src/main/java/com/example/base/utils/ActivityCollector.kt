package com.example.base.utils

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*

object ActivityCollector {

    private val stack = LinkedList<WeakReference<Activity>>()

    fun pushTask(activityWR: WeakReference<Activity>) {
        stack.push(activityWR)
    }

    fun removeTask(activityWR: WeakReference<Activity>) {
        stack.remove(activityWR)
    }

    fun clearTask() {
        stack.clear()
    }

}