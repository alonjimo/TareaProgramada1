#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>


int main()
{
 int sock,bytesrecibidos;
 struct sockaddr_in cliente;
 char datosenviados[2048];
 char datosrecibidos[2048];
 char datosrecibidos1[2048];
 const char * const mezcla = "\033[1;35m ";
 const char * const normal = "\033[1;32m";   


 sock = socket(AF_INET,SOCK_STREAM,0);
 if( sock<0)
  {perror("no se creo el socket\n");
   exit(1);
  }
 
 cliente.sin_family = AF_INET;
 cliente.sin_port = htons(4000);
 cliente.sin_addr.s_addr = inet_addr("10.42.0.12");
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
		printf("%s mensaje: %s %s",mezcla,datosrecibidos,normal);

	}
}

 else{
      while(1){
		printf("Alonso: ");
		gets(datosenviados);
		
		if(send(sock,datosenviados,sizeof(datosenviados),0) < 0){
			
			perror("envio fallido\n");
			close(sock);
			exit(1);
		}

	}

}

return 0;

}
		 
