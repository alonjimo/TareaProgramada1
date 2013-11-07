import java.util.StringTokenizer;

public class validacion { //falta lista de ambiente estatico
	public static  String tipolista = "";
	
	public String definirtipo(String expre, lista dinamica){
	String nueva = "";
	{if(estaEnString(expre,"val")!=-1)
		{if(valEntero(expre)){
		    //System.out.println("la variable "+sacarvariable(expre)+ " es de tipo int");
		    return sacarvariable(expre)+"!"+ "int";
		}
		else
		    {if(valBoolean(expre)){
		    	//System.out.println("la variable "+sacarvariable(expre)+ " es de tipo boolean");
		    	return sacarvariable(expre)+"!" + "boolean";
		    }
		    }
		if(esTupla(expre)){
			//System.out.println("la variable "+sacarvariable(expre)+ " es de tipo tupla: "+tipotupla(expre));
			return sacarvariable(expre)+"!" + tipotupla(expre);}
		if(esLista(expre)){
			//System.out.println("la variable "+sacarvariable(expre)+ " es de tipo: "+tipolista(expre));
			return sacarvariable(expre)+"!"+ tipolista(expre);}


		else{
			String var = sacarvariable(expre)+"!";
			StringTokenizer tk = new StringTokenizer(expre,"=;");
			tk.nextToken();
			tk = new StringTokenizer(tk.nextToken()," ;");
			try{
				int num = dinamica.obtenervalorint(tk.nextToken());
				return var+"int";
			}
			catch(Exception e){
				
			}
		}
		}
     else
        {
    	 if(esnumero(expre)){
          	//System.out.println("la expresion es de tipo int");
          	return expre+ "!"+ "int";
          }
         if(esbool(expre)){
         	//System.out.println("la expresion es de tipo boolean");
        	 return expre+ "!"+"boolean";
           }
         if(expre.substring(0,1).equals("(")){
         	//System.out.println("la expresion es de tipo tupla");
        	 return expre+ "!"+ tipotupla_aux(expre);
         }
		if(eslistabasico(expre)){
			//System.out.println("la expresion es de tipo: "+tipolistaaux(expre));
			return expre+ "!"+tipolistaaux(expre);}
        }
        		
	}
	return nueva;
	}
	
	public String sacarvariable(String ex)
	{String res = "";
	 StringTokenizer t = new StringTokenizer(ex);
	 while(t.hasMoreTokens())
	   {if(t.nextToken().equals("val"))
	     {return t.nextToken();
	     }
	   }
	return res;
	}
	
	public boolean valEntero(String str){
	 boolean respuesta = false;
     StringTokenizer tokens = new StringTokenizer(str," ;");
     while(tokens.hasMoreTokens())
       {if(tokens.nextToken().equals("="))
          {if(esnumero(tokens.nextToken()))
              {respuesta = true;       	 
              }
       }
       }
     return respuesta;
     }
	
	public boolean valBoolean(String str){
	 boolean respuesta = false;
	 StringTokenizer tokens = new StringTokenizer(str);
	 while(tokens.hasMoreTokens())
	       {if(tokens.nextToken().equals("="))
	          {String cadena = tokens.nextToken();	     
	    	   if(esbool(cadena))
	              {respuesta = true;        	 
	              }
	          }
	       }	
	return respuesta;
	}
	
    public static int estaEnString(String a, String b){
        int respuesta;
        int indice;
        respuesta = a.indexOf(b);
        if(respuesta==-1)
            return respuesta;
        else{
            indice = respuesta;
            return indice+2;
        }
    }	
	
	static boolean esLong(String cadena)
	 {boolean respuesta = false;
		try {Long.parseLong(cadena);
		     respuesta = true;
			
		} catch (Exception e) {
			return respuesta;
		}
	  return respuesta;
	 }
	
	static boolean esnumero(String cadena){
		if((esLong(cadena))|(cadena.matches("[-+]?\\~\\d*\\.?\\d+"))){
			return true;
		}
		else
			return false;
	}
	
	
	static boolean esbool(String cadena)
	 {boolean respuesta = false;
      if((cadena.equals("true"))|(cadena.equals("false")))
    	  return true;
	  return respuesta;
	 }
	 	
	static boolean eslistabasico(String cadena)
	 {boolean respuesta = false;
      if(cadena.substring(0,1).equals("["))
    	  return true;
	  return respuesta;
	 }	
	
//	void  evaluadorPrincipal(String expresion){
//		StringTokenizer tokens = new StringTokenizer(expresion);
//		while(tokens.hasMoreTokens()){
//			
//			if (tokens.nextToken().equals("=")){
//				if (tokens.nextToken().equals("if")){metodoif(expresion); }
//				else{
//					
//					try{ esTupla(expresion);}
//					catch(Exception e){
//						
//						esLista(expresion);
//						
//					}			
//					}
//			}
//		}	
//	}
	
	boolean esTupla(String expresion ){
	StringTokenizer tokens = new StringTokenizer(expresion);
	while(tokens.hasMoreTokens()){
		if (tokens.nextToken().equals("=")){
			char t = "(".charAt(0);
			 char r =tokens.nextToken().charAt(0);
			if (r==t){
				return true;
			}
			
		}				
	}
				

	return false;
		
	}

	boolean esLista(String expresion ){
		StringTokenizer tokens = new StringTokenizer(expresion);
		while(tokens.hasMoreTokens()){
			if (tokens.nextToken().equals("=")){
				char t = "[".charAt(0);
				 char r =tokens.nextToken().charAt(0);
				if (r==t){					
					return true;
					
				}
				
			}				
		}
					
			return false;
			
			
		}	
	
	String separarigual(String s){
	String cadena = "";
	StringTokenizer t = new StringTokenizer(s,"=");
	cadena = t.nextToken();
	cadena = t.nextToken();
	return cadena;	
		
	}
	
	public String metodoif (String expresion, lista estatico, lista dinamico){
		String variable = sacarvariable(expresion);
		String despuesigual = separarigual(expresion);		
		StringTokenizer t = new StringTokenizer(expresion,"=");
		expresion = t.nextToken();	
		lista variablesTemporales = new lista();
		StringTokenizer t1 = new StringTokenizer(t.nextToken());
		String strTemp = "";
		while(t1.hasMoreTokens()){
			String var = t1.nextToken();
			if(var.equals("if"))
					{var = t1.nextToken();
				     while(var.equals("then")==false)
				        {strTemp+=var;					 
						 var = t1.nextToken();
				        }
					}
		}
		t1 = new StringTokenizer(strTemp,">=<",true);
		String var = t1.nextToken();
		if(estatico.esvariable(var,estatico)){
			String tipo = estatico.devolvertipo(var, estatico);
			if(tipo.equals("int")){
				int valorVariable = solucionint(var,dinamico);
				String s = Integer.toString(valorVariable);
				String exp = s+t1.nextToken();
				while(t1.hasMoreTokens()){
					exp+=t1.nextToken();
				}			
				boolean res = solucionCondicionint(exp,dinamico);
				if(res){
					String then = separarthen(despuesigual);
					int respuesta;
					StringTokenizer despThen = new StringTokenizer(then);
					String primer = "";
					String tempo1 ="";
					while(despThen.hasMoreTokens()){
						primer = despThen.nextToken();
						if (dinamico.esvariable(primer, dinamico)){
							String a = dinamico.devolvervalor(primer, dinamico);
							tempo1 += a+" ";
						}
						else{
							tempo1+=primer+" ";
						}
					}
					respuesta = solucionint(tempo1,dinamico);
					String resultado = Integer.toString(respuesta);
					dinamico.InsertaFinal(variable,resultado);
					estatico.InsertaFinal(variable, "int");
					//System.out.println("variable "+variable+"es tipo int y el valor es "+resultado);
				}
				else{
					String els = separarelse(despuesigual);
					int respuesta;
					StringTokenizer despElse = new StringTokenizer(els);
					String primer = "";
					String tempo1 ="";
					while(despElse.hasMoreTokens()){
						primer = despElse.nextToken();
						if (dinamico.esvariable(primer, dinamico)){
							String a = dinamico.devolvervalor(primer, dinamico);
							tempo1 += a+" ";
						}
						else{
							tempo1+=primer+" ";
						}
					}
					respuesta = solucionint(tempo1,dinamico);
					String resultado = Integer.toString(respuesta);
					//System.out.println("variable "+variable+"es tipo int y el valor es "+resultado);
					dinamico.InsertaFinal(variable,resultado);
					estatico.InsertaFinal(variable, "int");
				}
			}
		}
						
				
		return strTemp;
		

	}	
	
String separarthen(String ex){
String expre="";
StringTokenizer t1 = new StringTokenizer(ex," ;");
while(t1.hasMoreTokens())
  {String token = t1.nextToken();
   if(token.equals("then")){
	token = t1.nextToken();
	while(token.equals("else")==false)
	{
	   expre+=token+" ";
	   token = t1.nextToken();}   
      }	
  }
return expre;
}


String separarelse(String ex){
String expre="";
StringTokenizer t1 = new StringTokenizer(ex," ;");
while(t1.hasMoreTokens())
  {String token = t1.nextToken();
   if(token.equals("else")){
	token = t1.nextToken();
	//System.out.println(token);
	expre+=token+" ";
	while(t1.hasMoreTokens())
	{  token = t1.nextToken();
	   expre+=token+" ";
	   }   
      }	
  }
return expre;
}	

public boolean eslista(String cadena){
boolean respuesta =false;
if(cadena.substring(0,1).equals("["))
	return true;
else
	return respuesta;
}

public String tipolistaaux(String lista)
{String respuesta="";
 String separador ="";
 while(lista.charAt(0)==('[')){
	lista = lista.substring(1); 
 }
 if(lista.substring(0,1).equals("("))
    {separador = ")";}
 else
	 separador =",";
 StringTokenizer t = new StringTokenizer(lista,separador);
 lista = t.nextToken();
 if(esnumero(lista))
 {respuesta += "int list";
   return respuesta;}
if(esbool(lista))
{respuesta += "boolean list";
return respuesta;}
if (esTupla(lista))
   {respuesta += tipotupla_aux(lista) + "list";
	return respuesta;
   }
 
return respuesta;	
}


public String tipolista(String expresion){
String respuesta = "";
String sepa="";
StringTokenizer tokens = new StringTokenizer(expresion);
String lista ="";
while(tokens.hasMoreTokens()){
if(tokens.nextToken().equals("="))
   {lista =tokens.nextToken();
   lista=lista.substring(1);
   lista=lista.substring(0,lista.length()-1);
   if(lista.substring(0,1).equals("("))
      {sepa = ")";}
   else
	   sepa =",";
   StringTokenizer t = new StringTokenizer(lista,sepa);
   lista = t.nextToken();
   lista +=")";
   if(esnumero(lista))
     {respuesta += "int list";
	   return respuesta;}
   if(esbool(lista))
   {respuesta += "boolean list";
	return respuesta;}
   if(lista.substring(0,1).equals("("))
     {return tipotupla(lista)+" list";}
   if(eslista(lista)){
	     StringTokenizer tk = new StringTokenizer(lista,"]");
	     String lo = tk.nextToken();
		 respuesta += tipolistaaux(lo);
		 int cant=cantidad(lista);
		 while(cant>0)
		 {respuesta += " list";
		  cant--;	 
		 }
		 return respuesta;
	 }
   }
}
return respuesta;
}

int cantidad(String exp)
{int cant =0;
 int i = 0;
while(exp.charAt(i)=='['){
	cant+=1;
	i++; 
}
 return cant;
}

public String tipotupla_aux(String argumento){
	String respuesta = "(";
	String argumentos = argumento.substring(1,argumento.length()-1);
	StringTokenizer tokens2 = new StringTokenizer (argumentos,",");
	int max =tokens2.countTokens();
	if(argumentos.equals("")){
		respuesta="()";
	}
	else{
		for(int i=1;i<max;i++){
			String arg = tokens2.nextToken();
			if(esnumero(arg))
				respuesta+="int*";
			else if(esbool(arg))
				respuesta+="boolean*";
			else if(esTupla(arg))
				respuesta+=tipotupla_aux(arg)+"*";
			else if(esLista(arg))
				respuesta+=tipolistaaux(arg)+"*";
		}
		try{
			String arg = tokens2.nextToken();
			if(esnumero(arg))
				respuesta+="int)";
			else if(esbool(arg))
				respuesta+="boolean)";
			//		else if(esTupla(arg))
			//			respuesta+=tipotupla_aux(arg)+")";
			else if(esLista(arg))
				respuesta+=tipolistaaux(arg)+")";
		}
		catch(Exception e){
			respuesta+=")";
		}
	}
	return respuesta;	
	
}
	
public String tipotupla(String expresion)
{String respuesta ="(";
StringTokenizer tokens = new StringTokenizer (expresion, ";=");
String argumentos=expresion;
String cadena="";
if(tokens.countTokens()>1){
	tokens.nextToken();
	cadena = tokens.nextToken();
	argumentos = cadena.substring(2,cadena.length()-1);
}
else{
	cadena = tokens.nextToken();
	argumentos = cadena.substring(1,cadena.length()-1);
}
//tokens.nextToken();
//String cadena = tokens.nextToken();
//String argumentos = cadena.substring(2,cadena.length()-1);
int parentesis = 0;
if(argumentos.equals("")){
	respuesta="()";
}
else{
	while(argumentos.charAt(0)=='('&&argumentos.charAt(1)=='('){
		parentesis++;
		argumentos = argumentos.substring(1,argumentos.length()-1);
	}
	StringTokenizer tokens2 = new StringTokenizer (argumentos,",");
	int max = contar_argumentos(argumentos);
	String argumento = tokens2.nextToken();
	if(argumento.charAt(0)=='('||argumento.charAt(0)=='['){
		String argaux = argumento;
		if(argumento.charAt(0)=='('){
			try{
				argumento = tokens2.nextToken();
				while(argumento.charAt(argumento.length()-1)!=')'){
					argaux +=","+argumento;
					argumento = tokens2.nextToken();
				}
				argaux += ","+argumento;
				respuesta+=tipotupla_aux(argaux);
			}
			catch(Exception xz){
				argaux += "";
				respuesta+=tipotupla_aux(argaux);
			}
		}
		else{
			try{
				argumento = tokens2.nextToken();
				while(argumento.charAt(argumento.length()-1)!=']'){
					argaux +=","+argumento;
					argumento = tokens2.nextToken();
				}
				argaux += ","+argumento;
				respuesta+=tipolistaaux(argaux)+"*";
			}
			catch(Exception xz){
				argaux += "";
				respuesta+=tipolistaaux(argaux)+"*";
			}
		}
	}
	else{
		if(esnumero(argumento))
			respuesta+="int";
		else if(esbool(argumento))
			respuesta+="boolean";
		else if(esLista(argumento))
			respuesta+=tipolistaaux(argumento);
	}
	for(int i=2;i<=max;i++){
		argumento = tokens2.nextToken();
		if(argumento.charAt(0)=='('){
			String argaux = argumento;
			if(argumento.charAt(argumento.length()-1)==')'){
				argaux +="";
				respuesta+="*"+tipotupla_aux(argaux);
			}
			else{
				try{
					argumento = tokens2.nextToken();
					while(argumento.charAt(argumento.length()-1)!=')'){
						argumento = tokens2.nextToken();
						argaux +=","+argumento;
						argumento = tokens2.nextToken();
					}
					argaux += ","+argumento;
					respuesta+=tipotupla_aux(argaux);
				}
				catch(Exception lol){
					argaux += "";
					respuesta+=tipotupla_aux(argaux);
				}
			}
		}
		else{
			if(esnumero(argumento))
				respuesta+="*int";
			else if(esbool(argumento))
				respuesta+="*boolean";
			else if(esLista(argumento))
				respuesta+="*"+tipolistaaux(argumento);
		}
	}
	try{
		argumento = tokens2.nextToken();
		if(argumento.charAt(0)=='('){
			String argaux = argumento;
			try{
				argumento = tokens2.nextToken();
				while(argumento.charAt(argumento.length()-1)!=')'){
					argaux +=","+argumento;
					argumento = tokens2.nextToken();
				}
				argaux += ","+argumento;
				respuesta+="*"+tipotupla_aux(argaux)+")";
			}
			catch(Exception z){
				argaux+="";
				respuesta+="*"+tipotupla_aux(argaux)+")";
			}
			
		}
		else{
			if(esnumero(argumento))
				respuesta+="*int)";
			else if(esbool(argumento))
				respuesta+="*boolean)";
			//		else if(esTupla(argumento))
			//			respuesta+=tipotupla_aux(argumento)+")";
			else if(esLista(argumento))
				respuesta+="*"+tipolistaaux(argumento)+")";
		}
	}
	catch(Exception e){
		respuesta+=")";
	}
	for(int x =1;x<=parentesis;x++){
		respuesta = "("+respuesta+")";
	}
}
return respuesta;		
}
public int contar_argumentos(String arg){
	int resultado = 0;
	StringTokenizer tokens = new StringTokenizer(arg,",;");
	while(tokens.hasMoreTokens()){
		String argumento = tokens.nextToken();
		if(argumento.charAt(0)=='('||argumento.charAt(0)=='['){
			//String argaux = argumento;
			//argumento = tokens.nextToken();
			if(argumento.charAt(0)=='('){
				while(argumento.charAt(argumento.length()-1)!=')'){
					argumento = tokens.nextToken();
				}
				resultado++;
				if(tokens.hasMoreTokens())
					argumento = tokens.nextToken();
				else;
			}
			else{
				while(argumento.charAt(argumento.length()-1)!=']'){
					argumento = tokens.nextToken();
				}
				resultado++;
				if(tokens.hasMoreTokens())
					argumento = tokens.nextToken();
				else;
			}
		}
		else{
			resultado++;
			if(tokens.hasMoreTokens())
				argumento=tokens.nextToken();
			else;
		}
	}
	return resultado;
}

public String hd(String expresion){
	String respuesta ="";
	String temp ="";
	String a ="";
	expresion = expresion.substring(1,expresion.length()-1);
	StringTokenizer t = new StringTokenizer(expresion,",");
	while(t.hasMoreTokens()){
		if(expresion.substring(0,1).equals("[")){
			a = t.nextToken();
			temp+=a;
			if((a.substring(a.length()-1).equals("]")==false)){	
				temp+=",";	
			}	
			else
			{		
				return temp;
			}
		}
		else{
			if(esnumero(expresion.substring(0,1))){
				a = t.nextToken();
				return temp += a;
			}
			if((expresion.substring(0,1)).equals("t")|(expresion.substring(0,1)).equals("f")){
				a = t.nextToken();
				return temp += a;
			}
			else{
				a = t.nextToken();
				temp+=a;
				if((a.substring(a.length()-1).equals(")")==false)){
					temp+=",";
				}
				else
					return temp;
			}
			
		}
	}

	return respuesta;
}

public String tl(String expresion){
	String h = hd(expresion);
	int indice;
	int largo = h.length();
	indice = expresion.indexOf(h);
	String respuesta = expresion.substring(indice+largo+1,expresion.length()-1);
	return respuesta;
}

public String sacarnumeral(String argumento,int pos){
	String argumentos = argumento.substring(1,argumento.length()-1);
	StringTokenizer tokens = new StringTokenizer(argumentos,",");
	String respuesta = "";
	for(int i=1;i<=pos;i++){

		String arg = tokens.nextToken();
		respuesta="";
		if(arg.charAt(0)=='('||arg.charAt(0)=='['){
			//String argaux = argumento;
			//argumento = tokens.nextToken();
			if(arg.charAt(0)=='('){
				while(arg.charAt(arg.length()-1)!=')'){
					respuesta+=arg+",";
					arg = tokens.nextToken();
				}
				respuesta+=arg;
			}
			else{
				while(arg.charAt(arg.length()-1)!=']'){
					respuesta+=arg+",";
					arg = tokens.nextToken();
				}
				respuesta+=arg;
			}
		}
		else{
			respuesta=arg;
			if(tokens.hasMoreTokens())
				argumento=tokens.nextToken();
			else;
		}

	}
	return respuesta;
}

public String evaluar(String expresion,lista dinamico){
	String respuesta="";
	String val = sacarvariable(expresion);
	StringTokenizer tokens = new StringTokenizer (expresion,"=;");
	if(tokens.countTokens()>1){
		tokens.nextToken();
		String cadena = tokens.nextToken();
		respuesta = cadena.substring(1);
	}
	else{
		String cadena = tokens.nextToken();
		respuesta = cadena.substring(1);
	}
	String temporal = respuesta;
	StringTokenizer tk = new StringTokenizer(respuesta," ");
	respuesta = tk.nextToken();
	if(esnumero(respuesta));
	else if(esbool(respuesta));
	else if(eslista(respuesta));
	else if(respuesta.charAt(0)=='(');
	else{
		respuesta = temporal;
		tk = new StringTokenizer(respuesta," ");
		String str = tk.nextToken();
		if(str.equals("if")){
			//Invoca metodo de IF
		}
		else if(str.equals("let")){
			//Invoca metodo de LET//
		}
		else{
			respuesta = ""+solucionint(respuesta,dinamico);
		}
	}
	
	
	return respuesta;
}

public int solucionint(String exp, lista dinamico){
	int respuesta = 0;
	StringTokenizer tk = new StringTokenizer(exp);
	try{
		int temp = dinamico.obtenervalorint(tk.nextToken());
		while(tk.hasMoreTokens()){
			String oper = tk.nextToken();
			int num2 = dinamico.obtenervalorint(tk.nextToken());
			String op = oper;
			switch (op){
			case "*":
				temp *= num2;
				break;
			case "/":
				temp /= num2;
				break;
			case "-":
				temp -= num2;
				break;
			case "+":
				temp+=num2;
				break;
			}
		}
		respuesta = temp;
	}
	catch(Exception e){

	}
	return respuesta;
}

public boolean solucionCondicionint(String exp, lista dinamico){
	boolean respuesta = false;
	StringTokenizer tk = new StringTokenizer(exp,">=<",true);
	try{
		int temp = dinamico.obtenervalorint(tk.nextToken());
		while(tk.hasMoreTokens()){
			String oper = tk.nextToken();
			int num2 = dinamico.obtenervalorint(tk.nextToken());
			String op = oper;
			switch (op){
			case "<":
				if(temp < num2){
					return true;
				}
				break;
			case ">":
				if(temp > num2){
					return true;
				}
				break;
			case "=":
				if(temp==num2){
					return true;
				}
				break;
			}
		}		
	}
	catch(Exception e){
	}
	return respuesta;
}


public boolean solucionCondicionbool(String exp, lista dinamico){
	boolean respuesta = false;
	StringTokenizer tk = new StringTokenizer(exp,"=",true);
	    String a = "";
	    while(tk.hasMoreTokens())
	      {a = tk.nextToken();
	       if(a.equals("=")){a = tk.nextToken();}	
	      }
		boolean temp = dinamico.obtenervalorbool(a);
		tk = new StringTokenizer(exp,"=",true);
		while(tk.hasMoreTokens()){			
			boolean num2 = dinamico.obtenervalorbool(tk.nextToken());
			String oper = tk.nextToken();
			String op = oper;
			if(op.equals("="))
			   {if((temp==true&num2==true)|(temp==false&num2==false))
				   return true;
			    else
				    return false;
            
		       }
		}
	return respuesta;
}

}