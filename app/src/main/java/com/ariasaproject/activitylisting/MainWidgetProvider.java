package com.ariasaproject.activitylisting;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;


public class MainWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
			      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			      
			      appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}