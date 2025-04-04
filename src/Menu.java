import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Menu extends Frame{
    private int currency=100;
    private JLabel label= new JLabel("Casino");
    private JButton slots=new JButton("Slots");
    private JButton blackjack=new JButton("Blackjack");
    private JButton save=new JButton("Save");
    private JButton load=new JButton("Load");
    private JLabel tokens=new JLabel("Your Credits: "+String.valueOf(getCurrency()));
    public int getCurrency(){
        return currency;
    }
    public void setCurrency(int c){
        currency=c;
        tokens.setText(String.valueOf(currency));
    }
    public void addToCurrency(int c){
        currency-=-c;
        tokens.setText(String.valueOf(currency));
    }
    public void saveTokens(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("Tokens.txt"))){
            writer.write(String.valueOf(getCurrency()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadTokens(){
        try(BufferedReader reader=new BufferedReader(new FileReader("Tokens.txt"))){
            String token=reader.readLine();
            if(token!=null){
                int tokenFromFile=Integer.parseInt(token);
                setCurrency(tokenFromFile);
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            System.out.println("Invalid tokens format in file.");
        }
    }

    Menu(){
        setSize(200,180);
        JPanel panel=new JPanel(new GridLayout(4,1));
        //panel.add(slots);
        panel.add(blackjack);
        panel.add(save);
        panel.add(load);
        add(tokens,BorderLayout.NORTH);
        add(label);
        add(panel, BorderLayout.SOUTH);
        /*
        slots.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Slots slots=new Slots(Menu.this);
                slots.setVisible(true);
            }
        });
         */
        blackjack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Blackjack blackjack=new Blackjack(Menu.this);
                blackjack.setVisible(true);
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                saveTokens();
            }
        });
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loadTokens();
            }
        });
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

}