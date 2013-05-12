package TuftsDriver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TuftsDriver {


	  private static String[] split;
	  private static Map <String, ArrayList> allSoundtracks = new HashMap <String, ArrayList>();	//each artist key is linked to a list of soundtracks
	  private static Map <String, ArrayList> allIDs = new HashMap <String, ArrayList>();	//each artist key is linked to a list of IDs for soundtracks
	  private static String artistName;


	    public static void main(String args[]) throws IOException {
	    	FileInputStream inFile; //opens and parses file
	    	inFile = new FileInputStream("/Users/Jen/Documents/data.txt");
	    	getData(initFile(inFile));
	    	userInput();
	    	inFile.close();

	    	Search findMovie = new Search ();
	    	ArrayList<String> movieRec = findMovie.oneArtist(artistName, allSoundtracks);
	    	System.out.println("You should watch: " + movieRec);
	    }

	    public static BufferedReader initFile(FileInputStream newFile) throws IOException { //prepares file to be read
	        InputStreamReader inReader = new InputStreamReader(newFile);
	        BufferedReader reader = new BufferedReader(inReader);
	        return reader;
	    }

	    public static void getData(BufferedReader read) throws IOException { //breaks up file. split[0] is first column, split[1] is second, ect. 

	        String line, partString = "<sep>";
	        line = read.readLine();
	        while (line != null) {
	            split = line.split(partString);
	            line = read.readLine();
		        fillSoundtracks (split);
	        }
	    }

	    public static void fillSoundtracks (String [] mySplit)
	    {
	    	String thisArtist; 
	    	ArrayList<String> theseSoundtracks = new ArrayList <String>();
	    	ArrayList<String> theseIDs = new ArrayList <String>();
	    	String soundtrack;
	    	String id;

            thisArtist = mySplit[0].toLowerCase().trim();
            soundtrack = mySplit[1];
            id = mySplit [2];
            
            //if already there, adds to existing
            if (allSoundtracks.containsKey(thisArtist))
            {
            	theseSoundtracks = allSoundtracks.get(thisArtist);
            	theseSoundtracks.add(soundtrack);
            	theseIDs.add(id);
            	allSoundtracks.remove(thisArtist);
            	allSoundtracks.put(thisArtist, theseSoundtracks);
            	allIDs.put(thisArtist, theseIDs);
            }
            //creates new key with stuff
            else 
            {
            	theseSoundtracks.add(soundtrack);
            	theseIDs.add(id);
            	allSoundtracks.put(thisArtist, theseSoundtracks);
            	allIDs.put(thisArtist, theseIDs);
            }
            
	    }

	    public static void userInput(){
	    	Scanner userInput = new Scanner(System.in);
	    	String info = "";
	    	System.out.print("Enter Artist");
	    	artistName = userInput.nextLine();
	    	artistName = artistName.toLowerCase().trim();

	    	ArrayList<String> list = allSoundtracks.get(artistName);

	    	for (int i = 0; i < allSoundtracks.get(artistName).size(); i++)
	    	{
	    		info = info + allSoundtracks.get(artistName).get(i);
	    		info = info + " ID: " + allIDs.get(artistName).get(i) + "\n";
	    	}
	    	System.out.println(artistName);
	    	System.out.println("\n" + info);

	    }
}