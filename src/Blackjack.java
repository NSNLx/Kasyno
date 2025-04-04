import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Blackjack extends Frame{
    private Menu menu;

    private int playerValue=0;
    private int dealerValue=0;
    private boolean passed=false;
    private boolean dealerPassed=false;
    private int price=5;

    public int getPlayervalue() {
        return playerValue;
    }

    public void setPlayervalue(int playerValue) {
        this.playerValue = playerValue;
    }

    public int getDealervalue() {
        return dealerValue;
    }

    public void setDealervalue(int dealerValue) {
        this.dealerValue = dealerValue;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isDealerpassed() {
        return dealerPassed;
    }

    public void setDealerpassed(boolean dealerPassed) {
        this.dealerPassed = dealerPassed;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void gameReset(){
        playerValue=0;
        dealerValue=0;
        passed=false;
        dealerPassed=false;
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }
    public void win(){
        JOptionPane.showMessageDialog(null, "You won");
        gameReset();
        menu.addToCurrency(price);
    }

    public void loss(){
        JOptionPane.showMessageDialog(null, "You lost");
        gameReset();
        menu.addToCurrency(-price);
    }

    public void draw(){
        JOptionPane.showMessageDialog(null, "Draw");
        gameReset();
    }

    public void hitCardPlayer(){
        Card a=Card.getRandomCard();
        playerValue+=a.getPower();
        ImageIcon cardIcon = new ImageIcon(getClass().getResource(a.fileName()));
        Image scaledImage = cardIcon.getImage().getScaledInstance(60, 90, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(scaledImage);
        JLabel card=new JLabel(cardIcon);
        playerPanel.add(card);
        playerPanel.revalidate();
        playerPanel.repaint();
    }

    public void hitCardDealer(){
        Card b=Card.getRandomCard();
        dealerValue+=b.getPower();
        ImageIcon cardIconDealer = new ImageIcon(getClass().getResource(b.fileName()));
        Image scaledImageDealer = cardIconDealer.getImage().getScaledInstance(60, 90, Image.SCALE_SMOOTH);
        cardIconDealer = new ImageIcon(scaledImageDealer);
        JLabel cardDealer=new JLabel(cardIconDealer);
        dealerPanel.add(cardDealer);
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }

    //Thread thread = new Thread();

    //public void sleep(){
    //    try{
    //        thread.sleep(400);
    //    }catch(InterruptedException e){
    //        e.printStackTrace();}
    //}
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JPanel panel2;
    private JButton hit;
    private JButton stand;
    private JPanel game;
    private JLabel label;
    private JTextField stake;
    private JButton deal;
    private JPanel panel;

    Random randomInt=new Random();
    public Blackjack(Menu menu){
        this.menu=menu;
        setSize(300,500);
        JOptionPane.showMessageDialog(null,"Ace currently works as 1 at all times");

        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Your cards"));
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        dealerPanel = new JPanel();
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Dealers cards"));
        dealerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel2 = new JPanel(new GridLayout(2,1));
        hit = new JButton("Hit");
        stand = new JButton("Stand");

        game = new JPanel(new GridLayout(1,2));

        label = new JLabel("Blackjack");
        stake = new JTextField();
        stake.setText("5");
        deal = new JButton("Deal");
        panel = new JPanel(new GridLayout(3,1));

        panel.add(label);
        panel.add(stake);
        panel.add(deal);
        add(panel, BorderLayout.NORTH);

        game.add(playerPanel);
        game.add(dealerPanel);
        add(game, BorderLayout.CENTER);

        panel2.add(hit);
        panel2.add(stand);
        add(panel2, BorderLayout.SOUTH);

        Random randomInt=new Random();

        deal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    if(Integer.parseInt(stake.getText())>0&&Integer.parseInt(stake.getText())<=menu.getCurrency()){
                        price=Integer.parseInt(stake.getText());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid stake");
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid stake");
                }
            }
        });

        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(dealerPassed==true){
                    hitCardPlayer();
                    if(playerValue>21){
                        loss();
                    }
                }
                if(passed==false&&dealerPassed==false){
                    if(playerValue<21&&dealerValue<21){
                        hitCardPlayer();
                        hitCardDealer();
                    }
                    if(playerValue>21&&dealerValue<=21){
                        loss();
                    }
                    if(playerValue<=21&&dealerValue>21){
                        win();
                    }
                    if(playerValue>21&&dealerValue>21){
                        draw();
                    }
                    if(dealerValue<=21&&dealerValue>12+randomInt.nextInt(3)&&dealerValue>playerValue){
                        dealerPassed=true;
                        dealerPanel.add(new JLabel("Dealer passed"));
                        dealerPanel.revalidate();
                        dealerPanel.repaint();
                    }
                }
                if(dealerPassed==true&&dealerValue==playerValue){
                    draw();
                }

            }
        });

        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (playerValue>1) {
                    passed=true;
                    playerPanel.add(new JLabel("You passed"));
                    playerPanel.revalidate();
                    playerPanel.repaint();
                    if(playerValue==dealerValue){
                        if(dealerValue>11){
                            hitCardDealer();
                            if(dealerValue<=21){
                                dealerPanel.add(new JLabel("Dealer passed"));
                                loss();
                            }
                            if(dealerValue>21){
                                win();
                            }
                            else{
                                dealerPanel.add(new JLabel("Dealer passed"));
                                draw();
                            }
                        }
                    }
                }
                if(dealerValue>playerValue){
                    loss();
                }
                else{
                    while (dealerValue<playerValue) {
                        hitCardDealer();
                        if(dealerValue>21){
                            win();

                        }
                        if(dealerValue>playerValue&&dealerValue<=21){
                            dealerPanel.add(new JLabel("Dealer passed"));
                            dealerPanel.revalidate();
                            dealerPanel.repaint();
                            loss();

                        }
                        if(dealerValue==playerValue&&playerValue>11&&dealerValue>11){
                            dealerPanel.add(new JLabel("Dealer passed"));
                            dealerPanel.revalidate();
                            dealerPanel.repaint();
                            draw();

                        }
                        if(dealerPassed==true&&dealerValue==playerValue){
                            dealerPanel.add(new JLabel("Dealer passed"));
                            draw();
                        }
                        if (passed==true&&playerValue==dealerValue&&dealerValue>11){ {
                            dealerPanel.add(new JLabel("Dealer passed"));
                            draw();
                        }
                            if(dealerPassed==true&&passed==true){
                                if(dealerValue>playerValue){
                                    loss();
                                }
                            }
                        }

                    }
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });

    }
}