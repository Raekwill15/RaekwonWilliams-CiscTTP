package HomeWork3;
import java.io.*;
import java.util.*;
//Raekwon Williams CISC 3130
public class Hw3Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File file = new File("D:\\eclipse\\movies.csv");
		File outFile = new File("Hw3OutputFile.txt");
		PrintWriter out = new PrintWriter(outFile);
		
		//Creates the binary tree and prints what was entered with readfiles
		MovieBST movieTree = new MovieBST();
		out.flush();
		readFiles(file,movieTree,out);
		
		//Prints the root of the entire tree in alphabetical order
		System.out.println("ROOT: " + movieTree.getRoot());
		System.out.println("Full Binary Tree: ");
		movieTree.printTree(movieTree.getRoot(),out);
		System.out.println("");
		
		//Prints all movies to the left of the root
		System.out.println("Left OF ROOT: ");
		movieTree.printTree(movieTree.getRoot().getLeft(),out);
		System.out.println("");
		
		//Prints all movies to the right of the root
		System.out.println("Right of ROOT: ");
		movieTree.printTree(movieTree.getRoot().getRight(), out);
		
		out.close();
		System.out.println("Fin");
	}
	
	//Takes the movie csv and converts it into a binary tree
	//Then also prints the movies that were entered after tokenizing and storing them to a tree
	public static void readFiles(File file,MovieBST movieTree,PrintWriter out) {
	String row;
	
		try {
			BufferedReader csvReader = new BufferedReader (new FileReader(file));
			//Limited the amount of movies read to 100 because there are over 9000 movies in the file
			//Program still works with 9000 movies but output would have many files
			System.out.println("MOVIES ENTERED: ");
			for (int x=0;x<100;x++) {
				row = csvReader.readLine();
				StringTokenizer token = new StringTokenizer(row,",");
				token.nextToken();
				
				String title;
				int year;
				String[] genres = new String[10];
				
				//Breaks up the title which includes the year in parenthesis and removes the parenthesis and parses the year
				//Then adds the movie to the tree
				StringTokenizer titleToken = new StringTokenizer(token.nextToken(),"(");
				title = titleToken.nextToken();
				year = Integer.parseInt(titleToken.nextToken().replace(")", ""));
				genres = token.nextToken().split("|");
				movieTree.addMovie(title, year, genres);
				System.out.println(title + " (" + year + ")");
			}
			csvReader.close();
			System.out.println("\n\n");
			return;
	}
	catch(Exception e) {
		System.out.println("Null Pointer " + e);
	}
	return;
	}
}