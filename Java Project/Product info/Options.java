package product_info;


public class Options {
	Wel welobj2 = new Wel();
	 Welcome Main_obj= new Welcome();
	
	
	Options() {
		welobj2.menu();
		setActions();
	}
	
	Actions Act = new Actions();

	public void setActions() {
		switch (welobj2.getChoice().charAt(0)) {
		case '1': {
			Act.add_new(Main_obj.prodobj);
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			welobj2.menu();
			setActions();
			break;
		}
		case '2': {
			Act.display(Main_obj.prodobj);
			welobj2.menu();
			setActions();
			break;
		}
		case '3': {
			Act.view_single_prod_data(Main_obj.prodobj);
			welobj2.menu();
			setActions();
			break;
		}
		case '4': {
			Act.sort_by_name(Main_obj.prodobj);
			welobj2.menu();
			setActions();
			break;
		}
		case '5': {
			//Act.delete_product();

			welobj2.menu();
			setActions();
			break;
		}
		case '6': {
			Act.about_us();
			welobj2.menu();
			setActions();
			break;
		}
		case '7': {
			System.exit(0);
			
		}
		}
	}
}
