package com.example.objects;

import com.example.data.VertexArray;
import com.example.program.TextureShaderProgram;
import com.example.utils.Constans;
import  static  android.opengl.GLES20.*;
/**
 * Created by Administrator on 2016/5/12.
 */
public class Table {
    private static final int POSITION_COMPONENT_COUNT=2, TEXTURE_COORDINATES_COMPONENT_COUNT=2,
            STRIDE=(POSITION_COMPONENT_COUNT+TEXTURE_COORDINATES_COMPONENT_COUNT)* Constans.BYTES_PER_FLOAT;
    private final VertexArray vertexArray;
    private static final float[] VERTEX_DATA={
        0f,0f,0.5f,0.5f,
        -0.5f,-0.8f,0f,0.9f,
            0.5f,-0.8f,1f,0.9f,
            0.5f,0.8f,1f,0.1f,
            -0.5f,0.8f,0f,0.1f,
            -0.5f,-0.8f,0f,0.9f,
    };
    public Table(){
        vertexArray=new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram textureProgram){
        vertexArray.setVertexAttribPointer(0,textureProgram.getPositionAttributeLocation(),POSITION_COMPONENT_COUNT,STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT,textureProgram.getTextureCoordinatesAttributeLocation(),TEXTURE_COORDINATES_COMPONENT_COUNT,STRIDE);
    }
    public void draw(){
        glDrawArrays(GL_TRIANGLE_FAN,0,6);
    }



}
