package com.example.opengldemo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.example.utils.LoggerConfig;
import com.example.utils.ShaderHelper;
import com.example.utils.TextResourceReader;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
public class AirHockeyRenender implements Renderer{

	private static final int POSITION_COMPONENT_COUNT=2,BYTE_PER_FLOAT=4,COLOR_COMPONENT_COUNT=3,STRIDE=(POSITION_COMPONENT_COUNT+COLOR_COMPONENT_COUNT)*BYTE_PER_FLOAT;
	private FloatBuffer vertexData;
	private Context context;
	private int program;
	private static final String U_COLOR="u_Color",A_POSITION="a_Position",A_COLOR="a_Color";
	private int uColorLocation,aPositionLocation,aColorLocation;
	
	public AirHockeyRenender(Context context) {
		// TODO Auto-generated constructor stub
		float[] tableVertices={

								0f,0f,1f,1f,1f,
								-0.5f,-0.5f,0.7f,0.7f,0.7f,
								0.5f,-0.5f,0.7f,0.7f,0.7f,
								0.5f,0.5f,0.7f,0.7f,0.7f,
								-0.5f,0.5f,0.7f,0.7f,0.7f,
								-0.5f,-0.5f,0.7f,0.7f,0.7f,

								-0.5f,0f,1f,0f,0f,
								0.5f,0f,1f,0f,0f,

								0f,-0.25f,0f,0f,1f,
								0f,0.25f,1f,0f,0f
		};
		vertexData=ByteBuffer.allocateDirect(tableVertices.length*BYTE_PER_FLOAT)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexData.put(tableVertices);
		this.context=context;
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		String vertexShaderSource = TextResourceReader
				.readTextFromResource(context, R.raw.simple_vertex_shader);
		String fragmentShaderSource = TextResourceReader
				.readTextFromResource(context, R.raw.simple_fragment_shader);

		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

		program = ShaderHelper.linkProgram(vertexShader, fragmentShader);

		if (LoggerConfig.ON) {
			ShaderHelper.validateProgram(program);
		}

		glUseProgram(program);

		uColorLocation = glGetUniformLocation(program, U_COLOR);

		aColorLocation=glGetAttribLocation(program,A_COLOR);
		aPositionLocation = glGetAttribLocation(program, A_POSITION);

		// Bind our data, specified by the variable vertexData, to the vertex
		// attribute at location A_POSITION_LOCATION.
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT,
				false, STRIDE, vertexData);
		glEnableVertexAttribArray(aPositionLocation);

		vertexData.position(POSITION_COMPONENT_COUNT);
		glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT,
				false, STRIDE	, vertexData);
		glEnableVertexAttribArray(aColorLocation);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		glClear(GL_COLOR_BUFFER_BIT);

		// Draw the table.
//		glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
		glDrawArrays(GL_TRIANGLES, 0, 6);

		// Draw the center dividing line.
//		glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
		glDrawArrays(GL_LINES, 6, 2);

		// Draw the first mallet blue.
//		glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
		glDrawArrays(GL_POINTS, 8, 1);

		// Draw the second mallet red.
//		glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
		glDrawArrays(GL_POINTS, 9, 1);
	}

}
