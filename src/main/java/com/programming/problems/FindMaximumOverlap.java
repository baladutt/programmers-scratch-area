package com.programming.problems;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
/**
 * There are X-axis parallel line segments. Start and end points of these segments are given.
 * One has to find the maximum overlap between two parallel lines.
 * 
 * Obviously y coordinate values are useless.
 * 
 * @author bdutt
 *
 */
class Line {
	int start;
	int end;

	public Line(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class FindMaximumOverlap {

	public int findMaxOverlap(Vector<Line> lines) {
		Collections.sort(lines, new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				return o1.start - o2.start;
			}
		});

		int maxOverlap = 0;
		Vector<Line> unendedLinesAbove = new Vector<Line>();

		return findMaxOverlap(lines, 0, unendedLinesAbove, maxOverlap);
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}

	private int min(int a, int b) {
		return a < b ? a : b;
	}

	private int findMaxOverlap(Vector<Line> lines, int index,
			Vector<Line> unendedLinesAbove, int maxOverlap) {
		Line line = lines.get(index);

		if (line.end - line.start > maxOverlap) {

			// Get current line overlap with all of the above lines
			for (int i = 0; i < unendedLinesAbove.size(); i++) {
				Line aboveLine = unendedLinesAbove.get(i);
				if (line.start >= aboveLine.end) {
					unendedLinesAbove.remove(i--);
					continue;
				}
				maxOverlap = max(maxOverlap, min(aboveLine.end, line.end)
						- line.start);
			}
			unendedLinesAbove.add(line);
		}
		if (++index < lines.size())
			maxOverlap = findMaxOverlap(lines, index, unendedLinesAbove,
					maxOverlap);

		return maxOverlap;
	}

	public static void main(String[] args) {
		Vector<Line> lines = new Vector<Line>();
		lines.add(new Line(0, 10));
		lines.add(new Line(5, 20));
		lines.add(new Line(7, 10));
		lines.add(new Line(8, 14));
		lines.add(new Line(10, 20));
		System.out.println(""+(new FindMaximumOverlap()).findMaxOverlap(lines));

	}

}
