package ポーカー;

public class Poker {

	public static void main(String[] args) {
		Make_Poker mpoker = new Make_Poker();
		int win = 0;

		System.out.println("　　　　　　　ポーカーを始めます　　　　　　　");
		mpoker.CardSet();

		//ドロー
		mpoker.Drowing();
		mpoker.showCard();
		mpoker.showInf();
		mpoker.Return();
		mpoker.DreturnJugde();
		mpoker.showCard();
		System.out.println();
		System.out.print("ディーラーの役は=>");
		mpoker.DRoleJudge(win);
		System.out.println();
		System.out.print("自分の役は=>");
		mpoker.RoleJudge(win);
		mpoker.WIN();

	}

}
