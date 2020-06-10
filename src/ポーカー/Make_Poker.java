package ポーカー;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Make_Poker {

	ArrayList<Make_Poker> deck = new ArrayList<Make_Poker>();
	ArrayList<Make_Poker> hand = new ArrayList<Make_Poker>();

	String suit;
	int num;

	public Make_Poker(String suit, int num){
		this.suit = suit;
		this.num = num;

	}
	public Make_Poker(){

	}
	//カードのスート決め
	public String Drowing_suit(int card_num){
		if(0 <= card_num && card_num <= 12){
			suit = "♠";
		}else if(13 <= card_num && card_num <= 25){
			suit = "♣";
		}else if(26 <= card_num && card_num <= 38){
			suit = "♥";
		}else if(39 <= card_num && card_num <= 51){
			suit = "♦";
		}
		return suit;
	}
	//カードの数字決め
	public int Drowing_num(int card_num2){

		if(card_num2 == 0 || card_num2 == 13 || card_num2 == 26 || card_num2 == 39){
			num = 1;
		}
		else if(card_num2 == 1 || card_num2 == 14 || card_num2 == 27 || card_num2 == 40){
			num = 2;
		}
		else if(card_num2 == 2 || card_num2 == 15 || card_num2 == 28 || card_num2 == 41){
			num = 3;
		}
		else if(card_num2 == 3 || card_num2 == 16 || card_num2 == 29 || card_num2 == 42){
			num = 4;
		}
		else if(card_num2 == 4 || card_num2 == 17 || card_num2 == 30 || card_num2 == 43){
			num = 5;
		}
		else if(card_num2 == 5 || card_num2 == 18 || card_num2 == 31 || card_num2 == 44){
			num = 6;
		}
		else if(card_num2 == 6 || card_num2 == 19 || card_num2 == 32 || card_num2 == 45){
			num = 7;
		}
		else if(card_num2 == 7 || card_num2 == 20 || card_num2 == 33 || card_num2 == 46){
			num = 8;
		}
		else if(card_num2 == 8 || card_num2 == 21 || card_num2 == 34 || card_num2 == 47){
			num = 9;
		}
		else if(card_num2 == 9 || card_num2 == 22 || card_num2 == 35 || card_num2 == 48){
			num = 10;
		}
		else if(card_num2 == 10 || card_num2 == 23 || card_num2 == 36 || card_num2 == 49){
			num = 11;
		}
		else if(card_num2 == 11 || card_num2 == 24 || card_num2 == 37 || card_num2 == 50){
			num = 12;
		}
		else if(card_num2 == 12 || card_num2 == 25 || card_num2 == 38 || card_num2 == 51){
			num = 13;
		}

		return num;

	}
	//カードセット
	public void CardSet(){
		for(int cnt = 0; cnt < 52; cnt++){

			suit = Drowing_suit(cnt);
			num = Drowing_num(cnt);
			deck.add(new Make_Poker(suit,num));

		}
	}
	//ドロー処理
	public void DrowingSet(){
		int rand = new java.util.Random().nextInt(deck.size());
		hand.add(deck.get(rand));
		deck.remove(rand);


	}
	//手札セット
	public void Drowing(){
		for(int player = 1; player <= 2; player++){
			if(player == 1){
				System.out.println("-------------------ディーラーの手札セット-----------------------");
				for(int how_many = 0; how_many<5; how_many++){
					DrowingSet();
				}

			}else{
				System.out.println("   -------------------自分の手札セット-----------------------");
				for(int how_many = 0; how_many<5; how_many++){
					DrowingSet();
				}
			}
		}
	}
	//手札表示
	public void showCard(){
		for(int player = 1; player <= 2; player++){
			if(player == 1){
				System.out.println("ディーラーの手札:");
				for(int cards = 0; cards < 5; cards++){
					hand.get(cards).showInfomation();

				}

			}else{
				System.out.println();
				System.out.println("自分の手札:");
				for(int cards = 5; cards < 10; cards++){
					hand.get(cards).showInfomation();

				}
			}
		}
		System.out.println();
	}
	//表示処理
	public void showInfomation(){
		if(num == 1){
			System.out.print(suit + "A　");

		}
		else if(num == 11){
			System.out.print(suit + "J　");

		}
		else if(num == 12){
			System.out.print(suit + "Q　");

		}
		else if(num == 13){
			System.out.print(suit + "K　");

		}
		else if(num == 14){
			System.out.print(suit + "A　");

		}
		else{
			System.out.print(suit + num + "　");

		}

	}
	//カードを返す
	public void Return(){
		System.out.println();
		//yes no の格納
		int[] array = new int[5];
		int YesNo;
		for(int cnt = 0; cnt < 5; cnt++){
			System.out.print((cnt+1) + "枚目を交換しますか？　");
			System.out.print("1:のこす　2:かえる");
			System.out.print("　　==>");
			YesNo = new Scanner(System.in).nextInt();

			array[cnt] = YesNo;
		}

		//カードの交換
		for(int cnt = 0; cnt < 5; cnt++){
			if(array[cnt] == 2){
				int rand = new java.util.Random().nextInt(deck.size());
				hand.set(cnt+5, deck.get(rand));
				deck.remove(rand);
			}else{

			}
		}
		System.out.println();
	}


	//自分の役決め
	public int RoleJudge(int win){
		int[] suitList = new int[5];//スート番号を入れておくList
		int[] numList = new int[5];//数字を入れ
		int Jnum = 0;

		for(int cnt = 0; cnt < 5; cnt++){
			suitList[cnt] = hand.get(cnt+5).cardJudgeSuit();//スート番号の格納
			numList[cnt] = hand.get(cnt+5).cardJugdeNum();//数字の格納
		}
		//昇順に並び替え
		Arrays.sort(numList);


		for(int cnt = 0; cnt < 3; cnt++){

			if(numList[cnt] == numList[cnt +1]){
				Jnum = 1;//ワンペア
				if(cnt == 0 && numList[2] == numList[3]||
						numList[3] == numList[4]){
					Jnum = 2; //ツーペア
				}
				if(cnt == 1 && numList[3] == numList[4]){
					Jnum = 2; //ツーペア
				}
			}
		}

		int cnts = 0;

		if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[2] &&
				suitList[2] == suitList[3] &&
				suitList[3] == suitList[4]){

			Jnum = 5;//フラッシュ

		}else if(numList[cnts] +4 == numList[cnts +1] +3 &&
				numList[cnts +2] +2 == numList[cnts +3] +1 &&
				numList[cnts +3] +1 == numList[cnts]){
			Jnum = 4;//ストレート
			if(Jnum == 5){
				Jnum = 8;//ストレートフラッシュ
			}else if(numList[cnts] == 10){
				Jnum = 9;//ロイヤルフラッシュ
			}
		}


		if(numList[cnts] == numList[cnts+2] ||
				numList[cnts+1] == numList[cnts+3] ||
				numList[cnts+2] == numList[cnts+4]){
			Jnum = 3;//スリーオブアカインド
			if(numList[cnts] == numList[cnts+1] && numList[cnts+1] == numList[cnts+2] ||
					numList[cnts+2] == numList[cnts+3] && numList[cnts+3] == numList[cnts+4]){
				Jnum = 6;//フルハウス
			}else if(numList[cnts] == numList[cnts+3] ||
					numList[cnts+1] == numList[cnts+4]){
				Jnum = 7; //フォーオブアカインド
			}
		}
		if(win == 0){
			cardJnum(Jnum);
		}
		return Jnum;
	}


	//ディーラーの役決め
	//役決め
	public int DRoleJudge(int win){
		int[] suitList = new int[5];//スート番号を入れておくList
		int[] numList = new int[5];//数字を入れておくList
		int Jnum = 0;//役の変数


		for(int cnt = 0; cnt < 5; cnt++){
			suitList[cnt] = hand.get(cnt).cardJudgeSuit();//スート番号の格納
			numList[cnt] = hand.get(cnt).cardJugdeNum();//数字の格納
		}
		//昇順に並び替え
		Arrays.sort(numList);

		for(int cnt = 0; cnt < 3; cnt++){

			if(numList[cnt] == numList[cnt +1]){
				Jnum = 1;//ワンペア
				if(cnt == 0 && numList[cnt+1] == numList[cnt +2]||
						numList[3] == numList[4]){
					Jnum = 2; //ツーペア
				}
				if(cnt == 1 && numList[3] == numList[4]){
					Jnum = 2; //ツーペア
				}
			}
		}

		int cnts = 0;

		if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[2] &&
				suitList[2] == suitList[3] &&
				suitList[3] == suitList[4]){

			Jnum = 5;//フラッシュ

		}else if(numList[cnts] +4 == numList[cnts +1] +3 &&
				numList[cnts +2] +2 == numList[cnts +3] +1 &&
				numList[cnts +3] +1 == numList[cnts]){
			Jnum = 4;//ストレート
			if(Jnum == 5){
				Jnum = 8;//ストレートフラッシュ
			}else if(numList[cnts] == 10){
				Jnum = 9;//ロイヤルフラッシュ
			}
		}


		if(numList[cnts] == numList[cnts+2] ||
				numList[cnts+1] == numList[cnts+3] ||
				numList[cnts+2] == numList[cnts+4]){
			Jnum = 3;//スリーオブアカインド
			if(numList[cnts] == numList[cnts+1] && numList[cnts+1] == numList[cnts+2] ||
					numList[cnts+2] == numList[cnts+3] && numList[cnts+3] == numList[cnts+4]){
				Jnum = 6;//フルハウス
			}else if(numList[cnts] == numList[cnts+3] ||
					numList[cnts+1] == numList[cnts+4]){
				Jnum = 7; //フォーオブアカインド
			}
		}
		if(win == 0){
			cardJnum(Jnum);
		}
		return Jnum;
	}

	//役の表示
	public void cardJnum(int Jnum){
		if(Jnum == 0){
			System.out.println("ハイカード");
		}else if(Jnum == 1){
			System.out.println("ワンペア");
		}else if(Jnum == 2){
			System.out.println("ツーペア");
		}else if(Jnum == 3){
			System.out.println("スリーオブアカインド");
		}else if(Jnum == 4){
			System.out.println("ストレート");
		}else if(Jnum == 5){
			System.out.println("フラッシュ");
		}else if(Jnum == 6){
			System.out.println("フルハウス");
		}else if(Jnum == 7){
			System.out.println("フォーオブアカインド");
		}else if(Jnum == 8){
			System.out.println("ストレートフラッシュ");
		}else if(Jnum == 9){
			System.out.println("ロイヤルフラッシュ");
		}
	}
	//数字を返す
	public int cardJugdeNum() {
		if(num == 1){
			num = 14;
		}
		return num;

	}

	//スートの数字化
	public int cardJudgeSuit() {
		int Jnum = 0;//?
		if(suit == "♠"){
			Jnum = 0;
		}else if(suit == "♥"){
			Jnum = 1;
		}else if(suit == "♦"){
			Jnum = 2;
		}else if(suit == "♣"){
			Jnum = 3;
		}
		return Jnum;
	}
	//何枚目かの表示
	public void showInf(){
		System.out.println("[1] [2] [3] [4] [5]");

	}

	//ディーラーの交換処理
	public void DreturnJugde(){
		ArrayList<Integer> change = new ArrayList<Integer>();//交換するカードList
		int[] suitList = new int[5];//スート番号を入れておくList
		int[] numList = new int[5];//数字を入れておくList
		String Role = "ハイカード";//役の変数

		for(int cnt = 0; cnt < 5; cnt++){
			suitList[cnt] = hand.get(cnt).cardJudgeSuit();//スート番号の格納
			numList[cnt] = hand.get(cnt).cardJugdeNum();//数字の格納
		}

		//フラッシュ
		if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[2] &&
				suitList[2] == suitList[3] &&
				suitList[3] == suitList[4]){

			Role = "フラッシュ";
			System.out.println(Role);

		}
		//ストレート
		if(numList[0]+4 == numList[1]+3 &&
				numList[1]+3 == numList[2]+2 &&
				numList[2]+2 == numList[3]+1 &&
				numList[3]+1 == numList[4]){

			if(Role == "フラッシュ"){
				Role = "ストレートフラッシュ!!";
			}

			if(numList[4] == 14){
				Role = "ロイヤルフラッシュ!!";
			}

			Role = "ストレート";

		}
		//フラッシュ狙い
		else if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[2] &&
				suitList[2] == suitList[3]){
			change.add(4);
		}else if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[2] &&
				suitList[2] == suitList[4]){
			change.add(3);
		}else if(suitList[0] == suitList[1] &&
				suitList[1] == suitList[3] &&
				suitList[3] == suitList[4]){
			change.add(2);
		}else if(suitList[0] == suitList[2] &&
				suitList[2] == suitList[3] &&
				suitList[3] == suitList[4]){
			change.add(1);
		}else if(suitList[1] == suitList[2] &&
				suitList[2] == suitList[3] &&
				suitList[3] == suitList[4]){
			change.add(0);
		}
		//ストレート狙い
		else if(numList[0] == numList[1]+1 &&
				numList[1]+1 == numList[2]+2 &&
				numList[2]+2 == numList[3]+3 &&
				numList[3]+3 != numList[4]+4){
			change.add(4);
		}else if(numList[0] == numList[1]+1 &&
				numList[1]+1 == numList[2]+2 &&
				numList[2]+2 != numList[3]+3 &&
				numList[2]+2 == numList[4]+4){
			change.add(3);
		}else if(numList[0] == numList[1]+1 &&
				numList[1]+1 != numList[2]+2 &&
				numList[1]+1 == numList[3]+3 &&
				numList[3]+3 == numList[4]+4){
			change.add(2);
		}else if(numList[0] != numList[1]+1 &&
				numList[0] == numList[2]+2 &&
				numList[2]+2 == numList[3]+3 &&
				numList[3]+3 == numList[4]+4){
			change.add(1);
		}else if(numList[0] != numList[1]+1 &&
				numList[1]+1 == numList[2]+2 &&
				numList[2]+2 == numList[3]+3 &&
				numList[3]+3 == numList[4]+4){
			change.add(0);
		}

		//ペア
		//↓↓↓↓↓↓↓↓↓↓↓フォーオブアカインド↓↓↓↓↓↓↓↓↓↓↓↓
		else if(numList[0] == numList[1] &&
				numList[1] == numList[2] &&
				numList[2] == numList[3]){
			change.add(4);
		}else if(numList[0] == numList[2] &&
				numList[2] == numList[3] &&
				numList[3] == numList[4]){
			change.add(1);
		}else if(numList[0] == numList[1] &&
				numList[1] == numList[3] &&
				numList[3] == numList[4]){
			change.add(2);
		}else if(numList[0] == numList[1] &&
				numList[1] == numList[2] &&
				numList[2] == numList[4]){
			change.add(3);
		}else if(numList[1] == numList[2] &&
				numList[2] == numList[3] &&
				numList[3] == numList[4]){
			change.add(0);
		}

		//↓↓↓↓↓↓↓↓↓↓↓スリーオブアカインド↓↓↓↓↓↓↓↓↓↓↓↓
		else if(numList[0] == numList[1] &&
				numList[1] == numList[2]){
			change.add(3);
			change.add(4);
			System.out.println(Role);
		}else if(numList[0] == numList[1] &&
				numList[1] == numList[3]){
			change.add(2);
			change.add(4);
			System.out.println(Role);
		}else if(numList[0] == numList[1] &&
				numList[1] == numList[4]){
			change.add(2);
			change.add(3);
			System.out.println(Role);
		}else if(numList[1] == numList[2] &&
				numList[2] == numList[3]){
			change.add(0);
			change.add(4);
			System.out.println(Role);
		}else if(numList[1] == numList[2] &&
				numList[2] == numList[4]){
			change.add(0);
			change.add(3);
			System.out.println(Role);
		}else if(numList[1] == numList[3] &&
				numList[3] == numList[4]){
			change.add(0);
			change.add(2);
		}else if(numList[2] == numList[3] &&
				numList[3] == numList[4]){
			change.add(0);
			change.add(1);
		}else if(numList[0] == numList[3] &&
				numList[3] == numList[4]){
			change.add(1);
			change.add(2);
		}else if(numList[0] == numList[2] &&
				numList[2] == numList[4]){
			change.add(1);
			change.add(3);
		}else if(numList[0] == numList[2] &&
				numList[2] == numList[3]){
			change.add(1);
			change.add(4);
		}

		//↓↓↓↓↓↓↓↓↓↓↓ツーペア↓↓↓↓↓↓↓↓↓↓↓↓
		//フルハウス狙い
		else if(numList[0] == numList[1] &&
				numList[2] == numList[3]){
			change.add(4);
		}else if(numList[0] == numList[1] &&
				numList[2] == numList[4]){
			change.add(3);
		}else if(numList[0] == numList[1] &&
				numList[3] == numList[4]){
			change.add(2);
		}else if(numList[0] == numList[2] &&
				numList[1] == numList[3]){
			change.add(4);
		}else if(numList[0] == numList[2] &&
				numList[1] == numList[4]){
			change.add(3);
		}else if(numList[0] == numList[2] &&
				numList[3] == numList[4]){
			change.add(1);
		}else if(numList[1] == numList[3] &&
				numList[0] == numList[4]){
			change.add(2);
		}else if(numList[0] == numList[3] &&
				numList[1] == numList[2]){
			change.add(4);
		}else if(numList[0] == numList[3] &&
				numList[1] == numList[4]){
			change.add(2);
		}else if(numList[0] == numList[3] &&
				numList[2] == numList[4]){
			change.add(1);
		}else if(numList[0] == numList[4] &&
				numList[1] == numList[2]){
			change.add(3);
		}else if(numList[0] == numList[4] &&
				numList[1] == numList[3]){
			change.add(2);
		}else if(numList[0] == numList[4] &&
				numList[2] == numList[3]){
			change.add(1);
		}else if(numList[1] == numList[2] &&
				numList[3] == numList[4]){
			change.add(0);
			System.out.println(Role);
		}else if(numList[1] == numList[3] &&
				numList[2] == numList[4]){
			change.add(0);
		}else if(numList[1] == numList[4] &&
				numList[2] == numList[3]){
			change.add(0);
		}

		//↓↓↓↓↓↓↓↓↓↓↓ワンペア↓↓↓↓↓↓↓↓↓↓↓↓
		else if(numList[0] == numList[1]){
			change.add(2);
			change.add(3);
			change.add(4);
		}else if(numList[0] == numList[2]){
			change.add(1);
			change.add(3);
			change.add(4);
		}else if(numList[0] == numList[3]){
			change.add(1);
			change.add(2);
			change.add(4);
		}else if(numList[0] == numList[4]){
			change.add(1);
			change.add(2);
			change.add(3);
		}else if(numList[1] == numList[2]){
			change.add(0);
			change.add(3);
			change.add(4);
		}else if(numList[1] == numList[3]){
			change.add(0);
			change.add(2);
			change.add(4);
		}else if(numList[1] == numList[4]){
			change.add(0);
			change.add(2);
			change.add(3);
		}else if(numList[2] == numList[3]){
			change.add(0);
			change.add(1);
			change.add(4);
		}else if(numList[2] == numList[4]){
			change.add(0);
			change.add(1);
			change.add(3);
		}else if(numList[3] == numList[4]){
			change.add(0);
			change.add(1);
			change.add(2);
		}
		//↓↓↓↓↓↓↓↓↓↓↓ハイカード↓↓↓↓↓↓↓↓↓↓↓↓
		else{
			change.add(0);
			change.add(1);
			change.add(2);
			change.add(3);
			change.add(4);
		}

		//交換処理
		//カードの交換
		for(int cnt = 0; cnt < change.size(); cnt++){
			int C = change.get(cnt);
			int rand = new java.util.Random().nextInt(deck.size());
			hand.set(C , deck.get(rand));
			deck.remove(rand);

		}

	}
	//勝利判定
	public void WIN(){
		int win = 1;
		int winNum = RoleJudge(win);
		int winNum2 = DRoleJudge(win);


		if(winNum < winNum2){
			System.out.println("😨😨😨😨😨😨 敗北！ 😨😨😨😨😨😨");
		}else if(winNum > winNum2){
			System.out.println("😃😃😃😃😃😃 勝利！ 😃😃😃😃😃😃");
		}else if(winNum == winNum2){
			System.out.println("<<<<<<<<<<< ドロー! >>>>>>>>>>>>");
		}
	}
}