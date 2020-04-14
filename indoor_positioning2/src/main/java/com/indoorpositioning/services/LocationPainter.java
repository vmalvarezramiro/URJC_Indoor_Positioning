package com.indoorpositioning.services;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.indoorpositioning.MapVariables;
import com.indoorpositioning.PositioningVariables;
import com.indoorpositioning.persistence.Receiver;
public class LocationPainter {

	protected byte[] createImageWithLocation(String resourcePath,Integer pointX,Integer pointY, List<Receiver> usedReceiverData,List<Receiver> unusedReceivers, Color beaconColor) throws IOException {
		// Las dimensiones de la imagen / mapa
		Double imageWidth=MapVariables.mapWidth,imageHeight=MapVariables.mapHeight;
		// Las dimensiones relativas de los ejes x e y de la imagen / mapa (se agrega 1 para evitar una mala visualización cuando los receptores están en el borde del mapa)
		Double pointX_max=MapVariables.pointX_max+1,pointY_max=MapVariables.pointY_max+1;
		// La relación entre dimensiones reales y relativas de la imagen / mapa
		Double relationX=(imageWidth/pointX_max),relationY=imageHeight/pointY_max;
		int rcvPointWidth=MapVariables.rcvPointWidth,rcvPointHeight=MapVariables.rcvPointHeight;
		int bkPointWidth=MapVariables.bkPointWidth,bkPointHeight=MapVariables.bkPointHeight;
		//Location of the beacon
		int bkLocationX=(int) (pointX*relationX),bkLocationY=(int) (imageHeight-(pointY*relationY));
		// Ubicación de la baliza
		int rcvLocationX,rcvLocationY;
		try {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(resourcePath));
			// Obtenga el contexto de gráficos para este único objeto BufferedImage 
			Graphics g = bufferedImage.getGraphics();  
			// Pintar receptores usados
			g.setColor(PositioningVariables.usedRcvsColor);
			for (int i = 0; i < usedReceiverData.size(); i++) {
				rcvLocationX=(int)(usedReceiverData.get(i).getX_pos()*relationX);
				rcvLocationY=(int)(imageHeight-(usedReceiverData.get(i).getY_pos()*relationY));
				g.fillOval(rcvLocationX,rcvLocationY , rcvPointWidth, rcvPointHeight);
			}
			// Pintar receptores no utilizados
			g.setColor(PositioningVariables.unusedRcvsColor);

			for (int i = 0; i < unusedReceivers.size(); i++) {
				rcvLocationX=(int)(unusedReceivers.get(i).getX_pos()*relationX);
				rcvLocationY=(int)(imageHeight-(unusedReceivers.get(i).getY_pos()*relationY));
				g.fillOval(rcvLocationX,rcvLocationY , rcvPointWidth, rcvPointHeight);
			}
			// Pintar la ubicación de la baliza con beaconColor
			g.setColor(beaconColor);
			g.fillOval(bkLocationX,bkLocationY , bkPointWidth, bkPointHeight);
			// Convierte la imagen en una matriz de bytes
      		ByteArrayOutputStream baos = new ByteArrayOutputStream();
      		ImageIO.write( bufferedImage, "PNG", baos );
      		baos.flush();
      		byte[] biToByte = baos.toByteArray();
      		baos.close();
      		// Deshacerse del contexto de gráficos para ahorrar recursos
			g.dispose();  
			return biToByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
