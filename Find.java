
package TuftsDriver;

import java.util.List;
import com.echonest.api.v4.Artist;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;


public class Find {

	EchoNestAPI echoNest = new EchoNestAPI("QU4UFSKPZCJGFGW8M");

	//constructor
	public Find ()
	{

	}

	//returns the nth similar artist 
	public String findSimilar(String myArtist, int n)
	{
		  List<Artist> artists = null;
		  String returnArtist = null;
			try 
			{
				artists = echoNest.searchArtists(myArtist);
			} 
			catch (EchoNestException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    if (artists.size() > 0) 
		    {
		        Artist randArtist = artists.get(0);
		        try 
		        {
					for (Artist simArtist : randArtist.getSimilar(n)) {
					    returnArtist = simArtist.getName().toLowerCase().trim();
					}
				} 
		        catch (EchoNestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		return returnArtist;
	}

}
