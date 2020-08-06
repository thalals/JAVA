import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 백준 1012번
 * 
 * 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다.
 * 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에,
 * 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다.
 * 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
 * (한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있다고 간주한다)
 * 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어놓았다.
 * 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
 * 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다.
 * (0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.)
 * 
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50),
 * 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다.
 * 
 * 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
 */
public class 양배추_dfs_bfs {
	private static int count = 0;
	private static int m; // 가로
	private static int n; // 세로
	private static int k; // 배추의 위치
	private static int map[][];
	private static boolean visit[][];
	static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 t

		while (t != 0) {
			String arr[] = br.readLine().split(" ");
			m = Integer.parseInt(arr[0]);
			n = Integer.parseInt(arr[1]);
			k = Integer.parseInt(arr[2]);

			map = new int[n][m]; // 밭
			visit = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(visit[i], false);
			}

			// 지렁이 위치
			for (int i = 0; i < k; i++) {
				String jirung_map = br.readLine();
				StringTokenizer str = new StringTokenizer(jirung_map, " ");
				int a = Integer.parseInt(str.nextToken());
				int b = Integer.parseInt(str.nextToken());
				map[b][a] = 1;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && visit[i][j]!= true  ) {
						dfs(i, j);	//배추수확(인근배추 제거) ->상하좌우 인접 방문
						count++;
					}
				}
			}
			System.out.println(count);
			count=0;
			t--;
		}
	}

	// 깊이탐색
	private static void dfs(int x, int y) {
		if (visit[x][y]==true)
			return;

		visit[x][y] = true;
		
		for(int i=0;i<4;i++) {
			int nextX = x+dx[i];
			int nextY = y+dy[i];
			
			//밭을 벗어나면 패스
			if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                continue;
            }
			
			if(map[nextX][nextY]==1)
				dfs(nextX,nextY);
		}
		
	}

}
