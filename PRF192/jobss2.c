#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include <ctype.h>
#include<windows.h>

FILE *fo;
struct numer
{
    int stt;
    char nun;
    int numb;
};
int del() {
    fclose(fo);
   int del = remove("FU.txt");
   fo=fopen("FU.txt","a+");
}
typedef struct numer NU;
void function1(int *n,NU arr[]){
    int d;
    printf("Nhap so luong du lieu muon them vao :");
    scanf("%d",&d);
    while(d--){
        printf("phan tu thu %d ",++*n);
        int numbernew;scanf("%d",&arr[*n].numb);
        fprintf(fo,"%d-%d\n",*n,arr[*n].numb);
    }
}
void function2(int *n,NU arr[]){
    printf("Nhap gia tri muon tim kiem :");
    int m;scanf("%d",&m);int c=0;int dem=0;
    for(c=0;c<*n;c++){
        if(m==arr[c].numb){
            dem++;
            if(dem!=0) printf("Gia tri can tim xuat hien o vi tri %d \n",arr[c].stt);
        }
    }
    if (dem==0) printf("gia tri khong ton tai !\n");
}
void function3(int *n,NU arr[]){
    printf("Nhap gia tri muon xoa :");
    int m;scanf("%d",&m);int c=0;int dem=0;int x=0,y=0;
    for(c=0;c<*n;c++){
        if(m==arr[c].numb){
           dem=1;
           for(x=c;x<*n;x++){
            arr[x].stt=arr[x+1].stt;
            arr[x].nun=arr[x+1].nun;
            arr[x].numb=arr[x+1].numb;
           }
           break;
        }
    }
    del();
    for(y=0;y<*n-1;y++){
        fprintf(fo,"%d-%d\n",y,arr[y].numb);
    }
    if (dem== 0) {
        printf("gia tri muon xoa khong ton tai");

    }
}
void function4(int *n,NU arr[]){
    printf("Nhap gia tri muon xoa :");
    int m;scanf("%d",&m);int c=0;int dem=0;int x=0,y=0;
    for(c=0;c<*n;c++){
        if(m==arr[c].numb){
           dem=1;
           for(x=c;x<*n;x++){
            arr[x].stt=arr[x+1].stt;
            arr[x].nun=arr[x+1].nun;
            arr[x].numb=arr[x+1].numb;
           }
           --*n;
        }
    }
    del();
    for(y=0;y<*n;y++){
        fprintf(fo,"%d-%d\n",y,arr[y].numb);
    }
    if (dem== 0) {
        printf("gia tri muon xoa khong ton tai");

    }
}
void function5(int *n,NU arr[]){
    int i=0;
    for(i=0;i<*n;i++){
        printf("%d%c%d\n",arr[i].stt,arr[i].nun,arr[i].numb);
    }
}
void function6(int *n,NU arr[]){
    int tg;int i,j;int td;
    for( i = 0; i < *n - 1; i++){
        for(j = i + 1; j < *n; j++){
            if(arr[i].numb > arr[j].numb){
                // Hoan vi 2 so a[i] va a[j]
                tg = arr[i].numb;
                arr[i].numb = arr[j].numb;
                arr[j].numb = tg;
                 td = arr[i].stt;
                arr[i].stt = arr[j].stt;
                arr[j].stt = td;
            }
        }
    }
    printf("mang da duoc sap sep tang dan trong file FU.txt");
    del();int y;
    for(y=0;y<*n;y++){
        fprintf(fo,"%d-%d\n",arr[y].stt,arr[y].numb);
    }
}
void function7(int *n,NU arr[]){
    int tg;int i,j;int td;
    for( i = 0; i < *n - 1; i++){
        for(j = i + 1; j < *n; j++){
            if(arr[i].numb < arr[j].numb){
                // Hoan vi 2 so a[i] va a[j]
                tg = arr[i].numb;
                arr[i].numb = arr[j].numb;
                arr[j].numb = tg;
                 td = arr[i].stt;
                arr[i].stt = arr[j].stt;
                arr[j].stt = td;
            }
        }
    }
    printf("mang da duoc sap sep giam dan trong file FU.txt");
    del();int y;
    for(y=0;y<*n;y++){
        fprintf(fo,"%d-%d\n",arr[y].stt,arr[y].numb);
    }
}
void menu(){
printf("1- Add a value\n");
printf("2- Search a value\n");
printf("3- Remove the first existence of a value\n");
printf("4- Remove all existences of a value\n");
printf("5- Print out the array");
printf("6- Sort the array in ascending order (positions of elements are preserved)\n");
printf("7- Sort the array in descending order (positions of elements are preserved)\n");
printf("Others- Quit\n");
}
int main(){
    NU arr[201];
    int m=1;
    while(m==1||m==2||m==3||m==4||m==5||m==6||m==7){
    fo=fopen("FU.txt","a+");
    int i=0;
    while(fscanf(fo,"%d %c %d",&arr[i].stt,&arr[i].nun,&arr[i].numb)!=EOF){
        ++i;
    }
    if(i==0) printf("ERRO- can't fine any data in file FU.txt\n");
        menu();
        printf("chose :");
        scanf("%d",&m);
        switch (m)
        {
        case 1:
            function1(&i,arr);
             fclose(fo);
              getch();
             system("cls");
            break;
         case 2:
            function2(&i,arr);
             fclose(fo);
             getch();
             system("cls");
            break;
        case 3:
            function3(&i,arr);
             fclose(fo);
              getch();
             system("cls");
            break;
         case 4:
            function4(&i,arr);
             fclose(fo);
              getch();
             system("cls");
             break;
        case 5:
            function5(&i,arr);
             fclose(fo);
              getch();
             system("cls");
             break;
        case 6:
            function6(&i,arr);
             fclose(fo);
              getch();
             system("cls");
             break;
        case 7:
            function7(&i,arr);
             fclose(fo);
              getch();
             system("cls");
             break;
        default:
            break;
        }
    }
    printf("GOOB BYE PRFF 192!");
    getch();
    system("cls");
}
