package pld.gameoflife;

public class BoardTester {

	public static void main(String[] args) throws InterruptedException {
		Board board = Board.gosperGliderGun(80, 40);
		board.addListener(new Board.Listener() {
			public void onBoardUpdated(Board sender) {
				showBoard(sender);
			}
		});
		while (true) {
			board.nextTurn();
			Thread.sleep(200);
		}
	}
	
	private static void showBoard(Board board) {
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				System.out.print(board.isAliveAt(x, y) ? "X" : " ");
			}
			System.out.println();
		}
	}

}
