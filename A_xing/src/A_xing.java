import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class A_xing {

	public final static int[] dx = { 0, -1, 0, 1, -1, -1, 1, 1 };
	public final static int[] dy = { -1, 0, 1, 0, 1, -1, -1, 1 };
	
	final static public int[][] map = {
	       { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	       { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } 
	};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point start = new Point(1,1);
		Point end = new Point(6,6);
		
		Stack<Point> stack =printPath(start,end);
		if(null==stack){
			System.out.println("can not go to the destination!");
		}
		else {
			while(!stack.isEmpty()){
//				System.out.println(stack.pop()+"->");
				Point temp =stack.pop();
				map[temp.x][temp.y]=7;
			}
			for(int i=0;i<15;i++){
				for(int k=0;k<15;k++){
					if(map[i][k]==0)System.out.print(" "+" ");
					else
					System.out.print(""+map[i][k]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}
	public static Stack<Point> printPath(Point start,Point end){
		ArrayList<Point> openTable = new ArrayList<Point>();
		ArrayList<Point> closeTable = new ArrayList<Point>();
		openTable.clear();
		closeTable.clear();
		Stack<Point> pathStack = new Stack<Point>();
		start.parent = null;
		
		Point currenPoint = new Point(start.x,start.y);
		boolean flag =true;
		
		while(flag){
			for(int i=0;i<8;i++){
				int fx = currenPoint.x+dx[i];
				int fy = currenPoint.y+dy[i];
				Point tempPoint = new Point(fx,fy);
				if(map[fx][fy]==1){
					continue;
				}else{
					if(end.equals(tempPoint)){
						flag=false;
						end.parent = currenPoint;
						break;
				}
				if(i<4){
					tempPoint.G = currenPoint.G +10;
				}else{
					tempPoint.G = currenPoint.G +14;//the tempPoint to the current's dis
				}
				tempPoint.H = Point.getDis(tempPoint, end);//the dis of curren to end
				tempPoint.F = tempPoint.G +tempPoint.H;
				if(openTable.contains(tempPoint)){//
					int pos = openTable.indexOf(tempPoint);
					Point temp = openTable.get(pos);
					if(temp.F>tempPoint.F){
						openTable.remove(pos);
						openTable.add(tempPoint);
						tempPoint.parent = currenPoint;
					}
				}
				else if(closeTable.contains(tempPoint)){
					int pos = closeTable.indexOf(tempPoint);
					Point temp = closeTable.get(pos);
					if(temp.F>tempPoint.F){
						closeTable.remove(pos);
						openTable.add(tempPoint);
						tempPoint.parent = currenPoint;
					}
				}
				else{
					openTable.add(tempPoint);
					tempPoint.parent = currenPoint;
				}
				}
			}
			if(openTable.isEmpty()){
				return null;
			}
			if(false == flag){
				break;
			}
			openTable.remove(currenPoint);
			closeTable.add(currenPoint);
			Collections.sort(openTable);
			currenPoint = openTable.get(0);
		}
		Point node = end;
		while (node.parent!=null) {
			pathStack.push(node);
			node = node.parent;			
		}
		
		return pathStack;
		
	}
}
