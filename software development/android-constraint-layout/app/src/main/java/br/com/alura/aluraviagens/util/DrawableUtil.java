package br.com.alura.aluraviagens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class DrawableUtil {

    public static Drawable getDrawableFromName(Context context, String drawableName) {
        Resources resources = context.getResources();
        int drawableId = resources.getIdentifier(drawableName, "drawable", context.getPackageName());
        return resources.getDrawable(drawableId);
    }

}
