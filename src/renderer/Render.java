package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
/**
 * to creat from scene colors matrix
 * 
 * @author da077
 *
 */
public class Render {

	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	public Render setImageWriter(ImageWriter _imageWriter) {
		imageWriter = _imageWriter;
		return this;
	}

	public Render setScene(Scene _scene) {
		return this;
	}

	public Render setCamera(Camera _camera) {
		camera = _camera;
		return this;
	}

	public Render setRayTracer(RayTracerBase _rayTracer) {
		rayTracer = _rayTracer;
		return this;
	}

	/**
	 * Goes through a loop on our screen. For each pixel a beam will be built and for each beam we will get a color from the horn comb.
	 *  The  color in the appropriate pixel of the image manufacturer
	 */
	public void renderImage() {
		try {
			if (imageWriter == null) {
				throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
			}
			//if (scene == null) {
			//	throw new MissingResourceException("missing resource", Scene.class.getName(), "");
			//}
			if (camera == null) {
				throw new MissingResourceException("missing resource", Camera.class.getName(), "");
			}
			if (rayTracer == null) {
				throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
			}
		
			//rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = rayTracer.traceRay(ray);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
		}
			
          catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }

	}

	/**
	 * A function that creates a network of lines and colors them. Does not override the existing image but adds above
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, Color color) {
		try {
			if (imageWriter == null)
				throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
			int nX = imageWriter.getNx();
			int nY = imageWriter.getNy();
			for (int i = 0; i < nY; i++) {
				for (int j = 0; j < nX; j++) {
					if (i % interval == 0 || j % interval == 0) {
						imageWriter.writePixel(j, i, color);
					}
				}
			}
		} catch (MissingResourceException e) {

		}
	}

	/**
	 * func that call to the same func at imagewriter class to create picture
	 */
	public void writeToImage() {
		try {
			if (imageWriter == null)
				throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
		imageWriter.writeToImage();
		}
		catch (MissingResourceException e) {

		}
	}
}
