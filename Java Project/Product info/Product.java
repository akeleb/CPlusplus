package product_info;




class Prod {
	private DateFormat Format= new SimpleDateFormat("dd-MM-yyyy");
	String prod_name;

	private double buying_price;

	private double selling_price;

	private ArrayList<String> s_no = new ArrayList<String>();

	private int quantity;

	private double tax;

	private double profit;

	private double revenue;
	private  Date Exp_date=null;

	public void setName(String name) {

		this.prod_name = name;

	}

	public void setSp(double sp) {

		this.selling_price = sp;

	}
	public  String getExp_date() {
		return Format.format(Exp_date);
	}

	public void setExp_date(Date exp_date) {
		Exp_date = exp_date;
	}

	public void setBp(double bp) {

		this.buying_price = bp;

	}

	public void setSn(int qnt) {

		Scanner input = new Scanner(System.in);

		for (int i = 0; i < qnt; i++) {

			System.out.println("ENTER THE NUMBER SERIAL NUMBER:");

			String check = input.nextLine();
			if(i!=0){

			if (search_s_no_linear(check,i) == -2) {

				System.err.println("THE SERIAL NUMBER IS TAKEN\n");

				setSn(qnt);

			}}

			this.s_no.add(check);

		}

	}

	int search_s_no_linear(String key,int i) {

		
		int index = i - 1;
		int found = 0;
		do {
			if (key.compareTo(this.s_no.get(index))==0)
				found = 1;
			else
				index--;

		} while (found == 0 && index >= 0);
		if (found == 1)
			index = -2;

		return index;

	}

	public void setRv(double rv) {

		this.setRevenue(rv);

	}

	public void setPr(double pr) {

		this.profit = pr;

	}

	public void setQt(int qt) {

		this.quantity = qt;

	}

	public String getName() {

		return prod_name;

	}

	public double getSp() {

		return selling_price;

	}

	public double getBp() {

		return buying_price;

	}

	public double getRv() {

		return getRevenue();

	}

	public double getPr() {

		return profit;

	}

	public String getSn(int i) {

		return this.s_no.get(i);

	}

	public int getQt() {

		return quantity;

	}

	public double calcrev() {

		return selling_price * quantity;

	}

	public double calcprof() {

		return buying_price - selling_price;

	}

	public void calcTax() {

		if (this.selling_price > 1 && this.selling_price < 300)

			this.tax = 0.15 * this.selling_price;

		else if (this.selling_price > 300 && this.selling_price < 10000)

			this.tax = 0.20 * this.selling_price;

		else

			this.tax = 0.25 * this.selling_price;

	}

	public double getTax() {

		return tax;

	}

	public void calcProfit() {

		this.profit = this.selling_price - this.buying_price;

	}

	public double getRevenue() {

		return revenue;

	}

	public void setRevenue(double revenue) {

		this.revenue = revenue;

	}

	class Extra extends Prod {

	}

}
