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
 char nombrerecibido[100];
 char nombreenviado[100];
 struct hostent *hp;

 printf("digite su username: ");
 gets(nombreenviado);
 strcat(nombreenviado,":");

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
 cliente.sin_addr.s_addr = inet_addr("172.16.1.129"); //Conexión compu-compu
 bzero(&(cliente.sin_zero),8); 
     

 
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

else
{
 if(fork())
          {
           recv(sock,nombrerecibido,sizeof(nombrerecibido),0);	  
	   while(1){
	            recv(sock,datosrecibidos,sizeof(datosrecibidos),0);
                    printf("%s",nombrerecibido);
		    printf("%s\n",datosrecibidos);
			  
		   }
	  }

 else{send(sock,nombreenviado,sizeof(nombreenviado),0);
      while(1){	       
	       gets(datosenviados); 
	       if(send(sock,datosenviados,sizeof(datosenviados),0)<0)
               {
		 perror("envio fallido\n");
		 close(sock);
	         exit(1);
	       }
	       printf("enviado con exito \n");
	      }

     }
}
return 0;
}
