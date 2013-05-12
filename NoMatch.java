package TuftsDriver;


import java.util.ArrayList;
import java.util.Map;

public class NoMatch {

	int counter; 

	public NoMatch()
	{
		counter = 0; 
	}

	//returns true if "artist" is found in the map
 public boolean exists (String myArtist, Map <String, ArrayList> mySoundtracks)
	{
		return mySoundtracks.containsKey(myArtist);
	}

	//searches for a key artist similar to the one that had no matches, and makes sure it is available
	//returns a good artist to use 
	public String similarArtist(String myArtist)
	{
    	counter++;	//finds next one with next call
    	
		Find find = new Find();
		String tempArt;
    	myArtist = find.findSimilar(myArtist, counter);	//finds the nth similar artist
    	
		return myArtist;
	}


}