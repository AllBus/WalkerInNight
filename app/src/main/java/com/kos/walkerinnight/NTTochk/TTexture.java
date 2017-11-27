package com.kos.walkerinnight.NTTochk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import javax.microedition.khronos.opengles.GL10;

public class TTexture {
    //создаем поле для хранения имени текстуры
    private int name;
    
    // конструктор двумерной текстуры
    //передаем в качестве аргументов контекст 
    //и идентификатор ресурса графического файла
    public TTexture(String fileName) {
            //создаем пустой массив из одного элемента
            //в этот массив OpenGL ES запишет свободный номер текстуры, 
            // который называют именем текстуры
            int []names = new int[1];
            // получаем свободное имя текстуры, которое будет записано в names[0]
            GLES20.glGenTextures(1, names, 0);
            //запомним имя текстуры в локальном поле класса
            name = names[0];
            //теперь мы можем обращаться к текстуре по ее имени name
            //устанавливаем режим выравнивания по байту
            GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
            //делаем текстуру с именем name текущей
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, name);
            //устанавливаем фильтры текстуры
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER,
                    GLES20.GL_LINEAR_MIPMAP_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER,
                    GLES20.GL_LINEAR);
           //устанавливаем режим повтора изображения 
            //если координаты текстуры вышли за пределы от 0 до 1
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_S,
                    GLES20.GL_REPEAT);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_WRAP_T,
                    GLES20.GL_REPEAT);
            // загружаем картинку в Bitmap из файла
            //InputStream is =
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeFile(fileName) ;
                } finally {
                    
                }
            
          
            //переписываем Bitmap в память видеокарты
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            // удаляем Bitmap из памяти, т.к. картинка уже переписана в видеопамять
            bitmap.recycle();
            // Важный момент ! 
            // Создавать мипмапы нужно только
            // после загрузки текстуры в видеопамять
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
    }// конец конструктора двумерной текстуры
   
    // конструктор двумерной текстуры из ресурса
    //передаем в качестве аргументов контекст 
    //и идентификатор ресурса графического файла
    public TTexture(Context context, int idpicture) {
            //создаем пустой массив из одного элемента
            //в этот массив OpenGL ES запишет свободный номер текстуры, 
            // который называют именем текстуры
            int []names = new int[1];
            // получаем свободное имя текстуры, которое будет записано в names[0]
            GLES20.glGenTextures(1, names, 0);
            //запомним имя текстуры в локальном поле класса
            name = names[0];
            //теперь мы можем обращаться к текстуре по ее имени name
            //устанавливаем режим выравнивания по байту
            GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
            //делаем текстуру с именем name текущей
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, name);
            //устанавливаем фильтры текстуры
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,  GLES20.GL_LINEAR_MIPMAP_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,  GLES20.GL_LINEAR);
           //устанавливаем режим повтора изображения 
            //если координаты текстуры вышли за пределы от 0 до 1
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
            // загружаем картинку в Bitmap из ресурса
            Bitmap bitmap =
                    BitmapFactory.decodeResource(context.getResources(), idpicture);
            //переписываем Bitmap в память видеокарты
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            // удаляем Bitmap из памяти, т.к. картинка уже переписана в видеопамять
            bitmap.recycle();
            // Важный момент ! 
            // Создавать мипмапы нужно только
            // после загрузки текстуры в видеопамять
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
    }// конец конструктора двумерной текстуры

    //нам будет нужен метод, который возвращает имя текстуры
    public int getName() {
            return name;
    }


    static public int createTargetTexture(int width, int height) {
        int texture;
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        texture = textures[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        return texture;
    }

    static public int createFrameBuffer(int width, int height, int targetTextureId) {
        int framebuffer;
        int[] framebuffers = new int[1];
        GLES20.glGenFramebuffers(1, framebuffers, 0);

        framebuffer = framebuffers[0];
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, framebuffer);

        int depthbuffer;
        int[] renderbuffers = new int[1];
        GLES20.glGenRenderbuffers(1, renderbuffers, 0);
        depthbuffer = renderbuffers[0];

        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, depthbuffer);
        GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER,
                GLES20.GL_DEPTH_COMPONENT16, width, height);
        GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER,
                GLES20.GL_DEPTH_ATTACHMENT,
                GLES20.GL_RENDERBUFFER, depthbuffer);

        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER,
                GLES20.GL_COLOR_ATTACHMENT0, GL10.GL_TEXTURE_2D,
                targetTextureId, 0);
        int status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
        if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException("Framebuffer is not complete: " +
                    Integer.toHexString(status));
        }
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        return framebuffer;
    }
}// конец класса