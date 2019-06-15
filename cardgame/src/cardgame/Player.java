package cardgame;
public class Player 
{
	private Card[] cards;
	public int numberOfCards;
	private boolean IsHuman;
	private Card playedCard;
	private int turn;
	
	public Player()
	{
		IsHuman=false;
	}
	public void get(Card[] cards)
	{
		this.cards = cards;
		for(int i=0;i<cards.length;i++)
		{
			for(int j=0;j<cards.length-1-i;j++)
			{
				if(gethighestcard(cards[j],cards[j+1]))
				{
					Card temp=cards[j];
					cards[j]=cards[j+1];
					cards[j+1]=temp;
				}
			}
		}
		numberOfCards= cards.length;
	}
	public Card gethighestcard(Card[] roundcards)
	{
		Card temp = roundcards[0];
		for(int i = 0;i < roundcards.length-1;i++)
		{
			if(roundcards[i+1].getRank() > temp.getRank())
			{
				temp = roundcards[i+1];
			}
			if(roundcards[i+1].getRank() == temp.getRank())
			{
				if(roundcards[i+1].getSuit() > temp.getRank())
				{
					temp = roundcards[i+1];
				}
			}
		}
		return temp;
	}
	public boolean gethighestcard(Card card1,Card card2)
	{
		if(card2.getRank() > card1.getRank())
		{
			return false;
		}
		if(card2.getRank() == card1.getRank())
		{
			if(card2.getSuit() > card1.getSuit())
			{
				return false;
			}
		}
		return true;
	}
	public void addcard(Card[] newcards)
	{
		Card[] temp = new Card[numberOfCards + newcards.length];
		for(int i = 0; i<cards.length;i++)
		{
			temp[i] = cards[i];
		}
		for(int i = 0; i<newcards.length;i++)
		{
			temp[numberOfCards+i] = newcards[i];
		}
		cards= temp;
		get(cards);
	}
	public void playcard(int turn,Card[] roundcards)
	{
		Card temp = gethighestcard(roundcards);
		if(turn == 0)
		{
			playedCard = cards[numberOfCards-1];   //highest card to play
			numberOfCards--;
		}
		else
		{
			if(gethighestcard(cards[numberOfCards-1],temp))
			{
				playedCard = cards[numberOfCards-1];
				numberOfCards--;
			}
			else
			{
				playedCard = cards[0];
				for(int i = 0; i<cards.length-1;i++)
				{
					cards[i]= cards[i+1];
				}
				numberOfCards--;
			}
		}
	}
	public boolean playcard(int index,int turn,Card[] roundcards)
	{
		boolean chk=false;
		if(index < numberOfCards)
		{
			playedCard = cards[index];
			for(int i = index; i<cards.length-1;i++)
			{
				cards[i]= cards[i+1];
			}
			numberOfCards--;
			chk = true;
		}
		return chk;
	}
	public boolean Issame(Card card1,Card card2)
	{
		if(card1.getRank()==card2.getRank())
			if(card1.getSuit()==card2.getSuit())
				return true;
		return false;
	}
	public void remove_duplication()
	{
		for(int i = 0;i<cards.length-1;i++)
		{
			if(Issame(cards[i],cards[i+1]))
			{
				remove(i);
			}
		}
	}
	private void remove(int i) 
	{
		for(int j = i;j<cards.length-1;j++)
			cards[i]=cards[i+1];
		get(cards);
	}
	public void print()
	{
		for(int i=0;i<numberOfCards;i++)
		{
			System.out.print(cards[i].show()+"  ");
		}
		
		System.out.println();
	}
	
	//getter-setter methods
	public void setHuman(){
		IsHuman=true;
	}
	public boolean IsHuman(){
		return IsHuman;
	}
	public Card playedCard(){
		return playedCard;
	}
}