/*
 * Tarea Programda #1
 * Estudiantes:
 * 	Alonso Jiménez
 * 	Edwin Fernánez
 * 	Fernando Mata
 * 	José Andrés Hernández
 * 	Esteban Céspedes
*/

//Headers y librerías

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

//Struct que almacena los datos del usuario
struct contacto{
	char usuario[15];
	int puerto;
	char dir_Ip [15];
};

//Función que lee el archivo en donde se encuentran los contactos
void leerarchivo(struct contacto aux){
	char nuevo [60];
	char puerto_aux[50];
	sprintf(puerto_aux,"%d",aux.puerto);
	strcpy(nuevo,aux.usuario);
	strcat(nuevo,",");
	strcat(nuevo,puerto_aux);
	strcat(nuevo,",");
	strcat(nuevo,aux.dir_Ip);
	strcat(nuevo,"\n");
	FILE *fp;
	fp = fopen("Contactos.txt", "a+");
	fputs(nuevo,fp);
	fclose(fp);
}

/*
 * Función que agrega un contacto al directorio de contactos
 * y lo almacena en un archivo .txt 
*/

void agregarcontacto()
{
		char term;
		struct contacto var;
		printf("Inserte el nombre de usuario: ");
		scanf("%s",var.usuario);
		printf("Inserte el puerto a utilizar: ");
		//scanf("%d",&(var.puerto));
		if(scanf("%d%c", &var.puerto, &term) != 2 || term != '\n'){
			printf("Puerto no válido\n");
			agregarcontacto();
		}
		else{
			printf("Inserte la direccion IP del contacto: ");
		    scanf("%s",&var.dir_Ip);
		    printf("Inserte la direccion IP del contacto: ");
			scanf("%s",&var.dir_Ip);
			int aux;
			struct sockaddr_in auxcliente;
			aux = socket(AF_INET,SOCK_STREAM,0);
			char str[INET_ADDRSTRLEN];
			if(inet_pton(AF_INET, var.dir_Ip, &(auxcliente.sin_addr)) <= 0){
			printf("IP inválida\n");
			agregarcontacto();
			}
			else{
				leerarchivo(var);
			}
	    }
}

char eliminarcontacto()
{

char contacto[15];
char cadena[15];
int valor;
int valor2;
char caden;
int modificado = 0;
int valoracion=0;
char adena[100];
printf("introduzca nombre del contacto a eliminar: ");
scanf("%s",contacto);
FILE *archivo;
FILE *dest;
archivo=fopen("Contactos.txt","r");// abre para leer
dest = fopen ("aux.txt", "w");// archivo a modificar
if (archivo ==NULL)
{
printf("\Error de apertura del archivo.\n\n");
}

while (!feof(archivo)){
	valoracion=fscanf(archivo,"%[a-zA-Z],%d,%d\n",cadena,&valor,&valor2);
	if (strcmp (cadena, contacto) != 0)
	{ 
  
	char buffer[50];
	char buf[50];
	sprintf( buffer, "%d", valor );
	strcat(cadena, ",");
	strcat(cadena, buffer);
	strcat(cadena, ",");
	sprintf( buf, "%d", valor2 );
	strcat(cadena, buf);
	strcat(cadena, "\n");
	fputs (cadena, dest);
	}
	if (strcmp (cadena, contacto) == 0)
	{ 
		printf("contacto eliminado");
	}
}

fclose(archivo);
fclose(dest);
remove ("Contactos.txt"); //borramos el fichero de origen
rename("aux.txt","Contactos.txt");// aca renombramos el archivo al original
return 0;
}
// fin de la funcionalidad

void enviarmensaje()
{

char contacto[15];
char cadena[15];
int valor;
char valor2[15];
char caden;
int modificado = 0;
int valoracion=0;
char adena[100];
printf("introduzca nombre del contacto al que desea enviar el  mensaje: ");
scanf("%s",contacto);
FILE *archivo;
FILE *dest;
archivo=fopen("Contactos.txt","r");// abre para leer
dest = fopen ("aux.txt", "w");// archivo a modificar
if (archivo ==NULL)
{
printf("\Error de apertura del archivo.\n\n");
}
while (!feof(archivo)){

valoracion=fscanf(archivo,"%[a-zA-Z],%d,%s\n",cadena,&valor,&valor2);

if (strcmp (cadena, contacto) == 0)
{ 
  
intervenir(cadena,valor,valor2);
break;  
  

}

}
fclose(archivo);
fclose(dest);
}// fin de la funcionalidad


void intervenir(char carac[100], int puerto, char ip[100])

{
cliente(puerto,ip);

}

void menu()
{ 
  int opc;
  while(opc != 3)
  {printf("_____________Chat lenguajes___________\n");
  printf("1)Agregar contacto.\n");
  printf("2)Enviar mensaje.\n");
  printf("3)Eliminar contacto.\n");
  printf("4) Salir.\n");
  
  
  printf("Ingrese una opcion: ");
  scanf("%i",&opc);
  switch(opc)
  {
     case 1:
          agregarcontacto();           
          break;
     case 2:
          enviarmensaje();
          break;
     case 3:
          eliminarcontacto();
          break;
     case 4:
          printf("Saliendo\n");
          sleep(2);
          exit(0);
          break;
     default: 
              printf("Opcion invalida.\n");
              break;                            
              
		}
	}
}

int cliente(int puerto, char ip[100])
{
 int sock;
 struct sockaddr_in cliente;
 char datosenviados[2048];
 char datosrecibidos[2048];
 
 const char * const mezcla = "\033[1;33m ";
 const char * const normal = "\033[1;32m";   


 sock = socket(AF_INET,SOCK_STREAM,0);
 if( sock<0)
  {perror("no se creo el socket\n");
   exit(1);
  }
 
 cliente.sin_family = AF_INET;
 cliente.sin_port = htons(puerto);
 cliente.sin_addr.s_addr = inet_addr(ip);

 
 
 
 bzero(&(cliente.sin_zero),8);
 
 int cont = 0;
 
 while(cont == 0 )
 {
	 if(connect(sock,(struct sockaddr *)&cliente,sizeof(cliente))>= 0){
		 system("clear");
		 printf("Contacto Disponible\n\n");
		 cont++;
	 }
	 else{
		 sleep(1);
		 printf("Contacto no disponible, espera o intenta comunicarte mas tarde.\n");
		 sleep(1);
		 system("clear");
	 }
  
 }
 if(fork()){
	while(1){
		recv(sock,datosrecibidos,sizeof(datosrecibidos),0);
		if(datosrecibidos[0]=='q'){
			break;
		}
		printf("\r");
		printf("%s mensaje: %s %s\n",mezcla,datosrecibidos,normal);
		
	}
	exit(1);
	menu();
}

 else{
	
      while(1){
		  
		fgets(datosenviados, 2048, stdin);
		
		if(send(sock,datosenviados,sizeof(datosenviados),0) < 0){
			
			perror("envio fallido\n");
			close(sock);
			exit(1);
		}
	
	}

}
return 0;

}
		 
void main(){
	menu();
}
