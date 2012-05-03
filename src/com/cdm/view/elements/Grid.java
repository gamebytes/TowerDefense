package com.cdm.view.elements;

import java.util.Set;
import java.util.TreeSet;

import com.cdm.view.Position;
import com.cdm.view.elements.units.Unit;
import com.cdm.view.enemy.EnemyUnit;

public class Grid {
	public enum CellType {
		FREE, BLOCK, EMPTY
	};

	public class GridElement {

		private Set<Element> e;
		private int distToEnd;
		private int tempValue;
		private int distToUnit;

		private CellType cellType = CellType.FREE;

		public GridElement() {
			e = new TreeSet<Element>();
			distToEnd = -1;
			distToUnit = -1;
		}

		public Set<Element> getList() {
			return e;
		}

		public int getTempValue() {
			return tempValue;
		}

		public void setTempValue(int tempValue) {
			this.tempValue = tempValue;
		}

		public int getDistToEnd() {
			return distToEnd;
		}

		public int getDistToUnit() {
			return distToUnit;
		}

		public void setDistToEnd(int distToEnd) {
			this.distToEnd = distToEnd;
		}

		public CellType getCellType() {
			return cellType;
		}

		public void setCellType(CellType cellType) {
			this.cellType = cellType;
		}

		public boolean isFree() {
			return cellType.equals(CellType.FREE);
		}

		public void setDistToUnit(int distToUnit) {
			this.distToUnit = distToUnit;
		}

		public boolean isPassable() {
			if (e == null)
				return false;
			if (!isFree())
				return false;
			for (Element ce : e) {
				if (!(ce instanceof EnemyUnit))
					return false;
			}
			return true;

		}

		public boolean isEmpty() {
			return e.isEmpty();
		}

		public boolean contains(Unit unit) {
			System.out.println("CONTAINS ?");
			System.out.println(this);
			System.out.println(unit);
			System.out.println(e.contains(unit));
			return e.contains(unit);
		}

		public void remove(Unit unit) {
			System.out.println(this);
			System.out.println(e);
			e.remove(unit);
			System.out.println(e);
		}

		public void add(Unit unit) {
			System.out.println(this);
			System.out.println(e);
			e.add(unit);
			System.out.println(e);
		}

		public EnemyUnit getFirstEnemyUnit() {
			for (Element u : e) {
				if (u instanceof EnemyUnit) {
					return (EnemyUnit) u;
				}
			}
			return null;
		}

		public Unit getFirstUnit() {
			for (Element u : e) {
				if (u instanceof EnemyUnit) {
					return (Unit) u;
				}
			}
			return null;
		}

	}

	private GridElement[] cells;
	private int w, h;
	private int endy;

	public Grid(int pw, int ph, int pendy) {
		w = pw;
		h = ph;
		cells = new GridElement[w * h];
		int x, y;
		for (x = 0; x < w; x++)
			for (y = 0; y < h; y++)
				cells[x + y * w] = new GridElement();
		endy = pendy;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int endY() {
		return endy;
	}

	public GridElement getElement(int x, int y) {
		if (x >= 0 && y >= 0 && x < w && y < h)
			return cells[x + y * w];
		return null;
	}

	/*
	 * public Set<Element> get(int x, int y) { GridElement ge = getElement(x,
	 * y); if (ge != null) return ge.getList(); return null; }
	 */
	public boolean isEndPlace(int x, int y) {
		return (x == w - 1 && y == endy);
	}

	public boolean passable(int x, int y) {
		return getElement(x, y).isPassable();
	}

	public void print() {
		if (true)
			return;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				System.out.print(" " + getElement(x, y).getDistToEnd());
			}
			System.out.println();
		}
		System.out.println();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				System.out.print(" " + getElement(x, y).getDistToUnit());
			}
			System.out.println();
		}

	}

	public GridElement get(Position target) {
		return getElement(Math.round(target.x), Math.round(target.y));
	}
}
