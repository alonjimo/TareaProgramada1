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
 int sock;
 struct sockaddr_in cliente;
 char datosenviados[2048];
 char datosrecibidos[2048];
 struct hostent *hp;

 sock = socket(AF_INET,SOCK_STREAM,0);
 if( sock<0)
  {perror("no se creo el socket\n");
   exit(1);
  }
 else
    {printf("se creo el socket\n");
    }

 cliente.sin_family = AF_INET;         
 cliente.sin_port = htons(9005);
 cliente.sin_addr.s_addr = inet_addr("10.42.0.63"); //Conexión compu-compu
     

 
 //hp = gethostbyname ("10.42.0.1");
 //if(hp==0)
 //{
  //perror("fallo conectar con el host\n");
  //close(sock);
  //exit(1);
 //}

 
 if(connect(sock,(struct sockaddr *)&cliente,sizeof(cliente))<0)
 {
  perror("conexion fallida\n");
  close(sock);
  exit(1);
 }
 if(fork()){
		  
		  while(1){
			  
			  recv(sock,datosrecibidos,sizeof(datosrecibidos),0);
			  printf("mensaje: %s\n",datosrecibidos);
			  
			  /*if((var1= recv(misock,datosrecibidos,sizeof(datosrecibidos),0))<0)
			  { 
				  perror("mensaje de error\n");
			  }
			  else{
				  if(var1==0)
				  {
					  printf("terminando conexion\n");
                  }
                  else
				  { 
					  printf("mensaje: %s\n",datosrecibidos);
                  }*/
        //printf("llego el mensaje (var1 = %d)\n",var1);
        //close(misock);
				//} 
		 }
	  }
	  else{
		  while(1){
			  //printf("digite su mensaje: ");
			  scanf("%s",datosenviados); 
			  if(send(sock,datosenviados,sizeof(datosenviados),0)<0){
				  perror("envio fallido\n");
				  close(sock);
				  exit(1);
			  }
			  printf("enviado con exito \n");
 
		  }
	  }
 /*while(1){
 
 printf("digite su mensaje: ");
 scanf("%s",datosenviados); 

 if(send(sock,datosenviados,sizeof(datosenviados),0)<0)
  {perror("envio fallido\n");
   close(sock);
   exit(1);
  }
 printf("enviado con exito \n");
 
}*/
//close(sock);
return 0;
}
