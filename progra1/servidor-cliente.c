#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>

int main()
{

 int sock;
 char datosrecibidos[2048];    //arreglo para recibir los datos  
 char datosenviados[2048];
 char nombreenviado[15];
 char nombrerecibido[15]; 
 struct sockaddr_in servidor; //struct obligatorio   para puertos y almacenar direcciones
 int var1,misock;

 printf("digite su username: ");
 gets(nombreenviado);
 strcat(nombreenviado,":");

 sock = socket(AF_INET, SOCK_STREAM, 0);  
 int id;     
 if(sock ==-1)
  {
   perror("no se creo el socket");
   exit(1);
  }
 else
    {printf("se creo el socket\n");
    }

 servidor.sin_family = AF_INET;         
 servidor.sin_port = htons(9005);     
 servidor.sin_addr.s_addr = INADDR_ANY;
 bzero(&(servidor.sin_zero),8);

 if (bind(sock,(struct sockaddr *)&servidor,sizeof(servidor)))
 {
  perror("fallo el bind");
  exit(1);
 }

 listen(sock,5);
 //id=fork();
  misock = accept(sock, (struct sockaddr *)0,0);
  if (misock == -1)
   {perror("error al aceptar");
   }
  else
  {
	  //printf("hola\n");
	  if(fork()){
		  recv(misock,nombrerecibido,sizeof(nombrerecibido),0);
		  while(1){
			  
			  recv(misock,datosrecibidos,sizeof(datosrecibidos),0);
                          printf("%s",nombrerecibido);                 
			  printf("%s\n",datosrecibidos);

          		 }
	  }
	  else{   send(misock,nombreenviado,sizeof(nombreenviado),0);
		  while(1){			 
			  gets(datosenviados); 
			  if(send(misock,datosenviados,sizeof(datosenviados),0)<0){
				  perror("envio fallido\n");
				  close(misock);
				  exit(1);
			  }
			  printf("enviado con exito \n");
 
		  }
	  }  
}
 



return 0;  
}

