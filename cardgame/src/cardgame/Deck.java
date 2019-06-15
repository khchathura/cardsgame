package cardgame;
import java.util.Random;
public class Deck 
{
	private Card[] cards;	
	public Deck()
	{
		cards=new Card[52];
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<13;j++)
			{
				cards[j+13*i]=new Card(i,j);
			}
		}
	}
	public void show_Deck()
	{
		for(int i = 0;i<cards.length;i++)
			System.out.println(cards[i].show());
	}
	public void shuffle()
	{
		Random rnd=new Random();
		for(int i=0;i<52;i++)
		{
			int j=rnd.nextInt(52);
			Card temp=cards[i];
			cards[i]=cards[j];
			cards[j]=temp;
			
		}
	}
	public void distribute(Player[] players)
	{	
		for(int i=0;i<players.length;i++)
		{
			Card[] temp=new Card[13];
			for(int j=0;j<13;j++)
			{
				temp[j]=cards[j+13*i];
			}
			players[i].get(temp);
		}
	}	
}