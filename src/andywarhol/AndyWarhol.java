package andywarhol;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AndyWarhol {

    private static BufferedImage inputImage;
    private static BufferedImage outputImage;
    private static String outputLocation;

    AndyWarhol(File startImage, String outputFileName) {
        try {
            inputImage = ImageIO.read(startImage);
            outputLocation = startImage.getPath().substring(0, startImage.getPath().lastIndexOf("\\")) + "\\" + outputFileName;
            outputLocation = outputLocation.replace("\\", "\\\\");
            processAndCreateNewImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processAndCreateNewImage() throws IOException {
        outputImage = new BufferedImage(inputImage.getWidth() * 2, 2 * inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y < inputImage.getHeight();  y++)
        {
            for(int x = 0; x < inputImage.getWidth(); x++)
            {
                int pixel = inputImage.getRGB(x, y);
                int shiftedPixel = pixel;
                
                int blue = pixel & 255; //0xff
                
                shiftedPixel = shiftedPixel >> 8; //shiftedPixel >>=8
                int green = shiftedPixel & 255;
                
                shiftedPixel = shiftedPixel >> 8; //shiftedPixel >>=8
                int red = shiftedPixel & 255; 
                
                
                int iRed = red;//255 - red;
                int iGreen = green;//255 - green;
                int iBlue = blue;//255 - blue;
                
                int finalPixel = (iRed << 16) + (iGreen << 8) + (iBlue);
                
                if(x == 0 && y == 0)
                    System.out.println(red + "-" + green + "-" + blue);
                              
                outputImage.setRGB(x, y, finalPixel);
                
                iRed = red;
                iGreen = 255 - green;
                iBlue = 255 - blue;
                finalPixel = (iRed << 16) + (iGreen << 8) + (iBlue);
                
                outputImage.setRGB(x + inputImage.getWidth(), y, finalPixel);
                
                iRed = red;
                iGreen = green;
                iBlue = 255 - blue;
                finalPixel = (iRed << 16) + (iGreen << 8) + (iBlue);
                
                outputImage.setRGB(x, y + inputImage.getHeight(), finalPixel);
                
                iRed = 255 - red;
                iGreen = green;
                iBlue = 255 - blue;
                finalPixel = (iRed << 16) + (iGreen << 8) + (iBlue);
                
                outputImage.setRGB(x + inputImage.getWidth(), y + inputImage.getHeight(), finalPixel);
            }
        }
        ImageIO.write(outputImage, "PNG", new File(outputLocation + ".png") );
    }
}
