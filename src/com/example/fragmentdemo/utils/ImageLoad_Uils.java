package com.example.fragmentdemo.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/*******************************
 * 
 * @ClassName ImageLoad_Uils
 * @ClassDescription image load tools
 * @version 1.0.0
 * @author GeorgeShao
 * @time 2014-10-8 16:30:01
 */
public class ImageLoad_Uils {

	private static com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader
			.getInstance();
	private static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	/**
	 * 
	 * @param imageView
	 * 
	 * @param imageUrl
	 * 
	 * @param isCache
	 * 
	 * @param scaleType
	 * 
	 * @param defaultImage
	 * 
	 */
	public static void setImage(ImageView imageView, String imageUrl,
			boolean isCache, ScaleType scaleType, int defaultImage,
			Context context) {

		imageView.setScaleType(scaleType);
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(isCache).cacheOnDisk(isCache)
				.considerExifParams(true).showImageOnLoading(defaultImage)
				.showImageForEmptyUri(defaultImage)
				.displayer(new RoundedBitmapDisplayer(20))
				.displayer(new FadeInBitmapDisplayer(100))
				.showImageOnFail(defaultImage).build();

		imageLoader.displayImage(imageUrl, imageView, displayImageOptions,
				animateFirstListener);

	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
