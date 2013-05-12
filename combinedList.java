package TuftsDriver;

import java.util.ArrayList;
import java.util.Random;

public class combinedList {

	//constructor	
	public combinedList(){}

	public  String parse (ArrayList<String> movies){
		int x = movies.size();
		String[] finalList;
		String movie= " ";
		finalList = new String [x];
		for(int i = 0; i < x; i++){
			finalList[i] = movies.get(i);
		
		Random gen = new Random();
		int num = gen.nextInt(x);
		movie = finalList[num];
		
		}
		return movie;

	}
}