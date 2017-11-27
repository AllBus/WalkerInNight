package com.kos.walkerinnight.NTTochk.meshs;

import com.kos.walkerinnight.NTTochk.Shader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;



public class TArrayGLButtons extends TMeshWithoutNormal {



	private int fButtonCount;
    private int fDlinaButton;//Количество углов на одну кнопку
    private int fDlinaOrdered;//Количество индексов вершин на кнопку
	private int lastIndex;//Номер последней добавленной кнопки
	
	private float texSdvigX=0;
	private float texSdvigY=0;
	private int texSizeX=1;
	private int texSizeY=1;
	private List<ShortBuffer> orders;
	
    
    public TArrayGLButtons(int buttonCount)
    {
    	super();
    	fButtonCount=buttonCount;
    	fDlinaButton=4;//
    	fDlinaOrdered=2*3;
    	lastIndex=0;
    	init(fButtonCount*fDlinaButton,fButtonCount*fDlinaOrdered);
    	orders=new ArrayList<ShortBuffer>();
    	
    	GridOrdered(fButtonCount,fDlinaOrdered,fDlinaButton); 
    	
    }
    public void AddButton(float left,float top,float right,float bottom,
    		float texleft,float textop,float texright,float texbottom)
    {
        float[] ver=new float[fDlinaButton*3];
        float[] tex=new float[fDlinaButton*2];
        int i=0;
        
        ver[i*3+0]=left;
        ver[i*3+1]=top;
        ver[i*3+2]=0;
        tex[i*2+0]=texleft;
        tex[i*2+1]=textop;
        i++;
        
        ver[i*3+0]=right;
        ver[i*3+1]=top;
        ver[i*3+2]=0;
        tex[i*2+0]=texright;
        tex[i*2+1]=textop;
        i++;
        
        ver[i*3+0]=left;
        ver[i*3+1]=bottom;
        ver[i*3+2]=0;
        tex[i*2+0]=texleft;
        tex[i*2+1]=texbottom;
        i++;
        
        ver[i*3+0]=right;
        ver[i*3+1]=bottom;
        ver[i*3+2]=0;
        tex[i*2+0]=texright;
        tex[i*2+1]=texbottom;
        i++;
        
        vertexBuffer.position(lastIndex*fDlinaButton*3);
        texBuffer.position(lastIndex*fDlinaButton*2);
        
        vertexBuffer.put(ver);
        texBuffer.put(tex);
        
        ShortBuffer sb;
     // initialize byte buffer for the draw list
     		ByteBuffer dlb = ByteBuffer.allocateDirect(
     		// (# of coordinate values * 2 bytes per short)
     				fDlinaOrdered * 2);
     		dlb.order(ByteOrder.nativeOrder());
     	sb = dlb.asShortBuffer();
    

    	short drawOrder[] = new short[fDlinaOrdered];

    	i=lastIndex;
		drawOrder[0] = (short) (i * fDlinaButton + 0);
		drawOrder[1] = (short) (i * fDlinaButton + 1);
		drawOrder[2] = (short) (i * fDlinaButton + 2);

		drawOrder[3] = (short) (i * fDlinaButton + 3);
		drawOrder[4] = (short) (i * fDlinaButton + 2);
		drawOrder[5] = (short) (i * fDlinaButton + 1);
		sb.put(drawOrder);
		
     	sb.position(0);
		orders.add(sb);
        
    	lastIndex++;
    }
    public void AddButton(float left,float top,float right,float bottom,int texIndex)
    {
    	float mnX=1.0f/texSizeX;
    	float mnY=1.0f/texSizeY;
    	AddButton(left,top,right,bottom,(texIndex%texSizeX)*mnX+texSdvigX,(texIndex/texSizeX)*mnY+texSdvigY,
    			(texIndex%texSizeX+1)*mnX-texSdvigX,(texIndex/texSizeX+1)*mnY-texSdvigY);
    }
    /**
     * Сделать все кнопки готовыми к рисованию
     */
    public void Ready()
    {
    	vertexBuffer.position(0);
        texBuffer.position(0);
        drawListBuffer.position(0);  
    }
	public void setTexSize(int sizeX, int sizeY, float sdvigX, float sdvigY) {
		this.texSizeX=sizeX;
		this.texSizeY=sizeY;
		this.texSdvigX=sdvigX;
		this.texSdvigY=sdvigY;
		
	}
	public int Count() {
		
		return fButtonCount;
	}
	public void draw(Shader shader, int index) {
		
		shader.draw(fDlinaOrdered,orders.get(index));
		
	}
}
