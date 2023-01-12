import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.sound.sampled.*;


public class swuuidgen {
    private JFrame frame;
    private JTextField textField;
    private JButton generateButton;
    private JButton copyButton;
    private JButton playButton;
    private JButton pauseButton;
    private String randomUUIDString;
    public AudioInputStream audioInputStream; 
    public Clip clip; 

    public swuuidgen() {

        try {
        audioInputStream = AudioSystem.getAudioInputStream(new File("mssgr.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        frame = new JFrame("swUUIDgen v1.0");
        textField = new JTextField();
        generateButton = new JButton("Generate Correlation-ID UUID");
        copyButton = new JButton("Copy to clipboard");
        playButton = new JButton("Play Music");
        pauseButton = new JButton("Pause Music");

        //Audio
        

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UUID uuid = UUID.randomUUID();      
                randomUUIDString = uuid.toString(); // Get the string representation of the UUID     
                textField.setText(randomUUIDString); // Print the generated UUID string
            }
        });
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(randomUUIDString);
                Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  
                clip.start(); 
                         
            }
            
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  
                    clip.stop();
                   
                   
                         
            }
            
        });

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);

        JLabel imageLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        layout.setConstraints(imageLabel, gbc);
        ImageIcon icon = new ImageIcon("img_1.png");
        imageLabel.setIcon(icon);
        frame.add(imageLabel);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        layout.setConstraints(textField, gbc);
        frame.add(textField);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        layout.setConstraints(generateButton, gbc);
        frame.add(generateButton);

        gbc.gridx = 1;
        gbc.gridy = 2;
        layout.setConstraints(copyButton, gbc);
        frame.add(copyButton);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        layout.setConstraints(playButton, gbc);
        frame.add(playButton);
        gbc.gridx = 1;
        gbc.gridy = 3;
        layout.setConstraints(pauseButton, gbc);
        frame.add(pauseButton);

        frame.setSize(600, 315);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new swuuidgen();
    }
}