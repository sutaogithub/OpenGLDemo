package com.example.utils;

import android.opengl.GLES20;
import android.util.Log;

import static android.opengl.GLES20.*;
/**
 * Created by zhangsutao on 2016/5/11.
 */
public class ShaderHelper {

    public static final  String TAG="SHADERHELPER";
    public static int compileVertexShader(String shaderCode){
        return compileShader(GL_VERTEX_SHADER,shaderCode);
    }
    public static int compileFragmentShader(String shaderCode){
        return compileShader(GL_FRAGMENT_SHADER,shaderCode);
    }

    private static int compileShader(int type,String shaderCode) {
        final int shaderId=glCreateShader(type);
        if(shaderId==0){
            if(LoggerConfig.ON){
                Log.w(TAG,"could not create  new shader");
            }
            return 0;
        }
        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);
        final int[] compileStatus=new int[1];
        glGetShaderiv(shaderId, GL_COMPILE_STATUS, compileStatus, 0);
        if(LoggerConfig.ON){
            Log.v(TAG,"result of compiling source:"+"\n"+shaderCode+"\n:"+glGetShaderInfoLog(shaderId));
        }
        if(compileStatus[0]==0){
            glDeleteShader(shaderId);
            if (LoggerConfig.ON) {
                Log.w(TAG, "Compilation of shader failed.");
            }
            return 0;
        }
        return shaderId;
    }
    public static int linkProgram(int vertexShaderId,int fragmentShaderId){
        final int programId=glCreateProgram();
        if(programId==0){
            if(LoggerConfig.ON){
                Log.w(TAG,"could not create  new program");
            }
            return 0;
        }

        glAttachShader(programId,vertexShaderId);
        glAttachShader(programId,fragmentShaderId);
        glLinkProgram(programId);
        final int[] linkStatus=new int[1];
        glGetProgramiv(programId,GL_LINK_STATUS,linkStatus,0);
        if (LoggerConfig.ON){
            Log.v(TAG,"result of lingking program:"+"\n"+glGetProgramInfoLog(programId));
        }
        if(linkStatus[0]==0){
            glDeleteProgram(programId);
            if (LoggerConfig.ON) {
                Log.w(TAG, "Linking of program failed.");
            }
            return 0;
        }

        return programId;
    }
    public static boolean validateProgram(int programId){
        glValidateProgram(programId);
        final int[] validateStatus=new int[1];
        glGetProgramiv(programId,GL_VALIDATE_STATUS,validateStatus,0);
        return  validateStatus[0]!=0;
    }
}
