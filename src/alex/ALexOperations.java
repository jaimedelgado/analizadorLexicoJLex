package alex;

public class ALexOperations {
  public AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public void error() {
	    System.err.println("***"+alex.fila()+" Caracter inexperado: "+ "\"" + (int)alex.lexema().toCharArray()[0] + "\"");
  }
  public UnidadLexica unidadId() {
	    return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.NOMBRE,alex.lexema());     
  }  
   

	public UnidadLexica unidadMod() {
	   return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MOD);    
  }
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIV);    
	}
	public UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.POR);    
	}
	public UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOS);    
	}
	public UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL);    
	}
	public UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAS);    
	}
	public UnidadLexica unidadApostrofe() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.APOSTROFE);    
	}
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.MAYORIGUAL, alex.lexema());    
	}
	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCIERRE);    
	}
	public UnidadLexica unidadPap() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PAP);    
	}
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.MENORIGUAL, alex.lexema());    
	}
	public UnidadLexica unidadCCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CCIERRE);    
	}
	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PUNTO);    
	}
	public UnidadLexica unidadCap() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CAP);    
	}
	public UnidadLexica unidadAnd() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.AND, alex.lexema());    
	}
	public UnidadLexica unidadPycoma() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PYCOMA);    
	}
	public UnidadLexica unidadSep() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SEPARADOR);    
	}
	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.COMA);    
	}
	public UnidadLexica unidadOr() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.OR, alex.lexema());    
	}
	public UnidadLexica unidadReferencia() {
	   return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.REFERENCIA);     
  }
	public UnidadLexica unidadNumReal() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.NUMREAL, alex.lexema());     
	}
	public UnidadLexica unidadNumEntero() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.NUMENTERO, alex.lexema());     
	}
	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOR);     
	}
	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYOR);     
	}
	public UnidadLexica unidadAsig() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.ASIG);     
	}
	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NOT);     
	}
	public UnidadLexica unidadCapertura() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CAP);    
	}
	public UnidadLexica unidadCcierre() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CCIERRE);    
	}
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF);    
	}
	public UnidadLexica unidadPcierre() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCIERRE);    
	}
	public UnidadLexica unidadIn() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.IN, alex.lexema());  
	}
	public UnidadLexica unidadIf() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.IF, alex.lexema());  
	}
	public UnidadLexica unidadDo() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.DO, alex.lexema());  
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.DISTINTO, alex.lexema());  
	}
	public UnidadLexica unidadInt() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.INT, alex.lexema());  
	}
	public UnidadLexica unidadOut() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.OUT, alex.lexema());  
	}
	public UnidadLexica unidadRec() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.REC, alex.lexema());  
	}
	public UnidadLexica unidadEnd() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.END, alex.lexema());  
	}
	public UnidadLexica unidadFun() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.FUN, alex.lexema());  
	}
	public UnidadLexica unidadTipo() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.TIPO, alex.lexema());  
	}
	public UnidadLexica unidadThis() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.THIS, alex.lexema());  
	}
	public UnidadLexica unidadThen() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.THEN, alex.lexema());  
	}
	public UnidadLexica unidadNull() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.NULL, alex.lexema());  
	}
	public UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.REAL, alex.lexema());  
	}
	public UnidadLexica unidadElse() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ELSE, alex.lexema());  
	}
	public UnidadLexica unidadFree() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.FREE, alex.lexema());  
	}
	public UnidadLexica unidadEndif() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENDIF, alex.lexema());  
	}
	public UnidadLexica unidadElsif() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ELSIF, alex.lexema());  
	}
	public UnidadLexica unidadAlloc() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ALLOC, alex.lexema());  
	}
	public UnidadLexica unidadSuper() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.SUPER, alex.lexema());  
	}
	public UnidadLexica unidadWhile() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.WHILE, alex.lexema());  
	}
	public UnidadLexica unidadCastInt() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.CASTINT, alex.lexema());  
	}
	public UnidadLexica unidadObject() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.OBJECT, alex.lexema());  
	}
	public UnidadLexica unidadReturn() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.RETURN, alex.lexema());  
	}
	public UnidadLexica unidadEndrec() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENDREC, alex.lexema());  
	}
	public UnidadLexica unidadMethod() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.METHOD, alex.lexema());  
	}
	public UnidadLexica unidadCastReal() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.CASTREAL, alex.lexema());  
	}
	public UnidadLexica unidadPointer() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.POINTER, alex.lexema());  
	}
	public UnidadLexica unidadReturns() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.RETURNS, alex.lexema());  
	}
	public UnidadLexica unidadExtends() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.EXTENDS, alex.lexema());  
	}
	public UnidadLexica unidadEndwhile() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENDWHILE, alex.lexema());  
	}
	public UnidadLexica unidadEndobject() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENDOBJECT, alex.lexema());  
	}
}
