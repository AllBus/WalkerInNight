package com.kos.walkerinnight.NTTochk.meshs;

public class THexagon extends TMeshWithoutNormal {
		float hexagonTexCoords[] = {0,0, 0.5f,0 ,1,0 
	    							,1,1, 0.5f,1, 0,1};
	    float hexagonCoords[] =new float[6*3];/* { -0.05f,  0.05f, 0.0f,   // top left
	                                    -0.05f, -0.05f, 0.0f,   // bottom left
	                                     0.05f, -0.05f, 0.0f,   // bottom right
	                                     0.05f,  0.05f, 0.0f,
	                                     0.00f, -0.06f, 0.0f,   // bottom right
	                                     0.00f,  0.06f, 0.0f}; // top right
*/
	    
	    
	    public final short drawOrder[] ={0,1,5, 1,2,5, 2,3,4, 2,4,5}; //{ 0, 1, 2, 0, 2, 3,1,2,4,3,0,5 }; // order to draw vertices

	    // Set color with red, green, blue and alpha (opacity) values
	    //    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

	    public THexagon(float radius) {
	        // initialize vertex byte buffer for shape coordinates
	       
	       
	        for (int i=0;i<6;i++)
	        {
	        	hexagonCoords[i*3]=(float)(radius* Math.sin(Math.PI*2*i/6));
	        	hexagonCoords[i*3+1]=(float)(radius* Math.cos(Math.PI*2*i/6));
	        	hexagonCoords[i*3+2]=0.0f;
	        }
	        
	        init(hexagonCoords,hexagonTexCoords,drawOrder);
	    }

		
	    public void Resize(float radius)
	    {
	      
	        for (int i=0;i<6;i++)
	        {
	        	hexagonCoords[i*3]=(float)(radius* Math.sin(Math.PI*2*i/6));
	        	hexagonCoords[i*3+1]=(float)(radius* Math.cos(Math.PI*2*i/6));
	        	hexagonCoords[i*3+2]=0.0f;
	        }
	        vertexBuffer.position(0);
	        vertexBuffer.put(hexagonCoords);
	        vertexBuffer.position(0);
	    }

		
}
