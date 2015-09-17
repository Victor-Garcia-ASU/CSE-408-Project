import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import java.util.Scanner;

class RGBObject
{
    double R; // RED
    double G; // GREEN
    double B; // BLUE
}

public class Program
{
    // scanner and choice are used for any user interactions
    static Scanner scanner = new Scanner(System.in);
    static int choice = -1;

    public static void main(String[] args)
    {
        // Load library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.out.println("Which program would you like to run?");
        System.out.println("1. First program");
        System.out.println("2. Second program");
        int choice = scanner.nextInt();
        if (choice == 1)
        {
            firstProgram();
        }
        else if (choice == 2)
        {
            secondProgram();
        }
        else
        {
            System.out.println("Invalid selection");
        }
    }

    public static void firstProgram()
    {
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

        System.out.println("### You have Selected " + colorSpaces[choice] + " Color Model ###");
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

        System.out.print("\nEnter the Number of bits <b>:");
        int b = scanner.nextInt();

        System.out.println("### Total of " + (int) Math.pow((double) 2, (double) b) + " color instance would be created ###");
        System.out.println("\n### Color map created ###");
        System.out.println("\n### File saved as " + "" + " ###");

        System.out.println("Visualize Color Model[Y/N]: ");
        String response = scanner.next().trim();
        boolean showColorMap = false;
        if (response.equalsIgnoreCase("Y"))
        {
            showColorMap = true;
        }

        if (showColorMap)
        {
            // TODO: Color map logic here
            RGBObject matrix [][] = new RGBObject[10][10];

        }

        // Creates a color map (with 2^b color instances) which maps each value
        // in range -1.0 to 1.0 to a color instance in the chosen color space
        // such that the value -1.0 maps to the floor color instance, 0.0 maps
        // to the middle color instance, and 1.0 maps to the ceiling color instance.
        // The mapping function should be continuous.
        // The program should output the resulting color map into an ASCII text file
        // as well as display it by visually painting the colors in the color map.



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

    public static void secondProgram()
    {
        System.out.println("Select a video by choosing any number between 1 and 10.");
        choice = scanner.nextInt();

        String videoFilePath = "C:\\Users\\JAKE\\Desktop\\sampleData\\" + Integer.toString(choice);
        // Put actual useful code here
    }
}
