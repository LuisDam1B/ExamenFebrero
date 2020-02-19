package com.example.examenfebrero.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Foto {
	public static int TAKE_PICTURE = 1;
	public static int SELECT_PICTURE = 2;
	
	public static Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeight){
    	int width=mBitmap.getWidth();
    	int height=mBitmap.getHeight();
    	float scaleWidth=((float) newWidth/width);
    	float scaleHeight=((float) newHeight/height);
    	
    	Matrix matrix =new Matrix();
    	
    	matrix.postScale(scaleWidth, scaleHeight);
    	return Bitmap.createBitmap(mBitmap,0,0,width,height,matrix,false);
    	
    }

	public static Drawable convertirBitMapADrawable(Bitmap bitmap, Context context)
	{
		Drawable d = new BitmapDrawable(context.getResources(), bitmap);
		return d;
	}
	public static Bitmap convertirStringBitmap(String imagen) {
		if(imagen!=null)
		{
			byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
		//BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,)
		return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);}
		return null;
	}

	public static Bitmap convertirRecursoBitmap(int recurso, Context context)
	{

		return  BitmapFactory.decodeResource(context.getResources(), recurso);
	}

	public static String ConvertirImagenString(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(bitmap!=null)	{	bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
		byte[] byte_arr = stream.toByteArray();
		String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
		return image_str;}
		return  null;
	}

	public static byte[] getBytesFromBitmap(Bitmap bitmap) {
		if (bitmap!=null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
			return stream.toByteArray();
		}
		return null;
	}

	public static Bitmap getFromBitmapBytes(byte[] imagenbyte) {
		if (imagenbyte!=null) {
			return  BitmapFactory.decodeByteArray(imagenbyte,0, imagenbyte.length);
		}
		return null;
	}
}
