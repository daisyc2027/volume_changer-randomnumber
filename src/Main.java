import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

class VolumeController extends JPanel{
    private int volume;
    private final JLabel label;

    public VolumeController(){
        JButton button = new JButton("Change");
        label = new JLabel("Your current value is: " + volume + "%");
        Font myFont = new Font("Times New Roman", Font.BOLD, 30);
        label.setFont(myFont);
        button.addActionListener(e -> {
            volume = new Random().nextInt(0,100);
            label.setText("Your current value is: " + volume + "%");
            System.out.print(volume);
            changeVolume(volume);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(button);
    }

    public void changeVolume(int volumeToBeChanged) {
        try {
            String[] command = { "osascript", "-e", "set volume output volume " + volumeToBeChanged};
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        VolumeController controller = new VolumeController();
        JFrame frame = new JFrame("Volume controller");
        frame.setBounds(500,100,500,200);
        frame.add(controller);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}