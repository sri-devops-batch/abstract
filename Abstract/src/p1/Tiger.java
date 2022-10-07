package p1;

public class Tiger extends Animal{
	@Override
	public int doEat(int foodQuantity) {
		int currentTigerPower = super.getPower();

		currentTigerPower += (foodQuantity/2);

		super.setPower(currentTigerPower);

		return currentTigerPower;
	}


	public void setTigerDetails(String name,int power)
	{
		super.setName(name);
		super.setPower(power);
	}



	public void huntAnimals()
	{
		int currentTigerPower = super.getPower();
	super.printAnimalDetails();

	if(currentTigerPower>10)
	{
		System.out.println(" Tiger HuntsAnimals in Zoo");
		currentTigerPower -= 10;
		super.setPower(currentTigerPower);
	}
	else
	{
		System.out.println("Tiger "+super.getName()+" power is low ("+super.getPower()+") , take some rest to gain energy");

	}
}

}
