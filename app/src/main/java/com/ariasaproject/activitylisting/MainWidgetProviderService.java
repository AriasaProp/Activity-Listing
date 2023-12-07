package com.ariasaproject.activitylisting;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MainWidgetProviderService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(getApplicationContext());
    }

    public static final class WidgetItem {
        public final String mLabel;
        public final String mFile;

        public WidgetItem(String mLabel, String mFile) {
            this.mLabel = mLabel;
            this.mFile = mFile;
        }
    }

    public static class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private final List<WidgetItem> mWidgetItems = new ArrayList<>();
        private final Context mContext;

        public ListRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
            // for example downloading or creating content etc, should be deferred to onDataSetChanged()
            // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
            mWidgetItems.add(new WidgetItem("Label A", "description A long"));
            mWidgetItems.add(new WidgetItem("Label B", "description B long"));
            mWidgetItems.add(new WidgetItem("Label C", "description C long"));

        }

        @Override
        public void onDestroy() {
            mWidgetItems.clear();
        }

        @Override
        public int getCount() {
            return mWidgetItems.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            // Position will always range from 0 to getCount() - 1.
            WidgetItem widgetItem = mWidgetItems.get(position);

            // Construct remote views item based on the item xml file and set text based on position.
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_layout_item);
            rv.setTextViewText(R.id.widget_item, widgetItem.mLabel);

            // Next, we set a fill-intent which will be used to fill-in the pending intent template
            // which is set on the collection view in MainWidgetProvider
            Intent fillInIntent = new Intent().putExtra(MainWidgetProvider.EXTRA_CLICKED_FILE, widgetItem.mFile);
            rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

            // You can do heaving lifting in here, synchronously. For example, if you need to
            // process an image, fetch something from the network, etc., it is ok to do it here,
            // synchronously. A loading view will show up in lieu of the actual contents in the
            // interim.

            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            // You can create a custom loading view (for instance when getViewAt() is slow.) If you
            // return null here, you will get the default loading view.
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onDataSetChanged() {
            mWidgetItems.clear();

            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                WidgetItem item = new WidgetItem("mLabel: " + i + " id: " + random.nextInt(), "mFile: " + i);
                mWidgetItems.add(item);
            }
        }
    }
}