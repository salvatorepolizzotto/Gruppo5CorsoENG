package it.eng.projectwork.gruppo05.model;

public enum STATE {
	PENDING {
		@Override
		public boolean canAddBid() {
			return false;
		}
		
		
		@Override
		public boolean amI(Auction auction) {
			return auction.getAuctionBeginning().getTime()>System.currentTimeMillis(); 
		}
	},
	SUSPEND {
		@Override
		public boolean canAddBid() {
			return false;
		}
		
		@Override
		public boolean amI(Auction auction) {
			return auction.isSuspended(); 
		}
	},
	STARTED {
		@Override
		public boolean canAddBid() {
			return true;
		}
		
		@Override
		public boolean amI(Auction auction) {
			long currentTimeMillis = System.currentTimeMillis();
			return auction.getAuctionBeginning().getTime()<=currentTimeMillis && currentTimeMillis<auction.getAuctionEnding().getTime() && !SUSPEND.amI(auction);
		}
		
		
	},CLOSED {
		@Override
		public boolean canAddBid() {
			return true;
		}
		@Override
		public boolean amI(Auction auction) {
			return System.currentTimeMillis()>auction.getAuctionEnding().getTime() && !SUSPEND.amI(auction);
		}
	};
	

	public abstract boolean canAddBid();
	

	public abstract boolean amI(Auction auction);
	
	
	public static STATE eval(Auction auction){
		for(STATE s : STATE.values()){
			if(s.amI(auction)){
				return s;
			}
		}
		throw new RuntimeException("Status Auction ["+auction+"] is not valid");
	}

}
