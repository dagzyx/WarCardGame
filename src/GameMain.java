package testgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMain {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Scanner delayScan = new Scanner(System.in);
		
		Better better1 = new Better();
		Better better2 = new Better();
		
		System.out.println("Enter desired delay between rounds in seconds: ");
		int delay = delayScan.nextInt() * 1000;

		System.out.println();
		System.out.println("First better to 3 wins, Wins!");
		
		
		while(better1.getWins() < 3 || better2.getWins() < 3) {
			System.out.println();
			System.out.println("Better 1's wins: " + better1.getWins());
			System.out.println("Better 2's wins: " + better2.getWins());
			System.out.println();
			
			System.out.println("Better 1, place your bet (enter p1 or p2): ");
			better1.setChoice(scan.nextLine());
			
			if(!(better1.getChoice().equalsIgnoreCase("p1") || better1.getChoice().equalsIgnoreCase("p2"))) {
				System.out.println("Invalid input from Better 1.");
				System.exit(1);
			}
			
			System.out.println("Better 2, place your bet (enter p1 or p2): ");
			better2.setChoice(scan.nextLine());
			
			if(!(better2.getChoice().equalsIgnoreCase("p1") || better2.getChoice().equalsIgnoreCase("p2"))) {
				System.out.println("Invalid input from Better 2.");
				System.exit(1);
			}
			
			System.out.println();
			System.out.println("Starting a round of War...");
			System.out.println();
			pause(delay);
			
			Random rand = new Random();
			List<Card> cardDeck = new ArrayList<Card>();
			
			//populate deck
			for(int i = 0; i < 4; i++) {
				for(int j = 2; j < 15; j++) {
					cardDeck.add(new Card(j, i));
				}
			}
			
			//shuffle deck randomly
			Collections.shuffle(cardDeck, rand);
			
			LinkedList<Card> deck1 = new LinkedList<Card>();
			LinkedList<Card> deck2 = new LinkedList<Card>();
			
			deck1.addAll(cardDeck.subList(0, 26));
			deck2.addAll(cardDeck.subList(26, cardDeck.size()));
			
			while(true) {
				Card p1Card = deck1.pop();
				Card p2Card = deck2.pop();
				
				System.out.println("Player 1's card is: " + p1Card.toString());
				System.out.println("Player 2's card is: " + p2Card.toString());
				System.out.println();
				pause(delay);
			
				if(p1Card.getCard() > p2Card.getCard()) {
					System.out.println("Player 1 wins the round. Player 1 is collecting the cards...");
					System.out.println();
					pause(delay);
					deck1.addLast(p1Card);
					deck1.addLast(p2Card);
					Collections.shuffle(deck1, rand);
				}
				else if(p1Card.getCard() < p2Card.getCard()) {
					System.out.println("Player 2 wins the round. Player 2 is collecting the cards...");
					System.out.println();
					pause(delay);
					deck2.addLast(p1Card);
					deck2.addLast(p2Card);
					Collections.shuffle(deck2, rand);
				}
				else {
					System.out.println("War!");
					pause(delay);
					
					LinkedList<Card> warDeck1 = new LinkedList<Card>();
					LinkedList<Card> warDeck2 = new LinkedList<Card>();
				
					if((deck1.size() < 4) || (deck2.size() < 4)) {
						if(deck1.size() > deck2.size()) {
							System.out.println("Player 1 wins by forfeit. Player 2 ran out of cards.");
							pause(delay);
							
							if(better1.getChoice().equalsIgnoreCase("p1")) {
								System.out.println("Better 1 has won the bet.");
								better1.incrementWins();
							}
							if(better2.getChoice().equalsIgnoreCase("p1")) {
								System.out.println("Better 2 has won the bet.");
								better2.incrementWins();
							}
							break;
						}
						else if(deck1.size() < deck2.size()) {
							System.out.println("Player 2 wins by forfeit. Player 1 ran out of cards.");
							pause(delay);
							
							if(better1.getChoice().equalsIgnoreCase("p2")) {
								System.out.println("Better 1 has won the bet.");
								better1.incrementWins();
							}
							if(better2.getChoice().equalsIgnoreCase("p2")) {
								System.out.println("Better 2 has won the bet.");
								better2.incrementWins();
							}
							break;
						}
					}
					for(int i = 0; i < 4; i++) {
						warDeck1.addLast(deck1.pop());
						warDeck2.addLast(deck2.pop());
					}
					
					if((warDeck1.size() == 4) && (warDeck2.size() == 4)) {
						
						System.out.println("Player 1's war card is: " + warDeck1.get(3).toString());
						System.out.println("Player 2's war card is: " + warDeck2.get(3).toString());
						System.out.println();
						pause(delay);
					
						if(warDeck1.get(3).getCard() > warDeck2.get(3).getCard()){
							System.out.println("Player 1 wins the war. Player 1 has received:");
							
							for(int i = 0; i < warDeck2.size(); i++) {
								System.out.println(warDeck2.get(i).toString());
							
							}
							System.out.println();
							pause(delay);
							deck1.addAll(warDeck1);
							deck1.addAll(warDeck2);
							Collections.shuffle(deck1, rand);
						}
						else if(warDeck1.get(3).getCard() < warDeck2.get(3).getCard()) {
							System.out.println("Player 2 wins the war. Player 2 has received:");
						
							for(int i = 0; i < warDeck1.size(); i++) {
								System.out.println(warDeck1.get(i).toString());
							}
							System.out.println();
							pause(delay);
							deck2.addAll(warDeck1);
							deck2.addAll(warDeck2);
							Collections.shuffle(deck2, rand);
						}
						else {
							System.out.println("DOUBLE WAR!!");
							pause(delay);
						
							if((deck1.size() < 4) || (deck2.size() < 4)) {
								if(deck1.size() > deck2.size()) {
									System.out.println("Player 1 wins by forfeit. Player 2 ran out of cards.");
									pause(delay);
									
									if(better1.getChoice().equalsIgnoreCase("p1")) {
										System.out.println("Better 1 has won the bet.");
										better1.incrementWins();
									}
									if(better2.getChoice().equalsIgnoreCase("p1")) {
										System.out.println("Better 2 has won the bet.");
										better2.incrementWins();
									}
									break;
								}
								else if(deck1.size() < deck2.size()) {
									System.out.println("Player 2 wins by forfeit. Player 1 ran out of cards.");
									pause(delay);
									
									if(better1.getChoice().equalsIgnoreCase("p2")) {
										System.out.println("Better 1 has won the bet.");
										better1.incrementWins();
									}
									if(better2.getChoice().equalsIgnoreCase("p2")) {
										System.out.println("Better 2 has won the bet.");
										better2.incrementWins();
									}
									break;
								}
							}
							for(int i = 0; i < 4; i++) {
								warDeck1.addLast(deck1.pop());
								warDeck2.addLast(deck2.pop());
							}
							if((warDeck1.size() == 8) && (warDeck2.size() == 8)) {
							
								System.out.println("Player 1's double war card is: " + warDeck1.get(7).toString());
								System.out.println("Player 2's double war card is: " + warDeck2.get(7).toString());
								System.out.println();
								pause(delay);
							
								if(warDeck1.get(7).getCard() > warDeck2.get(7).getCard()) {
									System.out.println("Player 1 wins the double war. Player 1 has received:");
							
									for(int i = 0; i < warDeck2.size(); i++) {
										System.out.println(warDeck2.get(i).toString());
									}
									System.out.println();
									pause(delay);
									deck1.addAll(warDeck1);
									deck1.addAll(warDeck2);
									Collections.shuffle(deck1, rand);
								}
								else if(warDeck1.get(7).getCard() < warDeck2.get(7).getCard()) {
									System.out.println("Player 2 wins the double war. Player 2 has received:");
							
									for(int i = 0; i < warDeck1.size(); i++) {
										System.out.println(warDeck1.get(i).toString());
									}
									System.out.println();
									pause(delay);
									deck2.addAll(warDeck1);
									deck2.addAll(warDeck2);
									Collections.shuffle(deck2, rand);
								}
								else {
									System.out.println("TRIPLE WAR!!!");
									break; //cant be asked to code this lmao
								}
							}
						
						}
					}
				
				
				}
				if(deck1.size() == 0) {
					System.out.println("Player 2 wins!");
					
					if(better1.getChoice().equalsIgnoreCase("p2")) {
						System.out.println("Better 1 has won the bet.");
						better1.incrementWins();
					}
					if(better2.getChoice().equalsIgnoreCase("p2")) {
						System.out.println("Better 2 has won the bet.");
						better2.incrementWins();
					}
					break;
				}
				else if(deck2.size() == 0) {
					System.out.println("Player 1 wins!");
					
					if(better1.getChoice().equalsIgnoreCase("p1")) {
						System.out.println("Better 1 has won the bet.");
						better1.incrementWins();
					}
					if(better2.getChoice().equalsIgnoreCase("p1")) {
						System.out.println("Better 2 has won the bet.");
						better2.incrementWins();
					}
					break;
				}
			}
			if(better1.getWins() == 3) {
				displayFinalScore(better1, better2);
				System.out.println("Better 1 Wins!");
				break;
			}
			else if(better2.getWins() == 3) {
				displayFinalScore(better1, better2);
				System.out.println("Better 2 Wins!");
				break;
			}
		}
		scan.close();
		delayScan.close();
		
	}
	
	public static void pause(int ms) {
	    try {
	        Thread.sleep(ms);
	    } catch (InterruptedException e) {
	        System.err.format("IOException: %s%n", e);
	    }
	}
	
	public static void displayFinalScore(Better better1, Better better2) {
		System.out.println();
		System.out.println("-------------Final score-------------");
		System.out.println("Better 1's Wins: " + better1.getWins());
		System.out.println("Better 2's Wins: " + better2.getWins());
		System.out.println("-------------------------------------");
		System.out.println();
	}
}
