import org.opencv.core.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.videoio.Videoio;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.FileReader;

class RGBObject
{
    double R; // RED
    double G; // GREEN
    double B; // BLUE

    RGBObject(){}

    RGBObject(double r, double g, double b)
    {
        this.R = r;
        this.G = g;
        this.B = b;
    }
}

public class Program
{
    // scanner and choice are used for any user interactions
    static Scanner scanner = new Scanner(System.in);
    static int choice = -1;

    private String type; // RGB, XYZ, etc

    public static void main(String[] args)
    {
        // Load library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.out.println("Which program would you like to run?");
        System.out.println("1. First program");
        System.out.println("2. Second program");
        Program program = new Program();
        int choice = scanner.nextInt();
        if (choice == 1)
        {
            program.firstProgram();
        }
        else if (choice == 2)
        {
            program.secondProgram();
        }
        else
        {
            System.out.println("Invalid selection");
        }
    }

    public void firstProgram()
    {
        RGBObject rgbObject1 = null;
        RGBObject rgbObject2 = null;
        RGBObject rgbObject3 = null;

        String colorSpaces [] = {"", "RGB", "XYZ", "Lab", "YUV", "YCbCr", "YIQ", "HSL"};

        // Menu
        System.out.println("Select a Color Model:");
        System.out.println("Press 1 for RGB");
        System.out.println("Press 2 for XYZ");
        System.out.println("Press 3 for Lab");
        System.out.println("Press 4 for YUV");
        System.out.println("Press 5 for YCbCr");
        System.out.println("Press 6 for YIQ");
        System.out.println("Press 7 for HSL");
        System.out.print("\nModel: ");

        choice = scanner.nextInt();
        String firstComponent;
        String secondComponent;
        String thirdComponent;

        switch (choice)
        {
            case 1:
                firstComponent = "R";
                secondComponent = "G";
                thirdComponent = "B";
                break;

            case 2:
                firstComponent = "X";
                secondComponent = "Y";
                thirdComponent = "Z";
                break;

            case 3:
                firstComponent = "L";
                secondComponent = "a";
                thirdComponent = "b";
                break;

            case 4:
                firstComponent = "Y";
                secondComponent = "U";
                thirdComponent = "V";
                break;

            case 5:
                firstComponent = "Y";
                secondComponent = "Cb";
                thirdComponent = "Cr";
                break;

            case 6:
                firstComponent = "Y";
                secondComponent = "I";
                thirdComponent = "Q";
                break;

            case 7:
                firstComponent = "H";
                secondComponent = "S";
                thirdComponent = "L";
                break;

            default:
                firstComponent = "R";
                secondComponent = "G";
                thirdComponent = "B";
        }

        type = colorSpaces[choice];
        System.out.println("### You have Selected " + type + " Color Model ###");
        System.out.print("\nEnter Component Cit " + firstComponent + " Component - ");
        int CitFirstComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit " + secondComponent + " Component - ");
        int CitSecondComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit " + thirdComponent + " Component - ");
        int CitThirdComponent = scanner.nextInt();
        System.out.println("\n### Cit - < " + CitFirstComponent + "," + CitSecondComponent + "," + CitThirdComponent + " > ###");

        System.out.print("\nEnter Component Ci0 " + firstComponent + " Component - ");
        int Ci0FirstComponent = scanner.nextInt();
        System.out.print("\nEnter Component Ci0 " + secondComponent + " Component - ");
        int Ci0SecondComponent = scanner.nextInt();
        System.out.print("\nEnter Component Ci0 " + thirdComponent + " Component - ");
        int Ci0ThirdComponent = scanner.nextInt();
        System.out.println("\n### Ci0 - < " + Ci0FirstComponent + "," + Ci0SecondComponent + "," + Ci0ThirdComponent + "> ###");

        System.out.print("\nEnter Component Cit " + firstComponent + " Component - ");
        int CiTFirstComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit " + secondComponent + " Component - ");
        int CiTSecondComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit " + thirdComponent + " Component - ");
        int CiTThirdComponent = scanner.nextInt();
        System.out.println("\n### CiT - < " + CiTFirstComponent + "," + CiTSecondComponent + "," + CiTThirdComponent + " > ###");

        // If type is not RGB, convert to RGB
        switch(choice)
        {
            case 2:
                rgbObject1 = convertXYZToRGB(CitFirstComponent, CitSecondComponent, CitThirdComponent);
                rgbObject2 = convertXYZToRGB(Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent);
                rgbObject3 = convertXYZToRGB(CiTFirstComponent, CiTSecondComponent, CiTThirdComponent);
                break;
            case 3:
                rgbObject1 = convertLabToRGB(CitFirstComponent, CitSecondComponent, CitThirdComponent);
                rgbObject2 = convertLabToRGB(Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent);
                rgbObject3 = convertLabToRGB(CiTFirstComponent, CiTSecondComponent, CiTThirdComponent);
                break;
            case 4:
                rgbObject1 = convertYUVToRGB(CitFirstComponent, CitSecondComponent, CitThirdComponent);
                rgbObject2 = convertYUVToRGB(Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent);
                rgbObject3 = convertYUVToRGB(CiTFirstComponent, CiTSecondComponent, CiTThirdComponent);
                break;
            case 5:
                rgbObject1 = convertYCbCrToRGB(CitFirstComponent, CitSecondComponent, CitThirdComponent);
                rgbObject2 = convertYCbCrToRGB(Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent);
                rgbObject3 = convertYCbCrToRGB(CiTFirstComponent, CiTSecondComponent, CiTThirdComponent);
                break;
            case 6:
                rgbObject1 = convertYIQToRGB(CitFirstComponent, CitSecondComponent, CitThirdComponent);
                rgbObject2 = convertYIQToRGB(Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent);
                rgbObject3 = convertYIQToRGB(CiTFirstComponent, CiTSecondComponent, CiTThirdComponent);
                break;
            default:
                break;
        }

        System.out.print("\nEnter the Number of bits <b>:");
        int b = scanner.nextInt();
        int colorInstances = (int) Math.pow((double) 2, (double) b);
        System.out.println("### Total of " + colorInstances  + " color instance would be created ###");
        System.out.println("\n### Color map created ###");

        //String fileName = type + "_" + CitFirstComponent + "-" + CitSecondComponent + "-" + CitThirdComponent
        //        + "_" + Ci0FirstComponent + "-" + Ci0SecondComponent + "-" + Ci0ThirdComponent
        //        + "_" + CiTFirstComponent + "-" + CiTSecondComponent + "-" + CiTThirdComponent
        //        + ".txt";
        System.out.println("\n### File saved! ###");

        System.out.println("Visualize Color Model[Y/N]: ");
        String response = scanner.next().trim();
        boolean showColorMap = false;
        if (response.equalsIgnoreCase("Y"))
        {
            showColorMap = true;
        }

        if (showColorMap)
        {
            DisplayColorMap dcm = new DisplayColorMap();
            if (choice == 1 || choice == 7)
            {
                dcm.setColorMapValues(colorInstances, CitFirstComponent, CitSecondComponent, CitThirdComponent, Ci0FirstComponent, Ci0SecondComponent, Ci0ThirdComponent, CiTFirstComponent, CiTSecondComponent, CiTThirdComponent, type);
            }
            else
            {
                dcm.setColorMapValues(colorInstances, rgbObject1.R, rgbObject1.G, rgbObject1.B, rgbObject2.R, rgbObject2.G, rgbObject2.B, rgbObject3.R, rgbObject3.G, rgbObject3.B, type);
            }
            dcm.createColorMap(dcm);
        }
    }

    // Calculation from www.easyrgb.com/index.php?X=MATH&H=01#text01
    public static RGBObject convertXYZToRGB(double x, double y, double z)
    {
        double var_X = x / 100;
        double var_Y = y / 100;
        double var_Z = z / 100;

        double var_R = var_X * 3.2406 + var_Y * -1.5372 + var_Z * -0.4986;
        double var_G = var_X * -0.9689 + var_Y * 1.8758 + var_Z * 0.0415;
        double var_B = var_X * 0.0557 + var_Y * -0.2040 + var_Z * 1.0570;

        if (var_R > 0.0031308)
        {
            var_R = 1.055 * (Math.pow(var_R,(1 / 2.4))) - 0.055;
        }
        else
        {
            var_R = 12.92 * var_R;
        }

        if (var_G > 0.0031308)
        {
            var_G = 1.055 * (Math.pow(var_G,(1 / 2.4))) - 0.055;
        }
        else
        {
            var_G = 12.92 * var_G;
        }

        if (var_B > 0.0031308)
        {
            var_B = 1.055 * (Math.pow(var_B,(1 / 2.4))) - 0.055;
        }
        else
        {
            var_B = 12.92 * var_B;
        }

        double R = var_R * 255;
        double G = var_G * 255;
        double B = var_B * 255;

        RGBObject rgbObject = new RGBObject();
        rgbObject.R = R;
        rgbObject.G = G;
        rgbObject.B = B;
        return rgbObject;
    }

    // Calculation from www.easyrgb.com/index.php?X=MATH&H=08#text08
    public static RGBObject convertLabToRGB(double L, double a, double b)
    {
        // First convert Lab to XYZ and then call convertXYZToRGB
        double var_Y = (L + 16) / 116;
        double var_X = a / 500 + var_Y;
        double var_Z = var_Y - b / 200;

        if (Math.pow(var_Y, 3) > 0.008856)
        {
            var_Y = Math.pow(var_Y, 3);
        }
        else
        {
            var_Y = (var_Y - 16 / 116) / 7.787;
        }

        if (Math.pow(var_X, 3) > 0.008856)
        {
            var_X = Math.pow(var_X, 3);
        }
        else
        {
            var_X = (var_X - 16 / 116) / 7.787;
        }

        if (Math.pow(var_Z, 3) > 0.008856)
        {
            var_Z = Math.pow(var_Z, 3);
        }
        else
        {
            var_Z = (var_Z - 16 / 116) / 7.787;
        }

        double X = 95.047 * var_X;
        double Y = 100.000 * var_Y;
        double Z = 108.883 * var_Z;

        return convertXYZToRGB(X,Y,Z);
    }

    // Calculation from www.pcmag.com/encyclopedia/term/55166/yuv-rgb-conversion-formulas
    public static RGBObject convertYUVToRGB(double Y, double U, double V)
    {
        RGBObject rgbObject = new RGBObject();
        rgbObject.R = Y + 1.140*V;
        rgbObject.G = Y - 0.395*U;
        rgbObject.B = Y + 2.032*U;
        return rgbObject;
    }

    // Calculation from stackoverflow.com/questions/4032380/ycbcr-to-rgb-from-matrix-table
    public static RGBObject convertYCbCrToRGB(double Y, double Cb, double Cr)
    {
        RGBObject rgbObject = new RGBObject();
        rgbObject.R = 1.0 * Y + 0 * Cb + 1.402 * Cr;
        rgbObject.G = 1.0 * Y + -0.344136 * Cb - 0.714136 * Cr;
        rgbObject.B = 1.0 * Y + 1.772 * Cb + 0 * Cr;
        return rgbObject;
    }

    // Calculation from www.cs.rit.edu/~ncs/color/t_convert.html
    public static RGBObject convertYIQToRGB(double Y, double I, double Q)
    {
        RGBObject rgbObject = new RGBObject();
        rgbObject.R = 1 * Y + 0.956 * I + 0.621 * Q;
        rgbObject.G = 1 * Y + -0.272 * I + -0.647 * Q;
        rgbObject.B = 1 * Y + -1.105 * I + 1.702 * Q;
        return rgbObject;
    }

    public void secondProgram()
    {
    	VideoCapture vc = new VideoCapture();
        
        List<Double> maxes = new ArrayList<Double>();
        List<Double> component1 = new ArrayList<Double>();
        List<Double> component2 = new ArrayList<Double>();
        List<Double> component3 = new ArrayList<Double>();

        //consume newline character left by nextInt
        scanner.nextLine();
        System.out.println("Enter the Path of the Folder containing video Files");
        String path = scanner.nextLine();
        System.out.println("Enter the videofile name");
        String filename = scanner.nextLine();
        System.out.println("Enter frame rate");
        scanner.nextLine();
        System.out.println("Enter first frame");
        int firstFrame = scanner.nextInt();
        System.out.println("Enter second frame");
        int secondFrame = scanner.nextInt();
        scanner.nextLine();    //consume newline character left by nextInt
        System.out.println("Enter the color map file name");
        String colorMap = scanner.nextLine();

        //parse the color-map once the user enters the name
        try{
            FileReader file = new FileReader(colorMap);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            while (line != null)
            {
                if (line.contains("->"))
                {
                    // 1st section is the bit number
                    // 2nd section is the range
                    // 3rd section is the rgb value
                    String sections[] = line.split("->");
                    
                    int indexOfAfterComma = sections[1].lastIndexOf(',') + 1;
                    int indexOfParenthesis = sections[1].lastIndexOf(')');
                    double maxValueInRange = Double.parseDouble(sections[1].substring(indexOfAfterComma, indexOfParenthesis));
                    maxes.add(maxValueInRange);
                   
                    String rgbValues[] = sections[2].split(",");
                    double redValue = Double.parseDouble(rgbValues[0].trim());
                    double greenValue = Double.parseDouble(rgbValues[1].trim());
                    double blueValue = Double.parseDouble(rgbValues[2].trim());
                    
                    component1.add(redValue);
                    component2.add(greenValue);
                    component3.add(blueValue);
                }
                line = reader.readLine();
            }

            reader.close();
        }catch (Exception e) {}

        //open the video specified by user
        vc.open(path + filename);
        //System.out.println(vc.get(Videoio.CAP_PROP_FRAME_COUNT));

        //retrieve first frame
        vc.set(Videoio.CAP_PROP_POS_FRAMES, firstFrame);
        Mat frame = new Mat();
        vc.grab();
        vc.retrieve(frame);

        //retrieve second frame
        vc.set(Videoio.CAP_PROP_POS_FRAMES, secondFrame);
        Mat frame2 = new Mat();
        vc.grab();
        vc.retrieve(frame2);

        //create original images
        Imgcodecs.imwrite("firstFrame.jpg", frame);
        Imgcodecs.imwrite("secondFrame.jpg", frame2);

        System.out.println("### Extracting the Grayscale for frame " + firstFrame + " and " + secondFrame +  " ###");
        //convert both frames to 8 bit gray-scale
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(frame2, frame2, Imgproc.COLOR_BGR2GRAY);
        System.out.println("### Extraction Done ###");
        
        String firstFrameFile = "file1_" + firstFrame + "_gray.jpg";
        String secondFrameFile = "file1_" + secondFrame + "_gray.jpg";
        System.out.println("### Extracted Grayscale saves as <" + firstFrameFile + "> and <" + secondFrameFile + " ##");
        Imgcodecs.imwrite(firstFrameFile, frame);
        Imgcodecs.imwrite(secondFrameFile, frame2);
        
        System.out.print("\nDo you want to visualize the Frames [Y/N]:");
        String response = scanner.next().trim();
        
        while (response.equalsIgnoreCase("Y")){
        	System.out.println("Select one of the option:");
        	System.out.println("Press 1 for Frame " + firstFrame);
        	System.out.println("Press 2 for Frame " + secondFrame);
        	System.out.println("Press 3 to move forward");
        	System.out.print("Choice: ");
        	int choice = scanner.nextInt();
        	
        	if(choice == 1){
        		BufferedImage image;
            	image = Mat2BufferedImage(frame);
            	displayImage(image);
        	}
        	else if(choice == 2){
        		BufferedImage image;
            	image = Mat2BufferedImage(frame2);
            	displayImage(image);
        	}
        	else if(choice == 3){
        		response = "N";
        	}
        }
        
        System.out.println("### Determining the Grayscale difference image ###");
        //calculate the difference between both frames
        Mat diff = new Mat();
        Core.absdiff(frame, frame2, diff);
        System.out.println("### Computation of grayscale difference image done ###");
        
        //create difference image
        Imgcodecs.imwrite("difference.jpg", diff);

        //convert difference image back to RGB for more color channels
        Imgproc.cvtColor(diff, diff, Imgproc.COLOR_GRAY2BGR);
        System.out.println("### Rescaling of grayscale difference image done ###");
        
        //loop through the image and apply the colors from color map
        for(int i=0; i<diff.height(); i++){

            for(int j=0; j<diff.cols(); j++){

                double[] rgb4 = diff.get(i, j);
                double value = scale(rgb4[0],-255,255,-1,1);

                for(int k = 0; k < maxes.size(); k++){
                    if(value < maxes.get(k)){
                        diff.put(i, j, new double[]{component1.get(k),component2.get(k),component3.get(k)});
                        break;
                    }
                }

            }
        }
        System.out.println("### Recoloring of Grayscale Difference image done ###");
        System.out.println("### Check out the visualization ###");
        
        //create the three images
        Imgcodecs.imwrite("Final.jpg", diff);
        
        //display the final colored image
        BufferedImage image;
    	image = Mat2BufferedImage(diff);
    	displayImage(image);
    }
    public static double scale(double valueIn, double baseMin, double baseMax, double limitMin, double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }
    //convert openCV mat to BufferedImage
    // source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
    public static BufferedImage Mat2BufferedImage(Mat m){
    		int type = BufferedImage.TYPE_BYTE_GRAY;
    	    if ( m.channels() > 1 ) {
    	        type = BufferedImage.TYPE_3BYTE_BGR;
    	    }
    	    int bufferSize = m.channels()*m.cols()*m.rows();
    	    byte [] b = new byte[bufferSize];
    	    m.get(0,0,b); // get all the pixels
    	    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
    	    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    	    System.arraycopy(b, 0, targetPixels, 0, b.length);  
    	    return image;

    }
    //display an image
    public static void displayImage(BufferedImage img2)
    {   
        ImageIcon icon=new ImageIcon(img2);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());        
        frame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);     
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}