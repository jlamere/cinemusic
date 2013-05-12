package TuftsDriver;
 
import java.util.ArrayList;
import java.util.Map;
 

public class Search {

	//constructor
	public Search ()
	{

	}

	public ArrayList<String> oneArtist(String[] artists, Map<String, ArrayList> mySoundtracks) {
/.		ArrayList<String> goodMovies = new ArrayList <String>();
		int x = artists.length, z;

	 //see if can find a sound track with one artist on it
			for(z = 0; z<x; z++){
			
				feedback = mySoundtracks.get(artists[z]);
				goodMovies.addAll(feedback);

				//goodMovies.addAll(feedback);
				}

			if(feedback != null){
				return goodMovies;	
			}
			//next steps: >1 artist and using recommendations

		return goodMovies;	


	}


	}