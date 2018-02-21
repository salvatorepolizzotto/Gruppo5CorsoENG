package it.eng.projectwork.gruppo05.model;

import java.util.Collection;
import java.util.Comparator;

public enum PRICING{
	RIALZO{
		@Override
		//restituisce il massimo importo
		public Bid getBestOffer(Auction auction) {
			Comparator<Bid> comparator = Comparator.comparing(Bid::getPrice);
			return auction.getAuctionBids().stream().max(comparator).get();
		}
		
		@Override
		//Per vedere se il prezzo di un Bid supera l'offerta maggiore.
		public boolean canAddBid(Bid b, Bid newBid) {
			return newBid.getPrice().compareTo(b.getPrice())>0;
		}
	
	};
	
	public abstract boolean canAddBid(Bid b, Bid newBid);
	
	public abstract Bid getBestOffer(Auction auction);
}