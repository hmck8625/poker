package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;
 

概要
ヘッズアップ、 毎回順番

joker 以外の52枚の山札を作成、シャッフルする
ボタンは常に相手とする
ブラインド無し
プレイヤーがフォールドしたら、その場でポットは勝者に渡り、次のカードが配られる
チップがなくなったらゲーム終了。
相手プレイヤーはハンドのつよさではなく、確率でベット、フォールド、コールしてくるように設定する。


 
public class texasHoldem {
	public static void main(String[] args) {
		System.out.println(セッションを開始します！グッドラック！);
		int handsuu = 0;
        プレイヤーの持っているチップを定義（初期値100）potも定義（初期値0）
        int list[] = {1000,1000,0,0,0,0};
        
        [0] herochip(自分)
        [1] enemychip(相手)
        [2] pot
        [3] needChiptoCall
        [4]foldFlag				       
        ===foldFlag===
    		 0 foldしてない
        	 1 heroがfold
        	 2 enemy がfold
        
        [5]alInFlag
        ===allInFlag===
        	 0 allinしてない
    		 1 heroがallin
    		 2 enemy がallin
        
		デッキを作成
		List Integer deck = new ArrayList(52);
		
		while(list[0]  0 && list[1]  0) {
			handsuu ++;
			System.out.println(==========+handsuu+ハンド目+===========);
			デッキをシャッフル
			shuffleDeck(deck);
			
	        プレイヤーの手札リストを生成
	        List Integer hero = new ArrayList();
	        List Integer enemy = new ArrayList();
	        List Integer holeCard = new ArrayList();
	   
			プレイヤーにカードを二枚ずつ配る
	        hero.add(deck.get(0));
	        enemy.add(deck.get(1));
	        hero.add(deck.get(2));
	        enemy.add(deck.get(3));

	        ホールカード5枚分用意しておく
	    	holeCard.add(deck.get(4));
	    	holeCard.add(deck.get(5));
	    	holeCard.add(deck.get(6));
            holeCard.add(deck.get(7));
            holeCard.add(deck.get(8));

	      
	    	Scanner scan = new Scanner(System.in);

	    		プリフロップの処理
	    	
	    	street(hero, list, scan);
	    	

	    		ターン、リバーの処理
	    	
	    	フロップ3枚追加
	    	if(list[4] == 0 && list[5] == 0) {
	            System.out.println(フロップは + toDescription(holeCard.get(0)) + toDescription(holeCard.get(1)) +toDescription(holeCard.get(2)) +  です);
	            street(hero, list, scan);
	    	}


	        
	        ターン
	    	if(list[4] == 0 && list[5] == 0) {

	            System.out.println(ターンカードは + toDescription(holeCard.get(3)) + です。ホールカードは+ toDescription(holeCard.get(0)) + toDescription(holeCard.get(1)) +toDescription(holeCard.get(2)) +toDescription(holeCard.get(3)) +  です);
	            street(hero, list, scan);
	    	}

	        リバー
	    	if(list[4] == 0 && list[5] == 0) {

	            System.out.println(リバーカードは + toDescription(holeCard.get(4)) + です。ホールカードは+ toDescription(holeCard.get(0)) + toDescription(holeCard.get(1)) +toDescription(holeCard.get(2)) +toDescription(holeCard.get(3)) + toDescription(holeCard.get(4)) + です);
	            street(hero, list, scan);
	    	}

	    		ショーダウン。勝敗の決定
	    	if(list[4] == 1) {heroが降りた時
				System.out.println(enemyは+list[2]+$のチップを獲得しました );
	    		list[1] = list[1] + list[2];
	    		list[2] = 0;
	    		list[3] = 0;
	    		list[4] = 0;
	    	}else if(list[4] == 2) {
				System.out.println(heroは+list[2]+$のチップを獲得しました );
	    		list[0] = list[0] + list[2];
	    		list[2] = 0;
	    		list[3] = 0;
	    		list[4] = 0;
	    		
	    	}else {
	            int herorole[] = showdown(hero, holeCard);
	            int enemyrole[] = showdown(enemy, holeCard);
	            
	            System.out.println(ショウダウンです。ホールカードは+ toDescription(holeCard.get(0)) + toDescription(holeCard.get(1)) +toDescription(holeCard.get(2)) +toDescription(holeCard.get(3)) + toDescription(holeCard.get(4)) + です);
	            System.out.println(hero   + toDescription(hero.get(0)) + toDescription(hero.get(1)) + role(herorole[5]));
	            System.out.println(enemy   + toDescription(enemy.get(0)) + toDescription(enemy.get(1)) + role(enemyrole[5]));
	            checkWinner(herorole, enemyrole, list);
	    	}
		}

    		チップがなくなったら終了
		System.out.println(ゲーム終了です。お疲れ様でした。);
	}
	private static String role(int num) {
		switch(num) {
		case 0
			return ハイカード;
		case 1
			return ワンペア;
		case 2
			return ツーペア;
		case 3
			return スリーペア;
		case 4
			return フルハウス;
		case 5
			return フォーカード;
		}
		return error;
	}
	
	private static void checkWinner(int herorole[], int enemyrole[], int list[]) {
		if(herorole[5]  enemyrole[5]) {
			System.out.println(勝者はhero！役は + role(herorole[5]));
			System.out.println(heroは+list[2]+$のチップを獲得しました );
			list[0] = list[0] + list[2];
			list[2] = 0;
		}else if(herorole[5]  enemyrole[5]) {
			System.out.println(勝者はenemy！役は + role(enemyrole[5]));
			System.out.println(enemyは+list[2]+$のチップを獲得しました );
			list[1] = list[1] + list[2];
			list[2] = 0;
		}else {
			int i = 4;
			while(i = 0) {
				if(herorole[i]  enemyrole[i]) {
					System.out.println(勝者はhero！役は + role(herorole[5]) +  キッカーは + herorole[i]);
					System.out.println(heroは+list[2]+$のチップを獲得しました );
					list[0] = list[0] + list[2];
					list[2] = 0;
					break;
				}else if(herorole[i]  enemyrole[i]) {
					System.out.println(勝者はenemy！役は + role(enemyrole[5]) +  キッカーは + enemyrole[i]);
					System.out.println(enemyは+list[2]+$のチップを獲得しました );
					list[1] = list[1] + list[2];
					list[2] = 0;
					break;
				}else{
					i --;
				}
				System.out.println(引き分けです);
				list[0] = list[0] + list[2]2;
				list[1] = list[1] + list[2]2;	
				list[2] = 0;
			}

		}	
	}
		

	
	private static int[] showdown(ListInteger player, ListInteger holeCard) {
		int array[] = {
				toNumber(holeCard.get(0)),
				toNumber(holeCard.get(1)),
				toNumber(holeCard.get(2)),
				toNumber(holeCard.get(3)),
				toNumber(holeCard.get(4)),
				toNumber(player.get(0)),
				toNumber(player.get(1)),
		};
		return handRank(array);
		
	}
	

	private static void street(ListInteger hero, int list[], Scanner scan) {
	    	heroAction(hero, list, scan);
	    	while(true) {

	        	enemyAction(list);
	    		if(list[3] == 0) {
					System.out.println(次のストリートに進みます);
	        		break;
	    		}
	    		else if(list[4] == 2) {
					System.out.println(enemyは降りましたheroの勝ちです);
	        		break;
	        	}
	        		

	        	heroAction(hero, list, scan);
	        	if(list[3] == 0) {
					System.out.println(次のストリートに進みます);
	        		break;
	        	}
	        	else if(list[4] == 1) {
					System.out.println(heroは降りましたenemyの勝ちです);
	        		break;
	        	}
	    	}
	    	
	}
	
	public static int[] handRank(int array[]) {
		int count[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};

		for(int i  array) {
			count[i-1] = count[i-1] + 1;
		}
		int hand[] = new int[6];
		int newCount[] = count.clone();
		int pairNumber1;
		int pairNumber2;
		Arrays.sort(newCount);
		if(newCount[12] == 1) {
			System.out.println(hicard);
			Arrays.sort(array);
			System.arraycopy(array, 2, hand, 0, 5);
			return hand;
		}else if(newCount[12] == 2 && newCount[11] == 1) {
			System.out.println(pair);
			pairNumber1 = handNumber(count, 2).get(0);   
			pairHand(array, pairNumber1, hand);
			return hand;
		}else if(newCount[12] == 2 && newCount[11] == 2) {
			System.out.println(two-pair);
			pairNumber1 = handNumber(count, 2).get(0); 
			pairNumber2 = handNumber(count, 2).get(1);
			twoPairHand(array, pairNumber1,pairNumber2, hand);
			return hand;
		}else if(newCount[12] == 3 && newCount[11] == 1) {
			System.out.println(three-card);
			pairNumber1 = handNumber(count, 3).get(0);   
			threePairHand(array, pairNumber1, hand);
			return hand;
		}else if(newCount[12] == 3 && newCount[11] == 2  newCount[11] == 3) {
			System.out.println(full-house);
			pairNumber1 = handNumber(count, 3).get(0); 
			pairNumber2 = handNumber(count, 2).get(0);
			fullHouseHand(pairNumber1, pairNumber2, hand);
			return hand;
		}else if(newCount[12] == 4) {
			System.out.println(four-card);
			pairNumber1 = handNumber(count, 4).get(0);
			fourCard(array, pairNumber1, hand);
			return hand;
		}
		return hand;
	}

	public static void fourCard(int array[], int pairNumber1, int hand[]) {
		Arrays.sort(array);
		int handNum = 0;
		int i = 6;
		while(handNum  1) {
			if(array[i] != pairNumber1) {
				hand[handNum] = array[i];
				i --;
				handNum ++;
			}else {
				i --;
			}
		}
		hand[1] = pairNumber1;
		hand[2] = pairNumber1;
		hand[3] = pairNumber1;
		hand[4] = pairNumber1;
		hand[5] = 5;
	}
	public static void fullHouseHand(int pairNumber1, int pairNumber2 ,int hand[]) {
		hand[0] = pairNumber1;
		hand[1] = pairNumber1;
		hand[2] = pairNumber1;
		hand[3] = pairNumber2;
		hand[4] = pairNumber2;
		hand[5] = 4;
	}
	public static void threePairHand(int array[], int pairNumber, int hand[]) {
		Arrays.sort(array);
		int handNum = 0;
		int i = 6;
		while(handNum  2) {
			if(array[i] != pairNumber) {
				hand[handNum] = array[i];
				i --;
				handNum ++;
			}else {
				i --;
			}
		}
		hand[2] = pairNumber;
		hand[3] = pairNumber;
		hand[4] = pairNumber;
		hand[5] = 3;
	}
	public static void twoPairHand(int array[], int pairNumber1, int pairNumber2, int hand[]) {
		Arrays.sort(array);
		int handNum = 0;
		int i = 6;
		while(handNum  1) {
			if(array[i] != pairNumber1 && array[i] != pairNumber2) {
				hand[handNum] = array[i];
				i --;
				handNum ++;
			}else {
				i --;
			}
		}
		hand[1] = pairNumber1;
		hand[2] = pairNumber1;
		hand[3] = pairNumber2;
		hand[4] = pairNumber2;
		hand[5] = 2;
	}
	public static void pairHand(int array[], int pairNumber, int hand[]) {
		Arrays.sort(array);
		int handNum = 0;
		int i = 6;
		while(handNum  3) {
			if(array[i] != pairNumber) {
				hand[handNum] = array[i];
				i --;
				handNum ++;
			}else {
				i --;
			}
		}
		hand[3] = pairNumber;
		hand[4] = pairNumber;
		hand[5] = 1;
	}
	
	public static ListInteger handNumber(int array[], int overlapCount) {
	   ListInteger num =  new ArrayList();
	   int count = 0;
		for(int i  array) {

			if(i == overlapCount) {
				num.add(count);
			}
			count ++;
		}
		return  num;
	}
	
	private static void enemyAction(int list[]) {敵のアクション（乱数で変動）
		Random rand = new Random();
		int randomValue = rand.nextInt(100);乱数を生成
		
		if(list[3] == 0) {check or bet
			switch(randomValue%2) {
			case 0チェックする
				System.out.println(enemyはcheckしました。);
				break;
			case 1ベット
				int bet = list[2]+1;ベット額はポットサイズ+1で固定
				if(bet  list[1]) {
					bet = list[1];
					list[5] = 2;allinFlagを立てた
					System.out.println(ememyは+bet+$ベットしました。ALL-inです);
				}else{
				System.out.println(ememyは+bet+$ベットしました。);
				手持ちチップを減らし、ポットをふやす、needtoCallも増やす
				}
				list[1] = list[1] - bet;
				list[2] = list[2] + bet;
				list[3] = bet;
				break;
			}
		}else {call or raise or fold
				switch(randomValue % 3) {
				case 0コールする
					if(list[3] = list[1]) {手持ちのチップがすくないとき全額コール、差額はheroに返却
						list[0] = list[0] + list[3] - list[1];
 						list[3] = list[1];
 						list[5] = 2;allinflag
 						System.out.println(enemyはcallしました。All-inです);
					}else {
					System.out.println(enemyはcallしました。);
					}
					手持ちチップを減らしポットをふやす。needtocallをリセット
					list[1] = list[1] - list[3];
					list[2] = list[2] + list[3];
					list[3] = 0;
					break;
				case 1
					int raise = list[3]  2;
					if(raise = list[1]) {
						raise = list[1];
						list[5] = 2;
						System.out.println(ememyはAll-inです。);
					}else {
						System.out.println(ememyは+raise+$にレイズしました。);		
					}
					needtocall-raise = 次のneedtocall
					list[1] = list[1] - raise;
					list[2] = list[2] + raise;
					list[3] = raise - list[3];
					break;
				case 2
					System.out.println(ememyはfoldしました);
					list[4] = 2;			
					break;
				}
			}
	}
	
	private static void heroAction(ListInteger hero, int list[], Scanner scan) {
    	printData(hero, list);
    	
		if(list[3] == 0) {needchiptocallが0の時つまり、ベットレイズが入っていないとき
			while(true) {
                System.out.println(アクションを選択してください check  c or bet  b);
            	String str = scan.next();
            	boolean flag = checkBet(list, str, scan);
            	if(flag == true) {
            		break;
            	}else {
            		System.out.println(入力が不正です!);
            	}	
			}
		}else {
			while(true) {
                System.out.println(アクションを選択してください call  c or raise  r or fold  f);
            	String str = scan.next();
            	boolean flag = callRaiseFold(list, str, scan);
            	if(flag == true) {
            		break;
            	}else {
            		System.out.println(入力が不正です!);
            	}	
			}
		}
	}
	
	private static boolean callRaiseFold(int list[], String str, Scanner scan){
    	if(c.equals(str)) {コールしたときの処理
    		相手に回す
     	   if(list[3] = list[0]) {
				list[1] = list[1] + list[3] - list[0];
				list[3] = list[0];
              	list[5] = 1;
				System.out.println(heroはcallしました。All-inです);
       	   }else {
    		System.out.println(callしました。);
       	   }
     	   list[0]  = list[0] - list[3];
    		list[2] = list[2] + list[3];
    		list[3] = 0;
    		return true;
    	}else if(r.equals(str)) {ベットしたとき。ベット額を聞いてチップを移動、
    		ベット額を聞く
    		while(true) {
                System.out.println(いくらレイズしますか？最大 + list[0] +$です);
                int raise = scan.nextInt();
                if(raise = list[0]  &&  raise = list[3]) {レイス額が持ってる分を越えてる場合と前のベット（レイズ）より小さいばあい
             	   if(raise == list[0]) {
                      	System.out.println(Allinです);
                      	list[5] = 1;
               	   }
                	raise(list, raise);
	                break;
                }else {
                	System.out.println(レイズ額が不正です。正しい値を入力してください);
                }
    		}
    		return true;
    	}else if(f.equals(str)){
        	System.out.println(フォールドします);
        	list[4] = 1;
        	return true;
    	}else {
    		return false;
    	}
	}
	
	private static boolean checkBet(int list[], String str, Scanner scan){
    	if(c.equals(str)) {チェックしたときの処理
    		相手に回す
    		System.out.println(checkしました。);
    		return true;
    	}else if(b.equals(str)) {ベットしたとき。ベット額を聞いてチップを移動、
    		ベット額を聞く
    		while(true) {
                System.out.println(いくらベットしますか？最大 + list[0] +$です);
                int bet = scan.nextInt();
               if(bet = list[0]  &&  bet  0) {
            	   if(bet == list[0]) {
                   	System.out.println(Allinです);
                   	list[5] = 1;
            	   }
	                bet(list, bet);
	                break;
                } else {ベット額が持ってる分を越えてる場合とポットより少ないときは
                	System.out.println(ベット額が不正です。正しい値を入力してください);
                }
    		}
    		return true;
    	}else {
    		return false;
    	}
	}

	private static void raise(int list[], int raise) {
		list[0] = list[0] - raise;
		list[2] = list[2] + raise;
		list[3] = raise - list[3]; 相手がコールするのに必要なチップ＝今回のレイズの額ー前回レイズ額
        System.out.println(raise +$にレイズしました);
	}
	private static void bet(int list[], int bet) {
		list[0] = list[0] - bet;自分のチップを減らす
		list[2] = list[2] + bet;ポットを増やす
		list[3] = bet;相手がコールするのに必要なチップ
        System.out.println(bet +$ベットしました);
	}

	private static void printData(List Integer player, int list[]) {
        System.out.println(============================================);
        System.out.println(あなたのハンドは + toDescription(player.get(0)) + toDescription(player.get(1)) + です);
        System.out.println(あなたの所持チップは + list[0] +$です。相手の所持チップは +list[1]+$です);
        System.out.println(ポットは+list[2]+ です);
	}
	private static void shuffleDeck(ListInteger deck) {

         リストに1-52の連番を代入
    	 for(int i = 1; i = 52; i++) {
    		 deck.add(i);
    	 }
 
        山札をシャッフル
       Collections.shuffle(deck);
 
        リストの中身を確認（デバッグ用）
       
        for (int i=0; ideck.size(); i++)
        {
           System.out.println(deck.get(i));
        }
        
    }

	山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
    private static String toSuit(int cardNumber) {
   	 switch((cardNumber - 1)13) {
   	 case 0
   		 return ♣;
   	 case 1
   		 return ♦;
   	 case 2
   		 return ♥;
   	 case 3
   		 return ♠;
		 default
			 return 例外です;
   	 }
   }

    山札の数をカードの数に置き換えるメソッド
    private static int toNumber(int cardNumber) {
   	 int number = cardNumber % 13;
   	 if(number == 0) {
   		 number = 13;
   	 }
   	 return number;
    }

    カード番号をランク（AやJ,Q,K）に変換するメソッド
    private static String toRank(int number) {
   	 switch(number) {
   	 case 1
   		 return A;
   	 case 11
   		 return J;
   	 case 12
   		 return Q;
   	 case 13
   		 return K;
		 default
			 String str = String.valueOf(number);
			 return str;
			 
   	 }
    }

   山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド
    private static String toDescription(int cardNumber) {
   	 String rank = toRank(toNumber(cardNumber));
   	 String suit = toSuit(cardNumber);
   	 
   	 return suit + rank;
   	 
    }
}
