package com.example.program;

import android.content.Context;


import com.example.utils.ShaderHelper;
import com.example.utils.TextResourceReader;
import static android.opengl.GLES20.*;
/**
 * Created by Administrator on 2016/5/12.
 */
public class ShaderProgram {
    protected static final String U_MATRIX="u_Matrix",U_TEXTURE_UNIT="u_TextureUnit",
            A_POSITION="a_Position",A_COLOR="a_Color",A_TEXTURE_COORDINATES="a_TextureCoordinate";
    protected final int program;
    protected ShaderProgram(Context context,int vertexShaderReourceId,int fragmentShaderResourceId){
        program= ShaderHelper.buildProgram(TextResourceReader.readTextFromResource(context,vertexShaderReourceId),TextResourceReader.readTextFromResource(context,fragmentShaderResourceId));

    }
    public void useProgram(){
        glUseProgram(program);
    }

}
