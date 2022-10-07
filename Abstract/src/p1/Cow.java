package p1;

public class Cow extends Animal{
	@Override
	public int doEat(int foodQuantity) {
		int currentCowpower = super.getPower();

		currentCowpower += (foodQuantity);

		super.setPower(currentCowpower);

		return currentCowpower;
	}

	public void givesMilk()
	{
		int currentCowPower = super.getPower();
		super.printAnimalDetails();

		if(currentCowPower>10)
		{
			System.out.println(" Cat is doing hunting of the Rat ");
			currentCowPower -= 9;
			super.setPower(currentCowPower);
		}
		else
		{
			System.out.println("Cow "+super.getName()+" power is low ("+super.getPower()+") , feed the cow to gain power");

		}
	}

	public void setCowDetails(String name,int power)
	{
		super.setName(name);
		super.setPower(power);
	}


}


