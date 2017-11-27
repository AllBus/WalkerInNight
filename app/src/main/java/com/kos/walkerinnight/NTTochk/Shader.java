package com.kos.walkerinnight.NTTochk;


import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Shader {
	//����� ������� ������ �� ��������� ���������
	//������ ������ ��� ��������� ����
	private int program_Handle;
	private int mPositionHandle;
	private int mPositionTexHandle;

	public int getProgram() {
		return program_Handle;
	}

	//��� �������� ������� ������ �������� � �����������
	//������ ���� ���������� � ������������ �������
	public Shader(String vertexShaderCode, String fragmentShaderCode) {
		//�������� �����, ��������� ��������� ���������
		//��� ���� ����������� ���� program_Handle
		createProgram(vertexShaderCode, fragmentShaderCode);
	}

	// �����, ������� ������� ��������� ���������, ���������� � ������������
	private void createProgram(String vertexShaderCode, String fragmentShaderCode) {

		int vertexShader_Handle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		GLES20.glShaderSource(vertexShader_Handle, vertexShaderCode);
		GLES20.glCompileShader(vertexShader_Handle);

		int fragmentShader_Handle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		GLES20.glShaderSource(fragmentShader_Handle, fragmentShaderCode);
		GLES20.glCompileShader(fragmentShader_Handle);

		program_Handle = GLES20.glCreateProgram();
		GLES20.glAttachShader(program_Handle, vertexShader_Handle);
		GLES20.glAttachShader(program_Handle, fragmentShader_Handle);
		GLES20.glLinkProgram(program_Handle);
	}

	//�����, ������� ���������
	//����� ��������� ������ vertexBuffer � ��������� a_vertex
	public void linkVertexBuffer(FloatBuffer vertexBuffer) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� ������� a_vertex
		int a_vertex_Handle = GLES20.glGetAttribLocation(program_Handle, "a_vertex");
		//�������� ������������� �������� a_vertex
		GLES20.glEnableVertexAttribArray(a_vertex_Handle);
		//��������� ����� ��������� ������ vertexBuffer � ��������� a_vertex
		GLES20.glVertexAttribPointer(
				a_vertex_Handle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
	}

	public void linkVertexAndNormalBuffer(FloatBuffer vertexBuffer, FloatBuffer normalBuffer) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� ������� a_vertex
		int a_vertex_Handle = GLES20.glGetAttribLocation(program_Handle, "a_vertex");
		//�������� ������������� �������� a_vertex
		GLES20.glEnableVertexAttribArray(a_vertex_Handle);
		//��������� ����� ��������� ������ vertexBuffer � ��������� a_vertex
		GLES20.glVertexAttribPointer(
				a_vertex_Handle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

		//�������� ������ �� ������� a_normal
		int a_normal_Handle = GLES20.glGetAttribLocation(program_Handle, "a_normal");
		//�������� ������������� �������� a_normal
		GLES20.glEnableVertexAttribArray(a_normal_Handle);
		//��������� ����� �������� normalBuffer � ��������� a_normal
		GLES20.glVertexAttribPointer(
				a_normal_Handle, 3, GLES20.GL_FLOAT, false, 0, normalBuffer);
	}

	//�����, ������� ���������
	//����� ��������� �������� �������� normalBuffer � ��������� a_normal
	public void linkNormalBuffer(FloatBuffer normalBuffer) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� ������� a_normal
		int a_normal_Handle = GLES20.glGetAttribLocation(program_Handle, "a_normal");
		//�������� ������������� �������� a_normal
		GLES20.glEnableVertexAttribArray(a_normal_Handle);
		//��������� ����� �������� normalBuffer � ��������� a_normal
		GLES20.glVertexAttribPointer(
				a_normal_Handle, 3, GLES20.GL_FLOAT, false, 0, normalBuffer);
	}


	//�����, ������� ��������� ������� ������-����-��������
	// modelViewProjectionMatrix � ��������� u_modelViewProjectionMatrix
	public void linkModelViewProjectionMatrix(float[] modelViewProjectionMatrix) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� �������� u_modelViewProjectionMatrix
		int u_modelViewProjectionMatrix_Handle =
				GLES20.glGetUniformLocation(program_Handle, "u_modelViewProjectionMatrix");
		//��������� ������ modelViewProjectionMatrix
		//� ��������� u_modelViewProjectionMatrix
		GLES20.glUniformMatrix4fv(
				u_modelViewProjectionMatrix_Handle, 1, false, modelViewProjectionMatrix, 0);
	}

	//���������
	public void linkModelMatrix(float[] modelMatrix) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� �������� u_modelMatrix
		int u_modelMatrix_Handle =
				GLES20.glGetUniformLocation(program_Handle, "u_modelMatrix");
		//��������� ������ modelMatrix
		//� ��������� u_modelMatrix
		GLES20.glUniformMatrix4fv(
				u_modelMatrix_Handle, 1, false, modelMatrix, 0);
	}

	// �����, ������� ��������� ���������� ������ � ��������� u_camera
	public void linkCamera(float xCamera, float yCamera, float zCamera) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� �������� u_camera
		int u_camera_Handle = GLES20.glGetUniformLocation(program_Handle, "u_camera");
		// ��������� ���������� ������ � ��������� u_camera
		GLES20.glUniform3f(u_camera_Handle, xCamera, yCamera, zCamera);
	}

	// �����, ������� ��������� ���������� ��������� �����
	// � ��������� u_lightPosition
	public void linkLightSource(float xLightPosition, float yLightPosition, float zLightPosition) {
		//������������� �������� ���������
		GLES20.glUseProgram(program_Handle);
		//�������� ������ �� �������� u_lightPosition
		int u_lightPosition_Handle = GLES20.glGetUniformLocation(program_Handle, "u_lightPosition");
		// ��������� ���������� ��������� ����� � ��������� u_lightPosition
		GLES20.glUniform3f(u_lightPosition_Handle, xLightPosition, yLightPosition, zLightPosition);
	}


	/**
	 * Использовать программу шейдера. Выполнять необходимо каждый раз когда надо рисовать этим шейдером (если рисовали другим)
	 */
	public void useProgram() {
		GLES20.glUseProgram(program_Handle);
	}

	/**
	 * @param texture0
	 * @param GLTEXTURE GLES20.GL_TEXTURE0
	 * @param texName
	 */
	public void linkTexture(TTexture texture0, int GLTEXTURE, int textureIndex, String texName) {
		if (texture0 != null) {

			int u_texture0_Handle = GLES20.glGetUniformLocation(program_Handle, texName);
			GLES20.glActiveTexture(GLTEXTURE);
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture0.getName());
			GLES20.glUniform1i(u_texture0_Handle, textureIndex);

		}

	}//end linkTexture

	public void linkTexture(TTexture texture0, TTexture texture1) {
		GLES20.glUseProgram(program_Handle);
		if (texture0 != null) {

			int u_texture0_Handle =	GLES20.glGetUniformLocation(program_Handle, "u_texture0");

			GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture0.getName());
			GLES20.glUniform1i(u_texture0_Handle, 0);

		}
		if (texture1 != null) {

			int u_texture1_Handle =	GLES20.glGetUniformLocation(program_Handle, "u_texture1");

			GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture1.getName());
			GLES20.glUniform1i(u_texture1_Handle, 1);
		}
	}

	public void linkTexVertex(int coordsPerVertex, int vertexStride, FloatBuffer vertexBuffer, String attrName) {
		// get handle to vertex shader's vPosition member
		mPositionTexHandle = GLES20.glGetAttribLocation(program_Handle, attrName);

		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionTexHandle);

		// Prepare the triangle coordinate data
		GLES20.glVertexAttribPointer(mPositionTexHandle, coordsPerVertex,
				GLES20.GL_FLOAT, false,
				vertexStride, vertexBuffer);

	}

	public void linkVertex(int coordsPerVertex, int vertexStride, FloatBuffer vertexBuffer, String attrName) {
		// get handle to vertex shader's vPosition member
		mPositionHandle = GLES20.glGetAttribLocation(program_Handle, attrName);

		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);

		// Prepare the triangle coordinate data
		GLES20.glVertexAttribPointer(mPositionHandle, coordsPerVertex,
				GLES20.GL_FLOAT, false,
				vertexStride, vertexBuffer);

	}

	/**
	 * Disable vertex array
	 */
	public void unlinkVertex() {
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}

	public void unlinkTexVertex() {
		GLES20.glDisableVertexAttribArray(mPositionTexHandle);
	}

	/**
	 * Получить ссылку на атрибут
	 *
	 * @param attrName название атрибута
	 * @return хендл атрибута для его изменения
	 */
	public int getAttrHandle(String attrName) {
		return GLES20.glGetUniformLocation(program_Handle, attrName);
	}

	public void setColor(String attrName, float[] color) {
		int mColorHandle = getAttrHandle(attrName); // get handle to fragment shader's vColor member
		GLES20.glUniform4fv(mColorHandle, 1, color, 0);   // Set color for drawing the triangle
	}

	public void setColor(String attrName, float[] color, int offset) {
		int mColorHandle = getAttrHandle(attrName); // get handle to fragment shader's vColor member
		GLES20.glUniform4fv(mColorHandle, 1, color, offset);   // Set color for drawing the triangle
	}

	public void setColor(String attrName, int[] color) {
		int mColorHandle = getAttrHandle(attrName); // get handle to fragment shader's vColor member
		GLES20.glUniform3iv(mColorHandle, 1, color, 0);   // Set color for drawing the triangle
	}

	public void setMatrix(String attrName, float[] matrix) {
		int mMVPMatrixHandle = getAttrHandle(attrName);      // get handle to shape's transformation matrix
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, matrix, 0); // Apply the projection and view transformation
	}

	public void setColor(int mColorHandle, float[] color) {
		GLES20.glUniform4fv(mColorHandle, 1, color, 0);   // Set color for drawing the triangle
	}

	public void setMatrix(int mMVPMatrixHandle, float[] matrix) {
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, matrix, 0); // Apply the projection and view transformation
	}

	public void draw(int length, ShortBuffer drawListBuffer) {
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, length,
				GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

	}

	public void setColor(int mColorHandle, float[] color, int offset) {
		GLES20.glUniform4fv(mColorHandle, 1, color, offset);   // Set color for drawing the triangle

	}

	public void SetFloat(int mHandle, float value) {
		GLES20.glUniform1f(mHandle, value);

	}

	public void linkTexture(int mTextureID, int GLTEXTURE, String texName) {
		int u_texture0_Handle = GLES20.glGetUniformLocation(program_Handle, texName);
		//�������� ������� ���������� ���� GL_TEXTURE0
		GLES20.glActiveTexture(GLTEXTURE);
		//� ���������� ����� GL_TEXTURE0
		//������ �������� �������� � ������ texture0.getName()
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureID);
		//��������� ����� ����� ��������  texture0 � ��������� u_texture0
		//� ������� ���������� �����
		GLES20.glUniform1i(u_texture0_Handle, 0);
	}

	public void SetInt(int mHandle, int value) {
		GLES20.glUniform1i(mHandle, value);

	}

	public void ready() {
		useProgram();
	}

}

