package TuftsDriver;


import java.io.IOException;



public class Spotify {


	public Spotify(){

	}
	public void postPic( String id)
	{
		System.out.println("Fuck");
		
	
		try 
		{
			Runtime.getRuntime().exec("open " + id);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}

	}


