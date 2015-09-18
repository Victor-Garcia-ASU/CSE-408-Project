import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayColorMap
{
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private JFrame jFrame;

    private static int WIDTH = 500; // 500 px width
    private static int HEIGHT = 65; // 65 px height

    private int colorInstances; // 2^b colorinstances

    private String type; // RGB, XYZ, etc

    private double CitFirstComponent; // floor values ex: 0, 0, 0
    private double CitSecondComponent;
    private double CitThirdComponent;

    private double Ci0FirstComponent; // medium values ex: 50, 50, 50
    private double Ci0SecondComponent;
    private double Ci0ThirdComponent;

    private double CiTFirstComponent; // ceiling values ex: 100, 100, 100
    private double CiTSecondComponent;
    private double CiTThirdComponent;

    private String fileName; // ascii file path to write to
    private ArrayList<String> rgbValues = new ArrayList<String>();

    DisplayColorMap()
    {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        imageIcon = new ImageIcon( image );
        jLabel = new JLabel( imageIcon );
        jFrame = new JFrame( "Color Map" );
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container container = jFrame.getContentPane();
        container.setLayout( new BorderLayout() );
        container.add( jLabel, BorderLayout.CENTER );
        jFrame.pack();
    }

    public static void createColorMap(DisplayColorMap dcm)
    {
        dcm.paint();
        dcm.view();
    }

    // Creates a color map (with 2^b color instances) which maps each value
    // in range -1.0 to 1.0 to a color instance in the chosen color space
    // such that the value -1.0 maps to the floor color instance, 0.0 maps
    // to the middle color instance, and 1.0 maps to the ceiling color instance.
    // The mapping function should be continuous.
    // The program should output the resulting color map into an ASCII text file
    // as well as display it by visually painting the colors in the color map.
    private void paint()
    {
        Graphics graphics = image.getGraphics();

        int halfCI = colorInstances / 2;

        // Calculate distance from Cit to Ci0 and use half the color instances getting to ci0
        double difference1First = Ci0FirstComponent - CitFirstComponent;
        double difference2First = CiTFirstComponent - Ci0FirstComponent;

        double difference1Second = Ci0SecondComponent - CitSecondComponent;
        double difference2Second = CiTSecondComponent - Ci0SecondComponent;

        double difference1Third = Ci0ThirdComponent - CitThirdComponent;
        double difference2Third = CiTThirdComponent - Ci0ThirdComponent;

        double currentPoint1 = CitFirstComponent;
        double increment1 = difference1First / halfCI;
        double currentPoint2 = CitSecondComponent;
        double increment2 = difference1Second / halfCI;
        double currentPoint3 = CitThirdComponent;
        double increment3 = difference1Third / halfCI;

        double spaceIncrement = (WIDTH / 2) / halfCI;
        int x = 0;
        int y = 0;
        int i = 0;
        while (currentPoint1 <= Ci0FirstComponent)
        {
            rgbValues.add(i, currentPoint1 + ", " + currentPoint2 +", " + currentPoint3);
            i++;
            graphics.setColor(new Color((int)currentPoint1, (int)currentPoint2, (int)currentPoint3));
            graphics.fillRect(x, y, (int) spaceIncrement, 50);
            x += (int)spaceIncrement;
            currentPoint1 += increment1;
            currentPoint2 += increment2;
            currentPoint3 += increment3;
        }

        char [] data = new char [2];
        data[0] = '-';
        data[1] = '1';
        graphics.setColor(Color.black);
        graphics.drawChars(data, 0, 2, 5, 60);

        data = new char[1];
        data[0] = '0';
        graphics.drawChars(data, 0, 1, x , 60);

        currentPoint1 = Ci0FirstComponent;
        currentPoint2 = Ci0SecondComponent;
        currentPoint3 = Ci0ThirdComponent;
        increment1 = difference2First / halfCI;
        increment2 = difference2Second / halfCI;
        increment3 = difference2Third / halfCI;
        while (currentPoint1 <= CiTFirstComponent)
        {
            rgbValues.add(i, currentPoint1 + ", " + currentPoint2 +", " + currentPoint3);
            i++;
            graphics.setColor(new Color((int)currentPoint1, (int)currentPoint2, (int)currentPoint3));
            graphics.fillRect(x, y, (int) spaceIncrement, 50);
            x += (int) spaceIncrement;
            currentPoint1 += increment1;
            currentPoint2 += increment2;
            currentPoint3 += increment3;
        }
        data[0] = '1';
        graphics.drawChars(data, 0, 1, x - 5, 60);
        createASCIIFile();
    }


    public void createASCIIFile()
    {
        BufferedWriter bw = null;
        fileName = type + "_" + CitFirstComponent + "-" + CitSecondComponent + "-" + CitThirdComponent
                        + "_" + Ci0FirstComponent + "-" + Ci0SecondComponent + "-" + Ci0ThirdComponent
                        + "_" + CiTFirstComponent + "-" + CiTSecondComponent + "-" + CiTThirdComponent
                        + ".txt";
        try
        {
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(type + "\n");
            bw.write(colorInstances + "\n");
            double current = -1.0;
            double step = 2.0 / colorInstances; // how much to increment current by for the range
            double secondValue = 0;
            for (int i = 0; i < colorInstances; i++)
            {
                secondValue = current+step;
                bw.write(i + " -> [" + current + "," + secondValue + ") " + " -> " + rgbValues.get(i) + "\n");
                current += step;
            }
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setColorMapValues(int ci, double Cit1, double Cit2, double Cit3, double Ci01, double Ci02, double Ci03, double CiT1, double CiT2, double CiT3, String type1)
    {
        colorInstances = ci;
        CitFirstComponent = Cit1;
        CitSecondComponent = Cit2;
        CitThirdComponent = Cit3;
        Ci0FirstComponent = Ci01;
        Ci0SecondComponent = Ci02;
        Ci0ThirdComponent = Ci03;
        CiTFirstComponent = CiT1;
        CiTSecondComponent = CiT2;
        CiTThirdComponent = CiT3;
        type = type1;
    }

    private void view() { jFrame.setVisible( true ); }
}