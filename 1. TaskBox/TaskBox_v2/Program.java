public class Program {
	public static void main(String[] args) {
		final Table table = new Table(4, 4);
		final CostCalculator costCalculator = new CostCalculator(20, 500);
		double cost = costCalculator.calcCost(table, 30);
		System.out.println(cost);
	}
}