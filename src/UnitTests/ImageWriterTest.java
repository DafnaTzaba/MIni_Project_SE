package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

public class ImageWriterTest {

	@Test
    public void testWriteToImage() {
        ImageWriter imageWriter_ = new ImageWriter("testpink",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {
                    imageWriter_.writePixel(i, j, Color.BLACK);
                }
                // 500/10 = 50
                else if (j % 50 == 0) {
                    imageWriter_.writePixel(i, j, Color.WHITE);
                } else {
                    imageWriter_.writePixel(i, j, Color.GREEN);
                }
            }
        }
        imageWriter_.writeToImage();
    }

}
