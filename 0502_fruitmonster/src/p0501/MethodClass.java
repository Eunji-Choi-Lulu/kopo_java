package p0501;

import java.util.Random;
import java.util.Scanner;


/**
 * 메소드들을 모아놓은 메소드 클래스 정의 
 */
public class MethodClass {


	//예쁜 UI를 위해 색상 정의
	String RED = "\u001B[31m";
	String YELLOW = "\u001B[33m";
	String ORANGE = "\u001B[38;5;208m";
	String LIGHT_GREEN = "\u001B[38;5;118m";
	String PURPLE = "\u001B[35m";
	String PINK = "\u001B[95m";    
	String GOLD = "\u001B[38;5;220m";
	String RESET = "\u001B[0m";

	//랜덤 숫자를 생성하기 위한 객체 생성 
	Random r = new Random(); 
	
	//사용자 입력을 받기 위한 객체 생성
	Scanner s = new Scanner(System.in); 

	//플레이어 정보를 저장할 배열 생성
	Player[] player = new Player[10];
	
	//초기에 생성된 유저수 및 인덱스로 사용할 playerCnt생성
	int playerCnt = 0;
	
	//초기에 생성된 몬스터 수 및 인덱스로 사용할 invenCnt생성
	int invenCnt = 0; 
	
	//사용자의 최대 체력
	int maxHp = 100; 
	
	//체력 회복용 포션 갯수 총 2개 
	int potionCnt = 2; 
	
	//몬스터 정보를 저장할 배열 생성 
	Base[] monster = new Base[5];
	
	//생성자: MethodClass 객체가 생성될 때 호출됨
	//몬스터 배열에 각각의 몬스터 객체를 초기화하여 저장 
	MethodClass(){
		monster[0] = new M1(); 
		monster[1] = new M2(); 
		monster[2] = new M3(); 
		monster[3] = new M4(); 
		monster[4] = new M5(); 
	}

	
	/**
	 * 사용자의 닉네임 입력을 받는 메소드 <br>
	 * 닉네임이 중복되지 않으면 새로운 player 객체 생성 
	 * 성공시 true, 실패시 false반환
	 * boolean을 사용하는 이유는, 호출한 곳에서 유연하게 판단 가능하기 때문 -> 유저 생성 or 다시 입력받기 
	 */
	public boolean createUser() {
		System.out.print("닉네임을 입력해주세요: ");
		
		String temp = s.nextLine();
		
		//기존에 저장된 유저들 중에서 닉네임이 중복되는지 확인
		for(int i = 0; i < this.playerCnt; i++) {
			if(player[i].username.equals(temp)) {
				System.out.println("이미 존재하는 닉네임입니다.");
				return false; 
			}
		}
		
		//중복이 없으면 새로운 객체 생성 후 배열에 저장 
		player[playerCnt] = new Player(); 
		//입력한 닉네임을 설정
		player[playerCnt].username = temp; 
		
		//환영 메세지 출력
		System.out.println("반갑습니다! " + player[playerCnt].username + "님");

		//유저 생성 성공 
		return true; 
	}
	
	/**
	 * 플레이어들의 점수를 기준으로 순위를 정렬하고 출력하는 메서드
	 * 
	 * 이 메서드는 현재까지 게임을 플레이한 모든 유저의 점수를 기준으로
	 * 높은 점수 순으로 순위를 매기고, 그 결과를 콘솔에 출력합니다.
	 * 
	 * 주요 동작:
	 * - 유저 점수 배열을 만들어 내림차순으로 정렬 (삽입 정렬 사용)
	 * - 점수와 일치하는 유저 이름을 찾아 순위에 매핑
	 * - 순위표를 출력 (1등부터 N등까지)
	 * 
	 * 조건:
	 * - 등록된 유저가 한 명도 없을 경우, 안내 메시지만 출력 후 종료됩니다.
	 */
	public void ranking() {
		// 유저가 한 명도 등록되지 않았을 경우 메세지 출력 후 종료
		if (this.playerCnt == 0) {
			System.out.println("현재 순위가 없습니다. 1등에 도전해보세요!");
			return;
		}
		
		// 유저 점수를 저장할 배열
		int[] rank = new int[this.playerCnt];
		
		// 순위에 해당하는 유저 이름을 저장할 배열
		String[] rankUser = new String[this.playerCnt];
		
		// 유저의 점수를 rank 배열에 복사
		for (int i = 0; i < this.playerCnt; i++) {
			rank[i] = player[i].score;
		}
		
		// 삽입 정렬을 사용하여 점수를 내림차순으로 정렬
		for (int i = 1; i < this.playerCnt; i++) {
			int key = rank[i]; 	// 현재 비교할 점수
			int j = i - 1; 
			
			// 앞쪽 점수들이 key보다 작으면 뒤로 한 칸씩 이동
			while (j >= 0 && rank[j] < key) {
				rank[j + 1] = rank[j];
				j--;
			}	
			
			// key를 적절한 위치에 삽입
			rank[j + 1] = key;
		}

		// 정렬된 점수와 실제 유저 이름을 매칭
		for (int i = 0; i < this.playerCnt; i++) {
			for (int j = 0; j < this.playerCnt; j++) {
				
				// 점수가 일치하는 유저를 찾았을 때
				if (rank[i] == player[j].score) {
					
					// 중복된 이름이 이미 등록되었는지 확인
		            boolean alreadyExists = false;
		            for (int k = 0; k < this.playerCnt; k++) {
		                if (rankUser[k] != null && rankUser[k].equals(player[j].username)) {
		                    alreadyExists = true;
		                    break;
		                }
		            }
		            
		            // 중복이 아니라면 유저 이름을 해당 순위에 할당
		            if (!alreadyExists) {
		                rankUser[i] = player[j].username;
		                break; // 한 명만 매칭 후 루프 종료
		            }
				}
			}
		}
		
		// 최종 순위 출력
		System.out.println("========== 순위 ==========");
		for (int i = 0; i < this.playerCnt; i++) {
			//printf를 사용 (문자열 형식지정자 %d와 정수 형식 지정자 %s를 사용)
	        System.out.printf("%d등 : %s, %d점%n", i + 1, rankUser[i], rank[i]);
		}
	}
	
	/**
	 * 몬스터를 조우하는 메소드 생성 
	 * 60% 확률로 몬스터를 조우하기 
	 * 조우할 경우 각 몬스터의 등장확률로 나타나게 하기 <br>
	 * 각 플레이어는 몬스터를 상태로 "싸운다" 혹은 "도망간다" 중 하나를 선택 가능 
	 * 확률분포: <br> 
	 * M1: 30% //포도
	 * M2: 30% //레몬
	 * M3: 15% //오렌지
	 * M4: 15% //메론
	 * M5: 10% //수박
	 */
	public void encounter() {
		int encounter = r.nextInt(100);
		
		//60%확률로 몬스터를 조우하기 
		if (encounter < 60) {
			
			//조우 메세지 출력
			System.out.println("과일 몬스터를 마주쳤다!!!");
			
			//0~99까지의 난수를 생성해 확률로 몬스터 만나게 설정
			int chance = r.nextInt(100);
			
			//30%확률로 M1 마주치기
			if (chance < 30) {
				
				//M1 조우 및 결투 선택 메세지 출력
				System.out.println("이것은 거봉인가 포도인가? 앗 포도다!");
				System.out.println(PURPLE + "🍇🍇🍇🍇🍇🍇🍇🍇🍇🍇🍇🍇🍇🍇" + RESET);
				System.out.println("선택지를 골라주세요");
				System.out.println("1: 싸운다 | 2: 도망간다");
				
				//번호 입력을 위한 변수 생성 
				//변수는 String으로 생성해 숫자 말고 다른 문자를 입력했을 때 재입력 요청
				String num = s.nextLine(); 
				
				//1.싸우기 선택
				if (num.equals("1")) {
					System.out.println("싸움을 선택하셨습니다");
					
					//1-1.몬스터를 잡았을 경우 (이때 Base의 catchMonster를 monster를 통해 불러오기) 
					if(monster[0].catchMonster()) {
						System.out.println("포도를 잡았다! 운악산에서 따온 것인가? 너무 신선하고 맛있다");
					
						//잡은 몬스터를 인벤토리에 저장하기
						player[playerCnt].inventory[invenCnt] = monster[0].name;
						
						//저장했으니, 그 다음에 잡은 몬스터 정보를 넣기 위해 인벤토리 증가시키기 
						invenCnt++; 
						
						//점수 획득 메세지 출력
						System.out.println(monster[0].score + "점을 획득했다");
						
						//몬스터 포획시 얻는 점수를 player 총점에 더하기
						player[playerCnt].score += monster[0].score;
					
					//1-2. 포도의 공격력이 없으므로, 별도의 출력 메세지 없음 
					} 
					
					else {
						
					}
				} 
				
				//2.도망가기 선택	
				else if (num.equals("2")) {
					System.out.println("도망가기를 선택하셨습니다");
				}
				
				//3.잘못된 입력 처리
				else {
					System.out.println("앗 잘못 입력하셨어요.." + monster[0]+ "가 도망쳤다!!");
				}
			}
			
			
			
			//30%확률로 M2 마주치기 
			else if (chance < 60) {
				
				//M2 조우 및 결투 선택 메세지 출력
				System.out.println("이것은 유자인가 레몬인가? 앗 레몬이다!");
				System.out.println(YELLOW + "🍋🍋🍋🍋🍋🍋🍋🍋🍋🍋🍋🍋🍋🍋" + RESET);
				System.out.println("선택지를 골라주세요");
				System.out.println("1: 싸운다 | 2: 도망간다");
				
				//번호 입력을 위한 변수 생성 
				//변수는 String으로 생성해 숫자 말고 다른 문자를 입력했을 때 재입력 요청
				String num = s.nextLine(); 
				
				//1.싸우기 선택
				if (num.equals("1")) {
					System.out.println("싸움을 선택하셨습니다");
					
					//1-1.몬스터를 잡았을 경우 (이때 Base의 catchMonster를 monster를 통해 불러오기) 
					if(monster[1].catchMonster()) {
						System.out.println("레몬을 잡았다! 이것은 이탈리아 시칠리아산인가? 너무 상큼하고 탐난다");
					
						//잡은 몬스터를 인벤토리에 저장하기
						player[playerCnt].inventory[invenCnt] = monster[1].name;
						
						//저장했으니, 그 다음에 잡은 몬스터 정보를 넣기 위해 인벤토리 증가시키기 
						invenCnt++; 
						
						//점수 획득 메세지 출력
						System.out.println(monster[1].score + "점을 획득했다");
						
						//몬스터 포획시 얻는 점수를 player 총점에 더하기
						player[playerCnt].score += monster[1].score;
					
					//1-2. 잡지 못했을 경우 몬스터에게 공격 당하도록 설정  
					} 
					
					else {
						System.out.println("너무 셔서 놓쳐버렸다...");
						System.out.println("체력을 " + monster[1].attack + " 만큼 잃었다.....");
						
						//사용자의 hp에서 몬스터 공격력만큼 감소
						player[playerCnt].hp -= monster[1].attack; 						
					}
				} 
				
				//2.도망가기 선택	
				else if (num.equals("2")) {
					System.out.println("도망가기를 선택하셨습니다");
				}
				
				//3.잘못된 입력 처리
				else {
					System.out.println("앗 잘못 입력하셨어요.." + monster[1]+ "가 도망쳤다!!");
				}
			}
			
			
			
			
			//15%확률로 M3 마주치기 
			else if (chance < 75) {
				
				//M3 조우 및 결투 선택 메세지 출력
				System.out.println("이것은 한라봉인가 오렌지인가? 앗 오렌지다!");
				System.out.println(ORANGE + "🍊🍊🍊🍊🍊🍊🍊🍊🍊🍊🍊🍊🍊🍊" + RESET);
				System.out.println("선택지를 골라주세요");
				System.out.println("1: 싸운다 | 2: 도망간다");
				
				//번호 입력을 위한 변수 생성 
				//변수는 String으로 생성해 숫자 말고 다른 문자를 입력했을 때 재입력 요청
				String num = s.nextLine(); 
				
				//1.싸우기 선택
				if (num.equals("1")) {
					System.out.println("싸움을 선택하셨습니다");
					
					//1-1.몬스터를 잡았을 경우 (이때 Base의 catchMonster를 monster를 통해 불러오기) 
					if(monster[2].catchMonster()) {
						System.out.println("오렌지를 잡았다. 오렌지주스를 만들어야겠다 후후후");
					
						//잡은 몬스터를 인벤토리에 저장하기
						player[playerCnt].inventory[invenCnt] = monster[2].name;
						
						//저장했으니, 그 다음에 잡은 몬스터 정보를 넣기 위해 인벤토리 증가시키기 
						invenCnt++; 
						
						//점수 획득 메세지 출력
						System.out.println(monster[2].score + "점을 획득했다");
						
						//몬스터 포획시 얻는 점수를 player 총점에 더하기
						player[playerCnt].score += monster[2].score;
					
					//1-2. 잡지 못했을 경우 몬스터에게 공격 당하도록 설정  
					} 
					
					else {
						System.out.println("껍질이 두꺼워서 까다가 놓쳐버렸다...");
						System.out.println("체력을 " + monster[2].attack + " 만큼 잃었다.....");
						
						//사용자의 hp에서 몬스터 공격력만큼 감소
						player[playerCnt].hp -= monster[2].attack; 						
					}
				} 
				
				//2.도망가기 선택	
				else if (num.equals("2")) {
					System.out.println("도망가기를 선택하셨습니다");
				}
				
				//3.잘못된 입력 처리
				else {
					System.out.println("앗 잘못 입력하셨어요.." + monster[2]+ "가 도망쳤다!!");
				}
			}
			
			
			
			//15%확률로 M4 마주치기 
			else if (chance < 90) {
				
				//M3 조우 및 결투 선택 메세지 출력
				System.out.println("이것은 참외인가 메론인가? 앗 메론이다!");
				System.out.println(LIGHT_GREEN + "🍈🍈🍈🍈🍈🍈🍈🍈🍈🍈🍈🍈🍈🍈" + RESET);
				System.out.println("선택지를 골라주세요");
				System.out.println("1: 싸운다 | 2: 도망간다");
				
				//번호 입력을 위한 변수 생성 
				//변수는 String으로 생성해 숫자 말고 다른 문자를 입력했을 때 재입력 요청
				String num = s.nextLine(); 
				
				//1.싸우기 선택
				if (num.equals("1")) {
					System.out.println("싸움을 선택하셨습니다");
					
					//1-1.몬스터를 잡았을 경우 (이때 Base의 catchMonster를 monster를 통해 불러오기) 
					if(monster[3].catchMonster()) {
						System.out.println("메론을 잡았다. 오늘 저녁은 품격있게 메론 프로슈토다!");
					
						//잡은 몬스터를 인벤토리에 저장하기
						player[playerCnt].inventory[invenCnt] = monster[3].name;
						
						//저장했으니, 그 다음에 잡은 몬스터 정보를 넣기 위해 인벤토리 증가시키기 
						invenCnt++; 
						
						//점수 획득 메세지 출력
						System.out.println(monster[3].score + "점을 획득했다");
						
						//몬스터 포획시 얻는 점수를 player 총점에 더하기
						player[playerCnt].score += monster[3].score;
					
					//1-2. 잡지 못했을 경우 몬스터에게 공격 당하도록 설정  
					} 
					
					else {
						System.out.println("너무 비싸서 당황하던 중 놓쳐버렸다...");
						System.out.println("체력을 " + monster[3].attack + " 만큼 잃었다.....");
						
						//사용자의 hp에서 몬스터 공격력만큼 감소
						player[playerCnt].hp -= monster[3].attack; 						
					}
				} 
				
				//2.도망가기 선택	
				else if (num.equals("2")) {
					System.out.println("도망가기를 선택하셨습니다");
				}
				
				//3.잘못된 입력 처리
				else {
					System.out.println("앗 잘못 입력하셨어요.." + monster[3]+ "이 도망쳤다!!");
				}
			}
			
			
			//10%확률로 M5 마주치기 
			else if (chance < 100) {
				
				//M3 조우 및 결투 선택 메세지 출력
				System.out.println("이것은 덩굴째 굴러온 수박인가? 오호 수박이다!");
				System.out.println(RED + "🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉🍉" + RESET);
				System.out.println("선택지를 골라주세요");
				System.out.println("1: 싸운다 | 2: 도망간다");
				
				//번호 입력을 위한 변수 생성 
				//변수는 String으로 생성해 숫자 말고 다른 문자를 입력했을 때 재입력 요청
				String num = s.nextLine(); 
				
				//1.싸우기 선택
				if (num.equals("1")) {
					System.out.println("싸움을 선택하셨습니다");
					
					//1-1.몬스터를 잡았을 경우 (이때 Base의 catchMonster를 monster를 통해 불러오기) 
					if(monster[4].catchMonster()) {
						System.out.println("시원한 수박이 왔어요~ 이번 여름은 잘 보낼 수 있겠다!");
					
						//잡은 몬스터를 인벤토리에 저장하기
						player[playerCnt].inventory[invenCnt] = monster[4].name;
						
						//저장했으니, 그 다음에 잡은 몬스터 정보를 넣기 위해 인벤토리 증가시키기 
						invenCnt++; 
						
						//점수 획득 메세지 출력
						System.out.println(monster[4].score + "점을 획득했다");
						
						//몬스터 포획시 얻는 점수를 player 총점에 더하기
						player[playerCnt].score += monster[4].score;
					
					//1-2. 잡지 못했을 경우 몬스터에게 공격 당하도록 설정  
					} 
					
					else {
						System.out.println("잘 익었는지 통통 확인하던 중에 도망가버렸다... ");
						System.out.println("체력을 " + monster[4].attack + " 만큼 잃었다.....");
						
						//사용자의 hp에서 몬스터 공격력만큼 감소
						player[playerCnt].hp -= monster[4].attack; 						
					}
				} 
				
				//2.도망가기 선택	
				else if (num.equals("2")) {
					System.out.println("도망가기를 선택하셨습니다");
				}
				
				//3.잘못된 입력 처리
				else {
					System.out.println("앗 잘못 입력하셨어요.." + monster[4]+ "이 도망쳤다!!");
				}
			}
		} 
		else {
			System.out.println("아무 일도 일어나지 않았다..");
		}	
	}
	
	/**
	 * 플레이어가 요정을 만나는 특별 이벤트 메소드<br>
	 * <br>
	 * 이 메서드는 게임 진행 중 무작위 이벤트로 호출되며,
	 * 플레이어는 2종류 중 하나의 요정을 만나게 됩니다:<br>
	 * <br>
	 * - 천사 요정: 점수 +5<br>
	 * - 악마 요정: 점수 -5<br>
	 * <br>
	 * 요정의 종류는 50% 확률로 결정되며, 결과에 따라<br>
	 * 현재 플레이어(user[userCount])의 점수가 즉시 변경됩니다.<br>
	 */
	public void fairy() {
		// 특별 요정 출현 메세지 출력
		System.out.println("특별요정을 만났다");
		
		// 0 ~ 1 사이의 난수 생성
		int fairyType = r.nextInt(2);
		
		// 천사 요정 (50%)
		if (fairyType == 0) {
			// 사용자 점수 + 5
			player[playerCnt].score += 5;
			
			// 천사 요정 메세지 출력
			System.out.println("너무 아름다운 천사를 만났다! 점수 +5");
			
		// 악마 요정 (50%)
		} else {
			// 사용자 점수 - 5
			player[playerCnt].score -= 5;
			
			// 악마 요정 메세지 출력
			System.out.println("이럴수가... 악마였다..... 점수 -5");
		}
	}
	
	
	/**
	 * 플레이어의 현재 점수를 출력하는 이벤트 메소드
	 */
	public void printScore() {
		System.out.println("현재 점수 : " + player[playerCnt].score);
	}
	
	
	/**
	 * 플레이어의 현재 잃었다 출력해주는 이벤트 메소드
	 */
	public void printHeart() {
		System.out.println("현재 체력 : " + player[playerCnt].hp);
	}
	
	
	/**
	 * 보물상자를 열었을 때의 이벤트를 처리하는 메서드<br>
	 * <br>
	 * 플레이어가 필드에서 보물상자를 발견했을 때 호출되며,
	 * 상자 안에서 세 가지 중 하나의 결과가 무작위로 등장:<br>
	 * <br>
	 * - 비타민C (30% 확률): 체력을 회복시켜줍니다.<br>
	 * 		- 체력이 85 이상이면 → 최대 체력까지 회복<br>
	 * 		- 체력이 100이면 → 아무 일도 일어나지 않음<br>
	 * 		- 그 외 → 체력 +15<br>
	 * - 폭탄 (30% 확률): 플레이어의 체력을 10 감소시킵니다.<br>
	 * - 종이 (40% 확률): 아무 효과 없는 꽝 아이템입니다.<br>
	 */
	public void treasure() {
		// 상자 발견 메세지 출력
		System.out.println("보물상자를 발견했습니다!");
		
		// 0~99 사이의 난수 생성
		int randNumber = r.nextInt(100);
		   
		// [비타민C] 30% 확률 (0~29)
		if (randNumber < 30) {
			// 플레이어의 현재 체력이 85 이상이면 → 최대 체력까지 회복
			if (player[playerCnt].hp >= 85) {
				System.out.println("비타민C를 발견! 최대 체력까지 회복한다");
				player[playerCnt].hp = maxHp; // 최대 체력으로 설정
			
			// 체력이 이미 100이라면 → 아무 효과 없음
			} else if (player[playerCnt].hp == 100) {
				System.out.println("체력이 이미 100이군... 아쉽지만 비타민C를 먹을 수 없다.");
			
			// 그 외 → 체력 +15 회복
			} else {
				System.out.println("비타민C를 발견!. 체력을 15 회복한다.");
				player[playerCnt].hp += 15; // 사용자 체력 + 15
			}
			
		// [폭탄] 30% 확률 (30~59)
		} else if (randNumber < 60) {
			// 체력 10 감소 메시지 출력 및 체력 감소
			System.out.println("폭탄 발견!! 체력이 10 깎였다...");
			player[playerCnt].hp -= 10; 
		
		// [종이] 40% 확률 (60~99)
		} else {
			// 꽝 메세지 출력 (효과 없음)
			System.out.println("종이 발견. 꽝!");
		}	      
	}
	
	/**
	 * 몬스터 도감을 출력하는 메소드 
	 */
	public void dict() {
		System.out.println("이름 | 점수 | 발견 확률 | 잡힐 확률");
		System.out.println(monster[0].name + " | " + monster[0].score + " | 30% | 100%" );
		System.out.println(monster[1].name + " | " + monster[1].score + " | 30% | 80%" );
		System.out.println(monster[2].name + " | " + monster[2].score + " | 15% | 60%" );
		System.out.println(monster[3].name + " | " + monster[3].score + " | 15% | 50%" );
		System.out.println(monster[4].name + " | " + monster[4].score + " | 10% | 20%" );		
	}
	
	/**
	 * 사용자가 잡은 몬스터들을 인벤토리에서 확인할 수 있는 메소드<br>
	 * 인벤토리가 비어있을 경우 잡은 몬스터가 없음 메세지 출력<br>
	 * 인벤토리에 몬스터가 있으면 잡은 몬스터 목록 출력
	 */
	
	public void catchedMon() {
		//잡은 몬스터가 없을 경우 
		//return으로 메소드 즉시 종료 -> 아래 코드를 실행하지 않음 (이전 로직으로 돌아감) 
		//특정 조건에서 조기종료할 필요가 있어서 return 사용 
		if(this.invenCnt == 0) {
			System.out.println("잡은 몬스터가 없다...!!");
			return; 
		}
		
		//인벤토리 목록 출력 
		//번호 및 0번쨰 인덱스부터 출력하기 위해 for문 사용 
		//이 메소드가 종료되면, 해당 메소드를 호출한 위치 (상위 메뉴 로직)으로 자연스럽게 돌아가므로, return 필요 없음 
		System.out.println("========== 몬스터 인벤토리 ==========");
		for (int j = 0; j < this.invenCnt; j++) {
			System.out.println((j+1) + ". " + player[playerCnt].inventory[j]);
		}
	}
	
	

	/**
	 * 게임의 메인 루프를 담당하는 메서드
	 * 
	 * 플레이어가 게임을 시작한 후, 종료 조건이 만족될 때까지 계속 반복되는 구조
	 * 주요 기능은 다음과 같습니다:
	 * 
	 * - 체력이 0 이하가 되면 게임 종료 및 점수 출력
	 * - 점수가 500점 이상이 되면 게임 종료 및 점수 출력
	 * - 반복되는 게임 메뉴를 통해 다음 선택 가능:
	 *     1. 탐색: 요정/보물상자/몬스터 중 하나 무작위로 발생
	 *     2. 물약 사용: 체력이 100이 아닐 경우 포션으로 회복
	 *     3. 몬스터 도감 확인
	 *     4. 인벤토리 확인 (잡은 몬스터 목록)
	 *     5. 현재 점수 출력
	 *     6. 현재 체력 출력
	 *     7. 수동 게임 종료
	 * 
	 * 사용자의 입력에 따라 각 기능이 호출되며, 입력 오류도 체크하여 안내 메시지를 출력합니다.
	 */
	public void inGame() {
		
		//while(true)를 사용하는 이유: 무한 루프를 만들기 위해 
		//게임이 끝날 때까지 반복적으로 로직을 실행해야 하기 때문 
		//체력이 0 이하이거나 특정 점수 이상이면, break로 루프를 빠져나가면서 게임이 종료되게 설정
		while(true) {
		//체력이 0 이하일 경우 게임 종료
			if (player[this.playerCnt].hp <= 0) {
				//게임 종료 메세지 및 점수 출력 
				System.out.println("체력이 0입니다. 게임을 종료합니다.");
				System.out.println("당신의 점수는" + player[this.playerCnt].score + "입니다.");
				
				//다음 유저로 인덱스 하나 증가시키기
				this.playerCnt++; 
				
				//인벤토리 초기화
				invenCnt = 0; 
				break;
			}
			
			//500점 이상일 경우 클리어
			//if와 else if가 아니라 두개의 다른 조건이므로 if를 사용 
			if (player[this.playerCnt].score > 500) {
				//축하메세지 출력 및 사용자의 최종 점수 출력 
				System.out.println("축하합니다! 점수 500점을 달성했습니다!");
				System.out.println("당신의 점수는" + player[this.playerCnt].score + "입니다.");
				
				//다음 유저로 인덱스 이동
				this.playerCnt++; 
				
				//인벤토리 초기화
				invenCnt = 0; 
				break; 
			}	
			
			//게임 메뉴 출력
		    System.out.println();
		    System.out.println("========== " + RED + "🍎 게임 메뉴 🍎" + RESET + " ==========");
			System.out.println("1. 탐색 | 2. 물약먹기(현재 개수 : "+ this.potionCnt+ "개) | 3. 몬스터 도감 | 4. 몬스터 인벤토리 | 5. 현재 점수 | 6. 현재 체력 | 7. 게임종료");
		
			System.out.println("번호를 입력하세요: ");
			
			//사용자 입력 받기 
			String temp = s.nextLine(); 
			
			//1. 탐색 선택시 
			if (temp.equals("1")) {
				
				//0~99까지의 난수 생성
				int randNum = r.nextInt(100);
				
				//10% 확률로 요정 등장
				if (randNum < 10) {
					fairy(); 
				}
				
				//10% 확률로 보물상자 등장
				else if (randNum < 20) {
					treasure(); 
				}
				
				//나머지 80%확률로 몬스터 조우 
				else {
					encounter();
				}

			}
			
			//2. 포션 선택시
			else if (temp.equals("2")) {
				
				//체력이 최대인경우 (100) 
				if (player[playerCnt].hp == 100) {
					System.out.println("이미 체력이 최대치입니다.");
				}
				
				//포션이 있는경우 
				else if (potionCnt > 0) {
					
					//포션이 있고, 체력이 80 이상인 경우 최대 체력까지 회복 
					if (player[playerCnt].hp > 80 ) {
						System.out.println("최대 체력까지 회복합니다.");
						player[playerCnt].hp = 100; 
						//포션 하나 감소시키기 
						this.potionCnt -= 1; 
					}
					
					//포션이 있고, 체력이 80 이하인 경우 
					else {
						System.out.println("체력을 20 회복합니다.");
						player[playerCnt].hp += 20; 
						//포션 하나 감소시키기
						this.potionCnt -= 1; 
					}
				}
				
				//포션이 없는 경우 
				else {
					System.out.println("사용할 수 있는 포션이 없습니다!");
				}
			}
			
			//3. 몬스터 도감 출력 
			else if (temp.equals("3")) {
				dict(); 
			}
			
			//4. 인벤토리 출력 
			else if (temp.equals("4")) {
				catchedMon(); 
			}
			
			//5. 현재 점수 출력
			else if (temp.equals("5")) {
				printScore(); 
			}
			
			//6. 현재 체력 출력
			else if (temp.equals("6")) {
				printHeart(); 
			}
			
			//7. 게임 종료 
			//while(true)는 무한 루프로, 명시적으로 중단하지 않으면 끝나지 않음. 
			//inGame()에서, hp<0, score>500, 그리고 7을 눌렀을 때 break로 루프를 빠져나가 메소드 종료
			else if (temp.equals("7")) {
				System.out.println("게임을 종료합니다.");
				
				//다음 유저 인덱스로 이동
				this.playerCnt++; 
				
				//인벤토리 초기화
				invenCnt = 0; 
				break;  
			}
			
		else {
			System.out.println("잘못된 입력입니다. 올바른 메뉴 번호로 입력해주세요.");	
		}
		
		//그외 잘못된 입력

		}
	}
	
	
	/**
	 * 게임의 초기 메인메뉴 메소드<br>
	 * 무한 루프로 게임 메뉴가 생성되어야 하므로 while(true)사용s
	 * StringBuilder를 사용
	 */
	public void gameStart() {
		while(true) {

			StringBuilder startMsg = new StringBuilder()
			        .append(PINK).append("🍓")
			        .append(PURPLE).append("🍇")
			        .append(RED).append("🍉")
			        .append(YELLOW).append("🍋")
			        .append(ORANGE).append("🍊")
			        .append(LIGHT_GREEN).append("🍈")
			        .append(RESET).append(" 과일 왕국에 오신 것을 환영합니다! ")
			        .append(LIGHT_GREEN).append("🍈")
			        .append(ORANGE).append("🍊")
			        .append(RED).append("🍉")
			        .append(PURPLE).append("🍇")
			        .append(GOLD).append("🍍")
			        .append(PINK).append("🍓").append(RESET);
			System.out.println(startMsg.toString());
			
		    
		    System.out.println("========== " + RED + "🍎 메인 메뉴 🍎" + RESET + " ==========");
		    System.out.println("1. 유저 생성 | 2. 현재 순위 | 3. 종료");
		    System.out.print("번호를 입력하세요: ");
		    
		    //사용자의 입력을 받음 
		    String temp = s.nextLine(); 
		    System.out.println();
		    
		    //1 입력시 유저 생성 후 게임 시작
		    if (temp.equals("1")) {
		    	
		    	//createUser메소드를 호출하여 유저를 생성하고 성공시 게임을 시작
		    	//실패시 createUser메소드 내에서 재입력을 받게 설정
		    	if (createUser()) {
		    		inGame();  //게임 진행
		    	}
		    }
		    
		    //2 입력시 현재 순위를 출력하는 메소드 호출 
		    else if (temp.equals("2")) {
		    	ranking(); 
		    }
		    
		    //3 입력시 게임 종료 
		    else if (temp.equals("3")) {
		    	System.out.println("게임을 종료합니다");
		    }
		    
		    else { 
		    	System.out.println("잘못된 입력입니다. 올바른 번호로 입력해주세요.");
		    }
		}
	}
}
