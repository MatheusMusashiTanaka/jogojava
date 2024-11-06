#include<stdio.h>
#include <stdlib.h>
#include <time.h>


int randomizer(){
  
    int integer = rand() % 50 + 1;
    return integer;
}


void jogar(int *jogadas){
    int num = randomizer();
    int tentativas = 0 ;
    int input = 0;
    printf("Um numero entre 1 e 50 foi escolhido vamos para o jogo \n");
    do{
       printf("fale um numero entre 1 e 50: \n");
       scanf("%d",&input);

        if (input > 50 || input < 1){
            printf("voce digitou um numero maior que 50 ou menor que 0\n");
            continue;
        }

        if (input > num){
            printf("tente um numero menor\n");
            tentativas++;

        } else if (input<num){
            printf("tente um numero maior\n");
            tentativas++;
        } else {
            tentativas++;
            (*jogadas)++;
            printf("Voce acertou !! o numero era %d\n" , num);
            printf("voce usou %d tentativas e ja jogou %d vezes.\n", tentativas , *jogadas);

        }

    } while (input != num);
    
}



void main()   {  
    srand(time(0));
    printf("Bem vindo ao jogo de adivinhacao\n");
    char checker;
    int jogadas =0;

    do
    {
        jogar(&jogadas);
        printf("Vamos jogar denovo? (s/n): ");
        scanf(" %c", &checker);
    } while (checker == 's' || checker == 'S');
    
}  
