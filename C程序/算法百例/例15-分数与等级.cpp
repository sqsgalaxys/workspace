#include <stdafx.h>

void main()
{
	/*
	*	题目：利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用
	*	A表示，60-89分之间的用B表示，60分以下的用C表示。 
	*	1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
	*/

	int score;
	char grade;

	scanf("%d",&score);

	grade= score>=90 ? 'A' : ( score>=60 ? 'B' : 'C');
	
	printf("%c\n",grade);
}