package com.ariasaproject.activitylisting;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWidgetProvider extends AppWidgetProvider {
    final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Date currentDate = new Date();
        String td = timeFormat.format(currentDate);
        for (int appWidgetId : appWidgetIds) {
        	RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	        views.setTextViewText(R.id.widget_state, td);
	        appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }
}


