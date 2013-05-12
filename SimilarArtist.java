package TuftsDriver;

public class SimilarArtist {

	
public SimilarArtist(){
	
}
public void findNew(String[] artists){
	int i = artists.length;
	int z;
	for(z=0; z<i; z++){
		Find find = new Find ();
		artists[z]=find.findSimilar(artists[z], 1);
		
	}
}
}
