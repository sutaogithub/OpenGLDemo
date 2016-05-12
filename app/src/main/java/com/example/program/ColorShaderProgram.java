package com.example.program;

import android.content.Context;

import com.example.opengldemo.R;

import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ColorShaderProgram extends ShaderProgram {
    private final int uMatrixLocation,aPositionLocation,aColorLocation;
    public ColorShaderProgram(Context context){
        super(context, R.raw.texture_vertex_shader,R.raw.texture_fragment_shader);
        uMatrixLocation=glGetUniformLocation(program,U_MATRIX);
        aColorLocation=glGetUniformLocation(program,A_COLOR);
        aPositionLocation=glGetUniformLocation(program,A_POSITION);
    }
    public int getPositionAttributeLocation(){
        return aPositionLocation;
    }
    public int getColorAttributeLocation(){
        return aColorLocation;
    }
    public  void setUniforms(float[] matrix){
        glUniformMatrix4fv(uMatrixLocation,1,false,matrix,0);
    }


}
