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
            System.out.println("Fine, be that way.");
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

        System.out.println("### You have Selected " + colorSpaces[choice] + " Color Model ###");
        System.out.print("\nEnter Component Cit Red Component - ");
        int CitRedComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit Green Component - ");
        int CitGreenComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit Blue Component - ");
        int CitBlueComponent = scanner.nextInt();
        System.out.println("\n### Cit - < " + CitRedComponent + "," + CitGreenComponent + "," + CitBlueComponent + " > ###");

        System.out.print("\nEnter Component Ci0 Red Component - ");
        int Ci0RedComponent = scanner.nextInt();
        System.out.print("\nEnter Component Ci0 Green Component - ");
        int Ci0GreenComponent = scanner.nextInt();
        System.out.print("\nEnter Component Ci0 Blue Component - ");
        int Ci0BlueComponent = scanner.nextInt();
        System.out.println("\n### Ci0 - < " + Ci0RedComponent + "," + Ci0GreenComponent + "," + Ci0BlueComponent + "> ###");

        System.out.print("\nEnter Component Cit Red Component - ");
        int CiTRedComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit Green Component - ");
        int CiTGreenComponent = scanner.nextInt();
        System.out.print("\nEnter Component Cit Blue Component - ");
        int CiTBlueComponent = scanner.nextInt();
        System.out.println("\n### CiT - < " + CiTRedComponent + "," + CiTGreenComponent + "," + CiTBlueComponent + " > ###");

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
        }

        // Creates a color map (with 2^b color instances) which maps each value
        // in range -1.0 to 1.0 to a color instance in the chosen color space
        // such that the value -1.0 maps to the floor color instance, 0.0 maps
        // to the middle color instance, and 1.0 maps to the ceiling color instance.
        // The mapping function should be continuous.
        // The program should output the resulting color map into an ASCII text file
        // as well as display it by visually painting the colors in the color map.



    }

    // Calculations from www.easyrgb.com/index.php?X=MATH&H=01#text01
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

    public static void secondProgram()
    {
        System.out.println("Select a video by choosing any number between 1 and 10.");
        choice = scanner.nextInt();

        String videoFilePath = "C:\\Users\\JAKE\\Desktop\\sampleData\\" + Integer.toString(choice);
        VideoCapture vc = new VideoCapture();
        vc.open(videoFilePath);
    }
}
