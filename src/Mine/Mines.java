package Mine;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import View.MainF;

public class Mines {
	public static int WIN = 1;
	public static int PLAYING = 0;
	public static int LOST = -1;
	public static int BLANK = 0;
	public static int OPENED = 1;
	public static int FLAG = 2;
	public static int WEN = 3;
	public static int BONG = 4;
	public static int NOTFOUND = 5;
	public static int FOUNDERR = 6;
	public static int LEI = -1;
	public static boolean hole=false;
	public static int grade = 0;
	int count;
	private int clickTime;
	private int mine[][];
	private int mask[][];
	int x;
	int y;
	private int left, ltag;
	public int win;
	public static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private ArrayList<Integer> changes = new ArrayList<Integer>();

	public int isWin() {
		return win;
	}

	public boolean isBound(int xx, int yy) {
		return xx >= 0 && xx < x && yy >= 0 && yy < y;
	}

	public Mines(int x, int y, int count) {
		// TODO Auto-generated method stub
		reset(x, y, count);
	}

	public Mines() {
		// TODO Auto-generated constructor stub
		this(9, 9, 10);
	}

	public void reset(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
		setClickTime(0);
		ltag = count;
		left = x * y - count;
		setMine(new int[x][y]);
		setMask(new int[x][y]);
		win = PLAYING;
		recover();
	}

	public void addMine(int n) {
		count = n;
		for (int i = 0; i < getMine().length; i++) {
			for (int j = 0; j < getMine()[0].length; j++) {
				if (getMine()[i][j] != -2) {
					getMine()[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			int rx = (int) (Math.random() * x);
			int ry = (int) (Math.random() * y);
			if (getMine()[rx][ry] == 0) {
				getMine()[rx][ry] = LEI;
			} else {
				i--;
			}
		}
		for (int i = 0; i < getMine().length; i++) {
			for (int j = 0; j < getMine()[0].length; j++) {
				if (getMine()[i][j] != LEI) {
					int nn = 0;
					for (int k = 0; k < 8; k++) {
						if (isBound(i + dx[k], j + dy[k])
								&& getMine()[i + dx[k]][j + dy[k]] == LEI) {
							nn++;
						}
					}
					getMine()[i][j] = nn;
				}
			}
		}
		print();
	}

	public void doFlag(int xx, int yy) {
		if (isBound(xx, yy) && getMask()[xx][yy] == 0) {
			getMask()[xx][yy] = this.FLAG;
			ltag--;
		} else if (isBound(xx, yy) && getMask()[xx][yy] == this.FLAG) {
			getMask()[xx][yy] = this.WEN;
			ltag++;
		} else if (isBound(xx, yy) && getMask()[xx][yy] == this.WEN) {
			getMask()[xx][yy] = this.BLANK;
		}
	}

	public void resetChange() {
		getChanges().clear();
	}

	public void doTag(int xx, int yy) {
		if (isBound(xx, yy)
				&& (getMask()[xx][yy] == 0 || getMask()[xx][yy] == this.WEN)) {
			getMask()[xx][yy] = this.OPENED;
			left--;
			getChanges().add(xx);
			getChanges().add(yy);
			if (left == 0) {
				win = WIN;
			}
		}
	}

	public int doIt(int xx, int yy) {
		if (!(isBound(xx, yy) && (getMask()[xx][yy] == 0 || getMask()[xx][yy] == this.WEN))) {
			return win;
		}
		if (getClickTime() == 0) {
			getMine()[xx][yy] = -2;
			addMine(count);
		}
		setClickTime(getClickTime() + 1);
		if (getMine()[xx][yy] == LEI) {
			doTag(xx, yy);
			win = LOST;
			return win;
		}
		if (getMine()[xx][yy] != 0) {
			doTag(xx, yy);
			return win;
		}
		doTag(xx, yy);
		for (int k = 0; k < 8; k++) {
			if (isBound(xx + dx[k], yy + dy[k])) {
				doIt(xx + dx[k], yy + dy[k]);
			}
		}
		return win;
	}

	public int open(int xx, int yy) {
		if (isBound(xx, yy) && getMask()[xx][yy] != this.OPENED) {
			return win;
		}
		int sum = 0;
		for (int i = 0; i < dx.length; i++) {
			int x = xx + dx[i], y = yy + dy[i];
			if (isBound(x, y) && getMask()[x][y] == this.FLAG) {
				sum++;
			}
		}
		if (sum != getMine()[xx][yy]) {
			return win;
		}
		for (int i = 0; i < dx.length; i++) {
			int x = xx + dx[i];
			int y = yy + dy[i];
			if (isBound(x, y)
					&& (getMask()[x][y] == this.BLANK || getMask()[x][y] == this.WEN)) {
				doIt(x, y);
			}
		}
		return win;
	}

	public void recover() {
		for (int i = 0; i < getMask().length; i++) {
			for (int j = 0; j < getMask()[0].length; j++) {
				getMask()[i][j] = 0;
			}
		}
	}

	void print() {
		for (int i = 0; i < getMine().length; i++) {
			for (int j = 0; j < getMine()[0].length; j++) {
				System.out.print(getMine()[i][j] + "\t");
			}
			System.out.println();
		}
	}

	@SuppressWarnings("unchecked")
	public void over() {
		if (win == WIN) {
			for (int i = 0; i < getMine().length; i++) {
				for (int j = 0; j < getMine()[0].length; j++) {
					if (getMine()[i][j] == LEI) {
						getMask()[i][j] = this.FLAG;
					}
				}
			}
		} else if (win == LOST) {
			for (int i = 0; i < getMine().length; i++) {
				for (int j = 0; j < getMine()[0].length; j++) {
					if (getMine()[i][j] == LEI
							&& (getMask()[i][j] == this.BLANK || getMask()[i][j] == this.WEN)) {
						getMask()[i][j] = this.NOTFOUND;
					} else if (getMine()[i][j] == LEI
							&& getMask()[i][j] == this.OPENED) {
						getMask()[i][j] = this.BONG;
					} else if (getMine()[i][j] != LEI
							&& getMask()[i][j] == this.FLAG) {
						getMask()[i][j] = this.FOUNDERR;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Mines m = new Mines();
		m.getMine()[3][3] = -2;
		m.addMine(10);
		m.print();
	}

	public void setMine(int mine[][]) {
		this.mine = mine;
	}

	public int[][] getMine() {
		return mine;
	}

	public void setMask(int mask[][]) {
		this.mask = mask;
	}

	public int[][] getMask() {
		return mask;
	}

	public void setChanges(ArrayList<Integer> changes) {
		this.changes = changes;
	}

	public ArrayList<Integer> getChanges() {
		return changes;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getLtag() {
		return ltag;
	}

	public void setLtag(int ltag) {
		this.ltag = ltag;
	}

	public void setClickTime(int clickTime) {
		this.clickTime = clickTime;
	}

	public int getClickTime() {
		return clickTime;
	}
}
