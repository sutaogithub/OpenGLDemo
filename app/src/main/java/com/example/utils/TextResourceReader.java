package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class TextResourceReader {
	
	public static String readTextFromResource(Context context,int resId){
		StringBuilder builder=new StringBuilder();
		InputStream input = null;
		BufferedReader reader = null;
		String line;
		try {
			 input=context.getResources().openRawResource(resId);
			 reader=new BufferedReader(new InputStreamReader(input));
			while((line=reader.readLine())!=null){
				builder.append(line+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				reader.close();
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return builder.toString();

		
	}

}
