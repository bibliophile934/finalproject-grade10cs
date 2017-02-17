// Ruju Jambusaria
// June 6, 2014
// Project 2: The Game of 22

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class project2final extends Applet implements ActionListener
{
    Panel p_card, card1, card2, card3, card4, card5, card6, card7, iS; // all panels are global so that they can be used in init and actionPerformed
    CardLayout cdLayout = new CardLayout ();
    int row = 4, totnum = 0, turn = 0, score1 = 0, score2 = 0, leftnum = 22, pic = 1; //leftnum is 22 because there are 22 points needed at the beginning
    String name1, name2, player;
    char theme; // theme variable makes it possible to use only one variable to change the theme as opposed to ints (ex. themea, themeb, and themec)
    // with ints, action Performed would have to set each one of them to 0 when resetting and would increase the value when the theme is being used
    JButton[] cards = new JButton [row * row]; // declaration of the array
    JButton[] tut = new JButton [row * row];
    JLabel total, whoseturn, youwin, tutwhoseturn, tuttotal, tshowleftnum, chosentheme, showscore1, showscore2, scorewin1, scorewin2, whattodo, showleftnum, lpic;
    ButtonGroup choosetheme = new ButtonGroup ();
    JRadioButton theme1, theme2, theme3;
    JProgressBar progress, tprogress;

    public void init ()
    {
        resize (850, 800); // Resize the screen so the game will fit properly.
        p_card = new Panel (); // To hold all of the screens
        p_card.setLayout (cdLayout);

        introScreen (); //start up screen - leads to instructions or the game
        startMenu (); // options: theme of game
        instructions (); // the rules of the game & can lead directly to the game
        tutorial (); // Lets the user try flipping a card
        legend (); // Tells user what the widgets on the side mean or do
        play (); // Screen where user plays the game
        win (); // Users are taken to this screen when someone winss
        setLayout (new BorderLayout ());
        add ("Center", p_card); // Panel for the entire program (allows different scsreens)
    }


    public void introScreen ()
    {
        card1 = new Panel ();
        card1.setBackground (new Color (0, 128, 1));

        iS = new Panel (new GridLayout (4, 2)); // panel named iS because the method name is introScreen

        JLabel title = new JLabel ("The Game of"); // Title
        title.setFont (new Font ("Magneto", Font.BOLD, 80));

        JButton gotostart = new JButton ("Play"); //Allows user to go to the screen on which they can choose the theme their game will be
        gotostart.setBackground (Color.blue);
        gotostart.addActionListener (this);
        gotostart.setActionCommand ("gotostart");
        gotostart.setFont (new Font ("Arial", Font.PLAIN, 40));

        JButton instruct = new JButton ("Instructions"); // Allows user to see instructions
        instruct.setBackground (Color.blue);
        instruct.addActionListener (this);
        instruct.setActionCommand ("instructions");
        instruct.setFont (new Font ("Arial", Font.PLAIN, 40));

        JLabel pic22 = new JLabel (createImageIcon ("22.jpg"));

        // adding all the buttons & labels
        card1.add (title);
        card1.add (pic22);
        iS.add (gotostart);
        iS.add (instruct);
        card1.add (iS);
        p_card.add ("1", card1);
    }


    public void startMenu ()  // shows the 3 themes the user can choose from and leads them to the play screen
    { // info from this page helps set up the play screen
        card2 = new Panel ();
        card2.setBackground (new Color (0, 185, 0));

        JLabel choose = new JLabel ("Choose a Theme");
        choose.setFont (new Font ("Magneto", Font.BOLD, 80));
        choose.setForeground (new Color (99, 14, 150));

        JLabel choosetheme = new JLabel ("To choose a theme, click the button on the left of your desired theme.");
        choosetheme.setFont (new Font ("Papyrus", Font.BOLD, 20));
        choosetheme.setForeground (new Color (99, 14, 150));

        Panel p = new Panel ();
        Panel p1 = new Panel ();
        Panel p2 = new Panel ();
        Panel p3 = new Panel (new BorderLayout ());

        JLabel goclassic = new JLabel (createImageIcon ("classicTitle.jpg"));
        goclassic.setPreferredSize (new Dimension (400, 149));

        JLabel gohp = new JLabel (createImageIcon ("HPtitle.jpg")); // Unedited title is from: Zurn, Zach, ed. "Harry Potter Font." Name That Font. N.p., n.d. Web. 20 May 2014.
        //<http://www.namethatfont.net/harry-potter-book-font/>.
        gohp.setPreferredSize (new Dimension (400, 149));

        JLabel gocolor = new JLabel (createImageIcon ("rainBowTitle.jpg"));
        gocolor.setPreferredSize (new Dimension (400, 149));

        theme1 = new JRadioButton (); // So that the user can choose which theme they want using the radio buttons
        theme1.setText ("");
        theme1.setActionCommand ("classic");
        theme1.addActionListener (this);
        theme1.setBackground (new Color (99, 14, 150));
        choosetheme.add (theme1);

        theme2 = new JRadioButton ();
        theme2.setText ("");
        theme2.setActionCommand ("HP");
        theme2.addActionListener (this);
        theme2.setBackground (new Color (99, 14, 150));
        choosetheme.add (theme2);

        theme3 = new JRadioButton ();
        theme3.setText ("");
        theme3.setActionCommand ("color");
        theme3.addActionListener (this);
        theme3.setBackground (new Color (99, 14, 150));
        choosetheme.add (theme3);

        JButton instructions = new JButton ("Instructions");
        instructions.setActionCommand ("paneinstruct");
        instructions.addActionListener (this);

        // adding panels within panels (so that the layout is not an eyesore)
        card2.add (choose);
        card2.add (choosetheme);
        p.add (theme1); // assigns a radiobutton to a theme
        p.add (goclassic);
        p1.add (theme2);
        p1.add (gohp);
        p2.add (theme3);
        p2.add (gocolor);
        p3.add ("North", p); // ensures that the radiobuttons line up with the JLabels that indicate the theme
        p3.add ("Center", p1);
        p3.add ("South", p2);
        card2.add (p3);
        card2.add (instructions);

        p_card.add ("2", card2);
    }


    public void play ()
    {
        card3 = new Panel ();
        card3.setBackground (new Color (0, 128, 1));

        chosentheme = new JLabel ("CLASSiC");
        chosentheme.setPreferredSize (new Dimension (800, 50));
        chosentheme.setFont (new Font ("Forte", Font.BOLD, 30));
        chosentheme.setForeground (Color.white);

        theme = 'a';

        Panel p = new Panel (new GridLayout (4, 4)); // Makes the cards in a 4 X 4 format
        for (int i = 0 ; i < (row * row) ; i++) // Adds all of the cards
        {
            cards [i] = new JButton (createImageIcon ("a" + (i + 1) + ".jpg")); // images from: Knoll, Byron. "Playing Cards." Google+. N.p., 8 Mar. 2011. Web. 15 May 2014.
            //<https://plus.google.com/photos/+ByronKnoll/albums/5580157611626216385/5581811304467457986?pid=5581811304467457986&oid=115222167450521446250>.
            cards [i].addActionListener (this);
            cards [i].setActionCommand ("" + i); // it doesn't need to have an action command relating to the theme
            // (ex. theme a doesn't need an action command of a1, a2, etc.) this is because there is only one screen to play on and therefore, the
            //action command can be simply a number (the array stays the same - only the icon changes, so the elements in the array already have action
            //commands assigned to them)
            // if it had a letter in it, this line: int num = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ());
            // would be needed in action performed to convert the action command into a integer
            cards [i].setBackground (new Color (0, 128, 1)); // To make the button's background the same color as the screen's background
            cards [i].setBorderPainted (false); // To remove the border around each card and make it more visually appealing and less distracting
            p.add (cards [i]);
        }
        JButton back = new JButton ("Back to Themes"); // Enables user to go back to the start menu
        back.addActionListener (this);
        back.setActionCommand ("back");

        Panel inaline = new Panel (new GridLayout (11, 1));

        total = new JLabel ("Total: 0"); // Shows sum of cards turned over
        total.setPreferredSize (new Dimension (60, 18)); // So that this JLabel doesn't show: "Total..." when totnum becomes a 2-digit number
        total.setForeground (Color.white);

        whoseturn = new JLabel ("Turn: Player 1"); // Shows whose turn it is currently
        whoseturn.setPreferredSize (new Dimension (100, 18));
        whoseturn.setForeground (Color.white);

        JLabel gameswon = new JLabel ("Games Won"); // global because otherwise, when background changes, it remains the original red color at all times
        gameswon.setPreferredSize (new Dimension (150, 15));
        gameswon.setForeground (Color.white);
        gameswon.setFont (new Font ("Arial", Font.PLAIN, 19));

        showscore1 = new JLabel ("Player 1: 0"); // shows games won by player 1
        showscore1.setPreferredSize (new Dimension (100, 15));
        showscore1.setForeground (Color.white);

        showscore2 = new JLabel ("Player 2: 0");  // shows games won by player 2
        showscore2.setPreferredSize (new Dimension (100, 15));
        showscore2.setForeground (Color.white);

        showleftnum = new JLabel ("Points needed: 22");
        showleftnum.setPreferredSize (new Dimension (60, 18));
        showleftnum.setForeground (Color.white);

        JButton reset = new JButton ("Reset Game");
        reset.addActionListener (this);
        reset.setActionCommand ("reset");

        progress = new JProgressBar (0, 0, 100); // don't need to set value because it is already at 0 by default
        progress.setStringPainted (true);
        progress.setForeground (Color.green);

        JButton resetall = new JButton ("Reset All");
        resetall.addActionListener (this);
        resetall.setActionCommand ("resetall");

        JButton instruct = new JButton ("Instructions");
        instruct.addActionListener (this);
        instruct.setActionCommand ("paneinstruct");

        card3.add (chosentheme);
        card3.add (p);
        //adding indicators
        inaline.add (whoseturn);
        inaline.add (total);
        inaline.add (progress);
        inaline.add (showleftnum);
        inaline.add (gameswon);
        inaline.add (showscore1);
        inaline.add (showscore2);
        //adding buttons
        inaline.add (back);
        inaline.add (instruct);
        inaline.add (reset);
        inaline.add (resetall);
        card3.add (inaline);
        p_card.add ("3", card3);
    }


    public void tutorial ()  // introduces players to pressing buttons and which labels show what
    { // everything that is on the play screen must be on the tutorial screen (but may not have to function properly)
        card4 = new Panel ();
        card4.setBackground (new Color (0, 128, 1));

        JLabel tutchosentheme = new JLabel ("TUTORiAL");
        tutchosentheme.setPreferredSize (new Dimension (800, 50));
        tutchosentheme.setFont (new Font ("Forte", Font.BOLD, 30));

        Panel t = new Panel (new GridLayout (4, 4));

        for (int i = 0 ; i < (row * row) ; i++) // Adds all of the cards
        {
            tut [i] = new JButton (createImageIcon ("a" + (i + 1) + ".jpg"));
            tut [i].setBackground (new Color (0, 128, 1));
            tut [i].setBorderPainted (false);
            if (i > 0)
            {
                tut [i].setEnabled (false);
                tut [i].setDisabledIcon (createImageIcon ("a" + (i + 1) + ".jpg"));
            }
            t.add (tut [i]);
        }


        tut [0].addActionListener (this);
        tut [0].setActionCommand ("tut0");

        tut [1].addActionListener (this);
        tut [1].setActionCommand ("tut1");

        Panel tinaline = new Panel (new GridLayout (11, 1));

        whattodo = new JLabel ("Player 1: Click on the ace of spaces (the first card) to turn it over.");
        whattodo.setFont (new Font ("Britannic Bold", Font.BOLD, 20));
        whattodo.setPreferredSize (new Dimension (800, 30));

        tuttotal = new JLabel ("Total: 0"); // Shows sum of cards turned over
        tuttotal.setPreferredSize (new Dimension (60, 15)); // So that this JLabel doesn't show: "Total..." when totnum becomes a 2-digit number
        tuttotal.setForeground (Color.white);

        tutwhoseturn = new JLabel ("Turn: Player 1"); // Shows whose turn it is currently
        tutwhoseturn.setPreferredSize (new Dimension (100, 15));
        tutwhoseturn.setForeground (Color.white);

        tshowleftnum = new JLabel ("Points needed: 22");
        tshowleftnum.setPreferredSize (new Dimension (60, 18));
        tshowleftnum.setForeground (Color.white);

        JLabel gameswon = new JLabel ("Games Won");
        gameswon.setPreferredSize (new Dimension (150, 15));
        gameswon.setForeground (Color.white);
        gameswon.setFont (new Font ("Arial", Font.PLAIN, 19));

        JLabel showscore1 = new JLabel ("Player 1: 0"); // shows games won by player 1
        showscore1.setPreferredSize (new Dimension (100, 15));
        showscore1.setForeground (Color.white);

        JLabel showscore2 = new JLabel ("Player 2: 0");  // shows games won by player 2
        showscore2.setPreferredSize (new Dimension (100, 15));
        showscore2.setForeground (Color.white);

        tprogress = new JProgressBar (0, 0, 100); // don't need to set value because it is already at 0 by default
        tprogress.setStringPainted (true);
        tprogress.setForeground (Color.green);

        JButton reset = new JButton ("Reset Game");
        JButton resetall = new JButton ("Reset All");
        JButton back = new JButton ("Back");
        JButton instruct = new JButton ("Instructions");

        JButton gotostart = new JButton ("Play Game");
        gotostart.setBackground (Color.blue);
        gotostart.addActionListener (this);
        gotostart.setActionCommand ("gotostart");
        gotostart.setFont (new Font ("Arial", Font.BOLD, 30));

        JButton gotolegend = new JButton ("Legend");
        gotolegend.addActionListener (this);
        gotolegend.setActionCommand ("gotolegend");
        gotolegend.setFont (new Font ("Arial", Font.BOLD, 30));
        gotolegend.setBackground (Color.blue);


        card4.add (tutchosentheme);
        card4.add (whattodo);
        card4.add (t);
        //adding indicators
        tinaline.add (tutwhoseturn);
        tinaline.add (tuttotal);
        tinaline.add (tprogress);
        tinaline.add (tshowleftnum);
        tinaline.add (gameswon);
        tinaline.add (showscore1);
        tinaline.add (showscore2);
        //adding buttons
        tinaline.add (instruct);
        tinaline.add (back);
        tinaline.add (reset);
        tinaline.add (resetall);
        card4.add (tinaline);
        card4.add (gotostart);
        card4.add (gotolegend);
        p_card.add ("4", card4);
    }


    public void instructions ()
    {
        card5 = new Panel ();
        card5.setBackground (new Color (57, 249, 30));

        JLabel title = new JLabel ("Instructions");
        title.setFont (new Font ("Magneto", Font.BOLD, 100));
        title.setForeground (new Color (99, 14, 150));

        JTextArea TA = new JTextArea (9, 25); // as an alternative to many different JLabels (also easier to alter - to fit the window)
        TA.setText ("The game of 22 is a 2-player game.");
        TA.append ("The object of this game is to make a common total of 22 or \nget it as close as possible.");
        TA.append ("You add to the common total by flipping over cards. The face \nvalue is added to the total. ");
        TA.append ("The values are as follows: \nAce = 1\n2 = 2\n3 = 3\n4 = 4\nPlayers alternate turns");
        TA.append ("and on your turn, you must flip over only one card. If you make the total \n");
        TA.append ("22 or force your opponent to go over 22, you win. \nGood luck!");
        TA.setBackground (new Color (57, 249, 30)); // so there isn't a big white rectangle on the screen
        TA.setFont (new Font ("Tahoma", Font.PLAIN, 18));

        JButton gotostart = new JButton ("Play Game");
        gotostart.setBackground (Color.blue);
        gotostart.addActionListener (this);
        gotostart.setActionCommand ("gotostart");
        gotostart.setFont (new Font ("Arial", Font.BOLD, 30));

        JButton gotutorial = new JButton ("Tutorial");
        gotutorial.setActionCommand ("gotutorial");
        gotutorial.addActionListener (this);
        gotutorial.setBackground (Color.blue);
        gotutorial.setFont (new Font ("Arial", Font.BOLD, 30));

        card5.add (title);
        card5.add (TA); // only have to add the one JTextArea
        card5.add (gotostart);
        card5.add (gotutorial);
        p_card.add ("5", card5);
    }


    public void win ()  // The user is taken to this screen after they win.
    {
        card6 = new Panel ();
        card6.setBackground (new Color (57, 249, 30));

        Panel lineup = new Panel (new GridLayout (4, 1));

        youwin = new JLabel ("You win!");
        youwin.setFont (new Font ("Magneto", Font.PLAIN, 60));
        youwin.setPreferredSize (new Dimension (820, 80));

        JButton again = new JButton ("Play Again");
        again.setActionCommand ("back"); // has the same effect as going back to the startMenu screen
        again.addActionListener (this);
        again.setBackground (Color.blue);
        again.setFont (new Font ("Arial", Font.BOLD, 30));

        JLabel fireworks = new JLabel (createImageIcon ("fireworks.jpg"));

        JLabel gameswon = new JLabel ("Games Won");
        gameswon.setFont (new Font ("Matura MT Script Capitals", Font.BOLD, 40));

        scorewin1 = new JLabel ("Player 1: 0");
        scorewin1.setFont (new Font ("Matura MT Script Capitals", Font.BOLD, 30));

        scorewin2 = new JLabel ("Player 2: 0");
        scorewin2.setFont (new Font ("Matura MT Script Capitals", Font.BOLD, 30));

        card6.add (youwin);
        lineup.add (gameswon);
        lineup.add (scorewin1);
        lineup.add (scorewin2);
        lineup.add (again);
        card6.add (lineup);
        card6.add (fireworks);
        p_card.add ("6", card6);
    }


    public void legend ()  // enables user to see the functions of the JLabels and JButtons on the right side of the screen
    {
        card7 = new Panel ();
        card7.setBackground (new Color (0, 128, 1));

        JLabel title = new JLabel ("Legend");
        title.setFont (new Font ("Magneto", Font.PLAIN, 40));
        title.setForeground (new Color (99, 14, 150));

        Panel oneline = new Panel (new GridLayout (3, 1));

        lpic = new JLabel (createImageIcon ("p1.jpg"));

        JButton nextpic = new JButton ("Next Picture >>");
        nextpic.addActionListener (this);
        nextpic.setActionCommand ("nextpic");
        nextpic.setFont (new Font ("Arial", Font.BOLD, 30));
        nextpic.setBackground (Color.blue);

        JButton prevpic = new JButton ("<< Previous Picture");
        prevpic.addActionListener (this);
        prevpic.setActionCommand ("previous");
        prevpic.setFont (new Font ("Arial", Font.BOLD, 30));
        prevpic.setBackground (Color.blue);

        JButton gotostart = new JButton ("Play Game");
        gotostart.setActionCommand ("gotostart");
        gotostart.addActionListener (this);
        gotostart.setFont (new Font ("Arial", Font.BOLD, 30));
        gotostart.setBackground (Color.blue);

        card7.add (lpic);
        oneline.add (nextpic);
        oneline.add (prevpic);
        oneline.add (gotostart);
        card7.add (oneline);
        p_card.add ("7", card7);
    }


    public void actionPerformed (ActionEvent e)
    {
        if (e.getActionCommand ().equals ("paneinstruct")) // takes user to the rules of the game (through a JOptionPane)
            JOptionPane.showMessageDialog (null, "The Game of 22 is a 2-player game. \nThe object of this game is to make a total of \n22 or get it as close as possible. \nYou add to a common total by flipping over cards. The face \nvalue is added to the common total. The values are as follows: \nAce = 1\n2 = 2\n3 = 3\n4 = 4\nPlayers alternate turns and on your turn, you \nmust flip over only one card. If you make the total \n22 or force your opponent to go over 22, you win. \nGood luck!", "**Instructions**", JOptionPane.INFORMATION_MESSAGE);
        else if (e.getActionCommand ().equals ("instructions"))
            cdLayout.show (p_card, "5");
        else if (e.getActionCommand ().equals ("gotutorial"))
            cdLayout.show (p_card, "4");
        else if (e.getActionCommand ().equals ("gotostart")) // leads to start menu
        {
            cdLayout.show (p_card, "2");
            player = atstart ();
        }
        else if (e.getActionCommand ().equals ("classic")) // leads to classic version
        {
            cdLayout.show (p_card, "3");
            theme = 'a';
            chosentheme.setText ("CLASSiC");
            loadrighttheme (theme);
        }
        else if (e.getActionCommand ().equals ("HP")) // leads to Harry Potter version
        {
            cdLayout.show (p_card, "3");
            theme = 'b';
            chosentheme.setText ("HARRY POTTER");
            loadrighttheme (theme);
        }
        else if (e.getActionCommand ().equals ("color")) // leads to rainbow version
        {
            cdLayout.show (p_card, "3");
            theme = 'c';
            chosentheme.setText ("RAiNBOW");
            loadrighttheme (theme);
        }
        else if (e.getActionCommand ().equals ("back")) // takes user back to start menu
        { // resets everything so that the user can re-choose everything
            cdLayout.show (p_card, "2");
            ifreset ();
            theme1.setSelected (false);
            theme2.setSelected (false);
            theme3.setSelected (false);
        }
        else if (e.getActionCommand ().equals ("reset")) // puts screen back to the starting layout
            ifreset ();
        else if (e.getActionCommand ().equals ("resetall")) // leads to startMenu and resets player (user has to refill it) names and the theme
        {
            cdLayout.show (p_card, "2");
            ifreset ();
            atstart ();
            score1 = 0;
            score2 = 0;
        }
        else if (e.getActionCommand ().equals ("tut0")) // in the tutorial (when the user presses the ace of spades)
            tut0 ();
        else if (e.getActionCommand ().equals ("tut1")) // in the tutorial (when the user presses the two of spades)
            tut1 ();
        else if (e.getActionCommand ().equals ("gotolegend"))
            cdLayout.show (p_card, "7");
        else if (e.getActionCommand ().equals ("nextpic")) // flips the pictures forward in the legend
        {
            pic++;
            flipPic (pic);
        }
        else if (e.getActionCommand ().equals ("previous")) // flips the pictures back in the legend
        {
            pic--;
            flipPic (pic);
        }
        else // "flips the card over" - changes the image to a flipped over card
        {
            int num = Integer.parseInt (e.getActionCommand ()); // converts the action command to an int type
            player = flipCard (num); // calls the flipCard method - num is a parameter because it is used in the method
            int prog = (totnum * 100) / 22;
            progress.setValue (prog);
            if (totnum >= 22) // if someone wins
                ifwins ();
        }
        total.setText ("Total: " + totnum); // changes the total on the screen (because it has to be done every time totnum changes)
        showleftnum.setText ("Points needed: " + leftnum); // also needs to be changed every time a card is flipped
        whoseturn.setText ("Turn: " + player); // shows whose turn it is
        showscore1.setText (name1 + ": " + score1); // resets game scores at the end as well (so that when the back button is pressed, it still resets this
        showscore2.setText (name2 + ": " + score2);
    }


    public String flipCard (int num)  // must be done every time a card is flipped
    {
        if (theme == 'a')
        {
            cards [num].setIcon (createImageIcon ("abackground.jpg")); // from: Ramone, Boby. "playing card back side 62x90 mm." shutterstock.com. N.p., n.d. Web. 16 May 2014.
            //<http://www.shutterstock.com/s/playing+card+back/search.html#id=103533200&src=psdFRcTa7lEtNH-YPLmX2w-1-3>.
            cards [num].setDisabledIcon (createImageIcon ("abackground.jpg")); // so that the disabled icon will also appear in color
        }
        else if (theme == 'b')
        {
            cards [num].setIcon (createImageIcon ("bbackground.jpg"));  // original from: Freitas, Wilson. "Harry Potter 7 Pattern." flickr.com. N.p., 3 May 2011. Web. 2 June 2014.
            //<http://www.flickr.com/photos/will-goingto/5685058432/>.
            cards [num].setDisabledIcon (createImageIcon ("bbackground.jpg"));
            changebackground ();
        }
        else // (if (theme == 'c')
        {
            cards [num].setIcon (createImageIcon ("cbackground.jpg"));
            cards [num].setDisabledIcon (createImageIcon ("cbackground.jpg"));
        }
        totnum += (num % row) + 1; // finds the column number of the card and therefore adds the card's value to the total when it is flipped over
        leftnum = 22 - totnum;
        turn++; // enables turn to change
        cards [num].setEnabled (false); // so user cannot click on the card again
        if (turn % 2 == 0) // changes whose turn it is
            player = name1;
        else
            player = name2;
        return player;
    }


    public void tut0 ()
    {
        tut [0].setIcon (createImageIcon ("abackground.jpg"));
        tut [0].setEnabled (false); // ensures that the user cannot click it again (in which case it will change tuttotal and tutwhoseturn back
        tut [0].setDisabledIcon (createImageIcon ("abackground.jpg")); // keeps image from graying over
        tut [1].setEnabled (true); // so that the 2 can only be clicked after the ace is clicked

        tprogress.setValue (4);

        whattodo.setText ("Player 2: Click on the 2 of spaces (the second card) to turn it over."); // instructs players to turn over the 2 of spades

        tuttotal.setText ("Total: 1"); // an int is not needed because the ace has to be clicked first
        tshowleftnum.setText ("Points needed: 21");
        tutwhoseturn.setText ("Turn: Player 2"); // String and players names are unneeded because the ace is clicked first (it will always go to player 2)
    }


    public void tut1 ()
    {
        tut [1].setIcon (createImageIcon ("abackground.jpg"));
        tut [1].setEnabled (false);
        tut [1].setDisabledIcon (createImageIcon ("abackground.jpg"));

        tprogress.setValue (13);

        whattodo.setText ("Click Play to begin playing or click legend to learn about the text on the side."); //after no more cards can be turned over

        tuttotal.setText ("Total: 3");
        tshowleftnum.setText ("Points needed: 19");
        tutwhoseturn.setText ("Turn: Player 1");
    }


    public void changebackground ()  // changes the screen background every time a card in the Harry Potter theme is flipped
    { //These are house colors in Harry Potter (red, green, blue, and yellow)
        int x = (int) (Math.random () * 4);
        for (int i = 0 ; i < cards.length ; i++) // chooses a random number so that a random color can be chosen
        {
            if (x == 0)  // Randomly changes the color of the background each time a card is flipped (there may be repeats)
            {
                card3.setBackground (new Color (174, 40, 37));
                cards [i].setBackground (new Color (174, 40, 37));
            }
            else if (x == 1)
            {
                card3.setBackground (new Color (84, 105, 159));
                cards [i].setBackground (new Color (84, 105, 159));
            }
            else if (x == 2)
            {
                card3.setBackground (new Color (18, 95, 49));
                cards [i].setBackground (new Color (18, 95, 49));
            }
            else
            {
                card3.setBackground (new Color (242, 193, 54));
                cards [i].setBackground (new Color (242, 193, 54));
            }
        }
    }


    public String atstart ()  // gets players names and stores them
    { // returns player so that it can display player 1's turn at the beginning and can store it
        name1 = JOptionPane.showInputDialog ("Enter Player 1's name: ");
        while (name1.equals (""))
            name1 = JOptionPane.showInputDialog ("Please enter Player 1's name: ");
        name2 = JOptionPane.showInputDialog ("Enter Player 2's name: ");
        while (name2.equals (""))
            name2 = JOptionPane.showInputDialog ("Please enter Player 2's name: ");
        showStatus ("Welcome " + name1 + " and " + name2 + "!");
        player = name1;
        return player;
    }


    public void ifreset ()  // resets all buttons to their appropriate starting cards
    {
        for (int i = 0 ; i < cards.length ; i++)
        {
            cards [i].setIcon (createImageIcon (theme + "" + (i + 1) + ".jpg"));
            cards [i].setEnabled (true); // lets all of them work
        }
        turn = 0;
        player = name1;
        totnum = 0;
        leftnum = 22;
        progress.setValue (0);
    }


    public void loadrighttheme (char theme)  // loads the correct theme's cards and sets the correct background
    {
        // for Harry Potter theme (cards):
        /*original lion from: Von Merta, Sheron, and Julie. "harry potter gryffindor template." Pinterest. N.p., Feb. 2014. Web. 29 May 2014.
        <http://www.pinterest.com/pin/259801472228580794/>.

        original snake from: Enriquez, Leeann, and Julie. "harry potter slytherin template." Pinterest. N.p., 2013. Web. 29 May 2014.
        <http://www.pinterest.com/pin/209276713907539391/>.

        original eagle from: Amy. "I'm in Ravenclaw!" http://herstory2.wordpress.com. N.p., 1 Nov. 2011. Web. 29 May 2014.
        <http://herstory2.wordpress.com/2011/11/01/im-in-ravenclaw/>.

        original badger from: "Badger Silhouette." http://www.allfreevectors.com/. N.p., n.d. Web. 29 May 2014.
        <http://www.allfreevectors.com/Badger-Silhouette-15108.html>.*/
        for (int i = 0 ; i < (row * row) ; i++)
        {
            cards [i].setIcon (createImageIcon (theme + "" + (i + 1) + ".jpg"));  // sets all cards back to the original layout
            cards [i].setEnabled (true);
            if (theme == 'a')
                cards [i].setBackground (new Color (0, 128, 1));
            else if (theme == 'b')
                cards [i].setBackground (new Color (174, 40, 37));
            else // if (theme == 'c')
                cards [i].setBackground (Color.black);
        }
        if (theme == 'a')
            card3.setBackground (new Color (0, 128, 1));
        else if (theme == 'b')
            card3.setBackground (new Color (174, 40, 37));
        else // if (theme == 'c')
            card3.setBackground (Color.black);
    }


    public void ifwins ()
    {
        cdLayout.show (p_card, "6"); // shows win screen
        turn = 0;
        if (totnum == 22) // since the turn switches, it has to be switched back to the player who made 22
            // otherwise, the player who is forced to go over 22 loses (the last player to go) and no changes have to be made to the
            // player variable therefore, there is no else statement
            {
                if (player == name1)
                    player = name2;
                else
                    player = name1;
            }
        youwin.setText (player + " wins!");
        if (player == name1)
            score1++;
        else if (player == name2)
            score2++;
        // updates JLabels
        scorewin1.setText (name1 + ": " + score1);
        scorewin2.setText (name2 + ": " + score2);
        showscore1.setText (name1 + ": " + score1);
        showscore2.setText (name2 + ": " + score2);
    }


    public void flipPic (int pic)  // flips the pictures in the legend screen
    {
        if (pic >= 11)
            pic = 1;
        else if (pic <= 0)
            pic = 10;
        lpic.setIcon (createImageIcon ("p" + pic + ".jpg"));
    }


    protected static ImageIcon createImageIcon (String path)  // so that the pictures will appear on the screen
    {
        java.net.URL imgURL = project2final.class.getResource (path);
        if (imgURL != null)
        {
            return new ImageIcon (imgURL);
        }
        else
        {
            System.err.println ("Couldn't find file: " + path);
            return null;
        }
    }
}
