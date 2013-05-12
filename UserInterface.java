package TuftsDriver;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UserInterface extends JFrame
{
	private static final int WIDTH = 400; 
	private static final int HEIGHT = 600;
	private JTextField enterArtist;
	private JLabel displayMovie, title, info1, info2, subtext;
	private JButton calculate;
	private CalculateButtonHandler cbHandler;
	private String textInput;
	private boolean pressed;
	private String [] artists; 
	Container pane;

	public UserInterface() throws IOException
	{
		getContentPane().setBackground(Color.BLACK);
		Color background = new Color(0,0,0);
		Color white = new Color (250, 250, 250);
		Font font1 = new Font ("Hollywood Capital", Font.PLAIN, 30);

		//sets up placement, fonts, and color
		title = new JLabel();
			title.setSize(2000, 200);
			title.setLocation(0, 0);
		displayMovie = new JLabel("CINEMUSIC", SwingConstants.CENTER );
			displayMovie.setForeground(Color.white);
			displayMovie.setFont(font1);
			displayMovie.setSize(300, 200);
			displayMovie.setLocation(100,0);
		enterArtist = new JTextField("Enter artists: ", SwingConstants.CENTER);
			enterArtist.setSize(300, 60);
			enterArtist.setLocation(50, 350);
		calculate = new JButton ("Search Movies");
			cbHandler = new CalculateButtonHandler();
			calculate.addActionListener(cbHandler);
			calculate.setSize(300, 50);
			calculate.setLocation(50, 410);
		info1 = new JLabel("find movies to watch with soundtracks that", SwingConstants.CENTER );
			info1.setForeground(Color.gray);
			info1.setFont(new Font("Serif", Font.PLAIN, 18));
			info1.setSize(400, 100);
			info1.setLocation(0,270);
		info2 = new JLabel("are similar to your favorite songs", SwingConstants.CENTER );
			info2.setForeground(Color.gray);
			info2.setFont(new Font("Serif", Font.PLAIN, 18));				
			info2.setSize(400, 100);				
			info2.setLocation(0,290);
		//subtext = new JLabel("Jennie Lamere & Barbara Duckworth\tusing the Echonest API");
			//subtext.setForeground(Color.gray);
			//subtext.setFont(new Font("Serif", Font.PLAIN, 12));				
			//subtext.setSize(400, 100);
			//subtext.setLocation(0, 550);


		//inserting top image
		ImageIcon icon = new ImageIcon("/Users/barbara/Pictures/projects/MovieReel.jpg");
		title.setIcon(icon); 

		//inserting logo
		ImageIcon cineMusicLogo = new ImageIcon ("/Users/barbara/Pictures/projects/CineMusicSmall.png");
		JLabel logo = new JLabel(cineMusicLogo);
			logo.setSize(100, 125);
			logo.setLocation(50, 40);

		//get the content pane
		pane = getContentPane();

		//puts things on pane
		pane.add(logo);
		pane.add(title);
		pane.add(info1);
		pane.add(info2);
		pane.add(enterArtist);
		pane.add(calculate);
		pane.add(displayMovie);
		//pane.add(subtext);

		//basic JFrame stuff
		setTitle("CineMusic");
		setSize (WIDTH, HEIGHT);
		setBackground(new Color (150, 30, 40));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//info from text box 
	public String getArtistEntered (){ return textInput;}

	//changes label to display movie
	public void setLabel(String movie)
	{
		displayMovie.setText(movie);
	}

	//has button been pressed?
	public boolean buttonPressed (){ return pressed;}

	private class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("button pressed");
			System.out.println("looking up " + enterArtist.getText());
			JButton B = (JButton)e.getSource();

			if (B == calculate)
			{
				textInput = enterArtist.getText();
				GetInput get = new GetInput(textInput);
				artists = get.returnInput();

				NoMatch goodArtist = new NoMatch ();
		    	Find find = new Find ();
		    	Search findMovie = new Search ();
		    	int counter = 0; 
		    	SimilarArtist random = new SimilarArtist();

		    	random.findNew(artists);

		    	for (int i = 0; i < artists.length; i++)
		    	{
		    		while (!(goodArtist.exists(artists[i], TuftsDriver.allSoundtracks)) && counter !=10)
			    	{
			    		artists[i] = goodArtist.similarArtist(artists[i]);
			    		counter++;

			    		if (counter > 5)
			    		{
			    			artists[i] = "skip";
			    			counter = 10;
			    		}
			    	}
		    	}

		    	findMovie.toOneArtist(artists, TuftsDriver.allSoundtracks, TuftsDriver.allIDs);

		    	ArrayList<String> movieRec = findMovie.getMovieList();
		    	ArrayList<String> idRec = findMovie.getIDList();

		    	if(idRec.size() == movieRec.size())
		    		System.out.println("same number of movies and IDs = true");
		    	else
		    		System.out.println("same number of movies and IDs = false");

		    	combinedList finalize = new combinedList ();
		    	finalize.parse(movieRec, idRec);
		    	Spotify spotify = new Spotify();
		    	String movie = finalize.getMovie();
		    	String id = finalize.getID();
		    	spotify.postPic(id);


		    	System.out.println("You should watch:  " + movie);
		    	System.out.println(movie + " = " + id);

		    	TuftsDriver.UI.setLabel(movie);

		    	//cue spotify app
		    	//Spotify addEffects = new Spotify();
		    	//addEffects.postPic(rec, TuftsDriver.allIDs, artists);
			}
		}
	}

}