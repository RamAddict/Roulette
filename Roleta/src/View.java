import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class View {
	public View() {

	}
	public int askGetInt(String question) {
		return Integer.parseInt(JOptionPane.showInputDialog(question));

	}
	public String askGetString(String question) {
		return JOptionPane.showInputDialog(question);
	}
	public void printJPane(String string) {
		JOptionPane.showMessageDialog(null,string);
	}

	public int showMenu(int bank) {

		return Integer.parseInt(JOptionPane.showInputDialog("Bem vindo à Roleta Européia! a mesa tem: "+bank+" fichas\n"
				+ "Digite 1-Para cadastrar novo jogador\n"
				+ "Digite 2-Para fazer uma aposta\n"
				+ "Digite 3-Para encerrar a rodada e girar a roleta\n"
				+ "Digite 4-para consultar Id de alguém por nome:\n"
				+ "Digite 5-Para encerrar o programa\n"));
	}
	public int askBetKind(int chips) {
		return Integer.parseInt(JOptionPane.showInputDialog("Entre o número correspondente à aposta:\n"
				+ "Você tem "+chips+" fichas\n"
				+ "0 a 36=números na roleta\n"
				+ "37-números pares\n"
				+ "38-números ímpares\n"
				+ "39-números altos (19-36)\n"
				+ "40-números baixos (1-18)\n"
				+ "41-vermelhos\n"
				+ "42-pretos\n"
				+ "43-1ªdúzia\n"
				+ "44-2ªdúzia\n"
				+ "45-3ªdúzia\n"
				+ "46-1ªcoluna\n"
				+ "47-2ªcoluna\n"
				+ "48-3ªcoluna"));
	}
	public String decideBetKind(int betCode) {
		if(betCode<37) return "número: "+betCode;
		else {
			switch (betCode) {
			case 37: return "números pares!";
			case 38: return "números ímpares ";
			case 39: return "números altos (19-36)";
			case 40: return "números baixos(1-18)";
			case 41: return "vermelhos";
			case 42: return "pretos";
			case 43: return "1ªdúzia";
			case 44: return "2ªdúzia";
			case 45: return "3ªdúzia";
			case 46: return "1ªcoluna";
			case 47: return "2ªcoluna";
			case 48: return "3ªcoluna";
			}
		}
		return "";
	}
	public void showSpinResult(int result) {
		ImageIcon rouleteGif = new ImageIcon(("Roleta.gif"));

		JOptionPane.showMessageDialog(null,"Saiu "+result+"! ","Roleta",JOptionPane.QUESTION_MESSAGE,rouleteGif);
	}

}