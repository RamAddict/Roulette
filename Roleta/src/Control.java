public class Control {
	public static void main(String[] args) {
		View ui = new View();//n jogadores, jogador sem dinheiro vai p posição 0, inacessível
		Player[] players = new Player[20]; //coloquei 20 como default, pode trocar pelo que quiser
		Roulette europeanRoulette = new Roulette();

		int menuChoice;
		int playerCounter=1;
		int bank=1000;


		for (int i=0;i<players.length;i++) {
			players[i]= new Player();
		}

		do{	try {
			menuChoice=ui.showMenu(bank);
		}//primeira vez q uso try catch, aprendi agr sozinho. Nao fiz no codigo todo por nao ser pedido
		catch(NumberFormatException e) { ui.printJPane("Digite um número");menuChoice=0;}
		switch(menuChoice){
		case 1:
			players[playerCounter]=new Player(ui.askGetString("Entre o nome do jogador a cadastrar: "),playerCounter);
			ui.printJPane(players[playerCounter].getName()
					+" cadastrado com sucesso!\n"
					+"Você tem 100 chips\n"
					+"Memorize:seu id: "+playerCounter+"\nboa sorte");
			playerCounter++; 
			break;

		case 2:
			boolean exists=false;
			int supposedID;
			do {
				supposedID=ui.askGetInt("Qual o ID de quem vai apostar?\n digite 0 para voltar ao menu principal");		
				for (int i=1;i<players.length;i++) {
					if(supposedID==players[i].getId()) {exists=true;break;}
				}
				if(supposedID!=0 && !exists) {ui.printJPane("Esse id pertence a ninguém");}
			}while(!exists && supposedID!=0);
			if(supposedID==0){break;}

			int betKind;
			do {
				betKind=ui.askBetKind(players[supposedID].getChips());
				if(betKind<0 || betKind>48) ui.printJPane("Favor escolher uma aposta que exista na mesa de roleta");
			}while(betKind<0 || betKind>48);

			int bet;
			do {
				bet=ui.askGetInt("Quanto você apostará?"+"Você tem "+players[supposedID].getChips()+" fichas");
				if(bet>players[supposedID].getChips()) {ui.printJPane("não tens esta quantia de fichas");}
			}while(bet<0 || bet>players[supposedID].getChips());
			players[supposedID].RemoveChips(bet);bank=bank+bet;	//remove chips from player and add to bank
			players[supposedID].setBets(betKind, bet); //set bet value in the array position
			ui.printJPane(  "Senhor(a) "+players[supposedID].getName()+" apostou: "
					+players[supposedID].getBets(betKind)+" fichas em "+ui.decideBetKind(betKind)+
					"\n Fichas restantes: "+players[supposedID].getChips());				
			break;

		case 3: boolean spin=true;
		for (int i=1;i<players.length;i++) {
			if(players[i].getTurnsWithoutBetting()==3) {ui.printJPane("Jogador: "+players[i].getName()+" está a 3 turnos sem apostar,"
					+ "faça uma aposta antes de girar a roleta");spin=false;break;}
		}if(spin==false) {break;}
		int result=europeanRoulette.spinRoulette();
		String colour=europeanRoulette.decideColour(result);
		ui.showSpinResult(result);

		int bankDeduction=0;
		for (int i=1;i<players.length;i++) {
			if(players[i].getId()!=0 && players[i].isBet()==false) {players[i].incrementTurnsWithoutBetting();}
			bankDeduction=players[i].decideWins(result, colour);
			players[i].setChips(players[i].getChips()+bankDeduction);
			bank=bank-bankDeduction;
			if(players[i].getChips()==0) {ui.printJPane("Jogador(a) "+players[i].getName()+" você foi"
					+ " eliminado por falta de dinheiro, seu id agora é zero. O id dos outros"
					+ " jogadores não é alterado");players[i].killPlayer();}
			players[i].setDidBet(false);
		}
		if(bank<=0) {ui.printJPane("Acabou o dinheiro do banco :(\n Saindo...");System.exit(0);}

		break;
		case 4:String compare=ui.askGetString("Entre o nome do individuo cujo id deseja saber");
		boolean exist=false;
		for (int i=1;i<players.length;i++) {
			if((players[i].getName().toLowerCase()).equals(compare.toLowerCase())) {exist=true;ui.printJPane("Um dos jogadores com este nome tem id: "+players[i].getId());}

		}if(exist==false) {
			ui.printJPane("Não existem jogadores com esse nome");};
			break;
		case 5:
			System.exit(0);
		}


		}while(true);
	}

}
