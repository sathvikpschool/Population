/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	 	Sathvik Prasanna
 *	@since		1/15/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName, stateName, cityType;
	private int population;
	
	/**
	 * constructor
	 * @param 	state 		state name
	 * @param 	city 		city name
	 * @param 	type 		type name
	 * @param 	pop 		population
	 */
	public City(String state, String city, String type, int pop) {
		cityName = city;
		stateName = state;
		cityType = type;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other) {
		if (other.population != population) 
			return population - other.population;
		if (!other.stateName.equals(stateName))
			return stateName.compareTo(other.stateName);
		return cityName.compareTo(other.cityName);

	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	//@Override
	boolean equals(City other) {
		return toString().equals(other.toString());
	}
	
	/**	Accessor methods */

	public String getCityName() {return cityName;}
	public String getStateName() {return stateName;}
	public String getCityType() {return cityType;}
	public int getPopulation() {return population;}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}
