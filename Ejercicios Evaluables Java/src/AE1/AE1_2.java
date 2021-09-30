package AE1;
import java.util.ArrayList;
public class AE1_2 {

	public static void main(String[] args) {
		
		//A)
		String[] Companeros={"Josep","Manel","Nestor","Claudiu","Victor","Enrique"};
		for(int i=0;i<Companeros.length;i++) {
			System.out.println(Companeros[i]);
			
		}
		System.out.println();
		//B
		ArrayList<String> compa=new ArrayList<String>(); 
		
		compa.add("Josep");
		compa.add("Manel");
		compa.add("Nestor");
		compa.add("Claudiu");
		compa.add("Victor");
		compa.add("Enrique");
		
		for(int i=0;i<compa.size() ;i++) {
			System.out.println(compa.get(i));
			
		}
			
		
		
	}

}
