// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println((int) (plus(2,3)));   // 2 + 3
	    System.out.println((int) minus(7,2));  // 7 - 2
   		System.out.println((int) minus(2,7));  // 2 - 7
 		System.out.println((int) times(3,4));  // 3 * 4
   		System.out.println((int) plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println((int) pow(5,3));      // 5^3
   		System.out.println((int) pow(3,5));      // 3^5
   		System.out.println((int) div(12,3));   // 12 / 3    
   		System.out.println((int) div(5,5));    // 5 / 5  
   		System.out.println((int) div(25,7));   // 25 / 7
   		System.out.println((int) mod(25,7));   // 25 % 7
   		System.out.println((int) mod(120,6));  // 120 % 6    
   		System.out.println((int) sqrt(36));
		System.out.println((int) sqrt(263169));
   		System.out.println((int) sqrt(76123));
	}  

	// Returns x1 + x2
	public static double plus(double x1, double x2) {

		double sum = x1;

		if(x2>=0){ 						//Checks if the addition variable is positive or negative

		for(int i=0 ; i<x2 ; i++){
			sum++;						// If its positive it adds 1 x2 amount of times (so x2) to sum which starts as x1
		}
		}	else {						// If its negative

			for(int i=0 ; i<x2 ; i++){
				sum--;					// If its negative it subtracts 1 x2 amount of times (so x2) to sum which starts as x1
		}
	}
		return (sum);
	}

	// Returns x1 - x2
	public static double minus(double x1, double x2) {
		
		double difference = x1;

		for (int j = 0; j < x2; j++) {
			difference--;					// Takes an initial value x1 and subtracts 1 x2 amount of times
		}
		return  (int) difference;
	}

	// Returns x1 * x2
	public static double times(double x1, double x2) {
        
		double product = 0;

		for (int i = 0; i < x2; i++) {

			for(int j = 0; j <x1 ; j++){
				
				product ++;

			}
			
		}


		return (product);
	}

	// Returns x^n (for n >= 0)
	public static double pow(int x, int n) {
		
		double power = x;

		if(n == 0){
			power = 1;
			return (power);
		} else{

		for(int i = 0 ; i < n ; i++){

		power = times(power,x);

		}

		return (power);
	}}
	// Returns the integer part of x1 / x2 
	public static double div(double x1,double x2) {
  
		int quotient = 0;

		while (x1 >= x2){
			x1 = minus(x1,x2);
			quotient ++;		// Every time x1 fits into x2, quotient goes up by one and repeats until x1 can no longer fit into x2
		}

		return (int) quotient;
	}

	// Returns x1 % x2
	public static int mod(double x1, double x2) {

        double remainder = 0;

		while (x1 >= x2){
			x1 = minus(x1,x2);
			remainder = x1;
		}

		return ((int) remainder);
	}	

	// Returns the integer part of sqrt(x) 
	// Returns the square root of x using a binary search approximation
	public static double sqrt(double x) {
											
			if(x < 0){
				return Double.NaN;

			}

			Double r = x;
			Double epsilon = 0.001;
			
			while ((r*r - x) > epsilon || (r*r - x) < 0){
				
				r = (((x/r) + r) / 2.0);
				
				for (int i = 0 ; i < r ; i++){
				if(i < r){
					i++;
				}
				else{
					return (i + 1);
				}

				}
			}
			return (r);
        }

        
    }
	
  	  


