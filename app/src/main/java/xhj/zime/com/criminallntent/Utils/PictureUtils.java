package xhj.zime.com.criminallntent.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/*
      在onResume(),onCreate()函数没有加载之前,谁也不知道photoView的大小是多少,为了避免可能过大
      加载很慢(Bitmap是个简单对象,它是不会进行源文件的压缩的,比如一张图片有5MB,但是实际加载的时候可能会有40MB).
      这时有两种解决方法,
      1.等photoView加载完毕(主线程会卡死)
      2.采用保守值估算(这时唯一可行的方法)
 */
public class PictureUtils {
    public static Bitmap getScaledBitmap(String path,int destWidth,int destHeight){
        //inJustDecodeBounds和inSampleSize是一起使用的,设置inJustDecodeBounds为true的时候就是说实际上
        //不显示图片,只是加载图片的宽和高加载到图片上,以便于缩略图的展示,获得倍数
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只加载图片的尺寸(像素大小)
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        //获得实际图片的大小 src表示源文件
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth){
            float scaleWidth = srcWidth / destWidth;
            float scaleHeight = srcHeight / destHeight;
            inSampleSize = Math.round(scaleWidth > scaleHeight ? scaleWidth : scaleHeight);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize  = inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path,size.x,size.y);
    }
}
