#include <stdafx.h>

void main()
{
	/*
		一球从100米高度自由落下，每次落地后
		反跳回原高度的一半；再落下，求它
		第10次落地时，共经过多少米？第10
		反弹多高？
	*/
	float s=100,h=s/2;
	int n=2;

	for(;n<=10;n++)
	{
		s=s+2*h;/*第n次落地时共经过的米数*/
		h=h/2;/*第n次反跳高度*/
	}

	printf("s=%f h=%f\n",s,h);
}