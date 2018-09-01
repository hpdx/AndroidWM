/*
 *    Copyright 2018 huangyz0918
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package com.watermark.androidwm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.watermark.androidwm.bean.WatermarkImage;
import com.watermark.androidwm.bean.WatermarkPosition;
import com.watermark.androidwm.bean.WatermarkText;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder class for setting default structural classes for watermark to use.
 *
 * @author huangyz0918 (huangyz0918@gmail.com)
 */
public final class WatermarkBuilder {
    private Context context;
    private Bitmap backgroundImg;
    private boolean isTileMode = false;

    private WatermarkImage watermarkImage;
    private WatermarkText watermarkText;
    private List<WatermarkText> watermarkTexts = new ArrayList<>();
    private List<WatermarkImage> watermarkBitmaps = new ArrayList<>();

    /**
     * Constructors for WatermarkBuilder
     */
    private WatermarkBuilder(@NonNull Context context, @NonNull Bitmap backgroundImg) {
        this.context = context;
        this.backgroundImg = backgroundImg;
    }

    private WatermarkBuilder(@NonNull Context context, @NonNull ImageView backgroundImageView) {
        this.context = context;
        backgroundFromImageView(backgroundImageView);
    }

    private WatermarkBuilder(@NonNull Context context, @DrawableRes int backgroundDrawable) {
        this.context = context;
        this.backgroundImg = BitmapFactory.decodeResource(context.getResources(), backgroundDrawable);
    }

    /**
     * to get an instance form class.
     *
     * @return instance of {@link WatermarkBuilder}
     */
    @SuppressWarnings("PMD")
    public static WatermarkBuilder create(Context context, Bitmap backgroundImg) {
        return new WatermarkBuilder(context, backgroundImg);
    }

    /**
     * to get an instance form class.
     * Load the background image from a {@link ImageView}。
     *
     * @return instance of {@link WatermarkBuilder}
     */
    @SuppressWarnings("PMD")
    public static WatermarkBuilder create(Context context, ImageView imageView) {
        return new WatermarkBuilder(context, imageView);
    }

    /**
     * to get an instance form class.
     * Load the background image from a DrawableRes。
     *
     * @return instance of {@link WatermarkBuilder}
     */
    @SuppressWarnings("PMD")
    public static WatermarkBuilder create(Context context, @DrawableRes int backgroundDrawable) {
        return new WatermarkBuilder(context, backgroundDrawable);
    }

    /**
     * Sets the {@link String} as the args
     * which ready for adding to a watermark.
     * Using the default position.
     *
     * @param inputText The text to add.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkText(@NonNull String inputText) {
        watermarkText = new WatermarkText(inputText);
        return this;
    }

    /**
     * Sets the {@link String} as the args
     * which ready for adding to a watermark.
     * Using the new position.
     *
     * @param inputText The text to add.
     * @param position  The position in the background image.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkText(@NonNull String inputText,
                                              @NonNull WatermarkPosition position) {
        watermarkText = new WatermarkText(inputText, position);
        return this;
    }

    /**
     * Sets the {@link String} as the args
     * which ready for adding to a watermark.
     *
     * @param watermarkString The {@link WatermarkText} object.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkText(@NonNull WatermarkText watermarkString) {
        watermarkText = watermarkString;
        return this;
    }

    /**
     * Sets the {@link String} as the args
     * which ready for adding to a watermark.
     * And, this is a set of Strings.
     *
     * @param watermarkTexts The texts to add.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkTexts(@NonNull List<WatermarkText> watermarkTexts) {
        this.watermarkTexts = watermarkTexts;
        return this;
    }

    /**
     * Sets the {@link Bitmap} as the args
     * which ready for adding to a background.
     * Using the default position.
     *
     * @param wmImg The image to add.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkImage(@NonNull Bitmap wmImg) {
        watermarkImage = new WatermarkImage(wmImg);
        return this;
    }

    /**
     * Sets the {@link Bitmap} as the args
     * which ready for adding to a background.
     * Using the new position.
     *
     * @param position The position in the background image.
     * @param wmImg    The bitmap to add into.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkImage(@NonNull Bitmap wmImg,
                                               @NonNull WatermarkPosition position) {
        watermarkImage = new WatermarkImage(wmImg, position);
        return this;
    }

    /**
     * Sets the {@link Bitmap} as the args
     * which ready for adding to a background.
     *
     * @param watermarkImg The {@link WatermarkImage} object.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkImage(@NonNull WatermarkImage watermarkImg) {
        watermarkImage = watermarkImg;
        return this;
    }

    /**
     * Sets the {@link Bitmap} as the args
     * which ready for adding into the background.
     * And, this is a set of bitmaps.
     *
     * @param bitmapList The bitmaps to add.
     * @return This {@link WatermarkBuilder}.
     */
    public WatermarkBuilder loadWatermarkImages(@NonNull List<WatermarkImage> bitmapList) {
        this.watermarkBitmaps = bitmapList;
        return this;
    }

    /**
     * Set mode to tile. We need to draw watermark over the
     * whole background.
     */
    public WatermarkBuilder setTileMode(boolean tileMode) {
        isTileMode = tileMode;
        return this;
    }

    /**
     * load a bitmap as background image from a ImageView.
     *
     * @param imageView the {@link ImageView} we need to use.
     */
    public WatermarkBuilder backgroundFromImageView(ImageView imageView) {
        imageView.invalidate();
        if (imageView.getDrawable() != null) {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            backgroundImg = drawable.getBitmap();
        }
        return this;
    }

    /**
     * let the watermark builder to build a new watermark object
     *
     * @return a new {@link Watermark} object
     */
    public Watermark getWatermark() {
        return new Watermark(
                context,
                backgroundImg,
                watermarkImage,
                watermarkBitmaps,
                watermarkText,
                watermarkTexts,
                isTileMode
        );
    }
}
