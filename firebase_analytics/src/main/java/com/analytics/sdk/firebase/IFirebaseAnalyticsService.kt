package com.analytics.sdk.firebase

import android.app.Activity
import com.analytics.service.IAnalyticsService

interface IFirebaseAnalyticsService : IAnalyticsService {
    fun init(activity: Activity)
}