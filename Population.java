import java.util.List;
/**
 * We have a list of	over	30,000	USA	cities	and	their	populations.	It	would	be	helpful	if	the	data	could	be	sorted	in	different	
and	helpful	ways	to	answer	important	questions.	Some	sample	queries:	"What	are	the	most	populous	cities	in	USA"	or	
"What	are	the	most	populous	cities	in	a	chosen	state?"	or	"What	states	in	the	USA	have	a	city	named	Springfield?"
In	this	project,	you	will	create	a	database	from	the	list	of	USA	cities	and	sort	the	data.	You	will	sort	the	data	for	
population	and	city	name	using	Selection	Sort,	Insertion	Sort,	and	Merge	Sort.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	 Sathvik Prasanna
 *	@since	1/15/23
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	/**
	 * constructor
	 */
	public Population() {
		cities = new java.util.ArrayList<City>();
	}
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

	/**
	 * reads cities from file into array
	 */
	public void loadCities() {
		java.util.Scanner r = FileUtils.openToRead(DATA_FILE);
		r.useDelimiter("\\t|\\n");
		//System.out.println(r.next());
		while (r.hasNext()) {
			cities.add(new City(r.next(), r.next(), r.next(), r.nextInt()));
		}
	}

	/**
	 * sorts cities ascending by population using selection sort
	 */
	public void ascendingPop() {
		int n = cities.size();
		for (int i = n-1; i >= 0; --i) {
			int mx = i;
			for (int j = 0; j < i; ++j) {
				if (cities.get(mx).compareTo(cities.get(j)) < 0) {
					mx = j;
				}
			}
			City tmp = cities.get(i);
			cities.set(i, cities.get(mx));
			cities.set(mx, tmp);
		}
	}

	/**
	 * sorts cities ascending by name using insertion sort
	 */
	public void ascendingName() {
		int n = cities.size();
		for (int i = 0; i < n; ++i) {
			int j = i;
			while (j >= 1 && cities.get(j).getCityName().compareTo(cities.get(j-1).getCityName()) < 0) {
				City tmp = cities.get(j);
				cities.set(j, cities.get(j-1));
				cities.set(j-1, tmp);
				--j;
			}
		}
	}


	/**
	 * sorts cities descending by population using mergesort
	 * @param c 		the array of cities
	 */
	public void descendingPop(List<City> c) {
		if (c.size() == 1) return;
		int mid = c.size()/2;
		List<City> l = new java.util.ArrayList<City>(), r = new java.util.ArrayList<City>();
		for (int i = 0; i < mid; ++i) l.add(c.get(i));
		for (int i = mid; i < c.size(); ++i) r.add(c.get(i));
		descendingPop(l); descendingPop(r);

		int i = 0, j = 0;
		int k = 0;
		while (i < l.size() || j < r.size()) {
			if (i == l.size()) {
				c.set(k, r.get(j)); j++;
			} else if (j == r.size()) {
				c.set(k, l.get(i)); i++;
			} else if (l.get(i).compareTo(r.get(j)) > 0) {
				c.set(k, l.get(i)); i++;
			} else {
				c.set(k, r.get(j)); j++;
			}
			k++;
		}
	}



	/**
	 * sorts cities descending by name using mergesort
	 * @param 	c 		the array of cities
	 */
	public void descendingName(List<City> c) {
		if (c.size() == 1) return;
		int mid = c.size()/2;
		List<City> l = new java.util.ArrayList<City>(), r = new java.util.ArrayList<City>();
		for (int i = 0; i < mid; ++i) l.add(c.get(i));
		for (int i = mid; i < c.size(); ++i) r.add(c.get(i));
		descendingName(l); descendingName(r);

		int i = 0, j = 0;
		int k = 0;
		while (i < l.size() || j < r.size()) {
			if (i == l.size()) {
				c.set(k, r.get(j)); j++;
			} else if (j == r.size()) {
				c.set(k, l.get(i)); i++;
			} else if (l.get(i).getCityName().compareTo(r.get(j).getCityName()) > 0) {
				c.set(k, l.get(i)); i++;
			} else {
				c.set(k, r.get(j)); j++;
			}
			k++;
		}
	}


	/**
	 * prints a given number of cities
	 * @param 	amt 	the number of cities to print
	 */
	public void printCities(int amt) {
		for (int i = 0; i < amt; ++i) 
			System.out.printf("%4d: %s\n", i+1, cities.get(i).toString());
	}



	/**
	 * run
	 * runs game until quit and uses menus
	 */
	public void run() {
		printIntroduction();
		loadCities();
		printMenu();
		boolean cont = true;
		while (cont) {
			int op = Prompt.getInt("Enter selection", 1, 9);
			long start = System.currentTimeMillis();
			if (op == 1) {
				System.out.println("\nFifty least populous cities");
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				ascendingPop(); 
				printCities(50);
			} else if (op == 2) {
				System.out.println("\nFifty most populous cities");
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				descendingPop(cities); 
				printCities(50);
			} else if (op == 3) {
				System.out.println("\nFifty cities sorted by name");
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				ascendingName(); 
				printCities(50);
			} else if (op == 4) {
				System.out.println("\nFifty cities sorted by name descending");
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				descendingName(cities);
				printCities(50);
			} else if (op == 5) {
				descendingPop(cities);
				boolean valid = false;
				String res = "";
				String state = "aaaa";
				while (!valid) {
					state = Prompt.getString("Enter state name (ie. Alabama)");
					int cnt = 0;
					for (City c : cities) {
						if (c.getStateName().equals(state)) {
							if (cnt < 50) //res += c.toString() + "\n";
								res += String.format("%4d: %s\n", cnt+1, c.toString());
							cnt++;
						}
					}
					if (res.length() > 0) valid = true;
					else System.out.println("Error: " + state + " is not valid");
				}
				System.out.println("\nFifty most populous cities in " + state);
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				System.out.println(res);
			} else if (op == 6) {
				descendingPop(cities);
				boolean valid = false;
				String res = "";
				String city = "aaa";
				int cnt = 0;
				while (!valid) {
					city = Prompt.getString("Enter city name");
					for (City c : cities) {
						if (c.getCityName().equals(city)) {
							//res += c.toString() + "\n";
							res += String.format("%4d: %s\n", cnt+1, c.toString());
							cnt++;
						}
					}
					if (res.length() > 0) valid = true;
					else System.out.println("Error: " + city + " is not valid");
				}
				System.out.println("\nCity " + city + " by population");
				System.out.printf("      %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
				System.out.println(res);

			} else if (op == 9) {
				cont = false;
			}
			long end = System.currentTimeMillis();
			if (op <= 4) System.out.println("\nElapsed Time " + (end-start) + " milliseconds\n\n");

		}
		System.out.println("Thanks for playing Population!");
	}


	
	/**
	 * main
	 * runs population game
	 * @param 	args 	command line arguments
	 *
	 */
	public static void main(String[] args) {
		(new Population()).run();
	}
	
}
