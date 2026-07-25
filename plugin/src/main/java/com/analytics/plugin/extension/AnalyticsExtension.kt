package com.analytics.plugin.extension

import com.analytics.plugin.extension.config.AnalyticsConfig
import com.analytics.plugin.extension.config.AppsFlyerConfig
import com.analytics.plugin.extension.config.FirebaseConfig
import com.analytics.plugin.extension.config.YandexMetricaConfig
import com.analytics.plugin.extension.info.AnalyticsInfo
import com.analytics.plugin.extension.info.AnalyticsName

open class AnalyticsExtension {
    val yandexMetrica = YandexMetricaConfig()
    val firebase = FirebaseConfig()
    val appsFlyer = AppsFlyerConfig()

    internal val analytics : MutableMap<AnalyticsInfo, AnalyticsConfig> = mutableMapOf()

    fun yandex(action: YandexMetricaConfig.() -> Unit) {
        yandexMetrica.enabled = true
        yandexMetrica.action()
        analytics[AnalyticsInfo(AnalyticsName.Yandex)] = yandexMetrica
    }

    fun firebase(action: FirebaseConfig.() -> Unit) {
        firebase.enabled = true
        firebase.action()
        analytics[AnalyticsInfo(AnalyticsName.Firebase)] = firebase
    }

    fun appsflyer(action: AppsFlyerConfig.() -> Unit) {
        appsFlyer.enabled = true
        appsFlyer.action()
        analytics[AnalyticsInfo(AnalyticsName.AppsFlyer)] = appsFlyer
    }
}