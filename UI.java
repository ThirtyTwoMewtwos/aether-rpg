import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UI extends JFrame implements ActionListener, KeyListener
{
    private JPanel windowPane = new JPanel();
    private JPanel gameWindow = new JPanel();
    private JPanel controlsWindow = new JPanel();
    
    private JTextArea output = new JTextArea(5,25);
    
    public UI()
    {
        windowPane.setLayout(new BorderLayout());
        
        output.setEnabled(false);
        controlsWindow.add(output);
        
        gameWindow.setMinimumSize(new Dimension(600,600));
        controlsWindow.setMinimumSize(new Dimension(600,150));
        
        windowPane.add(gameWindow, BorderLayout.CENTER);
        windowPane.add(controlsWindow, BorderLayout.SOUTH);
        add(windowPane);
        
        setMinimumSize(new Dimension(600,750));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(getMinimumSize());
        setTitle("Aether");
        pack();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {
        
    }   
    public void keyPressed(KeyEvent e)
    {
        
    }   
    public void keyTyped(KeyEvent e)
    {
        
    }
}
