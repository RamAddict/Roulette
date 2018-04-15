
public class Player {
	private int chips;
	private String name;
	private int id;
	private int[] bets;
	private int turnsWithoutBetting;
	private boolean didBet;
	//0-36 valores únicos: ganha 35:1 36xaposta
	//37-par 38-impar 39-alto 40-baixo 41-vermelho 42-preto 1:1 2xaposta
	//43-1ªdezena 44-2ªdezena 45-3ªdezena 2:1 3xaposta
	//46-1ªcoluna 47-2ªcoluna 48-3ªcoluna 2:1 3xaposta
	public Player() {
		name="nameless";
		this.id= 0;
		bets= new int[49];
		chips= 100;
		turnsWithoutBetting= 5;
		didBet=false;
	}
	public void killPlayer() {
		id= 0;
		chips= -1;
		turnsWithoutBetting= 5;
	}

	public Player(String name,int id) {
		this.name= name;
		this.chips= 100;
		this.id= id;
		bets= new int[49];
		turnsWithoutBetting=0;
	}
	public int getTurnsWithoutBetting() {
		return turnsWithoutBetting;
	}
	public void setTurnsWithoutBetting(int turns) {
		turnsWithoutBetting=turns;
	}
	public void incrementTurnsWithoutBetting() {
		turnsWithoutBetting++;
	}

	public void RemoveChips(int bet) {
		chips= chips-bet;
		if(bet!=0) {
			didBet=true;
			setTurnsWithoutBetting(0);
		}
	}
	public boolean isBet() {
		return didBet;
	}
	public void setDidBet(boolean didBet){
		this.didBet=didBet;
	}

	public int getChips() {
		return chips;
	}
	public void setChips(int chips) {
		this.chips= chips;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBets(int arrayPosition) {
		return bets[arrayPosition];
	}
	public void setBets(int arrayPosition,int valor) {
		bets[arrayPosition]=bets[arrayPosition]+valor;
	}
	public int decideWins(int result,String colour) {
		for(int i=0;i<37;i++) {
			if(i!=result) bets[i]=0;
		}
		bets[result]=36*bets[result];

		if(result%2 == 0) {bets[37] = bets[37]*2; bets[38]=0;}else {bets[38] = bets[38]*2; bets[37]=0;}

		if(result >= 19) {bets[39] = bets[39]*2; bets[40]=0;}else
			if (result <= 18 && result !=0) {bets[40] = bets[40]*2; bets[39]=0;}

		if(colour.equals("Vermelho")) {bets[41] = bets[41]*2; bets[42]=0;}else 
			if(colour.equals("Preto")) {bets[42] = bets[42]*2; bets[41]=0;}
		//37-par 38-impar 39-alto 40-baixo 41-vermelho 42-preto 1:1 2xaposta
		if(result >=1 && result <= 12) {bets[43] = bets[43]*3; bets[44]=0; bets[45]=0;}else
			if(result >= 13 && result<= 24) {bets[44]=bets[44]*3; bets[43]=0; bets[45]=0;}else
				if(result!=0) {bets[45]=bets[45]*3; bets[44]=0; bets[43]=0;}else {bets[43]=0; bets[44]=0; bets[45]=0;}

		if(result%3==0) {bets[48] = bets[48]*3; bets[47]=0; bets[46]=0;}else
			if(result%3==1) {bets[47] = bets[47]*3; bets[48]=0; bets[46]=0;}else
				if(result%3==2) {bets[46] = bets[46]*3; bets[47]=0; bets[48]=0;}

		if(result==0) {
			for(int i=1;i<49;i++) {//Zerando tudo no caso de zero...
				bets[i]=0;
			}
		}
		int sum=0;
		for (int i=0;i<49;i++) {//somando os ganhos
			sum = sum+bets[i];
		}
		return sum;
	}
}
