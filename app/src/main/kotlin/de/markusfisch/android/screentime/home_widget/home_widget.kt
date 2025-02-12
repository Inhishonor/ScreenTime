package de.markusfisch.android.screentime.home_widget
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class ScreenTimeWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        // Get the current screen time from SharedPreferences
        val sharedPreferences = context.getSharedPreferences("ScreenTimePrefs", Context.MODE_PRIVATE)
        val currentScreenTime = sharedPreferences.getLong("currentScreenTime", 0L)

        // Convert milliseconds to minutes
        val minutes = currentScreenTime / 60000

        // Update the widget UI
        val views = RemoteViews(context.packageName, R.layout.screen_time_widget_layout)
        views.setTextViewText(R.id.screenTimeTextView, widget_time_today, minutes)

        // Update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}