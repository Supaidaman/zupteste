package zupteste;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teste {
	
	public static boolean isDevelopmentEnvironment() {
	    boolean isEclipse = true;
	    if (System.getenv("eclipse42") == null) {
	        isEclipse = false;
	    }
	    return isEclipse;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc;
		//System.setIn(new FileInputStream(filename));
		//System.out.println(isDevelopmentEnvironment());
		
		//Se estiver dentro da IDE Eclipse, usa input do console
		if(isDevelopmentEnvironment())
			sc = new  Scanner(System.in);
		else{
			//Caso contrario, usa este arquivo
			//sc = new Scanner(System.in);
			File file = new File("testefile.txt");
			sc  = new Scanner(file);

		}
	    Point pointLimits;
	    pointLimits = new Point (sc.nextInt(),sc.nextInt());
	    while(sc.hasNext()) {
	    	Point pointInit =  new Point (sc.nextInt(),sc.nextInt());
	    	char direction = sc.next().trim().charAt(0);
	    	String directionInput = sc.next();
	    	moveRover(direction, directionInput, pointInit, pointLimits);
	    }
	    sc.close();

	}

	private static void moveRover(char direction, String directionInput, Point pointInit, Point pointLimits) {
		
		//em java string e char são coisas diferentes, melhor fazer o for classico
		for(int i = 0; i < directionInput.length(); i++)
		{
		   char c = directionInput.charAt(i);
		   switch(c)
		   {
		   	case 'L':
		   		//vira 90 graus pra esquerda
		   		direction = changeDirection('L',direction);
		   		break;
		   	case 'R':
		   	//vira 90 graus pra direita
		   		direction = changeDirection('R',direction);
		   		break;
		   		
		   	case 'M':
		   		//move um a frente
		   		pointInit= changePosition(pointInit, pointLimits, direction);
		   		break;
		   }
		 
		}
		//imprime resultado
		System.out.println(pointInit.x + " " + pointInit.y  + " " + direction);
		
	}

private static Point changePosition(Point pointInit, Point pointLimits, char direction) {
		// TODO Auto-generated method stub
	switch(direction)
	{
	case 'N':
		if ((pointInit.y+1)<= pointLimits.y)
			pointInit.y++;
		break;
	case 'S':
		if ((pointInit.y-1)>=0)
		 pointInit.y--;
		break;
	case 'W':
		if ((pointInit.x-1)>=0)
		pointInit.x--;
		break;
	case 'E':
		if ((pointInit.x+1)<= pointLimits.x)
		pointInit.x++;
		break;
	}
		return pointInit;
	}

/**
 * *  	N
 * 	  W   E
 *      S 
 * */
	private static char changeDirection(char c, char direction) {
		if(c=='L')
		{
			switch(direction)
			{
			case 'N':
				 direction = 'W';
				break;
			case 'S':
				direction = 'E';
				break;
			case 'W':
				direction = 'S';
				break;
			case 'E':
				direction = 'N';
				break;
			}
		}
		if(c=='R')
		{
			switch(direction)
			{
			case 'N':
				direction = 'E';
				break;
			case 'S':
				direction = 'W';
				break;
			case 'W':
				direction = 'N';
				break;
			case 'E':
				direction = 'S';
				break;
			}
		}
		return direction;
	}
}
