public class Sudokulearning {

	
	public static void main(String[] args) {
		int[][] arr = {{3, 0, 6, 5, 0, 8, 4, 0, 0}, 
                {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
                {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
                {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
                {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
                {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};;
        int[] rownos=new int[9];
		int[] colnos=new int[9];
		int[] sbnos=new int[9];
		System.out.println("Checkout the Given Sudoku Matrix Array:");

		for(int a = 0; a<arr.length; a++){
			for (int b=0;b <arr[0].length;b++ ) {
				System.out.print(arr[a][b]+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		System.out.println("Solved Matrix:");
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(arr[i][j]!=0){
					makeAChoice(arr,i*arr.length+j+1,rownos,colnos,sbnos,arr[i][j]);
				}
			}
		}
		
			sudoku(arr,1,rownos,colnos,sbnos);
		
		
	}


	public static void sudoku(int [][] arr,int cellno,int[]rownos,int[]colnos,int[]sbnos){
		if(cellno==82){
			for(int i=0;i<arr.length;i++){
				for(int j=0;j<arr[0].length;j++){
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			return;
		}
		int rno=(cellno-1)/arr.length;
		int cno=(cellno-1)%arr.length;
		
		if(arr[rno][cno]==0){
			int rowno=rownos[rno];
			int colno=colnos[cno];
			int sbno=sbnos[(rno/3)*3+cno/3];
			int no=rowno|colno|sbno;
			
			
			for(int choice=1;choice<=9;choice++){
				if((no&(1<<choice))==0){
					makeAChoice(arr,cellno,rownos,colnos,sbnos,choice);
					sudoku(arr,cellno+1,rownos,colnos,sbnos);
					unmakeAChoice(arr,cellno,rownos,colnos,sbnos,choice);
				}
			}
			
		}
		else{
			sudoku(arr, cellno+1, rownos, colnos, sbnos);
		}
	}
	public static void makeAChoice(int arr[][],int cellno,int[] rownos,int[] colnos,int[]sbnos,int choice){
		int rno=(cellno-1)/arr.length;
		int cno=(cellno-1)%arr.length;
		arr[rno][cno]=choice;
		 rownos[rno]^=(1<<choice);
		 colnos[cno]^=(1<<choice);
		 sbnos[(rno/3)*3+cno/3]^=(1<<choice);
	}
	public static void unmakeAChoice(int arr[][],int cellno,int[] rownos,int[] colnos,int[]sbnos,int choice){
		int rno=(cellno-1)/arr.length;
		int cno=(cellno-1)%arr.length;
		arr[rno][cno]=0;
		 rownos[rno]^=(1<<choice);
		 colnos[cno]^=(1<<choice);
		 sbnos[(rno/3)*3+cno/3]^=(1<<choice);
	}
}
