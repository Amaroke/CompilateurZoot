package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = vrai|faux
idf = [a-zA-Z_][a-zA-Z0-9_]*
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaire = [/]{2}.*{finDeLigne}


%%
"//".*                                    { /* DO NOTHING */ }

"variables"            { return symbol(CodesLexicaux.VARIABLES); }

"fonctions"            { return symbol(CodesLexicaux.FONCTION); }

"debut"                { return symbol(CodesLexicaux.DEBUT); }

"fin"              	   { return symbol(CodesLexicaux.FIN); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"entier"               { return symbol(CodesLexicaux.ENTIER); }

"booleen"              { return symbol(CodesLexicaux.BOOLEEN); }

"non"                  { return symbol(CodesLexicaux.NEGATION); }

"si"                   { return symbol(CodesLexicaux.SI); }

"alors"                { return symbol(CodesLexicaux.ALORS); }

"sinon"                { return symbol(CodesLexicaux.SINON); }

"finsi"                { return symbol(CodesLexicaux.FINSI); }

"repeter"              { return symbol(CodesLexicaux.REPETER); }

"jusqua"              { return symbol(CodesLexicaux.JUSQUA); }

"finrepeter"           { return symbol(CodesLexicaux.FINREPETER); }

"="                    { return symbol(CodesLexicaux.EGAL); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

","                    { return symbol(CodesLexicaux.VIRGULE); }

"("                    { return symbol(CodesLexicaux.PAR_OUVRANTE); }

")"                    { return symbol(CodesLexicaux.PAR_FERMANTE); }

"+"                    { return symbol(CodesLexicaux.ADDITION); }

"-"                    { return symbol(CodesLexicaux.NEGATIF); }

"*"                    { return symbol(CodesLexicaux.MULTIPLICATION); }

"<"                    { return symbol(CodesLexicaux.INFERIEUR); }

"=="                   { return symbol(CodesLexicaux.EGALITE); }

"!="                   { return symbol(CodesLexicaux.DIFFERENT); }

"et"                   { return symbol(CodesLexicaux.INTERSECTION); }

"ou"                   { return symbol(CodesLexicaux.UNION); }

"retourne"             { return symbol(CodesLexicaux.RETOURNE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{csteB}                { return symbol(CodesLexicaux.CSTBOOLEENNE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{commentaire}          { }

{espace}               { }

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

