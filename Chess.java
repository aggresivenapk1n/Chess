//Lang's version of Jason's Chess
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Chess extends JApplet {

 int[][] board = new int[8][8];
 private int selectedPiece = -1;
 private int startX,startY = 0;
 private boolean whitesTurn = true;
 ArrayList<String> legalMoves = new ArrayList<String>();
 

    //method that is auto called
    public void init() {


     addMouseListener(new MAdapter());

        setSize(800,800);
        
        
        //Initialize board
        initBoard();
        
        for(int j = 0; j < 8; j++) {
         for(int k = 0; k < 8; k++) {
          System.out.print(board[j][k] +", ");
         }
         System.out.println();
        }
  


    }
    
    private void initBoard() {
        //add pawns PAWNS = 1
        for(int i =0; i < 8; i++) {
         board[1][i] = 1;
         board[6][i] = -1;
        }
        
        //Add Knights KNIGHTS = 2;
        board[0][1] = board[0][6] = 2;
        board[7][1] = board[7][6] = -2;
        
        //Add Bishops BISHOPS = 3;
        board[0][2] = board[0][5] = 3;
        board[7][2] = board[7][5] = -3;
        
        //Add Rooks, ROOKS = 4;
        board[0][0] = board[0][7] = 4;
        board[7][0] = board[7][7] = -4;
        
        //Add Queens, QUEENS = 5;
        board[0][4] =5;
        board[7][4] =-5;
        
        //Add Kings, KINGS = 6;
        board[0][3] = 6;
        board[7][3] = -6;
    }

        public void paint(Graphics g) {
        //clear old stuff

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 800, 800);

        g.setColor(Color.BLACK);
        for(int j = 0; j < 8; j++) {
        	for(int i = j % 2; i <= 7; i+= 2 ) {
        		
        		g.setColor(Color.lightGray);
        		g.fillRect(35*i + 50, 35*j+50, 35,35);
        		
        	}
        }


        for(int i =0;i<9;i++) {
        	g.setColor(Color.BLACK);
            g.drawLine((35*i)+50, 50, (35*i)+50, 330);
            g.drawLine(50, (35*i)+50, 330, (35*i)+50);
            
        }
        for(int j = 0; j < 8; j++) {
        	for(int k = 0; k < 8; k++) {
        		
        		g.setColor(Color.BLACK);
        		
        		if(board[j][k] == 1) {
        			g.setColor(Color.WHITE);
        			g.fillRect(35*k + 55, 35*j+55, 10,10);
        		}
        		
        		if(board[j][k] == 2) {
        			g.setColor(Color.WHITE);
        			g.fillRect(35*k + 55, 35*j+55, 10,20);
        		}
        		
        		if(board[j][k] == 3) {
        			g.setColor(Color.WHITE);
        			g.fillOval(35*k + 55, 35*j+55, 15, 15);
        		}
        		
        		if(board[j][k] == 4) {
        			g.setColor(Color.WHITE);
        			g.fillRect(35*k + 55, 35*j+55, 15,15);
        		}
        		
        		if(board[j][k] == 5) {
        			g.setColor(Color.WHITE);
        			
        			g.fillRoundRect(35*k + 55, 35*j+55, 20,20, 10, 10);
        		}
        		
        		if(board[j][k] == 6) {
        			g.setColor(Color.WHITE);
        			g.fillOval(35*k + 55, 35*j+55, 25, 25);
        		}
        		//end white drawing
        		
        		if(board[j][k] == -1) {
        			g.setColor(Color.BLACK);
        			g.fillRect(35*k + 55, 35*j+55, 10,10);
        		}
        		
        		if(board[j][k] == -2) {
        			g.setColor(Color.BLACK);
//        			int[] knightY = {35*k + 55,35*k + 55+10,35*k + 55};
//        			int[] knightX = {35*k + 55,35*k + 55+10,35*k + 55+20};
//        			g.drawPolygon(knightX,knightY, 3);
        			g.fillRect(35*k + 55, 35*j+55, 10,20);
        		}
        		
        		if(board[j][k] == -3) {
        			g.setColor(Color.BLACK);
        			g.fillOval(35*k + 55, 35*j+55, 15, 15);
        			
        		}
        		
        		if(board[j][k] == -4) {
        			g.setColor(Color.BLACK);
        			g.fillRect(35*k + 55, 35*j+55, 15,15);
        		}
        		
        		if(board[j][k] == -5) {
        			g.setColor(Color.BLACK);
        			g.fillRoundRect(35*k + 55, 35*j+55, 20,20, 10, 10);
        		}
        		
        		if(board[j][k] == -6) {
        			g.setColor(Color.BLACK);
        			g.fillOval(35*k + 55, 35*j+55, 25, 25);
        		}
        		//end black drawing
        	}
        }

    }


  private class MAdapter implements MouseListener{



 public void mouseClicked(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }


 public void mouseEntered(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }


 public void mouseExited(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }


 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub
  //System.out.println("X position = " +e.getX()  + "\t Y position = " + e.getY());
	 int x = -1;
	  int y = -1;
	  String space ="";
	  selectedPiece =-5;
	  boolean bothlegal = false;
	  if(e.getX() > 50 && e.getX() <=85) {
		   space +=("A");
		   bothlegal = true;
		   x=0;
	  }else if(e.getX() > 85 && e.getX() <=120) {
		   space +=("B");
		   bothlegal = true;
		   x=1;
	  }else if(e.getX() > 120 && e.getX() <=155) {
		   space +=("C");
		   bothlegal = true;
		   x=2;
	  }else if(e.getX() > 155 && e.getX() <= 190) {
		   space +=("D");
		   bothlegal = true;
		   x=3;
	  }else if(e.getX() > 190 && e.getX() <= 225) {
		   space +=("E");
		   bothlegal = true;
		   x=4;
	  }else if(e.getX() > 225 && e.getX() <= 260) {
		   space +=("F");
		   bothlegal = true;
		   x=5;
	  }else if(e.getX() > 260 && e.getX() <= 295) {
		   space +=("G");
		   bothlegal = true;
		   x=6;
	  }else if(e.getX() > 295 && e.getX() <= 330) {
		   space +=("H");
		   bothlegal = true;
		   x=7;
	  }else {
	   bothlegal = false;
	  }
	  if(bothlegal) {
	   
	  
	  if(e.getY() > 50 && e.getY() <=85) {
		   space +=("1");
		   bothlegal = true;
		   y=0;
	  }else if(e.getY() > 85 && e.getY() <=120) {
		   space +=("2");
		   bothlegal = true;
		   y=1;
	  }else if(e.getY() > 120 && e.getY() <=155) {
		   space +=("3");
		   bothlegal = true;
		   y=2;
	  }else if(e.getY() > 155 && e.getY() <= 190) {
		   space +=("4");
		   bothlegal = true;
		   y=3;
	  }else if(e.getY() > 190 && e.getY() <= 225) {
		   space +=("5");
		   bothlegal = true;
		   y=4;
	  }else if(e.getY() > 225 && e.getY() <= 260) {
		   space +=("6");
		   bothlegal = true;
		   y=5;
	  }else if(e.getY() > 260 && e.getY() <= 295) {
		   space +=("7");
		   bothlegal = true;
		   y=6;
	  }else if(e.getY() > 295 && e.getY() <= 330) {
		   space +=("8");
		   bothlegal = true;
		   y=7;
	  }else {
	   bothlegal = false;
	  }
	  
  }
	  
	  startX = x;
	  startY = y;
  if(bothlegal)
  System.out.println(space);
  System.out.println(board[y][x]);
  System.out.println("is it whites turn? "+ whitesTurn);
  
  if(whitesTurn&&board[startY][startX] >= 0){
	  
  }
  if(board[y][x] == -1 ) { 
	  //System.out.println("Picked a -Pawn");
	  selectedPiece = -1;
  }else if(board[y][x] == 1) {
	  //System.out.println("Picked a Pawn");
	  selectedPiece =  1;
	  
	  //rooks
  }else if(board[y][x] == 4){
	  selectedPiece = 4;
	  System.out.println("Rook@");

	  int newX = startX;
	  int newY = startY+1;
	  while(newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
	  }
	  if(newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX;
	  newY = startY-1;
	  while(newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
	  }
	  if(newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX+1;
	  newY = startY;
	  while(newX<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX++;
	  }
	  if(newX<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX-1;
	  newY = startY;
	  while(newX>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX--;
	  }
	  if(newX>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
  }//End Pos Rook
  else if(board[y][x] == -4){
	  selectedPiece = -4;
	  System.out.println("-Rook");

	  int newX = startX;
	  int newY = startY-1;
	  while(newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
	  }
	  if(newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX;
	  newY = startY-1;
	  while(newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
	  }
	  if(newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX+1;
	  newY = startY;
	  while(newX<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX++;
	  }
	  if(newX<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX-1;
	  newY = startY;
	  while(newX>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX--;
	  }
	  if(newX>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
  }//End -Rooks
  else if(board[y][x]==3){
	  selectedPiece = 3;
	  
	  int newX = startX+1;
	  int newY = startY+1;
	  
	  while(newX<=7&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX++;
	  }
	  if(newX<=7&&newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY-1;
	  
	  while(newX>=0&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX--;
	  }
	  if(newX>=0&&newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX+1;
	  newY = startY-1;
	  
	  while(newX<=7&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX++;
	  }
	  if(newX<=7&&newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY+1;
	  
	  while(newX>=0&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX--;
	  }
	  if(newX>=0&&newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
  }//end white bishop
  else if(board[y][x]==-3){
	  selectedPiece = -3;
	  
	  int newX = startX+1;
	  int newY = startY+1;
	  
	  while(newX<=7&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX++;
	  }
	  if(newX<=7&&newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY-1;
	  
	  while(newX>=0&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX--;
	  }
	  if(newX>=0&&newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX+1;
	  newY = startY-1;
	  
	  while(newX<=7&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX++;
	  }
	  if(newX<=7&&newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY+1;
	  
	  while(newX>=0&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX--;
	  }
	  if(newX>=0&&newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
  }//end black bishops

  else if(board[y][x]==-5){
	  selectedPiece = -5;
	  
	  int newX = startX+1;
	  int newY = startY+1;
	  
	  while(newX<=7&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX++;
	  }
	  if(newX<=7&&newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY-1;
	  
	  while(newX>=0&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX--;
	  }
	  if(newX>=0&&newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX+1;
	  newY = startY-1;
	  
	  while(newX<=7&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX++;
	  }
	  if(newX<=7&&newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY+1;
	  
	  while(newX>=0&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX--;
	  }
	  if(newX>=0&&newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX;
	  newY = startY-1;
	  while(newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
	  }
	  if(newY<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX;
	  newY = startY-1;
	  while(newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
	  }
	  if(newY>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX+1;
	  newY = startY;
	  while(newX<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX++;
	  }
	  if(newX<=7&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX-1;
	  newY = startY;
	  while(newX>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX--;
	  }
	  if(newX>=0&&board[newY][newX]>0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
  }//end black queen
  else if(board[y][x]==5){
	  selectedPiece = 5;
	  int newX = startX+1;
	  int newY = startY+1;
	  
	  while(newX<=7&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX++;
	  }
	  if(newX<=7&&newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY-1;
	  
	  while(newX>=0&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX--;
	  }
	  if(newX>=0&&newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX+1;
	  newY = startY-1;
	  
	  while(newX<=7&&newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
		  newX++;
	  }
	  if(newX<=7&&newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX-1;
	  newY = startY+1;
	  
	  while(newX>=0&&newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
		  newX--;
	  }
	  if(newX>=0&&newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX;
	  newY = startY+1;
	  while(newY<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY++;
	  }
	  if(newY<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX;
	  newY = startY-1;
	  while(newY>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newY--;
	  }
	  if(newY>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX+1;
	  newY = startY;
	  while(newX<=7&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX++;
	  }
	  if(newX<=7&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
	  newX = startX-1;
	  newY = startY;
	  while(newX>=0&&board[newY][newX]==0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
		  
		  newX--;
	  }
	  if(newX>=0&&board[newY][newX]<0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("2Legal move: ("+ newY + ","+newX+")");
	  }
	  
  }//end white queen
  else if(board[y][x]==6){
	  selectedPiece = 6;
	  int newX = startX+1;
	  int newY = startY;
	  
	  if(board[newY][newX] <= 0&&newX>=0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX - 1;
	  newY = startY;
	  
	  if(board[newY][newX] <=0&& newX>= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX;
	  newY = startY+1;
	  
	  if(board[newY][newX] <= 0&&newY>=0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX;
	  newY = startY-1;
	  
	  if(newY>= 0&&board[newY][newX] <=0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX + 1;
	  newY = startY + 1;
	  if(newY>=0 && newX>=0&& board[newY][newX] <= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");

	  }
	  newX = startX -1;
	  newY = startY -1;
	  if(newY>=0 && newX>=0&& board[newY][newX] <= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX - 1;
	  newY = startY + 1;
	  if(newY>=0 && newX>=0&& board[newY][newX] <= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");

	  }
	  newX = startX +1;
	  newY = startY -1;
	  if(newY>=0 && newX>=0&& board[newY][newX] <= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  
  }//end white king
  else if(board[y][x]==-6){
	  selectedPiece = -6;
	  
	  //left and right
	  int newX = startX+1;
	  int newY = startY;
	  
	  if(board[newY][newX] >= 0&&newX<=7){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX - 1;
	  newY = startY;
	  
	  if(board[newY][newX] >=0&& newX<= 7){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  //up and down
	  newX = startX;
	  newY = startY+1;
	  
	  if(newY<=7&&board[newY][newX] >= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX;
	  newY = startY-1;
	  
	  if(newY<= 7&&board[newY][newX] >=0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  //diagonals
	  newX = startX + 1;
	  newY = startY + 1;
	  if(newY<=7 && newX<=7&& board[newY][newX] >= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");

	  }
	  newX = startX -1;
	  newY = startY -1;
	  if(newY<=7 && newX<=7&& board[newY][newX] >= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  newX = startX - 1;
	  newY = startY + 1;
	  if(newY<=7 && newX<=7&& board[newY][newX] >= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");

	  }
	  newX = startX +1;
	  newY = startY -1;
	  if(newY<=7 && newX<=7&& board[newY][newX] >= 0){
		  legalMoves.add(newY + " " + newX);
		  System.out.println("Legal move: ("+ newY + ","+newX+")");
	  }
	  
  }//end black king
	  
  
  
  
 }

 
 public void mouseReleased(MouseEvent e) {
  boolean bothlegal = false;
  String space = "";
  int x = -1;
  int y = -1;
  if(e.getX() > 50 && e.getX() <=85) {
   space +=("A");
   bothlegal = true;
   x=0;
  }
  else if(e.getX() > 85 && e.getX() <=120) {
   space +=("B");
   bothlegal = true;
   x=1;
  }
  else if(e.getX() > 120 && e.getX() <=155) {
   space +=("C");
   bothlegal = true;
   x=2;
  }
  else if(e.getX() > 155 && e.getX() <= 190) {
   space +=("D");
   bothlegal = true;
   x=3;
  }
  else if(e.getX() > 190 && e.getX() <= 225) {
   space +=("E");
   bothlegal = true;
   x=4;
  }
  else if(e.getX() > 225 && e.getX() <= 260) {
   space +=("F");
   bothlegal = true;
   x=5;
  }
  else if(e.getX() > 260 && e.getX() <= 295) {
   space +=("G");
   bothlegal = true;
   x=6;
  }
  else if(e.getX() > 295 && e.getX() <= 330) {
   space +=("H");
   bothlegal = true;
   x=7;
  }
  else {
   bothlegal = false;
  }
  if(bothlegal) {
   
  
  if(e.getY() > 50 && e.getY() <=85) {
   space +=("1");
   bothlegal = true;
   y=0;
  }
  else if(e.getY() > 85 && e.getY() <=120) {
   space +=("2");
   bothlegal = true;
   y=1;
  }
  else if(e.getY() > 120 && e.getY() <=155) {
   space +=("3");
   bothlegal = true;
   y=2;
  }
  else if(e.getY() > 155 && e.getY() <= 190) {
   space +=("4");
   bothlegal = true;
   y=3;
  }
  else if(e.getY() > 190 && e.getY() <= 225) {
   space +=("5");
   bothlegal = true;
   y=4;
  }
  else if(e.getY() > 225 && e.getY() <= 260) {
   space +=("6");
   bothlegal = true;
   y=5;
  }
  else if(e.getY() > 260 && e.getY() <= 295) {
   space +=("7");
   bothlegal = true;
   y=6;
  }
  else if(e.getY() > 295 && e.getY() <= 330) {
   space +=("8");
   bothlegal = true;
   y=7;
  }
  else {
   
  }
  
  }
  if(bothlegal) {
	  
	  //System.out.println(space);
	  //System.out.println(board[y][x]);
	  
	  //WhitePawn
	  if(board[startY][startX]== 1){
	  		//Pawn First Move
	  		boolean firstMove = false;
	  		if(startY == 6||startY == 1)
	  			firstMove = true;
	  	  
	  		if(y==startY+1&&x==startX){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else if(board[y][x]<0&&((y==startY+1&&x==startX+1)||y==startY+1&&x==startX-1)){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else if(firstMove == true&&y==startY+2&&x==startX){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else{
	  			board[startY][startX]=selectedPiece;
	  		}
	  		firstMove = false;
	  		System.out.println(firstMove);
	  	}
	  
	  	//black pawn if statements\
	    //First Pawn move
	  if(board[startY][startX]== -1){
	  		//Pawn First Move
	  		boolean firstMove = false;
	  		if(startY == 6||startY == 1)
	  			firstMove = true;
	  	  
	  		if(y==startY-1&&x==startX){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else if(board[y][x]>0&&((y==startY-1&&x==startX+1)||y==startY-1&&x==startX-1)){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else if(firstMove == true&&y==startY-2&&x==startX){
	  			board[y][x] = selectedPiece;
	  			board[startY][startX]=0;
	  		}else{
	  			board[startY][startX]=selectedPiece;
	  		}
	  		firstMove = false;
	  		System.out.println(firstMove);
	  }
	//black knight
		if(board[startY][startX]==-2&&!(board[y][x]<0)){
			if((y==startY-2||y==startY+2)&&(x==startX+1||x==startX-1)){
	  			board[y][x] = -2;
	  			board[startY][startX]=0;
			}
			else if((x==startX-2||x==startX+2)&&(y==startY+1||y==startY-1)){
				board[y][x] = -2;
	  			board[startY][startX]=0;}
			}else{
				board[startY][startX]=board[startY][startX];
			}
	//white knights association of americuh
		if(board[startY][startX]==2&&!(board[y][x]>0)){
			if((y==startY-2||y==startY+2)&&(x==startX+1||x==startX-1)){
	  			board[y][x] = 2;
	  			board[startY][startX]=0;
			}
			else if((x==startX-2||x==startX+2)&&(y==startY+1||y==startY-1)){
				board[y][x] = 2;
	  			board[startY][startX]=0;}
			}else{
				board[startY][startX]=board[startY][startX];
			}
	  System.out.println("Legal Moves String Size " + legalMoves.size());
	  	for(int i = 0;i<legalMoves.size();i++){
	  		System.out.println("Looking For (" + y+ "," +x+") Found: "+ legalMoves.get(i));
	  		String[] strA = legalMoves.get(i).split(" ");
	  		int newY = Integer.parseInt(strA[0]);
	  		int newX = Integer.parseInt(strA[1]);
	  		if(newX==x&&newY==y){
	  			board[y][x]= selectedPiece;
	  			board[startY][startX]=0;
	  			break;
	  			
	  		}
	  	}
	  	
	  	
		}
  		
	  	
	  	//print board
  System.out.println("------------");
	  	for(int j = 0; j < 8; j++) {
	  		for(int k = 0; k < 8; k++) {
	  			System.out.print(board[j][k] +", ");
	  		}
	  		System.out.println();
	  	}

	  	System.out.println("------------");
	  	legalMoves.clear();
	  	if (!whitesTurn){
	  		whitesTurn = true;
	  	} else{
	  		whitesTurn = false;
	  	}
	  	
   
   repaint();
  	}//End Mouse Released
}
}






