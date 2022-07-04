class Get{
	private static double getMain(String type){
		double value;
		for(boolean i = false;;i = true){
			if(i) System.out.print("Retry: ");
			else System.out.print("(" + type + "): ");
			
			try{
				value = Double.parseDouble(input.nextLine());
				return value;
			}catch(Exception e){
				System.out.print("(Please '" + type + "' only)");
			}
		}
	}

	public static int getInt(){
		return (int)getMain("Int");
	}
	
	public static float getFloat(){
		return (float)getMain("Float");
	}

	public static long getLong(){
		return (long)getMain("Long");
	}

	public static double getDouble(){
		return getMain("Double");
	}
	
	public static String getString(){
		return input.nextLine();
	}
	/*
	public String getName(){}
	public String getPassword(){}
	*/
}