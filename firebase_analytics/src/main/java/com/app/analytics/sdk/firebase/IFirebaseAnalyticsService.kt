package com.app.analytics.sdk.firebase

import android.app.Activity
import com.app.analytics.service.IAnalyticsService

interface IFirebaseAnalyticsService : IAnalyticsService {
    fun init(activity: Activity)
}