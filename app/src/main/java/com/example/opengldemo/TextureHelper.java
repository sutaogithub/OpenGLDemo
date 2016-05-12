package com.example.opengldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.utils.LoggerConfig;

import  static android.opengl.GLES20.*;
/**
 * Created by Administrator on 2016/5/12.
 */
public class TextureHelper {
    public static final String TAG="TextureHelper";
    public static int loadTexture(Context context,int resId){
        final int[] textureId=new int[1];
        glGenTextures(1,textureId,0);
        if(textureId[0]==0){
            if(LoggerConfig.ON){
                Log.w(TAG,"could not generate a new OpenGL texture object");
            }
            return  0;
        }
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        final Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),resId,options);
        if(bitmap==null){
            if(LoggerConfig.ON)
                Log.w(TAG,"resource Id "+resId+"could not be decode");
            glDeleteTextures(1,textureId,0);
            return 0;
        }
        glBindTexture(GL_TEXTURE_2D,textureId[0]);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        GLUtils.texImage2D(GL_TEXTURE_2D,0,bitmap,0);
        bitmap.recycle();
        glGenerateMipmap(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D,0);
        return textureId[0];
    }

}
