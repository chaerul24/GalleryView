package id.chaerul.library.galleryview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryView extends FrameLayout {

    private RecyclerView recyclerView;
    private GalleryAdapter adapter;
    private int columnCount = 1;
    private int maxItemCount = 4;

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        adapter = new GalleryAdapter();
        recyclerView.setAdapter(adapter);
        addView(recyclerView);
    }

    public void setColumnCount(int count) {
        this.columnCount = count;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
    }

    public void setMaxItemCount(int max) {
        this.maxItemCount = max;
    }

    public void setImages(List<String> imageUrls) {
        if (imageUrls.size() == 1) {
            setColumnCount(1);
        } else {
            setColumnCount(columnCount);
        }

        if (imageUrls.size() > maxItemCount) {
            adapter.setImageUrls(imageUrls.subList(0, maxItemCount));
        } else {
            adapter.setImageUrls(imageUrls);
        }
    }

    public void setImagesFromRes(List<Integer> imageResIds) {
        if (imageResIds.size() == 1) {
            setColumnCount(1);
        } else {
            setColumnCount(columnCount);
        }

        if (imageResIds.size() > maxItemCount) {
            adapter.setImageResIds(imageResIds.subList(0, maxItemCount));
        } else {
            adapter.setImageResIds(imageResIds);
        }
    }

}
