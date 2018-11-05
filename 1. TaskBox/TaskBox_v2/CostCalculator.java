public class CostCalculator {
	private double kmCost;
	private double kgCost;

	public CostCalculator(final double kmCost, final double kgCost) {
		this.kmCost = kmCost;             
		this.kgCost = kgCost;
	}

	
	public double calcCost(final Box box, final int km) {
		return box.getWeight() * kgCost + km * kmCost;
	}
}