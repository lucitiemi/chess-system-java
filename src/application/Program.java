package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		// para rodar o programa colorido, abrir o git bash na pasta bin do projeto e no terminal digitar:  java application/Program
		
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			} 
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();							// para que o programa aguarde até o usuario der enter
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();							// para que o programa aguarde até o usuario der enter
			}
			
			
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		
	}

}
