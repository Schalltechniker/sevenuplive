package mtn.sevenuplive.modes;

public class Displays {

	public static GridCoordinateTarget translate(DisplayGrid[] displays, int x, int y) {
		for (DisplayGrid grid: displays) {
			if (grid.hitTest(x, y)) {
				return new GridCoordinateTarget(grid, grid.translateX(x), grid.translateY(y));
			}
		}
		return null;
	}
	
	public static class GridCoordinateTarget {

		public GridCoordinateTarget(DisplayGrid display, int x_translated, int y_translated) {
			this.display = display;
			this.x_translated = x_translated;
			this.y_translated = y_translated;
		}
		
		public DisplayGrid getDisplay() {
			return display;
		}

		public void setDisplay(DisplayGrid display) {
			this.display = display;
		}

		public int getX_translated() {
			return x_translated;
		}

		public void setX_translated(int x_translated) {
			this.x_translated = x_translated;
		}

		public int getY_translated() {
			return y_translated;
		}

		public void setY_translated(int y_translated) {
			this.y_translated = y_translated;
		}

		private DisplayGrid display;
		private int x_translated;
		private int y_translated;
		
	}
}
