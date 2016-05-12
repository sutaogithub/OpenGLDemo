package com.example.program;

import android.content.Context;

import com.example.opengldemo.R;
import static android.opengl.GLES20.*;
/**
 * Created by Administrator on 2016/5/12.
 */
public class TextureShaderProgram extends ShaderProgram {

    private final int uMatrixLocation,uTextureUnitLocation,aPositionLocation,aTextureCoordinatesLocation;
    public TextureShaderProgram(Context context){
        super(context, R.raw.texture_vertex_shader,R.raw.texture_fragment_shader);
        uMatrixLocation=glGetUniformLocation(program,U_MATRIX);
        uTextureUnitLocation=glGetUniformLocation(program,U_TEXTURE_UNIT);
        aPositionLocation=glGetUniformLocation(program,A_POSITION);
        aTextureCoordinatesLocation=glGetUniformLocation(program,A_TEXTURE_COORDINATES);

    }
    public void setUniforms(float[] matrix,int textureId){
        glUniformMatrix4fv(uMatrixLocation,1,false,matrix,0);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,textureId);
        glUniform1i(uTextureUnitLocation,0);
    }
    public int getPositionLocation(){
        return aPositionLocation;
    }
    public int getTextureCoordinatesLocation(){
        return aTextureCoordinatesLocation;
    }

}
