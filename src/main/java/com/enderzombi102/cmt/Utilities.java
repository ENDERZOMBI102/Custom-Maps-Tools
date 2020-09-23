package com.enderzombi102.cmt;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import net.minecraftforge.fml.common.Loader;

public class Utilities {
	
	public static ByteBuffer readImageToBuffer(InputStream stream) throws IOException {
		
        BufferedImage bufferedImage = ImageIO.read(stream);
        IOUtils.closeQuietly(stream);
        int[] aint = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), (int[])null, 0, bufferedImage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

        for (int i : aint) {
            bytebuffer.putInt(i << 8 | i >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }
	
	public static BufferedImage resizeImage(BufferedImage src, int newPx) {
		// if the given image is already the requested dimensions, return it
		if ( src.getHeight(null) == newPx && src.getWidth(null) == newPx ) return src;
		// create new BufferedImage with the requested dimensions
		BufferedImage img = new BufferedImage(newPx, newPx, BufferedImage.TYPE_INT_ARGB);
		// get scaled image
        Image tmp = src.getScaledInstance(newPx, newPx, Image.SCALE_DEFAULT);
        // get the BufferedImage graphic writer
        Graphics2D bGr = img.createGraphics();
        // draw the scaled image
        bGr.drawImage(tmp, 0, 0, null);
        bGr.dispose();
        // return result
		return img;
	}
	
	public static Image resizeImage(Image src, int newPx) {
		// if the given image is already the requested dimensions, return it
		if ( src.getHeight(null) == newPx && src.getWidth(null) == newPx ) return src;
		// scale the image and return it
		return src.getScaledInstance(newPx, newPx, Image.SCALE_DEFAULT);
	}
	
	
	public static ByteBuffer toByteBuffer(BufferedImage src) {
		int[] aint = src.getRGB( 0, 0, src.getWidth(), src.getHeight(), (int[])null, 0, src.getWidth() );
		// allocate the required space
        ByteBuffer buf = ByteBuffer.allocate(4 * aint.length);
        // add everything
        for (int i : aint) {
            buf.putInt(i << 8 | i >> 24 & 255);
        }
        
        buf.flip();
		return buf;
	}
	
	public static String getConfigDirPath() {
		return Loader.instance().getConfigDir().getPath();
	}
	
}
