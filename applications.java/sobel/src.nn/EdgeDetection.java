
package Sobel;

import com.googlecode.fannj.Fann;

import Parrot.ParrotObserver;
import Sobel.TextFile.Mode;
import java.io.IOException;

public class EdgeDetection {
	public int width;
	public int height;
	public int[][][] image;
	public EdgeDetection() {}
	public void loadImage(String filePath) throws IOException {
		TextFile textFile = new TextFile (filePath, Mode.READ);

		width = textFile.loadInt();
		textFile.loadChar();
		height = textFile.loadInt();

		image = new int[height][][];
		for (int i=0; i<height; i++){
			image[i] = new int[width][];
			for(int j=0; j<width; j++){
				image[i][j] = new int[3];
			}
		}


		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; j++) {
				image[i][j][0] = textFile.loadInt();
				textFile.loadChar();
				image[i][j][1] = textFile.loadInt();
				textFile.loadChar();
				image[i][j][2] = textFile.loadInt();
				if (j < (width - 1)) {
					textFile.loadChar();
				}
			}
		}
	}
	public void slideWindow(int x, int y, int[][][] window) {
		int h = window.length;
		int w = window[0].length;
		int x0 = x - (int)((float)w/2.);
		int x1 = x + (int)((float)w/2.);
		int y0 = y - (int)((float)h/2.);
		int y1 = y + (int)((float)h/2.);
		int r = 0;
		for (int j = y0; j <= y1; ++j) {
			if (j < 0 || j >= height) {
				for (int i = x0; i <= x1; ++i) {
					window[r][i-x0][0] = 0;
					window[r][i-x0][1] = 0;
					window[r][i-x0][2] = 0;
				}
			} else {
				for (int i = x0; i <= x1; ++i) {
					if (i < 0 || i >= width) {
						window[r][i-x0][0] = 0;
						window[r][i-x0][1] = 0;
						window[r][i-x0][2] = 0;
					} else {
						window[r][i-x0][0] = image[j][i][0];
						window[r][i-x0][1] = image[j][i][1];
						window[r][i-x0][2] = image[j][i][2];
					}
				}
			}
			r++;
		}
	}
	public double sobel_old (int[][][] window) {
		double p1 = (double)luminance(window[0][0]);
		double p2 = (double)luminance(window[0][1]);
		double p3 = (double)luminance(window[0][2]);
		double p4 = (double)luminance(window[1][0]);
		double p5 = (double)luminance(window[1][1]);
		double p6 = (double)luminance(window[1][2]);
		double p7 = (double)luminance(window[2][0]);
		double p8 = (double)luminance(window[2][1]);
		double p9 = (double)luminance(window[2][2]);
		double x = (p1 + (p2 + p2) + p3 - p7 - (p8 + p8) - p9);
		double y = (p3 + (p6 + p6) + p9 - p1 - (p4 + p4) - p7);
		double l = Math.sqrt(x * x + y * y);
		return l;
	}
	public double sobel (double[][] window) {
		double p1 = window[0][0];
		double p2 = window[0][1];
		double p3 = window[0][2];
		double p4 = window[1][0];
		double p5 = window[1][1];
		double p6 = window[1][2];
		double p7 = window[2][0];
		double p8 = window[2][1];
		double p9 = window[2][2];
		double x = (p1 + (p2 + p2) + p3 - p7 - (p8 + p8) - p9);
		double y = (p3 + (p6 + p6) + p9 - p1 - (p4 + p4) - p7);
		double l = Math.sqrt(x * x + y * y);
		return l;
	}
	public  int luminance( int[] rgb) {
		double rC = 0.30;
		double gC = 0.59;
		double bC = 0.11;

		return (int)(rC * rgb[0] + gC * rgb[1] + bC * rgb[2] + 0.5) % 256;
	}
	public void makeGrayscale() {
		int i;
		int j;
		double luminance;

		double rC = 0.30 / 256.0;
		double gC = 0.59 / 256.0;
		double bC = 0.11 / 256.0;

		for(i = 0; i < image.length; i++) {
			for(j = 0; j < image[i].length; j++) {
				luminance = rC * image[i][j][0] +
							gC * image[i][j][1] +
							bC * image[i][j][2];

				image[i][j][0] = (int)(luminance * 256);
				image[i][j][1] = (int)(luminance * 256);
				image[i][j][2] = (int)(luminance * 256);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		EdgeDetection detector = new EdgeDetection();
		detector.loadImage(args[0]);
		detector.makeGrayscale();
		int[][][] window = new int[3][][];
		for(int i=0; i<3; i++){
			window[i] = new int[3][];
			for(int j=0; j<3; j++){
				window[i][j] = new int[3];
			}
		}

		double[][] luminance_window = new double[3][];
		for(int i = 0 ; i < 3; i++)
		{
			luminance_window[i] = new double[3];
		}

		for (int y=0; y < detector.height; y++) {
			for (int x=0; x < detector.width; x++) {
				detector.slideWindow(x,y, window);

				// Amir
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						luminance_window[i][j] = detector.luminance(window[i][j]);
					}
				}
				// Rima

				Double pIn[] = new Double[9];
				pIn[0] = luminance_window[0][0];
				pIn[1] = luminance_window[0][1];
				pIn[2] = luminance_window[0][2];
				pIn[3] = luminance_window[1][0];
				pIn[4] = luminance_window[1][1];
				pIn[5] = luminance_window[1][2];
				pIn[6] = luminance_window[2][0];
				pIn[7] = luminance_window[2][1];
				pIn[8] = luminance_window[2][2];
				double l;
// #begin_loosen parrot(input, "sobel", [9]<0.0; 256.0>pIn)
	Fann fann = new Fann("fann.config/sobel.nn");
	float[] parrotIn = new float[9];
	parrotIn[0] = (float)pIn[0].doubleValue();
	parrotIn[1] = (float)pIn[1].doubleValue();
	parrotIn[2] = (float)pIn[2].doubleValue();
	parrotIn[3] = (float)pIn[3].doubleValue();
	parrotIn[4] = (float)pIn[4].doubleValue();
	parrotIn[5] = (float)pIn[5].doubleValue();
	parrotIn[6] = (float)pIn[6].doubleValue();
	parrotIn[7] = (float)pIn[7].doubleValue();
	parrotIn[8] = (float)pIn[8].doubleValue();
	float[] parrotOut = fann.run(parrotIn);
// 				//ParrotObserver.initialize("Amir");
// 				//ParrotObserver currObserver = new ParrotObserver("amir.txt");
// 				//currObserver.write("amir", '1', true, 0.0,0.0);
// 				//ParrotObserver.write("amir", '0', parrotIn, 9, 0.0, 0.0);
// 				l = detector.sobel(luminance_window);
// 				//ParrotObserver.write("amir", '1', l, 0.0, 0.0);
// #end_loosen parrot(output, "sobel", <0.0; 1.0>l)
	l = parrotOut[0];

				if (l >= 255.0) l = 255.0;
				if (l < 0.0) l = 0.0;
				//loosen(l);
				//System.out.println("----");
				//System.out.println(l);
				//System.out.println("----");
			}
		}
	}
}
