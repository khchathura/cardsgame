package cardgame;
public class Card 
{
	private int rank, suit;
	private String[] suits={"club","diamond","heart","spade"};
	private String[] ranks={"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K","A" };
	
	public Card(int _suit,int _rank)
	{
		this.rank=_rank;
		this.suit=_suit;
	}
	public String show()
	{
		return ranks[rank]+" of "+suits[suit];
	}
	public int getRank()
	{
		return rank;
	}
	public int getSuit()
	{
		return suit;
	}
}