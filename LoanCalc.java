// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		int update = 0;
		// Gets the loan data
		// double loan = Double.parseDouble(args[0]);
		double loan = 100000;
		// double rate = Double.parseDouble(args[1]);
		double rate = 5;
		// int n = Integer.parseInt(args[2]);
		int n = 10;
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the ending balance of the loan, given a periodical payment
		double payment = 10000;
		double endBalance = endBalance(loan, rate, n, payment);
		System.out.println("If your periodical payment is " + payment + ", your ending balance is: " + (int) endBalance);
		
		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		// Converts the rate to a percentage
		rate = rate / 100.0;
		// Calculates P*(1+r)^n which is the first argument of the formula
		double p1 = Math.pow((1+rate), n);
		// Calculates the total Ending Balance
		double endBal = loan*p1 - ((payment * ((p1 - 1))/rate));

		return endBal;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
    	
		rate = rate / 100.0;  // Convert rate to decimal
        double low = 0.0;     // Lower bound of the payment (minimum possible payment)
        double high = loan;   // Upper bound of the payment (maximum possible payment)
        double payment = 0.0; // Start with an initial guess for the payment

        iterationCounter = 0;  // Reset iteration counter

        
        while ((high - low) > epsilon) {  // Continue until the range is small enough
            iterationCounter++;  // Increment the counter for each iteration
            payment = (low + high) / 2;  // Guess the middle value 
            double balance = endBalance(loan, rate, n, payment);  // Calculate the ending balance

            // Check if the balance is close enough to zero
            if (Math.abs(balance) < epsilon) {
                return payment; // Once found returns payment
            }

            // If balance is positive (still owe money), increase the lower bound
            if (balance > 0) {
                low = payment;
            }
            // If balance is negative (overpaid), reduce the upper bound
            else {
                high = payment;
            }
        }
			return payment; // I dont know hy i also need to return here but it it fixes the bug 
    }
		
    
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
		rate = rate / 100.0;  // Convert rate to decimal
		double payment = loan / n;  // Start with an estimate for the payment
		double increment = 10.0;  // Start with a reasonable increment (e.g., $10)
		iterationCounter = 0;  // Reset iteration counter
	
		double balance = endBalance(loan, rate, n, payment);  // Calculate the balance for the first guess
		
		while (Math.abs(balance) > epsilon) {
			if(iterationCounter > 10000){
				System.out.println("over 10,000 iterations, the process is to long");
				return 10000;
			}
			iterationCounter++;  // Count iterations
			balance = endBalance(loan, rate, n, payment);  // Recalculate the ending balance after each change
	
			// If the balance is close enough to zero, return the payment
			if (Math.abs(balance) < epsilon) {
				return payment;
			}
	
			// If the balance is positive (still owe money), increase the payment
			if (balance > 0) {
				payment += increment;
			}
			// If the balance is negative (overpaid), decrease the payment
			else {
				payment -= increment;
				int y = 0;
			}
	
			// If the balance is still far from zero, increase the increment
			if (Math.abs(balance) > epsilon * 10) {
				increment = increment * 2;  // Double the increment for faster adjustments
			}
			// Fine-tune the increment once the balance is closer to zero
			else if (Math.abs(balance) < epsilon * 10 && Math.abs(balance) > epsilon) {
				increment = increment / 2;  // Gradually reduce the increment for precision
			}
		}
		return payment;  // Return the payment that brings the balance close to zero
	}

    }
