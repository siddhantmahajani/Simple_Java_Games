package com.game;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
	
	static Scanner scanner;
	static String[] board;
	static String turn;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		board = new String[9];
		turn = "X";
		String winner = null;
		populateEmptyBoard();
		System.out.println("Welcom to Tic-Tac-Toe");
		System.out.println("----------------------");
		printBoard();
		while (winner == null) {
			System.out.println("Enter a slot number to enter " + turn);
			int numInput;
			try {
				numInput = scanner.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input. Please enter a valid input");
					continue;
				}
			} catch (Exception ex) {
				System.out.println("Invalid input. Please enter a valid input.");
				continue;
			}
			
			if (board[numInput - 1].equalsIgnoreCase(String.valueOf(numInput))) {
				board[numInput - 1] = turn;
				if ("X".equals(turn)) {
					turn = "O";
				} else {
					turn = "X";
				}
				printBoard();
				winner = checkWinner();
			} else {
				System.out.println("Slot already taken. Please select a different slot");
				continue;
			}
		}
		
		if ("Draw".equalsIgnoreCase(winner)) {
			System.out.println("It's a draw.");
		} else {
			System.out.println(winner + "'s has won.");
		}
	}
	
	private static String checkWinner() {
		for (int i = 0; i < 8; i++) {
			String line = null;
			switch (i) {
			case 0: 
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			
			if ("XXX".equalsIgnoreCase(line)) {
				return "X";
			} else if ("OOO".equalsIgnoreCase(line)) {
				return "O";
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (Arrays.asList(board).contains(String.valueOf(i + 1))) {
				break;
			} else if (i == 8) {
				return "Draw";
			}
		}
		
		System.out.println(turn + "'s turn, Enter a slot number to place " + turn + "in");
		return null;
	}
	
	private static void printBoard() {
		System.out.println("/---|---|---\\");
		System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
		System.out.println("/---|---|---\\");
	}
	
	private static void populateEmptyBoard() {
		for (int i = 0; i < 9; i++) {
			board[i] = String.valueOf(i + 1);
		}
	}
}
