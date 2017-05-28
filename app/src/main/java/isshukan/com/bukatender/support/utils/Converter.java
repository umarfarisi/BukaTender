package isshukan.com.bukatender.support.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @author Muhammad Umar Farisi
 * @created 26/05/2017
 */
public class Converter {
    public static String convertBitmapToString(Bitmap bitmap){
        if(bitmap!=null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        }
        return null;
    }

    public static String convertProductNameToUrlFormat(String title) {
        return title.replace(" ","-");
    }
}
