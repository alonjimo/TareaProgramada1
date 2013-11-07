
/* Sample TCP client */

#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>

struct enviar{
	char msj[1000];
}

int main()
{
	int sockfd,n;
	struct sockaddr_in servaddr,cliaddr;
	char sendline[1000];
	char recvline[1000];


	sockfd=socket(AF_INET,SOCK_STREAM,0); //Creación del socket cliente

   bzero(&servaddr,sizeof(servaddr));
   servaddr.sin_family = AF_INET; //Dominio de transmisión de datos del server
   servaddr.sin_addr.s_addr=inet_addr("192.168.2.104"); //IP del server
   servaddr.sin_port=htons(5000); //Puerto del server

   connect(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr)); //Conectarme al server

   while (fgets(struct enviar, 10000,stdin) != NULL)
   {
      sendto(sockfd,struct enviar,strlen(struct enviar),0,
             (struct sockaddr *)&servaddr,sizeof(servaddr));
      n=recvfrom(sockfd,struct enviar,10000,0,NULL,NULL);
      recvline[n]=0;
      fputs(recvline,stdout);
   }
}
