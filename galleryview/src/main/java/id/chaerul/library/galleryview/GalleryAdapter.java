package id.chaerul.library.galleryview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<String> imageUrls = new ArrayList<>();
    private List<Integer> imageResIds = new ArrayList<>();
    private boolean isUsingResource = false;

    public void setImageUrls(List<String> urls) {
        this.imageUrls = urls;
        this.isUsingResource = false;
        notifyDataSetChanged();
    }

    public void setImageResIds(List<Integer> resIds) {
        this.imageResIds = resIds;
        this.isUsingResource = true;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());

        int width;
        if ((isUsingResource ? imageResIds.size() : imageUrls.size()) == 1) {
            width = ViewGroup.LayoutParams.MATCH_PARENT; // gambar tunggal, full lebar
        } else {
            width = parent.getMeasuredWidth() / 2; // default 2 kolom
        }

        imageView.setLayoutParams(new ViewGroup.LayoutParams(width, width));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ViewHolder(imageView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isUsingResource) {
            holder.imageView.setImageResource(imageResIds.get(position));
        } else {
            Glide.with(holder.imageView.getContext())
                    .load(imageUrls.get(position))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return isUsingResource ? imageResIds.size() : imageUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
