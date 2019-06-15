package cardgame;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class Cardgame 
{
	public static int gethighestcardindex(Card[] roundcards)
	{
		int index = 0;
		Card temp = roundcards[0];
		for(int i = 0;i < roundcards.length-1;i++)
		{
			if(roundcards[i+1].getRank() > temp.getRank())
			{
				temp = roundcards[i+1];
				index = i+1;
			}
			if(roundcards[i+1].getRank() == temp.getRank())
			{
				if(roundcards[i+1].getSuit() > temp.getSuit())
				{
					temp = roundcards[i+1];
					index = i+1;
				}
			}
		}
		return index;
	}
	private static void createXML(int[] scores,String round)
	{
	    try
	    {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.newDocument();
	        
	        
	        
	        Element rootElement = doc.createElement("players");
	        doc.appendChild(rootElement);
	        for(int i=1; i<=4; i++)
	        {
	            Element player = doc.createElement("player");
	            Element playerscore = doc.createElement("score");
	            playerscore.setTextContent(String.valueOf(scores[i-1]));
	            player.setAttribute("round", String.valueOf(round));
	            player.appendChild(playerscore);
	            rootElement.appendChild(player);
	        }
	        
	        // Write the content into XML file
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("Card Game Score.xml"));
	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        // Beautify the format of the resulted XML
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.transform(source, result);
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	    }
	}
	public static void main(String[] args)
	{
		int round = 1; 
		int turn; 
		int index;
		Deck deck = new Deck();
		Card[] roundcards = new Card[4];
		Player[] players=new Player[4];
		int[] scores ={0,0,0,0};
		Scanner scan=new Scanner(System.in);
		for(short i=0;i<4;i++)
			players[i]=new Player();
		System.out.println("\t\t\tCardgame\n\t\tCards present in Deck");
		deck.show_Deck();
		System.out.println("\n\n\n");
		deck.shuffle();//shuffles the cards
		deck.distribute(players);
		
		System.out.println("In which mode do you want to play??\n1. As Human\n2. As computer");
		int mode=scan.nextInt();
		if(mode==1)
			players[0].setHuman();
		while(scores[0] < 52 && scores[1] < 52 && scores[2] < 52 && scores[3] < 52)
		{
			for(int i= 0;i<4;i++)
				roundcards[i] = new Card(1,1);
			System.out.println("\n\n\n");
			System.out.println("Round Number\t"+round);
			System.out.println("Please Select Card to play");
			players[0].print();
			scan=new Scanner(System.in);
			System.out.println("Please Enter index of card. It starting with 0");
			index = scan.nextInt();
			System.out.println("\n\n\n");
			turn = 0;
			if(players[0].numberOfCards !=0)
			{
				players[0].playcard(index,turn,roundcards);
				roundcards[0]=players[0].playedCard();
				System.out.println("Player 1 " + players[0].playedCard().show());
				turn++;
			}
			for(int i = 1; i<4;i++)
			{
				if(players[i].numberOfCards !=0)
				{
					players[i].playcard(turn,roundcards);
					roundcards[i]=players[i].playedCard();
					int j = i+1;
					System.out.println("Player"+j + "="+ players[i].playedCard().show());
					turn++;
				}
			}
			System.out.println("\n\n\n");
			int winner = gethighestcardindex(roundcards);
			players[winner].addcard(roundcards);
			for(int i = 0; i < 4;i++)
				scores[i]= players[i].numberOfCards;
			if(players[0].numberOfCards !=0)
				System.out.println("Player 1 cards"+scores[0]);
			if(players[1].numberOfCards !=0)
				System.out.println("Player 2 cards"+scores[1]);
			if(players[2].numberOfCards !=0)
				System.out.println("Player 3 cards"+scores[2]);
			if(players[3].numberOfCards !=0)
				System.out.println("Player 4 cards"+scores[3]);
			round++;
			System.out.println("\n\n\n");
			while(scores[0]+scores[1]+scores[2]+scores[3]>52)
			{
				for(int i = 0;i<4;i++)
				{
					players[i].remove_duplication();
					scores[i]= players[i].numberOfCards;
				}
			}
			if(players[0].numberOfCards ==0)
			{
				System.out.println("Player 1 Game Ended");
			}
			if(players[1].numberOfCards==0)
			{
				System.out.println("Player 2 Game Ended");
			}
			if(players[2].numberOfCards==0)
			{
				System.out.println("Player 3 Game Ended");
			}
			if(players[3].numberOfCards ==0)
			{
				System.out.println("Player 4 Game Ended");
			}
		}
		if(players[0].numberOfCards >51)
		{
			System.out.println("Game Ended\n Human Player wins\n All Computer Player loses");
		}
		else if(players[1].numberOfCards>51)
		{
			System.out.println("Game Ended\n Human Player Loses \n Player 2 Wins");
		}
		else if(players[2].numberOfCards>51)
		{
			System.out.println("Game Ended\n Human Player Loses \n Player 3 Wins");
		}
		else if(players[3].numberOfCards>51)
		{
			System.out.println("Game Ended\n Human Player Loses \n Player 4 Wins");
		}
		createXML(scores,String.valueOf(round));
		System.out.println("XML File Written");
	}
}
